package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.dto.AppointmentDTO;
import lk.ijse.dcs.dto.AppointmentDetailsDTO;
import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Pharm_IssueDrugsService;
import lk.ijse.dcs.service.custom.Recep_PatientService;
import lk.ijse.dcs.view.util.tblModel.Pharm_IssueDrugsTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Pharm_IssueDrugsController implements Initializable {

    @FXML
    private AnchorPane pharm_issueDrugsAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private TextField patientIDTF;
    @FXML
    private JFXButton searchPatientBtn;
    @FXML
    private JFXButton confirmBtn;
    @FXML
    private JFXButton patientDetailsPaneClearBtn;
    @FXML
    private JFXButton issueDrugsPaneClearBtn;
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
    private JFXTextArea addressTA;
    @FXML
    private JFXTextField ageTF;
    @FXML
    private JFXTextField drugCodeTF;
    @FXML
    private JFXTextField qtyTF;
    @FXML
    private Pane issueDrugsPane;
    @FXML
    private JFXButton doneBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXTextArea doctorsDescriptionTA;
    @FXML
    private JFXTextArea appointmentDetailTA;
    @FXML
    private JFXTextArea appointmentDescriptionTA;
    @FXML
    private TableView<Pharm_IssueDrugsTM> drugDetailsTbl;
    @FXML
    private JFXButton addSubBtn;
    @FXML
    private TextField appointmentNOTF;
    @FXML
    private TextField doctorIDTF;

    Pharm_IssueDrugsService pharmIssueDrugsService;

    private ObservableList<Pharm_IssueDrugsTM> pharmIssueDrugsTMS;

    private String searchedPatientID = null;

    private String myEmployeeID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            pharmIssueDrugsService =
                    (Pharm_IssueDrugsService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PHARM_ISSUEDRUGS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        drugDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        drugDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        drugDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        drugDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        drugDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        drugDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));

        this.myEmployeeID=LogInAllController.loggedEmployee.getEmployeeID();

        confirmBtn.setDisable(true);
        issueDrugsPane.setDisable(true);

        patientIDTF.requestFocus();

    }

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
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/pharmacistMainDash.fxml")));
            Stage stage = (Stage) this.pharm_issueDrugsAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Pharmacist's Main DashBoard");
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

    @FXML
    public void cancelBtn_onAction(ActionEvent event) {
        appointmentNOTF.setText("");
        doctorIDTF.setText("");
        appointmentDetailTA.setText("");
        appointmentDescriptionTA.setText("");
        doctorsDescriptionTA.setText("");

        pharmIssueDrugsTMS=drugDetailsTbl.getItems();
        pharmIssueDrugsTMS.removeAll(pharmIssueDrugsTMS);
        drugDetailsTbl.refresh();

        issueDrugsPane.setDisable(true);

        patientIDTF.setDisable(false);
        searchPatientBtn.setDisable(false);
        patientDetailsPaneClearBtn.setDisable(false);
        confirmBtn.setDisable(true);

        patientDetailsPaneClearBtn.fire();

    }

    @FXML
    public void confirmBtn_onAction(ActionEvent event) {
        try {
            AppointmentDTO appointmentDTO =pharmIssueDrugsService.getAppointment(this.searchedPatientID, LocalDate.now().toString());

            if(!appointmentDTO.getAppointmentNO().equals("emptySet")){
                patientIDTF.setDisable(true);
                searchPatientBtn.setDisable(true);
                patientDetailsPaneClearBtn.setDisable(true);
                confirmBtn.setDisable(true);
                issueDrugsPane.setDisable(false);

                appointmentNOTF.setText(appointmentDTO.getAppointmentNO());
                doctorIDTF.setText(appointmentDTO.getDoctorsID());
                appointmentDetailTA.setText(appointmentDTO.getDetails());
                appointmentDescriptionTA.setText(appointmentDTO.getOtherDescription());
                doctorsDescriptionTA.setText(appointmentDTO.getDoctorsDescription());

                ArrayList<AppointmentDetailsDTO> appointmentDetailsDTOS= (ArrayList<AppointmentDetailsDTO>) pharmIssueDrugsService.getAppointmentDetail(appointmentNOTF.getText());

                pharmIssueDrugsTMS=drugDetailsTbl.getItems();
                pharmIssueDrugsTMS.removeAll(pharmIssueDrugsTMS);

                for(AppointmentDetailsDTO detailsDTO:appointmentDetailsDTOS){
                    pharmIssueDrugsTMS.add(new Pharm_IssueDrugsTM(
                            detailsDTO.getDrugCode(),
                            detailsDTO.getDescription(),
                            Integer.toString(detailsDTO.getQty())
                    ));
                }
                drugDetailsTbl.refresh();
            }else{
                new Alert(Alert.AlertType.ERROR, "Sorry..., There isn't a Appointment Reservation to this Patient on Today.",ButtonType.OK).show();
            }

        } catch (Exception e) {

        }
    }

    @FXML
    public void doneBtn_onAction(ActionEvent event) {
        try {
            String employeeID_pharm=this.myEmployeeID;

            List<AppointmentDetailsDTO> appointmentDetailsDTOS=new ArrayList<>();

            pharmIssueDrugsTMS=drugDetailsTbl.getItems();
            for(Pharm_IssueDrugsTM issueDrugsTM:pharmIssueDrugsTMS){
                appointmentDetailsDTOS.add(new AppointmentDetailsDTO(
                        appointmentNOTF.getText(),
                        issueDrugsTM.getDrugCode(),
                        "DRGPK-00000",
                        issueDrugsTM.getDescription(),
                        Integer.parseInt(issueDrugsTM.getQty()),
                        0.00
                ));
            }

            boolean result=pharmIssueDrugsService.updateAppointmentAndDetails(employeeID_pharm, appointmentDetailsDTOS);

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Data has been Added Successfully.",ButtonType.OK).show();
                cancelBtn.fire();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Data",ButtonType.OK).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void drugDetailsTbl_onMouseClicked(MouseEvent event) {
        Pharm_IssueDrugsTM selectedItem=drugDetailsTbl.getSelectionModel().getSelectedItem();

        if(selectedItem!=null){
            drugCodeTF.setText(selectedItem.getDrugCode());
            qtyTF.setText(selectedItem.getQty());
            qtyTF.selectAll();
            qtyTF.requestFocus();
        }

    }

    @FXML
    public void issueDrugsPaneClearBtn_onAction(ActionEvent event) {
        pharmIssueDrugsTMS=drugDetailsTbl.getItems();
        for(Pharm_IssueDrugsTM issueDrugsTM:pharmIssueDrugsTMS){
            issueDrugsTM.setQty("0");
        }
        drugDetailsTbl.refresh();

        drugCodeTF.setText("");
        qtyTF.setText("");

    }

    @FXML
    public void patientDetailsPaneClearBtn_onAction(ActionEvent event) {
        patientIDTF.setText("");
        nameTF.setText("");
        maleRadioBtn.setSelected(false);
        femaleRadioBtn.setSelected(false);
        ageTF.setText("");
        addressTA.setText("");
        nicTF.setText("");

        confirmBtn.setDisable(true);

        patientIDTF.requestFocus();
    }

    @FXML
    public void patientIDTF_onAction(ActionEvent event) {
        searchPatientBtn.fire();
    }

    @FXML
    public void qtyTF_onAction(ActionEvent event) {
        addSubBtn.fire();
    }

    @FXML
    public void addSubBtn_onAction(ActionEvent event) {
        pharmIssueDrugsTMS=drugDetailsTbl.getItems();
        for(Pharm_IssueDrugsTM issueDrugsTM:pharmIssueDrugsTMS){
            if(issueDrugsTM.getDrugCode().equals(drugCodeTF.getText())){
                issueDrugsTM.setQty(qtyTF.getText());
                break;
            }
        }
        drugDetailsTbl.refresh();

        drugCodeTF.setText("");
        qtyTF.setText("");

        drugDetailsTbl.requestFocus();
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
                ageTF.setText(Integer.toString((LocalDate.now().getYear() - LocalDate.parse(searchedPatient.getDob()).getYear())));
                addressTA.setText(searchedPatient.getAddress());
                nicTF.setText(searchedPatient.getNic());

                this.searchedPatientID = patientIDTF.getText();
                confirmBtn.setDisable(false);

            } else {
                new Alert(Alert.AlertType.ERROR,
                        "Please insert a valid existing patientID.",
                        ButtonType.OK).show();
                patientDetailsPaneClearBtn.fire();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
