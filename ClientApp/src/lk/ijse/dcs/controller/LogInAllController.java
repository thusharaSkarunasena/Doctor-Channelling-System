package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.LogIn_AllService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class LogInAllController implements Initializable {

    @FXML
    private AnchorPane logInAllAnchPane;
    @FXML
    private JFXTextField userNameTF;
    @FXML
    private JFXPasswordField passwordPF;
    @FXML
    private JFXButton logInBtn;
    @FXML
    private Label welcomeTxtLbl;
    @FXML
    private Label warningLbl;

    private LogIn_AllService logInAllService;

    public static EmployeeDTO loggedEmployee;

    public static String loggedDateAndTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            logInAllService = (LogIn_AllService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.LOGIN_ALL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void userNameTF_onAction(ActionEvent event) {
        if (!userNameTF.getText().isEmpty()) {
            passwordPF.requestFocus();
        } else {
            warningLbl.setText("Please Fill Required Fields.");
            userNameTF.requestFocus();
        }
    }

    @FXML
    public void passwordPF_onAction(ActionEvent event) {
        if (!passwordPF.getText().isEmpty()) {
            logInBtn.fire();
        } else {
            warningLbl.setText("Please Fill Required Fields.");
            passwordPF.requestFocus();
        }
    }


    @FXML
    public void logInBtn_onAction(ActionEvent event) {

        if(userNameTF.getText().equals("")){
            userNameTF.requestFocus();
            warningLbl.setText("User Name is a Required Field.");
        }else if(passwordPF.getText().equals("")){
            passwordPF.requestFocus();
            warningLbl.setText("Password is a Required Field.");
        }else{
            String user = userNameTF.getText();
            String pass = passwordPF.getText();

            try {
                EmployeeDTO employee = logInAllService.getLoggedEmployeeDetails(user, pass);

                if (employee.getEmployeeID().equals("EMP-000")) {
                    userNameTF.setText("");
                    passwordPF.setText("");
                    userNameTF.requestFocus();
                    warningLbl.setText("Username or Password is InCorrect!");
                } else {
                    loggedEmployee = employee;
                    loggedDateAndTime= LocalDateTime.now().toString();

                    switch (loggedEmployee.getEmployment()) {

                        case "Admin": {
                            loadMainDash("adminMainDash",
                                    "New Philip Hospitals - Doctor Channelling System >>> Admin's Main DashBoard");
                        }
                        break;

                        case "Reception": {
                            loadMainDash("receptionMainDash",
                                    "New Philip Hospitals - Doctor Channelling System >>> Reception's Main DashBoard");
                        }
                        break;

                        case "Doctor": {
                            loadMainDash("doctorMainDash",
                                    "New Philip Hospitals - Doctor Channelling System >>> Doctor's Main DashBoard");
                        }
                        break;

                        case "Pharmacist": {
                            loadMainDash("pharmacistMainDash",
                                    "New Philip Hospitals - Doctor Channelling System >>> Pharmacist's Main DashBoard");
                        }
                        break;

                        case "Cashier": {
                            loadMainDash("cashierMainDash",
                                    "New Philip Hospitals - Doctor Channelling System >>> Cashier's Main DashBoard");
                        }
                        break;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void loadMainDash(String fxmlFileName, String title) {
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/" + fxmlFileName + ".fxml")));
            Stage stage = (Stage) this.logInAllAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle(title);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

            FadeTransition trans = new FadeTransition(Duration.millis(1000), temp.getRoot());
            trans.setFromValue(0.02);
            trans.setToValue(1);
            trans.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

