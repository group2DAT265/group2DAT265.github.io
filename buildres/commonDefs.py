import sys
import logging
import struct
import json

# Read a message from stdin and decode it.
def get_message():
    raw_length = sys.stdin.buffer.read(4)
    if not raw_length:
        logging.error("Raw_length null")
        sys.exit(0)
    message_length = struct.unpack("=I", raw_length)[0]
    logging.info("Got length: {} bytes to be read".format(message_length))
    message = sys.stdin.buffer.read(message_length).decode("utf-8")
    logging.info("Got message of {} chars".format(len(message)))
    data = json.loads(message)
    logging.info("Successfully retrieved JSON")
    return data