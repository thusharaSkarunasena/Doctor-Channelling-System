package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.dto.DrugDTO;
import lk.ijse.dcs.dto.DrugPackDTO;
import lk.ijse.dcs.dto.DrugPackDetailsDTO;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Doctor_DrugPackService;
import lk.ijse.dcs.service.custom.Pharm_DrugService;
import lk.ijse.dcs.view.util.tblModel.Doctor_DrugPackManagement_DrugPackDetailTM;
import lk.ijse.dcs.view.util.tblModel.Doctor_DrugPackManagement_DrugPackTM;
import lk.ijse.dcs.view.util.tblModel.Doctor_DrugPackManagement_DrugTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Doctor_DrugPackManagementController implements Initializable {

    @FXML
    private AnchorPane doctor_manageMedicinesAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private TextField drugItemSearchBoxTF;
    @FXML
    private JFXButton drugItemAddBtn;
    @FXML
    private JFXTextField drugNameTF;
    @FXML
    private JFXTextField drugCodeTF;
    @FXML
    private TableView<Doctor_DrugPackManagement_DrugPackDetailTM> packDetailsTbl;
    @FXML
    private JFXButton newPackBtn;
    @FXML
    private JFXButton savePackBtn;
    @FXML
    private JFXButton deletePackBtn;
    @FXML
    private JFXButton updatePackBtn;
    @FXML
    private JFXTextField packNameTF;
    @FXML
    private JFXTextField packCodeTF;
    @FXML
    private TextField myPacksSearchBoxTF;
    @FXML
    private TableView<Doctor_DrugPackManagement_DrugPackTM> myPacksTbl;
    @FXML
    private JFXButton deletePackItemBtn;
    @FXML
    private JFXButton clearAllPackItemBtn;
    @FXML
    private TableView<Doctor_DrugPackManagement_DrugTM> drugItemTbl;

    private Doctor_DrugPackService doctorDrugPackService;

    private ObservableList<Doctor_DrugPackManagement_DrugTM> doctorDrugManagementDrugTMs;

    private ObservableList<Doctor_DrugPackManagement_DrugPackDetailTM> doctorDrugManagementDrugPackDetailTMs;

    private ObservableList<Doctor_DrugPackManagement_DrugPackTM> doctorDrugPackManagementDrugPackTMs;

    private String myEmployeeID = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            doctorDrugPackService = (Doctor_DrugPackService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR_DRUGPACK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        drugItemTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        drugItemTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        drugItemTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        drugItemTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        drugItemTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("drugName"));
        drugItemTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));

        packDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        packDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        packDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        packDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("drugName"));

        myPacksTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        myPacksTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        myPacksTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("drugPackCode"));
        myPacksTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("drugPackName"));

        this.myEmployeeID = LogInAllController.loggedEmployee.getEmployeeID();

        loadDrugItemTbl();

        loadMyPacksTbl();

        generateNextDrugPackCode();

        drugItemAddBtn.setDisable(true);

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
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/doctorMainDash.fxml")));
            Stage stage = (Stage) this.doctor_manageMedicinesAnchPane.getScene().getWindow();
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

    public void loadDrugItemTbl() {
        try {
            Pharm_DrugService pharmDrugService =
                    (Pharm_DrugService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PHARM_DRUG);
            ArrayList<DrugDTO> drugDTOS = (ArrayList<DrugDTO>) pharmDrugService.getAllDrugs();

            doctorDrugManagementDrugTMs = drugItemTbl.getItems();
            doctorDrugManagementDrugTMs.removeAll(doctorDrugManagementDrugTMs);

            for (DrugDTO dto : drugDTOS) {
                doctorDrugManagementDrugTMs.add(new Doctor_DrugPackManagement_DrugTM(
                        dto.getDrugCode(),
                        dto.getName(),
                        dto.getDescription()
                ));
            }
            drugItemTbl.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMyPacksTbl() {
        try {
            doctorDrugPackManagementDrugPackTMs = myPacksTbl.getItems();
            doctorDrugPackManagementDrugPackTMs.removeAll(doctorDrugPackManagementDrugPackTMs);

            ArrayList<DrugPackDTO> drugPackDTOS = (ArrayList<DrugPackDTO>) doctorDrugPackService.getMyAllDrugPacks(myEmployeeID);

            if (!drugPackDTOS.isEmpty()) {
                for (DrugPackDTO packDTO : drugPackDTOS) {
                    doctorDrugPackManagementDrugPackTMs.add(new Doctor_DrugPackManagement_DrugPackTM(
                            packDTO.getDrugPackCode(),
                            packDTO.getName()
                    ));
                }
                myPacksTbl.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateNextDrugPackCode() {
        try {
            String nextDrugPackCode = doctorDrugPackService.generateNextDrugPackCode();
            packCodeTF.setText(nextDrugPackCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deletePackItemBtn_onAction(ActionEvent event) {
        Doctor_DrugPackManagement_DrugPackDetailTM packDetailTM = packDetailsTbl.getSelectionModel().getSelectedItem();
        if (packDetailTM != null) {
            doctorDrugManagementDrugPackDetailTMs.remove(packDetailTM);
            packDetailsTbl.refresh();
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Please Select an Existing Drug Pack Item from Pack Item Table to Delete.",
                    ButtonType.OK).show();
        }
    }

    @FXML
    public void clearAllPackItemBtn_onAction(ActionEvent event) {
        doctorDrugManagementDrugPackDetailTMs = packDetailsTbl.getItems();
        doctorDrugManagementDrugPackDetailTMs.removeAll(doctorDrugManagementDrugPackDetailTMs);
        packDetailsTbl.refresh();
    }

    @FXML
    public void drugItemAddBtn_onAction(ActionEvent event) {
        doctorDrugManagementDrugPackDetailTMs = packDetailsTbl.getItems();
        boolean isExist = false;

        for (Doctor_DrugPackManagement_DrugPackDetailTM packDetailTM : doctorDrugManagementDrugPackDetailTMs) {
            if (packDetailTM.getDrugCode().equals(drugCodeTF.getText())) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            doctorDrugManagementDrugPackDetailTMs.add(new Doctor_DrugPackManagement_DrugPackDetailTM(
                    drugCodeTF.getText(),
                    drugNameTF.getText()
            ));
            packDetailsTbl.refresh();
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Sorry.., The drug you are going to add is already in Drug Pack Details Table.",
                    ButtonType.OK).show();
        }

    }

    @FXML
    public void drugItemSearchBoxTF_keyReleased(KeyEvent event) {
        if (drugItemSearchBoxTF.getText().isEmpty()) {
            loadDrugItemTbl();
        } else {
            try {
                Pharm_DrugService pharmDrugService =
                        (Pharm_DrugService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PHARM_DRUG);
                ArrayList<DrugDTO> drugDTOS =
                        (ArrayList<DrugDTO>) pharmDrugService.searchDrug(drugItemSearchBoxTF.getText());

                if (drugDTOS.isEmpty()) {
                    drugItemSearchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadDrugItemTbl();
                } else {
                    drugItemSearchBoxTF.setStyle("-fx-text-fill: #000000");
                    doctorDrugManagementDrugTMs = drugItemTbl.getItems();
                    doctorDrugManagementDrugTMs.removeAll(doctorDrugManagementDrugTMs);

                    for (DrugDTO dto : drugDTOS) {
                        doctorDrugManagementDrugTMs.add(new Doctor_DrugPackManagement_DrugTM(
                                dto.getDrugCode(),
                                dto.getName(),
                                dto.getDescription()
                        ));
                    }
                    drugItemTbl.refresh();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void drugItemSearchBoxTF_onMouseClick(MouseEvent event) {
        drugItemSearchBoxTF.selectAll();
    }

    @FXML
    public void drugItemTbl_OnMouseClick(MouseEvent event) {
        Doctor_DrugPackManagement_DrugTM selectedItem = drugItemTbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            drugCodeTF.setText(selectedItem.getDrugCode());
            drugNameTF.setText(selectedItem.getDrugName());
        }
        if (!drugCodeTF.getText().isEmpty()) {
            drugItemAddBtn.setDisable(false);
        } else {
            drugItemAddBtn.setDisable(true);
        }
    }

    @FXML
    public void myPacksSearchBoxTF_onMouseClick(MouseEvent event) {
        myPacksSearchBoxTF.selectAll();
    }

    @FXML
    public void myPacksSearchBoxTF_keyReleased(KeyEvent event) {
        if (myPacksSearchBoxTF.getText().isEmpty()) {
            loadMyPacksTbl();
        } else {
            try {
                ArrayList<DrugPackDTO> drugPackDTOS = (ArrayList<DrugPackDTO>) doctorDrugPackService.searchDrugPacks(myEmployeeID, myPacksSearchBoxTF.getText());

                if (drugPackDTOS.isEmpty()) {
                    myPacksSearchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadMyPacksTbl();
                } else {
                    myPacksSearchBoxTF.setStyle("-fx-text-fill: #000000");

                    doctorDrugPackManagementDrugPackTMs = myPacksTbl.getItems();
                    doctorDrugPackManagementDrugPackTMs.removeAll(doctorDrugPackManagementDrugPackTMs);

                    if (!drugPackDTOS.isEmpty()) {
                        for (DrugPackDTO packDTO : drugPackDTOS) {
                            doctorDrugPackManagementDrugPackTMs.add(new Doctor_DrugPackManagement_DrugPackTM(
                                    packDTO.getDrugPackCode(),
                                    packDTO.getName()
                            ));
                        }
                        myPacksTbl.refresh();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void myPacksTbl_OnMouseClick(MouseEvent event) {
        try {
            Doctor_DrugPackManagement_DrugPackTM selectedItem = myPacksTbl.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                DrugPackDTO drugPackDTO = doctorDrugPackService.getDrugPack(selectedItem.getDrugPackCode());
                packCodeTF.setText(drugPackDTO.getDrugPackCode());
                packNameTF.setText(drugPackDTO.getName());

                doctorDrugManagementDrugPackDetailTMs = packDetailsTbl.getItems();
                doctorDrugManagementDrugPackDetailTMs.removeAll(doctorDrugManagementDrugPackDetailTMs);

                Pharm_DrugService pharmDrugService = (Pharm_DrugService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PHARM_DRUG);

                for (DrugPackDetailsDTO packDetailsDTO : drugPackDTO.getDrugPackDetails()) {
                    doctorDrugManagementDrugPackDetailTMs.add(new Doctor_DrugPackManagement_DrugPackDetailTM(
                            packDetailsDTO.getDrugCode(),
                            pharmDrugService.getDrug(packDetailsDTO.getDrugCode()).getName()
                    ));
                }
                packDetailsTbl.refresh();
            }
        } catch (Exception e) {

        }
    }

    @FXML
    public void newPackBtn_onAction(ActionEvent event) {
        doctorDrugManagementDrugPackDetailTMs = packDetailsTbl.getItems();
        doctorDrugManagementDrugPackDetailTMs.removeAll(doctorDrugManagementDrugPackDetailTMs);
        generateNextDrugPackCode();
        packNameTF.setText("");
        myPacksSearchBoxTF.setText("");
        loadMyPacksTbl();
    }

    @FXML
    public void packDetailsTbl_OnMouseClick(MouseEvent event) {
        // Do nothing
    }

    @FXML
    public void savePackBtn_onAction(ActionEvent event) {
        doctorDrugPackManagementDrugPackTMs = myPacksTbl.getItems();
        boolean isExist = false;

        for (Doctor_DrugPackManagement_DrugPackTM drugPackTM : doctorDrugPackManagementDrugPackTMs) {
            if ((drugPackTM.getDrugPackCode().equals(packCodeTF.getText())) | (drugPackTM.getDrugPackName().equals(packNameTF.getText()))) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            try {
                doctorDrugManagementDrugPackDetailTMs = packDetailsTbl.getItems();
                List<DrugPackDetailsDTO> drugPackDetailsDTOS = new ArrayList<>();

                for (Doctor_DrugPackManagement_DrugPackDetailTM drugPackDetailTM : doctorDrugManagementDrugPackDetailTMs) {
                    drugPackDetailsDTOS.add(new DrugPackDetailsDTO(
                            packCodeTF.getText(),
                            drugPackDetailTM.getDrugCode()
                    ));
                }

                DrugPackDTO drugPackDTO = new DrugPackDTO(
                        packCodeTF.getText(),
                        packNameTF.getText(),
                        this.myEmployeeID,
                        drugPackDetailsDTOS
                );

                boolean result = doctorDrugPackService.saveDrugPack(drugPackDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Your Drug Pack has been Saved Successfully.", ButtonType.OK).show();
                    newPackBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save Your Drug Pack.", ButtonType.OK).show();
                }
                myPacksTbl.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Sorry..., The Drug Pack you are Going to Save is Already in My Drug Pack Table.", ButtonType.OK).show();
        }
    }

    @FXML
    public void deletePackBtn_onAction(ActionEvent event) {
        try {
            Doctor_DrugPackManagement_DrugPackTM selectedItem = myPacksTbl.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                boolean result = doctorDrugPackService.deleteDrugPack(selectedItem.getDrugPackCode());
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Your Drug Pack has been Deleted Successfully.", ButtonType.OK).show();
                    newPackBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Delete Your Drug Pack.", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Select an Existing Pack from My Packs Table to Delete.", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updatePackBtn_onAction(ActionEvent event) {
        doctorDrugPackManagementDrugPackTMs = myPacksTbl.getItems();
        boolean isExist = false;

        for (Doctor_DrugPackManagement_DrugPackTM drugPackTM : doctorDrugPackManagementDrugPackTMs) {
            if ((drugPackTM.getDrugPackCode().equals(packCodeTF.getText())) | (drugPackTM.getDrugPackName().equals(packNameTF.getText()))) {
                isExist = true;
                break;
            }
        }

        if (isExist) {
            try {
                doctorDrugManagementDrugPackDetailTMs = packDetailsTbl.getItems();
                List<DrugPackDetailsDTO> drugPackDetailsDTOS = new ArrayList<>();

                for (Doctor_DrugPackManagement_DrugPackDetailTM drugPackDetailTM : doctorDrugManagementDrugPackDetailTMs) {
                    drugPackDetailsDTOS.add(new DrugPackDetailsDTO(
                            packCodeTF.getText(),
                            drugPackDetailTM.getDrugCode()
                    ));
                }

                DrugPackDTO drugPackDTO = new DrugPackDTO(
                        packCodeTF.getText(),
                        packNameTF.getText(),
                        this.myEmployeeID,
                        drugPackDetailsDTOS
                );

                boolean result = doctorDrugPackService.updateDrugPack(drugPackDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Your Drug Pack has been Updated Successfully.",
                            ButtonType.OK).show();
                    newPackBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "Failed to Update Your Drug Pack.",
                            ButtonType.OK).show();
                }
                myPacksTbl.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Please Select an Existing Drug Pack from My Drug Pack Table to Update.",
                    ButtonType.OK).show();
        }
    }

}
