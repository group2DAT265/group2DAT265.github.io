package org.jabref.gui.help;

import org.junit.jupiter.api.BeforeEach;

public class AboutDialogViewModelTest{
    
    private AboutDialogView aboutDialogView;

    

    @BeforeEach
    void setUpViewModel() throws Exception {
        aboutDialogView = new AboutDialogView();
        aboutDialogView.show();
    }

    
}
