package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
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
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Doctor_PatientHistoryService;
import lk.ijse.dcs.view.util.tblModel.Doctor_PatientHistory_AppointmentDetailTM;
import lk.ijse.dcs.view.util.tblModel.Doctor_PatientHistory_AppointmentTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Doctor_PatientHistoryController implements Initializable {

    @FXML
    private AnchorPane doctor_patientHistoryAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToPatientTreatmentIcon;
    @FXML
    private TextField patientHistorySearchBoxTF;
    @FXML
    private JFXTextField patientIDTF;
    @FXML
    private JFXTextField appointmentNumberTF;
    @FXML
    private JFXTextField appointmentDateTF;
    @FXML
    private JFXTextField appointmentTypeTF;
    @FXML
    private JFXTextField doctorsIDTF;
    @FXML
    private JFXTextArea detailsTA;
    @FXML
    private JFXTextArea otherDescriptionTA;
    @FXML
    private JFXTextArea doctorsDescriptionTA;
    @FXML
    private TableView<Doctor_PatientHistory_AppointmentTM> patientHistoryTbl;
    @FXML
    private TableView<Doctor_PatientHistory_AppointmentDetailTM> patientHistoryDrugDetailsTbl;

    private Doctor_PatientHistoryService doctorPatientHistoryService;

    private ObservableList<Doctor_PatientHistory_AppointmentTM> doctorPatientHistoryAppointmentTMS;

    private ObservableList<Doctor_PatientHistory_AppointmentDetailTM> doctorPatientHistoryAppointmentDetailTMS;

    private String searchedPatientID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            doctorPatientHistoryService = (Doctor_PatientHistoryService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR_PATIENTHISTORY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        patientHistoryTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        patientHistoryTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        patientHistoryTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        patientHistoryTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        patientHistoryTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("appointmentNO"));
        patientHistoryTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        patientHistoryTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        patientHistoryTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("doctorsID"));

        patientHistoryDrugDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        patientHistoryDrugDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        patientHistoryDrugDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        patientHistoryDrugDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));

        searchedPatientID = Doctor_PatientTreatmentController.searchedPatientID;

        setPatientID();

        loadPatientHistoryTbl();

    }

    @FXML
    public void backToPatientTreatmentIcon_onMouseEntered(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.rgb(0, 153, 204));
        glow.setWidth(5);
        glow.setHeight(5);
        glow.setRadius(5);
        icon.setEffect(glow);
    }

    @FXML
    public void backToPatientTreatmentIcon_onMouseExited(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();
        icon.setEffect(null);
    }

    @FXML
    public void backToPatientTreatmentIcon_onMouseClicked(MouseEvent event) {
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/doctor_patientTreatment.fxml")));
            Stage stage = (Stage) this.doctor_patientHistoryAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Doctor's Main DashBoard >>> Patient Treatments");
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

    public void setPatientID() {
        patientIDTF.setText(this.searchedPatientID);
    }

    public void loadPatientHistoryTbl() {
        try {
            doctorPatientHistoryAppointmentTMS = patientHistoryTbl.getItems();
            doctorPatientHistoryAppointmentTMS.removeAll(doctorPatientHistoryAppointmentTMS);

            ArrayList<AppointmentDTO> appointmentDTOS = (ArrayList<AppointmentDTO>) doctorPatientHistoryService.getAllPatientHistory(this.searchedPatientID);

            if (!appointmentDTOS.isEmpty()) {
                for (AppointmentDTO appointmentDTO : appointmentDTOS) {
                    doctorPatientHistoryAppointmentTMS.add(new Doctor_PatientHistory_AppointmentTM(
                            appointmentDTO.getAppointmentNO(),
                            appointmentDTO.getAppointmentDate(),
                            appointmentDTO.getAppointmentType(),
                            appointmentDTO.getDoctorsID()
                    ));
                }
                patientHistoryTbl.refresh();
            } else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Sorry..., Unable to Found Any History Belong to this Patient.",
                        ButtonType.OK).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void patientHistorySearchBoxTF_onMouseClick(MouseEvent event) {
        patientHistorySearchBoxTF.selectAll();
    }

    @FXML
    public void patientHistorySearchBoxTF_keyReleased(KeyEvent event) {
        if (patientHistorySearchBoxTF.getText().isEmpty()) {
            loadPatientHistoryTbl();
        } else {
            try {
                ArrayList<AppointmentDTO> appointmentDTOS =
                        (ArrayList<AppointmentDTO>) doctorPatientHistoryService.searchAllPatientHistory(this.searchedPatientID, patientHistorySearchBoxTF.getText());

                if (appointmentDTOS.isEmpty()) {
                    patientHistorySearchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadPatientHistoryTbl();
                } else {
                    patientHistorySearchBoxTF.setStyle("-fx-text-fill: #000000");

                    doctorPatientHistoryAppointmentTMS = patientHistoryTbl.getItems();
                    doctorPatientHistoryAppointmentTMS.removeAll(doctorPatientHistoryAppointmentTMS);

                    for (AppointmentDTO appointmentDTO : appointmentDTOS) {
                        doctorPatientHistoryAppointmentTMS.add(new Doctor_PatientHistory_AppointmentTM(
                                appointmentDTO.getAppointmentNO(),
                                appointmentDTO.getAppointmentDate(),
                                appointmentDTO.getAppointmentType(),
                                appointmentDTO.getDoctorsID()
                        ));
                    }
                    patientHistoryTbl.refresh();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void patientHistoryTbl_OnMouseClick(MouseEvent event) {
        try {
            Doctor_PatientHistory_AppointmentTM selectedItem = patientHistoryTbl.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                AppointmentDTO appointmentDTO = doctorPatientHistoryService.getPatientHistory(selectedItem.getAppointmentNO());
                appointmentNumberTF.setText(appointmentDTO.getAppointmentNO());
                appointmentDateTF.setText(appointmentDTO.getAppointmentDate());
                appointmentTypeTF.setText(appointmentDTO.getAppointmentType());
                doctorsIDTF.setText(appointmentDTO.getDoctorsID());
                detailsTA.setText(appointmentDTO.getDetails());
                otherDescriptionTA.setText(appointmentDTO.getOtherDescription());
                doctorsDescriptionTA.setText(appointmentDTO.getDoctorsDescription());

                ArrayList<AppointmentDetailsDTO> appointmentDetailsDTOS = (ArrayList<AppointmentDetailsDTO>) doctorPatientHistoryService.getPatientHistoryDetails(selectedItem.getAppointmentNO());
                doctorPatientHistoryAppointmentDetailTMS = patientHistoryDrugDetailsTbl.getItems();
                doctorPatientHistoryAppointmentDetailTMS.removeAll(doctorPatientHistoryAppointmentDetailTMS);

                for (AppointmentDetailsDTO detailsDTO : appointmentDetailsDTOS) {

                    doctorPatientHistoryAppointmentDetailTMS.add(new Doctor_PatientHistory_AppointmentDetailTM(
                            detailsDTO.getDrugCode(),
                            detailsDTO.getDescription()
                    ));
                }
                patientHistoryDrugDetailsTbl.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
