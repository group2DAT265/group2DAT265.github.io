package org.jabref.gui.wordcloud;

import javafx.scene.control.ButtonType;

import org.jabref.gui.util.BaseDialog;
import org.jabref.logic.l10n.Localization;

/*
 * TODO: Ensure the constructor creates a panel with all the editble settings for the wordcloud generation and returns that back to the @{GenerateWordCloudAction}
 */
public class WordCloudDialog extends BaseDialog<Void> {

    public WordCloudDialog() {
        setTitle(Localization.lang("Generate Wordcloud"));
        getDialogPane().setPrefSize(600, 600);
        getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return null;
            } else {
                return null;
            }
        });
    }
}
