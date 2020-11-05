package org.jabref.gui.webofscience;

import com.airhacks.afterburner.views.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jabref.gui.BasePanel;
import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;

import org.jabref.model.webofscience.WebOfScience;

import javax.inject.Inject;
import java.io.File;

public class WebOfScienceView extends BaseDialog<WebOfScience> {

    @Inject
    StateManager stateManager;

    @FXML
    private ButtonType downloadButton;
    @FXML
    private ImageView webOfScienceView;
    private final BasePanel basePanel;
    private final DialogService dialogService;

    public WebOfScienceView(BasePanel basePanel, DialogService dialogService) {
        this.basePanel = basePanel;
        this.dialogService = dialogService;

        this.setTitle(Localization.lang("Statistics"));
        ViewLoader.view(this)
                .load()
                .setAsDialogPane(this);

        ControlHelper.setAction(downloadButton, this.getDialogPane(), event -> System.out.println("Graph"));

        File file = new File("./src/main/resources/webofscience/examplegraph.png");
        Image image = new Image(file.toURI().toString());
        webOfScienceView.setImage(image);

    }

}
