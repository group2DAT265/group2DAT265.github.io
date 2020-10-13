package org.jabref.gui.wordcloud;

import com.airhacks.afterburner.views.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.StateManager;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.WordCloud;
import org.jabref.preferences.JabRefPreferences;

import javax.inject.Inject;

public class WordCloudView extends BaseDialog<WordCloud> {
    // TODO: Create the structure of the dialog
    // TODO: Add placeholder methods for the dialog buttoins etc.

    @Inject StateManager stateManager;

    @FXML private ButtonType generateButton;

    private final BasePanel basePanel;
    private final DialogService dialogService;
    private final JabRefPreferences prefs;

    public WordCloudView(BasePanel basePanel, DialogService dialogService, JabRefPreferences preferences) {
        this.basePanel = basePanel;
        this.dialogService = dialogService;
        this.prefs = preferences;

        this.setTitle(Localization.lang("Generate Word Cloud"));
        ViewLoader.view(this)
                  .load()
                  .setAsDialogPane(this);

//        ControlHelper.setAction(generateButton, this.getDialogPane(), );
    }

}
