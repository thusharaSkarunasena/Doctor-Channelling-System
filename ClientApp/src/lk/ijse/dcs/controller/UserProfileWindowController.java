package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dcs.dto.EmployeeDTO;

public class UserProfileWindowController implements Initializable {

    @FXML
    private AnchorPane userProfileWindowAnchPane;
    @FXML
    private JFXButton OKBtn;
    @FXML
    private Label DateNTime;
    @FXML
    private Label userNameTF;

    public EmployeeDTO loggedEmployee;
    public String loggedDateAndTime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loggedEmployee=LogInAllController.loggedEmployee;
        loggedDateAndTime=LogInAllController.loggedDateAndTime;

        setLoggedEmployeeName();
        setLoggedDateAndTime();

        OKBtn.requestFocus();
    }    

    @FXML
    private void disposeUserProfileWindow(MouseEvent event) {
        Stage stage = (Stage) OKBtn.getScene().getWindow();
        stage.close();
    }
    
    public void setLoggedEmployeeName(){
        try {
            userNameTF.setText(loggedEmployee.getName());
        } catch (Exception ex) {
            Logger.getLogger(UserProfileWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLoggedDateAndTime(){
        DateNTime.setText(loggedDateAndTime);
    }
}
