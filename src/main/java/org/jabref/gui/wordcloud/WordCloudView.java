package org.jabref.gui.wordcloud;

import com.airhacks.afterburner.views.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.WordCloud;

import javax.inject.Inject;
import java.io.File;

public class WordCloudView extends BaseDialog<WordCloud> {
    // TODO: Add available items to comboBoxes
    @Inject StateManager stateManager;

    @FXML private ButtonType generateButton;
    @FXML private ComboBox<String> fontSelection;
    @FXML private ComboBox<String> rotationSelection;
    @FXML private ComboBox<String> colorPaletteSelection;
    @FXML private ImageView wordcloudImageView;

    private final BasePanel basePanel;
    private final DialogService dialogService;

    public WordCloudView(BasePanel basePanel, DialogService dialogService) {
        this.basePanel = basePanel;
        this.dialogService = dialogService;

        this.setTitle(Localization.lang("Generate Word Cloud"));
        ViewLoader.view(this)
                  .load()
                  .setAsDialogPane(this);

        // TODO: Should trigger action that generates word cloud
        ControlHelper.setAction(generateButton, this.getDialogPane(), event -> System.out.println("Action here"));
    }

    @FXML
    public void initialize() {
        // TODO: Add available fonts
        ObservableList<String> fontList = FXCollections.observableArrayList("Comic Sans", "Helvetica", "Arial");
        fontSelection.setItems(fontList);

        // TODO: Add available rotation settings
        ObservableList<String> rotationList = FXCollections.observableArrayList("Horizontal", "Vertical");
        rotationSelection.setItems(rotationList);

        // TODO: Add available color palette settings
        ObservableList<String> paletteList = FXCollections.observableArrayList("Blue, Cyan, Other blue", "Red, Orange, Maroon");
        colorPaletteSelection.setItems(paletteList);

        // TODO: Add real image after generated word cloud
        File file = new File("examplewordcloud.png");
        Image image = new Image(file.toURI().toString());
        wordcloudImageView.setImage(image);
    }

}
