package org.jabref.gui.entrystatistics;

import com.airhacks.afterburner.views.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;

import org.jabref.model.entrystatistics.EntryStatistics;

public class EntryStatisticsView extends BaseDialog<EntryStatistics> {
    @FXML
    private ButtonType downloadButton;

    @FXML
    private VBox citationsBox;
    private final BasePanel basePanel;
    private final DialogService dialogService;

    public EntryStatisticsView(BasePanel basePanel, DialogService dialogService) {
        this.basePanel = basePanel;
        this.dialogService = dialogService;

        this.setTitle(Localization.lang("Statistics"));
        ViewLoader.view(this)
                .load()
                .setAsDialogPane(this);

        ControlHelper.setAction(downloadButton, this.getDialogPane(), event -> System.out.println("Graph"));
    }

    public void setStatistics(EntryStatistics statistics) {

        // TODO: Add graphs and stuff
        for (String year:statistics.getCitationReport().keySet()) {
            String labelText = String.format("%s – %d", year, statistics.getCitationReport().get(year));
            citationsBox.getChildren().add(new Label(labelText));
        }
    }

}
