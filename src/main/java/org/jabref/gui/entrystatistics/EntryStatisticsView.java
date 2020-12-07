package org.jabref.gui.entrystatistics;

import com.airhacks.afterburner.views.ViewLoader;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;

import org.jabref.model.entrystatistics.EntryStatistics;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

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
    private NumberAxis citYAxis;
    @FXML
    private CategoryAxis citXAxis;
    @FXML private BarChart<String, Integer> authorChart;
    @FXML private NumberAxis authYAxis;
    @FXML private CategoryAxis authXAxis;
    @FXML private BarChart<String, Integer> yearChart;
    @FXML private NumberAxis yearYAxis;
    @FXML private CategoryAxis yearXAxis;

    private final BasePanel basePanel;
    private final DialogService dialogService;
    JFileChooser chooser;

    public EntryStatisticsView(BasePanel basePanel, DialogService dialogService) {
        this.basePanel = basePanel;
        this.dialogService = dialogService;

        this.setTitle(Localization.lang("Statistics"));
        ViewLoader.view(this)
                .load()
                .setAsDialogPane(this);

        ControlHelper.setAction(downloadButton, this.getDialogPane(), event -> saveCharts());
    }


    public void setStatistics(EntryStatistics statistics) {

        citXAxis.setLabel("Year");
        citXAxis.setTickLabelRotation(90);
        citYAxis.setLabel("Citations");

        XYChart.Series<String, Integer> citSeries = new XYChart.Series<>();
        citSeries.setName("Citations per year");
        for (Map.Entry<String, Integer> entry : statistics.getCitationReport().entrySet()) {
            String tmpString = entry.getKey();
            Integer tmpValue = entry.getValue();
            XYChart.Data<String, Integer> d = new XYChart.Data<>(tmpString, tmpValue);
            citSeries.getData().add(d);
        }

        citationsChart.getData().add(citSeries);

        authXAxis.setLabel("Author");
        authXAxis.setTickLabelRotation(90);
        authYAxis.setLabel("Author credits");

        XYChart.Series<String, Integer> authSeries = new XYChart.Series<>();
        authSeries.setName("Author credits");
        for (Map.Entry<String, Integer> entry : statistics.getAuthCountReport().entrySet()) {
            String tmpString = entry.getKey();
            Integer tmpValue = entry.getValue();
            XYChart.Data<String, Integer> d = new XYChart.Data<>(tmpString, tmpValue);

            authSeries.getData().add(d);
        }

        authSeries.getData().sort(Comparator.comparingDouble(d -> d.getYValue().doubleValue() * -1));
        authorChart.getData().add(authSeries);

        yearXAxis.setLabel("Year");
        yearXAxis.setTickLabelRotation(90);
        yearYAxis.setLabel("Papers published");

        XYChart.Series<String, Integer> yearSeries = new XYChart.Series<>();
        yearSeries.setName("Papers published per year");
        for (Map.Entry<String, Integer> entry : statistics.getYearPubReport().entrySet()) {
            String tmpString = entry.getKey();
            Integer tmpValue = entry.getValue();
            XYChart.Data<String, Integer> d = new XYChart.Data<>(tmpString, tmpValue);
            yearSeries.getData().add(d);
        }

        yearSeries.getData().sort(Comparator.comparing(d -> d.getXValue()));
        yearChart.getData().add(yearSeries);
    }

    private void saveCharts() {
        try {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
            String strDate = dateFormat.format(date);

            WritableImage snapShot = citationsChart.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("citations" + strDate + ".png"));

            snapShot = authorChart.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("authors" + strDate + ".png"));

            snapShot = yearChart.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("years" + strDate + ".png"));
            dialogService.notify(Localization.lang("Charts saved!"));
        } catch (IOException e) {
            dialogService.notify(Localization.lang("Failed to save charts!"));
            e.printStackTrace();
        }
    }

}
