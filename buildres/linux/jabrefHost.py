#!/usr/bin/python3 -u

# Note that running python with the `-u` flag is required on Windows,
# in order to ensure that stdin and stdout are opened in binary, rather
# than text, mode.

import json
import logging
import platform
import shlex
import shutil
import struct
import subprocess
import sys
from pathlib import Path
from buildres.commonDefs import get_message

# We assume that this python script is located in "jabref/lib" while the executable is "jabref/bin/JabRef"
script_dir = Path(__file__).resolve().parent.parent
JABREF_PATH = script_dir / "bin/JabRef"
if not JABREF_PATH.exists():
    JABREF_PATH = shutil.which("jabref")

if not JABREF_PATH.exists():
    logging.error("Could not determine JABREF_PATH")
    sys.exit(-1)

logging_dir = Path.home() / ".mozilla/native-messaging-hosts/"
if not logging_dir.exists():
    logging_dir.mkdir(parents=True)
logging.basicConfig(filename=str(logging_dir / "jabref_browser_extension.log"))

# Encode a message for transmission, given its content.
def encode_message(message_content):
    encoded_content = json.dumps(message_content).encode("utf-8")
    encoded_length = struct.pack("=I", len(encoded_content))
    return {
        "length": encoded_length,
        "content": struct.pack(str(len(encoded_content)) + "s", encoded_content),
    }


# Send an encoded message to stdout.
def send_message(message):
    encoded_message = encode_message(message)
    sys.stdout.buffer.write(encoded_message["length"])
    sys.stdout.buffer.write(encoded_message["content"])
    sys.stdout.buffer.flush()


def add_jabref_entry(data):
    """Send string via cli as literal to preserve special characters"""
    cmd = [str(JABREF_PATH), "--importBibtex", r"{}".format(data)]
    logging.info("Try to execute command {}".format(cmd))
    try:
        response = subprocess.check_output(cmd, stderr=subprocess.STDOUT)
    except subprocess.CalledProcessError as exc:
        logging.error("Failed to call JabRef: {} {}".format(exc.returncode, exc.output))
    else:
        logging.info("Called JabRef and got: {}".format(response))
    return response


logging.info("Starting JabRef backend")

try:
    message = get_message()
except Exception as e:
    message = str(e)
logging.info(str(message))

if "status" in message and message["status"] == "validate":
    cmd = [str(JABREF_PATH), "--version"]
    try:
        response = subprocess.check_output(cmd, stderr=subprocess.STDOUT)
    except subprocess.CalledProcessError as exc:
        logging.error("Failed to call JabRef: {} {}".format(exc.returncode, exc.output))
        send_message({"message": "jarNotFound", "path": JABREF_PATH})
    else:
        logging.info("Response: {}".format(response))
        send_message({"message": "jarFound"})
else:
    entry = message["text"]
    output = add_jabref_entry(entry)
    send_message({"message": "ok", "output": str(output)})
