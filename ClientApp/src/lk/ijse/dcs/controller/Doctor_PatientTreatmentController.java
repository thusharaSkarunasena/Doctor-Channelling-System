package lk.ijse.dcs.controller;

import com.jfoenix.controls.*;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.dto.DrugDTO;
import lk.ijse.dcs.dto.DrugPackDTO;
import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Doctor_DrugPackService;
import lk.ijse.dcs.service.custom.Doctor_PatientTreatmentService;
import lk.ijse.dcs.service.custom.Pharm_DrugService;
import lk.ijse.dcs.service.custom.Recep_PatientService;
import lk.ijse.dcs.view.util.tblModel.Doctor_PatientTreatmentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Doctor_PatientTreatmentController implements Initializable {

    @FXML
    private AnchorPane doctor_patientTreatmentAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private TextField patientIDTF;
    @FXML
    private JFXButton searchPatientBtn;
    @FXML
    private JFXButton patientDetailsConfirmBtn;
    @FXML
    private JFXButton patientDetailsClearBtn;
    @FXML
    private JFXButton treatmentHistoryBtn;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField nicTF;
    @FXML
    private JFXRadioButton femaleRadioBtn;
    @FXML
    private ToggleGroup patientSex;
    @FXML
    private JFXRadioButton maleRadioBtn;
    @FXML
    private TextField ageTF;
    @FXML
    private JFXTextArea addressTA;
    @FXML
    private Pane treatmentPane;
    @FXML
    private JFXButton treatmentDoneBtn;
    @FXML
    private JFXButton treatmentClearBtn;
    @FXML
    private JFXButton treatmentCancelBtn;
    @FXML
    private TextField appointmentNoTF;
    @FXML
    private JFXComboBox<String> myFavouritePacksComboBox;
    @FXML
    private JFXComboBox<String> allDrugsComboBox;
    @FXML
    private JFXTextArea otherDescriptionTA;
    @FXML
    private TextField currentDateTF;
    @FXML
    private JFXTextArea myFavouritePacksDescriptionTA;
    @FXML
    private JFXTextArea allDrugsOtherDescriptionTA;
    @FXML
    private JFXButton myFavouritePacksAddSubBtn;
    @FXML
    private JFXButton allDrugsAddSubBtn;
    @FXML
    private JFXButton allDrugsUpdateSubBtn;
    @FXML
    private JFXButton myFavouritePacksUpdateSubBtn;
    @FXML
    private JFXButton clearAllSubBtn;
    @FXML
    private JFXButton deleteSubBtn;
    @FXML
    private TableView<Doctor_PatientTreatmentTM> packOrMedicineDetailsTbl;

    private Doctor_PatientTreatmentService doctorPatientService;

    ObservableList<Doctor_PatientTreatmentTM> doctorPatientTreatmentTMs;

    private String myEmployeeID = null;

    public static String searchedPatientID = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            doctorPatientService = (Doctor_PatientTreatmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR_PATIENTTREATMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        packOrMedicineDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        packOrMedicineDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        packOrMedicineDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("packOrDrugCode"));
        packOrMedicineDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));

        this.myEmployeeID = LogInAllController.loggedEmployee.getEmployeeID();

        loadMyFaouritePacksComboBox();
        loadAllDrugsComboBox();

        patientDetailsConfirmBtn.setDisable(true);
        treatmentHistoryBtn.setDisable(true);
        treatmentPane.setDisable(true);

        myFavouritePacksDescriptionTA.setDisable(true);
        allDrugsOtherDescriptionTA.setDisable(true);

        resumePreviousJob();

        patientIDTF.requestFocus();

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
        this.searchedPatientID = null;
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/doctorMainDash.fxml")));
            Stage stage = (Stage) this.doctor_patientTreatmentAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Doctor's Main DashBoard");
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

    public void loadMyFaouritePacksComboBox() {
        try {
            Doctor_DrugPackService doctorDrugPackService =
                    (Doctor_DrugPackService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR_DRUGPACK);
            ArrayList<DrugPackDTO> drugPackDTOS =
                    (ArrayList<DrugPackDTO>) doctorDrugPackService.getMyAllDrugPacks(this.myEmployeeID);
            ObservableList<String> myDrugPacks = FXCollections.observableArrayList();

            for (DrugPackDTO drugPackDTO : drugPackDTOS) {
                myDrugPacks.add(drugPackDTO.getDrugPackCode());
            }

            myFavouritePacksComboBox.setItems(myDrugPacks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllDrugsComboBox() {
        try {
            Pharm_DrugService pharmDrugService =
                    (Pharm_DrugService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PHARM_DRUG);
            ArrayList<DrugDTO> drugDTOS = (ArrayList<DrugDTO>) pharmDrugService.getAllDrugs();
            ObservableList<String> allDrugs = FXCollections.observableArrayList();

            for (DrugDTO drugDTO : drugDTOS) {
                allDrugs.add(drugDTO.getDrugCode());
            }

            allDrugsComboBox.setItems(allDrugs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumePreviousJob() {
        if (this.searchedPatientID != null) {
            patientIDTF.setText(searchedPatientID);
            searchPatientBtn.fire();
        }
    }

    @FXML
    public void myFavouritePacksAddSubBtn_onAction(ActionEvent event) {
        if (!myFavouritePacksDescriptionTA.isDisable()) {
            doctorPatientTreatmentTMs = packOrMedicineDetailsTbl.getItems();
            boolean isExist = false;
            for (Doctor_PatientTreatmentTM treatmentTM : doctorPatientTreatmentTMs) {
                if (treatmentTM.getPackOrDrugCode().equals(myFavouritePacksComboBox.getValue())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                doctorPatientTreatmentTMs.add(new Doctor_PatientTreatmentTM(
                        myFavouritePacksComboBox.getValue(),
                        myFavouritePacksDescriptionTA.getText()
                ));
                myFavouritePacksComboBox.setValue("");
                myFavouritePacksDescriptionTA.setText("");
                myFavouritePacksDescriptionTA.setDisable(true);
                packOrMedicineDetailsTbl.refresh();
            } else {
                new Alert(Alert.AlertType.ERROR,
                        "The Favourite Drug Pack You are Going to Add is Already in Pack or Drug Details Table. " +
                                "\n Try to Update Exist Favourite Drug Pack Description.",
                        ButtonType.OK).show();
            }
        }
    }

    @FXML
    public void allDrugsAddSubBtn_onAction(ActionEvent event) {
        if (!allDrugsOtherDescriptionTA.isDisable()) {
            doctorPatientTreatmentTMs = packOrMedicineDetailsTbl.getItems();
            boolean isExist = false;
            for (Doctor_PatientTreatmentTM treatmentTM : doctorPatientTreatmentTMs) {
                if (treatmentTM.getPackOrDrugCode().equals(allDrugsComboBox.getValue())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                doctorPatientTreatmentTMs.add(new Doctor_PatientTreatmentTM(
                        allDrugsComboBox.getValue(),
                        allDrugsOtherDescriptionTA.getText()
                ));
                allDrugsComboBox.setValue("");
                allDrugsOtherDescriptionTA.setText("");
                allDrugsOtherDescriptionTA.setDisable(true);
                packOrMedicineDetailsTbl.refresh();
            } else {
                new Alert(Alert.AlertType.ERROR,
                        "The Drug You are Going to Add is Already in Pack or Drug Details Table. " +
                                "\n Try to Update Exist Drug Description.",
                        ButtonType.OK).show();
            }
        }
    }

    @FXML
    public void myFavouritePacksUpdateSubBtn_onAction(ActionEvent event) {
        if (!myFavouritePacksDescriptionTA.isDisable()) {
            doctorPatientTreatmentTMs = packOrMedicineDetailsTbl.getItems();
            Doctor_PatientTreatmentTM doctorPatientTreatmentTM = null;
            boolean isExist = false;
            for (Doctor_PatientTreatmentTM treatmentTM : doctorPatientTreatmentTMs) {
                if (treatmentTM.getPackOrDrugCode().equals(myFavouritePacksComboBox.getValue())) {
                    isExist = true;
                    doctorPatientTreatmentTM = treatmentTM;
                    break;
                }
            }
            if (isExist) {
                doctorPatientTreatmentTM.setPackOrDrugCode(myFavouritePacksComboBox.getValue());
                doctorPatientTreatmentTM.setDescription(myFavouritePacksDescriptionTA.getText());

                myFavouritePacksComboBox.setValue("");
                myFavouritePacksDescriptionTA.setText("");
                myFavouritePacksDescriptionTA.setDisable(true);
                packOrMedicineDetailsTbl.refresh();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Select an Existing Drug Pack from Pack or Medicine Details Table to Update.", ButtonType.OK).show();
            }
        }
    }

    @FXML
    public void allDrugsUpdateSubBtn_onAction(ActionEvent event) {
        if (!allDrugsOtherDescriptionTA.isDisable()) {
            doctorPatientTreatmentTMs = packOrMedicineDetailsTbl.getItems();
            Doctor_PatientTreatmentTM doctorPatientTreatmentTM = null;
            boolean isExist = false;
            for (Doctor_PatientTreatmentTM treatmentTM : doctorPatientTreatmentTMs) {
                if (treatmentTM.getPackOrDrugCode().equals(allDrugsComboBox.getValue())) {
                    isExist = true;
                    doctorPatientTreatmentTM = treatmentTM;
                    break;
                }
            }
            if (isExist) {
                doctorPatientTreatmentTM.setPackOrDrugCode(allDrugsComboBox.getValue());
                doctorPatientTreatmentTM.setDescription(allDrugsOtherDescriptionTA.getText());

                allDrugsComboBox.setValue("");
                allDrugsOtherDescriptionTA.setText("");
                allDrugsOtherDescriptionTA.setDisable(true);
                packOrMedicineDetailsTbl.refresh();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Select an Existing Drug from Pack or Medicine Details Table to Update.", ButtonType.OK).show();
            }
        }
    }

    @FXML
    public void allDrugsComboBox_onAction(ActionEvent event) {
        allDrugsOtherDescriptionTA.setDisable(false);
        allDrugsOtherDescriptionTA.requestFocus();
    }

    @FXML
    public void clearAllSubBtn_onAction(ActionEvent event) {
        doctorPatientTreatmentTMs = packOrMedicineDetailsTbl.getItems();
        doctorPatientTreatmentTMs.removeAll(doctorPatientTreatmentTMs);

        myFavouritePacksComboBox.setValue("");
        myFavouritePacksDescriptionTA.setText("");

        allDrugsComboBox.setValue("");
        allDrugsOtherDescriptionTA.setText("");

        myFavouritePacksDescriptionTA.setDisable(true);
        allDrugsOtherDescriptionTA.setDisable(true);
    }

    @FXML
    public void deleteSubBtn_onAction(ActionEvent event) {
        Doctor_PatientTreatmentTM selectedItem = packOrMedicineDetailsTbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            doctorPatientTreatmentTMs.remove(selectedItem);
            if (selectedItem.getPackOrDrugCode().substring(0, 5).equals("DRGPK")) {
                myFavouritePacksComboBox.setValue("");
                myFavouritePacksDescriptionTA.setText("");
                myFavouritePacksDescriptionTA.setDisable(true);
            } else {
                allDrugsComboBox.setValue("");
                allDrugsOtherDescriptionTA.setText("");
                allDrugsOtherDescriptionTA.setDisable(true);
            }
            packOrMedicineDetailsTbl.refresh();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Select an Existing Drug Pack or Drug from Table to Delete", ButtonType.OK).show();
        }
    }

    @FXML
    public void myFavouritePacksComboBox_onAction(ActionEvent event) {
        myFavouritePacksDescriptionTA.setDisable(false);
        myFavouritePacksDescriptionTA.requestFocus();
    }

    @FXML
    public void packOrMedicineDetailsTbl_onMouseClicked(MouseEvent event) {
        Doctor_PatientTreatmentTM selectedItem = packOrMedicineDetailsTbl.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            if (selectedItem.getPackOrDrugCode().substring(0, 5).equals("DRGPK")) {
                myFavouritePacksComboBox.setValue(selectedItem.getPackOrDrugCode());
                myFavouritePacksDescriptionTA.setDisable(false);
                myFavouritePacksDescriptionTA.setText(selectedItem.getDescription());
            } else {
                allDrugsComboBox.setValue(selectedItem.getPackOrDrugCode());
                allDrugsOtherDescriptionTA.setDisable(false);
                allDrugsOtherDescriptionTA.setText(selectedItem.getDescription());
            }
        }
    }

    @FXML
    public void patientDetailsClearBtn_onAction(ActionEvent event) {
        patientIDTF.setText("");
        nameTF.setText("");
        maleRadioBtn.setSelected(false);
        femaleRadioBtn.setSelected(false);
        ageTF.setText("");
        addressTA.setText("");
        nicTF.setText("");

        patientIDTF.setDisable(false);
        searchPatientBtn.setDisable(false);
        patientDetailsClearBtn.setDisable(false);
        treatmentHistoryBtn.setDisable(true);
        patientDetailsConfirmBtn.setDisable(true);

        this.searchedPatientID = null;

        patientIDTF.requestFocus();
    }

    @FXML
    public void patientDetailsConfirmBtn_onAction(ActionEvent event) {

        String appointmentNO = getReservedAppointmentNO(LocalDate.now().toString(), this.myEmployeeID, this.searchedPatientID);

        if (appointmentNO != null) {
            patientIDTF.setDisable(true);
            searchPatientBtn.setDisable(true);
            patientDetailsClearBtn.setDisable(true);
            patientDetailsConfirmBtn.setDisable(true);
            treatmentPane.setDisable(false);
            appointmentNoTF.setText(appointmentNO);
            currentDateTF.setText(LocalDate.now().toString());
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Sorry..., There isn't a Appointment Reservation to this Patient on Today.",
                    ButtonType.OK).show();
        }

    }

    public String getReservedAppointmentNO(String currentDate, String employeeID, String patientID) {
        String reservedAppointmentNO = null;
        try {
            reservedAppointmentNO = doctorPatientService.getReservedAppointmentNO(currentDate, employeeID, patientID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservedAppointmentNO;
    }

    @FXML
    public void treatmentHistoryBtn_onAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/doctor_patientHistory.fxml"));
            Scene temp = new Scene(root);
            Stage stage = (Stage) this.doctor_patientTreatmentAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Doctor's Main DashBoard >>> Patient Treatments >>> Treatment History");
            stage.setResizable(false);
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
            trans.setFromX(+temp.getHeight());
            trans.setToX(0);
            trans.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void patientIDTF_onAction(ActionEvent event) {
        searchPatientBtn.fire();
    }

    @FXML
    public void searchPatientBtn_onAction(ActionEvent event) {
        try {
            Recep_PatientService patientService = (Recep_PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RECEP_PATIENT);

            ArrayList<PatientDTO> patientDTOS = (ArrayList<PatientDTO>) patientService.getAllPatients();

            boolean isExist = false;
            PatientDTO searchedPatient = null;

            for (PatientDTO dto : patientDTOS) {
                if (dto.getPatientID().equals(patientIDTF.getText())) {
                    isExist = true;
                    searchedPatient = dto;
                    break;
                }
            }

            if (isExist) {
                nameTF.setText(searchedPatient.getName());
                String gender = searchedPatient.getGender();
                if (gender.equals("Male")) {
                    maleRadioBtn.setSelected(true);
                    femaleRadioBtn.setSelected(false);
                } else {
                    maleRadioBtn.setSelected(false);
                    femaleRadioBtn.setSelected(true);
                }
                ageTF.setText(Integer.toString((LocalDate.now().getYear() - LocalDate.parse(searchedPatient.getDob()).getYear())));
                addressTA.setText(searchedPatient.getAddress());
                nicTF.setText(searchedPatient.getNic());

                this.searchedPatientID = patientIDTF.getText();
                treatmentHistoryBtn.setDisable(false);
                patientDetailsConfirmBtn.setDisable(false);

            } else {
                new Alert(Alert.AlertType.ERROR,
                        "Please insert a valid existing patientID.",
                        ButtonType.OK).show();
                patientDetailsClearBtn.fire();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void treatmentClearBtn_onAction(ActionEvent event) {
        otherDescriptionTA.setText("");
        clearAllSubBtn.fire();
    }

    @FXML
    public void treatmentCancelBtn_onAction(ActionEvent event) {
        treatmentClearBtn.fire();
        treatmentPane.setDisable(true);
        patientDetailsClearBtn.setDisable(false);
        patientDetailsClearBtn.fire();
        patientIDTF.setDisable(false);
        patientIDTF.setText("");
        searchPatientBtn.setDisable(false);
    }

    @FXML
    public void treatmentDoneBtn_onAction(ActionEvent event) {
        try {
            String appointmentNO = appointmentNoTF.getText();
            String doctorsDescription = otherDescriptionTA.getText();
            List<AppointmentDetailsDTO> appointmentDetailsDTOS = new ArrayList<>();

            doctorPatientTreatmentTMs = packOrMedicineDetailsTbl.getItems();

            for (Doctor_PatientTreatmentTM patientTreatmentTM : doctorPatientTreatmentTMs) {
                if (patientTreatmentTM.getPackOrDrugCode().substring(0, 5).equals("DRGPK")) {
                    appointmentDetailsDTOS.add(new AppointmentDetailsDTO(
                            appointmentNO,
                            "DRG-00000",
                            patientTreatmentTM.getPackOrDrugCode(),
                            patientTreatmentTM.getDescription(),
                            0,
                            0.00

                    ));
                } else {
                    appointmentDetailsDTOS.add(new AppointmentDetailsDTO(
                            appointmentNO,
                            patientTreatmentTM.getPackOrDrugCode(),
                            "DRGPK-00000",
                            patientTreatmentTM.getDescription(),
                            0,
                            0.00

                    ));
                }
            }

            boolean result = doctorPatientService.saveAppointmentAndDetails(appointmentNO, doctorsDescription, appointmentDetailsDTOS);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Appointment Details has been Saved Successfully.",
                        ButtonType.OK).show();
                treatmentCancelBtn.fire();
            } else {
                new Alert(Alert.AlertType.ERROR,
                        "Failed to Save Appointment Details.",
                        ButtonType.OK).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
