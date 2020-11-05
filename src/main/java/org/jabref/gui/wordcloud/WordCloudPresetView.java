package org.jabref.gui.wordcloud;

import com.airhacks.afterburner.views.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.util.BaseDialog;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.wordcloud.WordCloudPreset;

import javax.inject.Inject;

//Dialog to collect thee user's preferences about the word cloud generation
public class WordCloudPresetView extends BaseDialog<WordCloudPreset> {
    @Inject StateManager stateManager;

    @FXML private ButtonType generateButton;
    @FXML private ComboBox<String> fontSelection;
    @FXML private ComboBox<String> shapeSelection;
    @FXML private ComboBox<String> colorPaletteSelection;
    @FXML private ComboBox<String> backgroundSelection;
    //@FXML private ImageView wordcloudImageView;

    private final BasePanel basePanel;
    private final DialogService dialogService;

    public WordCloudPresetView(BasePanel basePanel, DialogService dialogService) {
        this.basePanel = basePanel;
        this.dialogService = dialogService;

        this.setTitle(Localization.lang("Generate Word Cloud"));
        ViewLoader.view(this)
                  .load()
                  .setAsDialogPane(this);

        setResultConverter(button -> {
            if (button == generateButton) {
                return getWordCloudPreset();
            } else {
                return null;
            }
        });
    }

    @FXML
    public void initialize() {
        ObservableList<String> fontList = FXCollections.observableArrayList("Serif", "SansSerif", "Monospaced");
        fontSelection.setItems(fontList);
        fontSelection.getSelectionModel().selectFirst();

        ObservableList<String> shapeList = FXCollections.observableArrayList("Rectangle", "Circle");
        shapeSelection.setItems(shapeList);
        shapeSelection.getSelectionModel().selectFirst();

        ObservableList<String> paletteList = FXCollections.observableArrayList("Blue color palette", "Red color palette", "Green color palette");
        colorPaletteSelection.setItems(paletteList);
        colorPaletteSelection.getSelectionModel().selectFirst();

        ObservableList<String> backgroundList = FXCollections.observableArrayList("White", "Black");
        backgroundSelection.setItems(backgroundList);
        backgroundSelection.getSelectionModel().selectFirst();

        // TODO: Add real image after generated word cloud
        //File file = new File("./src/main/resources/wordcloud/examplewordcloud.png");
        //Image image = new Image(file.toURI().toString());
        //wordcloudImageView.setImage(image);
    }

    // Generates the preferences from the user's selection in the dialog
    public WordCloudPreset getWordCloudPreset() {
        String font = fontSelection.getValue();
        String shape = shapeSelection.getValue();
        String color = colorPaletteSelection.getValue();
        String background = backgroundSelection.getValue();
        return new WordCloudPreset(font, shape, color, background);
    }

}
