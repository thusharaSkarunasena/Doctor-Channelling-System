package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Admin_EmployeeService;
import lk.ijse.dcs.view.util.tblModel.Admin_EmployeeManagementTM;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Admin_EmployeeManagementController implements Initializable, Observer {

    @FXML
    private AnchorPane admin_employeeManagementAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXComboBox<String> employmentComboBox;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<Admin_EmployeeManagementTM> employeeManagementTbl;
    @FXML
    private JFXTextField employeeIdTF;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField nicTF;
    @FXML
    private JFXTextField tel_homeTF;
    @FXML
    private JFXTextField tel_mobileTF;
    @FXML
    private JFXTextArea addressTA;
    @FXML
    private JFXTextField userNameTF;
    @FXML
    private JFXTextField passwordTF;
    @FXML
    private JFXTextField otherDetailsTF;

    private static Admin_EmployeeService adminEmployeeService;
    private static Observer observer;
    private static String reservedEmployeeID;

    private ObservableList<Admin_EmployeeManagementTM> employeeManagementTMs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            adminEmployeeService =
                    (Admin_EmployeeService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN_EMPLOYEE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            UnicastRemoteObject.exportObject(this, 0);
            observer = this;
            adminEmployeeService.register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        reservedEmployeeID = "emptyEmployeeID";

        employeeManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        employeeManagementTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        employeeManagementTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        employeeManagementTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        employeeManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        employeeManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("employment"));
        employeeManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nic"));

        loadEmploymentComboBox();
        loadEmployeeTable();

        generateNextEmployeeID();

    }

    @FXML
    public void backToMainDashIcon_onMouseEntered(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.rgb(0, 153, 204));
        glow.setWidth(5);
        glow.setHeight(5);
        glow.setRadius(5);
        icon.setEffect(glow);
    }

    @FXML
    public void backToMainDashIcon_onMouseExited(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();
        icon.setEffect(null);
    }

    @FXML
    public void backToMainDashIcon_onMouseClicked(MouseEvent event) {
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/adminMainDash.fxml")));
            Stage stage = (Stage) this.admin_employeeManagementAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Admin's Main DashBoard");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
            trans.setFromX(-temp.getHeight());
            trans.setToX(0);
            trans.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEmploymentComboBox() {
        employmentComboBox.getItems().addAll(
                "Admin", "Reception", "Doctor", "Pharmacist", "Cashier"
        );
    }

    public void loadEmployeeTable() {
        try {
            ArrayList<EmployeeDTO> employeeDTOS = (ArrayList<EmployeeDTO>) adminEmployeeService.getAllEmployees();
            employeeManagementTMs = employeeManagementTbl.getItems();
            employeeManagementTMs.removeAll(employeeManagementTMs);

            for (EmployeeDTO dto : employeeDTOS) {
                employeeManagementTMs.add(new Admin_EmployeeManagementTM(
                        dto.getEmployeeID(),
                        dto.getName(),
                        dto.getEmployment(),
                        dto.getNic()
                ));
            }
            employeeManagementTbl.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateNextEmployeeID() {
        try {
            String nextID = adminEmployeeService.generateNextEmployeeID();
            employeeIdTF.setText(nextID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void otherDetailsTF_onAction(ActionEvent event) {
        userNameTF.requestFocus();
    }

    @FXML
    public void userNameTF_onAction(ActionEvent event) {
        passwordTF.requestFocus();
    }

    @FXML
    public void passwordTF_onAction(ActionEvent event) {
        saveBtn.requestFocus();
    }

    @FXML
    public void deleteBtn_onAction(ActionEvent event) {
        try {
            String employeeID = employeeIdTF.getText();
            employeeManagementTMs = employeeManagementTbl.getItems();
            boolean isExist = false;
            for (Admin_EmployeeManagementTM employeeManagementTM : employeeManagementTMs) {
                if (employeeManagementTM.getEmployeeID().equals(employeeID)) {
                    isExist = true;
                }
            }

            if (isExist) {
                boolean result = adminEmployeeService.deleteEmployee(observer, employeeID);
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Employee has been deleted successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the Employee", ButtonType.OK).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void employmentComboBox_onAction(ActionEvent event) {
        addressTA.requestFocus();
    }

    @FXML
    public void nameTF_onAction(ActionEvent event) {
        employmentComboBox.requestFocus();
        employmentComboBox.show();
    }

    @FXML
    public void newBtn_onAction(ActionEvent event) {
        employeeIdTF.setText("");
        nameTF.setText("");
        employmentComboBox.setValue("");
        addressTA.setText("");
        nicTF.setText("");
        tel_homeTF.setText("");
        tel_mobileTF.setText("");
        otherDetailsTF.setText("");
        userNameTF.setText("");
        passwordTF.setText("");
        searchBoxTF.setText("");

        generateNextEmployeeID();
        loadEmployeeTable();

        try {
            adminEmployeeService.release(reservedEmployeeID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        newBtn.requestFocus();

    }

    @FXML
    public void nicTF_onAction(ActionEvent event) {
        tel_homeTF.requestFocus();
    }

    @FXML
    public void employeeManagementTbl_OnMouseClick(MouseEvent event) {
        Admin_EmployeeManagementTM selectedItem = employeeManagementTbl.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            try {
                if (adminEmployeeService.reserve(selectedItem.getEmployeeID())) {

                    if (!reservedEmployeeID.equals(selectedItem.getEmployeeID())) {
                        adminEmployeeService.release(reservedEmployeeID);
                        reservedEmployeeID = selectedItem.getEmployeeID();
                    }

                    EmployeeDTO employeeDTO = adminEmployeeService.getEmployee(selectedItem.getEmployeeID());

                    employeeIdTF.setText(employeeDTO.getEmployeeID());
                    nameTF.setText(employeeDTO.getName());
                    employmentComboBox.setValue(employeeDTO.getEmployment());
                    addressTA.setText(employeeDTO.getAddress());
                    nicTF.setText(employeeDTO.getNic());
                    tel_homeTF.setText(employeeDTO.getCn_home());
                    tel_mobileTF.setText(employeeDTO.getCn_mobile());
                    otherDetailsTF.setText(employeeDTO.getOtherDetails());
                    userNameTF.setText(employeeDTO.getUserName());
                    passwordTF.setText(employeeDTO.getPassword());
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "We are Sorry.., The Employee '"
                                    + selectedItem.getEmployeeID() +
                                    "' is Reserved by Another Client.",
                            ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void saveBtn_onAction(ActionEvent event) {
        employeeManagementTMs = employeeManagementTbl.getItems();
        boolean isExist = false;
        for (Admin_EmployeeManagementTM managementTM : employeeManagementTMs) {
            if ((managementTM.getEmployeeID().equals(employeeIdTF.getText())) | (managementTM.getNic().equals(nicTF.getText()))) {
                isExist = true;
                break;
            }
        }

        if (isExist) {
            new Alert(Alert.AlertType.ERROR,
                    "Sorry..., Employee you are going to save is already in employee table.",
                    ButtonType.OK).show();
        } else {
            try {
                EmployeeDTO employeeDTO = new EmployeeDTO(
                        employeeIdTF.getText(),
                        nameTF.getText(),
                        employmentComboBox.getValue(),
                        addressTA.getText(),
                        nicTF.getText(),
                        tel_homeTF.getText(),
                        tel_mobileTF.getText(),
                        otherDetailsTF.getText(),
                        userNameTF.getText(),
                        passwordTF.getText()
                );

                boolean result = adminEmployeeService.saveEmployee(observer, employeeDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Employee has been saved successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save the Employee", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void searchBoxTF_keyReleased(KeyEvent event) {
        if (searchBoxTF.getText().isEmpty()) {
            loadEmployeeTable();
        } else {
            try {
                ArrayList<EmployeeDTO> employeeDTOS =
                        (ArrayList<EmployeeDTO>) adminEmployeeService.findEmployees(searchBoxTF.getText());

                if (employeeDTOS.isEmpty()) {
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadEmployeeTable();
                } else {
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    employeeManagementTMs = employeeManagementTbl.getItems();
                    employeeManagementTMs.removeAll(employeeManagementTMs);

                    for (EmployeeDTO dto : employeeDTOS) {
                        employeeManagementTMs.add(new Admin_EmployeeManagementTM(
                                dto.getEmployeeID(),
                                dto.getName(),
                                dto.getEmployment(),
                                dto.getNic()
                        ));
                    }
                    employeeManagementTbl.refresh();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void searchBox_onMouseClick(MouseEvent event) {
        searchBoxTF.selectAll();

    }

    @FXML
    public void tel_homeTF_onAction(ActionEvent event) {
        tel_mobileTF.requestFocus();
    }

    @FXML
    public void tel_mobileTF_onAction(ActionEvent event) {
        otherDetailsTF.requestFocus();
    }

    @FXML
    public void updateBtn_onAction(ActionEvent event) {
        employeeManagementTMs = employeeManagementTbl.getItems();
        boolean isExist = false;
        for (Admin_EmployeeManagementTM managementTM : employeeManagementTMs) {
            if ((managementTM.getEmployeeID().equals(employeeIdTF.getText())) | (managementTM.getNic().equals(nicTF.getText()))) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            new Alert(Alert.AlertType.ERROR,
                    "Sorry..., Please select an existing employee from table to update.",
                    ButtonType.OK).show();
        } else {
            try {
                EmployeeDTO employeeDTO = new EmployeeDTO(
                        employeeIdTF.getText(),
                        nameTF.getText(),
                        employmentComboBox.getValue(),
                        addressTA.getText(),
                        nicTF.getText(),
                        tel_homeTF.getText(),
                        tel_mobileTF.getText(),
                        otherDetailsTF.getText(),
                        userNameTF.getText(),
                        passwordTF.getText()
                );

                boolean result = adminEmployeeService.updateEmployee(observer, employeeDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Employee has been updated successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update the Employee", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void onClose() {
        try {
            if (observer != null) {
                adminEmployeeService.unregister(observer);
            }
            if (reservedEmployeeID != null) {
                adminEmployeeService.release(reservedEmployeeID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void informDBUpdate(String primaryKey, String status) throws Exception {
        Platform.runLater(() -> {
            try {
                Alert tableUpdateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                tableUpdateAlert.setTitle("Reload?");
                tableUpdateAlert.setHeaderText(null);
                tableUpdateAlert.setContentText("The Employee '" + primaryKey + "' has been " + status + " by Another Client. \n Do You Want to Reload Employee Table Right Now?");
                Optional<ButtonType> action = tableUpdateAlert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    newBtn.fire();
                } else if (action.get() == ButtonType.CANCEL) {
                    tableUpdateAlert.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
