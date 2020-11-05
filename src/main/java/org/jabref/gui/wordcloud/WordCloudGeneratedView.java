package org.jabref.gui.wordcloud;

import com.airhacks.afterburner.views.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.util.BaseDialog;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.wordcloud.WordCloudPreset;

import javax.inject.Inject;
import java.io.File;

// Dialog  display the user the generated word cloud and give the  option tp download it or not
public class WordCloudGeneratedView extends BaseDialog<WordCloudPreset> {
    @Inject StateManager stateManager;

    @FXML private ButtonType downloadButton;
    @FXML private ImageView wordcloudGeneratedImageView;

    private final BasePanel basePanel;
    private final DialogService dialogService;

    public WordCloudGeneratedView(BasePanel basePanel, DialogService dialogService) {
        this.basePanel = basePanel;
        this.dialogService = dialogService;

        this.setTitle(Localization.lang("Download Word Cloud"));
        ViewLoader.view(this)
                  .load()
                  .setAsDialogPane(this);

        setResultConverter(button -> {
            if (button == this.downloadButton) {
                // Used to determine if the user wants to download or not
                return new WordCloudPreset();
            } else {
                return null;
            }
        });

    }

    @FXML
    public void initialize() {
        // TODO: Add real image after generated word cloud
        File file = new File("./src/main/resources/wordcloud/generated_wordcloud.png");
        Image image = new Image(file.toURI().toString());
        wordcloudGeneratedImageView.setImage(image);
    }

}
