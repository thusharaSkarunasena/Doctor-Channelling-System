package lk.ijse.dcs.controller;

import com.jfoenix.controls.*;
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
import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Recep_PatientService;
import lk.ijse.dcs.view.util.tblModel.Recep_PatientManagementTM;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Recep_PatientManagementController implements Initializable, Observer {

    @FXML
    private AnchorPane recep_patientAnchPane;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private Label mainTitle;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField patientIdTF;
    @FXML
    private JFXRadioButton femaleRadioBtn;
    @FXML
    private ToggleGroup patientSex;
    @FXML
    private JFXRadioButton maleRadioBtn;
    @FXML
    private JFXDatePicker dateOfBirthDP;
    @FXML
    private JFXTextField nicTF;
    @FXML
    private JFXTextArea addressTA;
    @FXML
    private JFXTextField contactNumberHomeTF;
    @FXML
    private JFXTextField contactNumberMobileTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<Recep_PatientManagementTM> patientManagementTbl;

    private static Recep_PatientService recepPatientService;
    private static Observer observer;
    private static String reservedPatientID;

    ObservableList<Recep_PatientManagementTM> patientManagementTMs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            recepPatientService =
                    (Recep_PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RECEP_PATIENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            UnicastRemoteObject.exportObject(this, 0);
            observer = this;
            recepPatientService.register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        reservedPatientID = "emptyPatientID";

        patientManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        patientManagementTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        patientManagementTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        patientManagementTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        patientManagementTbl.getColumns().get(4).setStyle("-fx-alignment:center");
        patientManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patientManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        patientManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dob"));
        patientManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("gender"));
        patientManagementTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("nic"));

        generateNextPatientID();
        loadPatientTable();

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
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/receptionMainDash.fxml")));
            Stage stage = (Stage) this.recep_patientAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Reception's Main DashBoard");
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

    public void generateNextPatientID() {
        try {
            String nextID = recepPatientService.generateNextPatientID();
            patientIdTF.setText(nextID);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadPatientTable() {
        try {
            patientManagementTMs = patientManagementTbl.getItems();
            patientManagementTMs.removeAll(patientManagementTMs);

            ArrayList<PatientDTO> patientDTOS = (ArrayList<PatientDTO>) recepPatientService.getAllPatients();

            for (PatientDTO dto : patientDTOS) {
                patientManagementTMs.add(new Recep_PatientManagementTM(
                        dto.getPatientID(),
                        dto.getName(),
                        dto.getDob(),
                        dto.getGender(),
                        dto.getNic()
                ));
            }
            patientManagementTbl.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void maleRadioBtn_onAction(ActionEvent event) {
        dateOfBirthDP.requestFocus();
    }

    @FXML
    public void femaleRadioBtn_onAction(ActionEvent event) {
        dateOfBirthDP.requestFocus();
    }

    @FXML
    public void dateOfBirthDP_onAction(ActionEvent event) {
        addressTA.requestFocus();
    }

    public void contactNumberHomeTF_onAction(ActionEvent actionEvent) {
        contactNumberMobileTF.requestFocus();
    }

    public void contactNumberMobileTF_onAction(ActionEvent actionEvent) {
        saveBtn.requestFocus();
    }

    @FXML
    public void deleteBtn_onAction(ActionEvent event) {
        patientManagementTMs = patientManagementTbl.getItems();
        boolean isExist = false;

        for (Recep_PatientManagementTM patientTM : patientManagementTMs) {
            if ((patientTM.getPatientID().equals(patientIdTF.getText())) | (patientTM.getNic().equals(nicTF.getText()))) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            new Alert(Alert.AlertType.ERROR,
                    "Please select an existing patient from patient table to delete.",
                    ButtonType.OK).show();
        } else {
            try {
                boolean result = recepPatientService.deletePatient(observer, patientIdTF.getText());
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Patient has been deleted successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "Failed to delete the Patient.",
                            ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void nameTF_onAction(ActionEvent event) {
        maleRadioBtn.requestFocus();
    }

    @FXML
    public void newBtn_onAction(ActionEvent event) {

        patientIdTF.setText("");
        nameTF.setText("");
        maleRadioBtn.setSelected(false);
        femaleRadioBtn.setSelected(false);
        dateOfBirthDP.setValue(null);
        addressTA.setText("");
        nicTF.setText("");
        contactNumberHomeTF.setText("");
        contactNumberMobileTF.setText("");

        generateNextPatientID();
        loadPatientTable();

        try {
            recepPatientService.release(reservedPatientID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        newBtn.requestFocus();

    }

    @FXML
    public void nicTF_onAction(ActionEvent event) {
        contactNumberHomeTF.requestFocus();
    }

    @FXML
    public void patientManagementTbl_OnMouseClick(MouseEvent event) {
        try {
            Recep_PatientManagementTM selectedItem = patientManagementTbl.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                if (recepPatientService.reserve(selectedItem.getPatientID())) {

                    if (!reservedPatientID.equals(selectedItem.getPatientID())) {
                        recepPatientService.release(reservedPatientID);
                        reservedPatientID = selectedItem.getPatientID();
                    }

                    PatientDTO patientDTO = recepPatientService.getPatient(selectedItem.getPatientID());

                    patientIdTF.setText(patientDTO.getPatientID());
                    nameTF.setText(patientDTO.getName());
                    String gender = patientDTO.getGender();
                    if (gender.equals("Male")) {
                        maleRadioBtn.setSelected(true);
                        femaleRadioBtn.setSelected(false);
                    } else {
                        maleRadioBtn.setSelected(false);
                        femaleRadioBtn.setSelected(true);
                    }
                    dateOfBirthDP.setValue(LocalDate.parse(patientDTO.getDob()));
                    addressTA.setText(patientDTO.getAddress());
                    nicTF.setText(patientDTO.getNic());
                    contactNumberHomeTF.setText(patientDTO.getCn_home());
                    contactNumberMobileTF.setText(patientDTO.getCn_mobile());
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "We are Sorry.., The Patient '"
                                    + selectedItem.getPatientID() +
                                    "' is Reserved by Another Client.",
                            ButtonType.OK).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void saveBtn_onAction(ActionEvent event) {

        patientManagementTMs = patientManagementTbl.getItems();
        boolean isExist = false;

        for (Recep_PatientManagementTM patientTM : patientManagementTMs) {
            if ((patientTM.getPatientID().equals(patientIdTF.getText())) | (patientTM.getNic().equals(nicTF.getText()))) {
                isExist = true;
                break;
            }
        }

        if (isExist) {
            new Alert(Alert.AlertType.ERROR,
                    "Patient you are going to save is already in patient table.",
                    ButtonType.OK).show();
        } else {
            String gender = null;
            if (maleRadioBtn.isSelected()) {
                gender = "Male";
            } else if (femaleRadioBtn.isSelected()) {
                gender = "Female";
            }

            try {
                boolean result = recepPatientService.savePatient(observer, new PatientDTO(
                        patientIdTF.getText(),
                        nameTF.getText(),
                        gender,
                        dateOfBirthDP.getValue().toString(),
                        addressTA.getText(),
                        nicTF.getText(),
                        contactNumberHomeTF.getText(),
                        contactNumberMobileTF.getText()
                ));
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Patient has been saved successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "Failed to save the Patient.",
                            ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void searchBoxTF_keyReleased(KeyEvent event) {

        if (searchBoxTF.getText().isEmpty()) {
            loadPatientTable();
        } else {
            try {
                ArrayList<PatientDTO> patientDTOS =
                        (ArrayList<PatientDTO>) recepPatientService.searchPatients(searchBoxTF.getText());

                if (patientDTOS.isEmpty()) {
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadPatientTable();
                } else {
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    patientManagementTMs = patientManagementTbl.getItems();
                    patientManagementTMs.removeAll(patientManagementTMs);

                    for (PatientDTO dto : patientDTOS) {
                        patientManagementTMs.add(new Recep_PatientManagementTM(
                                dto.getPatientID(),
                                dto.getName(),
                                dto.getDob(),
                                dto.getGender(),
                                dto.getNic()
                        ));
                    }
                    patientManagementTbl.refresh();
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
    public void updateBtn_onAction(ActionEvent event) {
        patientManagementTMs = patientManagementTbl.getItems();
        boolean isExist = false;

        for (Recep_PatientManagementTM patientTM : patientManagementTMs) {
            if ((patientTM.getPatientID().equals(patientIdTF.getText())) | (patientTM.getNic().equals(nicTF.getText()))) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            new Alert(Alert.AlertType.ERROR,
                    "Please select an existing patient from patient table to update.",
                    ButtonType.OK).show();
        } else {
            String gender = null;
            if (maleRadioBtn.isSelected()) {
                gender = "Male";
            } else if (femaleRadioBtn.isSelected()) {
                gender = "Female";
            }

            try {
                boolean result = recepPatientService.updatePatient(observer, new PatientDTO(
                        patientIdTF.getText(),
                        nameTF.getText(),
                        gender,
                        dateOfBirthDP.getValue().toString(),
                        addressTA.getText(),
                        nicTF.getText(),
                        contactNumberHomeTF.getText(),
                        contactNumberMobileTF.getText()
                ));
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Patient has been updated successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update the Patient.", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void onClose() {
        try {
            if (observer != null) {
                recepPatientService.unregister(observer);
            }
            if (reservedPatientID != null) {
                recepPatientService.release(reservedPatientID);
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
                tableUpdateAlert.setContentText("The Patient '" + primaryKey + "' has been " + status + " by Another Client." +
                        " \n Do You Want to Reload Patient Table Right Now?");
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
