package org.jabref.gui.entrystatistics;

import com.airhacks.afterburner.views.ViewLoader;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;

import org.jabref.model.entrystatistics.EntryStatistics;

import java.util.Map;

public class EntryStatisticsView extends BaseDialog<EntryStatistics> {
    @FXML
    private ButtonType downloadButton;
    @FXML
    private VBox authBox;
    @FXML
    private VBox yearBox;
    @FXML
    private BarChart<String, Integer> citationsChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

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

        xAxis.setLabel("Year");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Citations");

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Citations per year");
        for (Map.Entry<String, Integer> entry : statistics.getCitationReport().entrySet()) {
            String tmpString = entry.getKey();
            Integer tmpValue = entry.getValue();
            XYChart.Data<String, Integer> d = new XYChart.Data<>(tmpString, tmpValue);
            series.getData().add(d);
        }

        citationsChart.getData().add(series);

        for (String author : statistics.getAuthCountReport().keySet()) {
            String labelText = String.format("%s – %d", author, statistics.getAuthCountReport().get(author));
            authBox.getChildren().add(new Label(labelText));
        }

        for (String year : statistics.getYearPubReport().keySet()) {
            String labelText = String.format("%s – %d", year, statistics.getYearPubReport().get(year));
            yearBox.getChildren().add(new Label(labelText));
        }
    }

}
