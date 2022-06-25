package lk.ijse.dcs.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class MainDashDrawerController implements Initializable {

    @FXML
    private AnchorPane mainDashDrawer;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.gc();
    }    

    @FXML
    private void loadOptions(MouseEvent event) {
    }
    
}
