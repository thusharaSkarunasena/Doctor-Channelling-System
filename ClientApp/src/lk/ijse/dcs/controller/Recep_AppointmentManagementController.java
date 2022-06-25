package lk.ijse.dcs.controller;

import com.jfoenix.controls.*;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.EmployeeDTO;
import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Admin_EmployeeService;
import lk.ijse.dcs.service.custom.Recep_AppointmentService;
import lk.ijse.dcs.service.custom.Recep_PatientService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Recep_AppointmentManagementController implements Initializable {

    @FXML
    private AnchorPane recep_appointmentAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private TextField patientIDTF;
    @FXML
    private JFXTextField nameTF;
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
    private JFXButton searchPatientBtn;
    @FXML
    private JFXButton patientDetailsConfirmBtn;
    @FXML
    private JFXButton patientDetailsClearBtn;
    @FXML
    private Pane reserveAppointmentPane;
    @FXML
    private JFXDatePicker appointmentDateDP;
    @FXML
    private JFXButton reserveAppointmentBtn;
    @FXML
    private JFXButton appointmentClearBtn;
    @FXML
    private JFXButton appointmentCancelBtn;
    @FXML
    private TextField appointmentNoTF;
    @FXML
    private JFXComboBox<String> appointmentTypeComboBox;
    @FXML
    private JFXComboBox<String> doctorNameComboBox;
    @FXML
    private JFXTextArea detailsTA;
    @FXML
    private JFXTextArea otherDescriptionTA;
    @FXML
    private TextField queueNumberTF;

    private Recep_AppointmentService appointmentService;

    private String patientID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            appointmentService =
                    (Recep_AppointmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RECEP_APPOINTMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDatePickerBoundries();

        loadappointmentTypeComboBox();
        loadDoctorsNameComboBox();

        patientDetailsConfirmBtn.setDisable(true);
        reserveAppointmentPane.setDisable(true);

        appointmentTypeComboBox.setDisable(true);
        doctorNameComboBox.setDisable(true);
        detailsTA.setDisable(true);
        otherDescriptionTA.setDisable(true);

        patientIDTF.requestFocus();

    }

    public void setDatePickerBoundries() {

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
            Stage stage = (Stage) this.recep_appointmentAnchPane.getScene().getWindow();
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

    public void generateNextAppointmentNumber() {
        try {
            String nextAppointmentNumber = appointmentService.generateNextAppointmentNumber();
            appointmentNoTF.setText(nextAppointmentNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadappointmentTypeComboBox() {
        appointmentTypeComboBox.getItems().removeAll();
        appointmentTypeComboBox.getItems().addAll(
                "Type-01", "Type-02", "Type-03", "Type-04", "Type-05"
        );
    }

    public void loadDoctorsNameComboBox() {
        try {
            Admin_EmployeeService employeeService =
                    (Admin_EmployeeService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN_EMPLOYEE);
            ArrayList<EmployeeDTO> employeeDTOS = (ArrayList<EmployeeDTO>) employeeService.getAllEmployees();
            ObservableList<String> docterDetails = FXCollections.observableArrayList();

            if (!employeeDTOS.isEmpty()) {
                for (EmployeeDTO dto : employeeDTOS) {
                    if (dto.getEmployment().equals("Doctor")) {
                        String doctorDetail = dto.getEmployeeID() + " ~ " + dto.getName();
                        docterDetails.add(doctorDetail);
                    }
                }
                doctorNameComboBox.getItems().removeAll();
                doctorNameComboBox.setItems(docterDetails);
            }

        } catch (Exception e) {
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
            Recep_PatientService patientService =
                    (Recep_PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RECEP_PATIENT);

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
                dateOfBirthDP.setValue(LocalDate.parse(searchedPatient.getDob()));
                addressTA.setText(searchedPatient.getAddress());
                nicTF.setText(searchedPatient.getNic());
                contactNumberHomeTF.setText(searchedPatient.getCn_home());
                contactNumberMobileTF.setText(searchedPatient.getCn_mobile());

                this.patientID = patientIDTF.getText();
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
    public void appointmentDateDP_onAction(ActionEvent event) {
        appointmentTypeComboBox.setDisable(false);
        appointmentTypeComboBox.requestFocus();
        appointmentTypeComboBox.show();
    }

    @FXML
    public void appointmentClearBtn_onAction(ActionEvent event) {
        appointmentNoTF.setText("");
        detailsTA.setText("");
        otherDescriptionTA.setText("");
        queueNumberTF.setText("");

        generateNextAppointmentNumber();
        appointmentTypeComboBox.setDisable(true);
        doctorNameComboBox.setDisable(true);
        detailsTA.setDisable(true);
        otherDescriptionTA.setDisable(true);

        queueNumberTF.setAccessibleText("Q Number");

    }

    @FXML
    public void appointmentCancelBtn_onAction(ActionEvent event) {
        patientDetailsClearBtn.setDisable(false);
        patientDetailsClearBtn.fire();
        appointmentClearBtn.fire();
        patientIDTF.setDisable(false);
        reserveAppointmentPane.setDisable(true);
        patientIDTF.requestFocus();
        queueNumberTF.setAccessibleText("Q Number");
    }

    @FXML
    public void appointmentTypeComboBox_onAction(ActionEvent event) {
        doctorNameComboBox.setDisable(false);
        doctorNameComboBox.requestFocus();
        doctorNameComboBox.show();
    }

    @FXML
    public void doctorNameComboBox_onAction(ActionEvent event) {
        detailsTA.setDisable(false);
        otherDescriptionTA.setDisable(false);
        detailsTA.requestFocus();
        generateQueueNumber();
    }

    public void generateQueueNumber() {
        try {
            String nextQueueNumber =
                    appointmentService.getNextQueueNumber(appointmentDateDP.getValue().toString(),
                            doctorNameComboBox.getValue().substring(0,7));
            queueNumberTF.setText(nextQueueNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void patientDetailsClearBtn_onAction(ActionEvent event) {
        patientIDTF.setText("");
        nameTF.setText("");
        maleRadioBtn.setSelected(false);
        femaleRadioBtn.setSelected(false);
        dateOfBirthDP.setValue(null);
        addressTA.setText("");
        nicTF.setText("");
        contactNumberHomeTF.setText("");
        contactNumberMobileTF.setText("");

        patientIDTF.setDisable(false);
        searchPatientBtn.setDisable(false);
        patientDetailsClearBtn.setDisable(false);
        patientDetailsConfirmBtn.setDisable(true);

        patientIDTF.requestFocus();

    }

    @FXML
    public void patientDetailsConfirmBtn_onAction(ActionEvent event) {
        patientIDTF.setDisable(true);
        searchPatientBtn.setDisable(true);
        patientDetailsClearBtn.setDisable(true);
        patientDetailsConfirmBtn.setDisable(true);
        reserveAppointmentPane.setDisable(false);
        generateNextAppointmentNumber();
    }

    @FXML
    public void reserveAppointmentBtn_onAction(ActionEvent event) {
        try {
            String appointmentNO = appointmentNoTF.getText();
            String patientID = this.patientID;
            String dateAndTime = LocalDateTime.now().toString();
            String appointmentDate = appointmentDateDP.getValue().toString();
            String appointmentType = appointmentTypeComboBox.getValue();
            String doctorID = doctorNameComboBox.getValue().substring(0, 7);
            String details = detailsTA.getText();
            String otherDescription = otherDescriptionTA.getText();
            String queueNumber = queueNumberTF.getText();
            String doctorsDescription = "null_doctorsDescription_null";

            boolean result = appointmentService.reserveAppointment(new AppointmentDTO(
                    appointmentNO,
                    patientID,
                    dateAndTime,
                    appointmentDate,
                    appointmentType,
                    doctorID,
                    "demo_emp",
                    "demo_emp",
                    details,
                    otherDescription,
                    queueNumber,
                    doctorsDescription
            ));
            if (result) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Appointment has been Reserved Successfully.",
                        ButtonType.OK).show();
                appointmentCancelBtn.fire();
            } else {
                new Alert(Alert.AlertType.ERROR,
                        "Failed to Reserve the Appointment.",
                        ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
