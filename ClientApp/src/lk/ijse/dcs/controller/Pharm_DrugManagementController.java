package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.dcs.dto.DrugDTO;
import lk.ijse.dcs.observer.Observer;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Pharm_DrugService;
import lk.ijse.dcs.view.util.tblModel.Pharm_DrugManagementTM;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Pharm_DrugManagementController implements Initializable, Observer {

    @FXML
    private AnchorPane pharm_drugManagementAnchPane;
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
    private JFXTextArea descriptionTA;
    @FXML
    private JFXTextField brandTF;
    @FXML
    private JFXTextField unitPriceTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<Pharm_DrugManagementTM> drugManagementTbl;
    @FXML
    private JFXTextField drugCodeTF;
    @FXML
    private JFXTextField nameTF;

    private static Pharm_DrugService pharmDrugService;
    private static Observer observer;
    private static String reservedDrugCode;

    private ObservableList<Pharm_DrugManagementTM> pharmDrugManagementTMS;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            pharmDrugService = (Pharm_DrugService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PHARM_DRUG);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            UnicastRemoteObject.exportObject(this, 0);
            observer = this;
            pharmDrugService.register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        reservedDrugCode = "emptyDrugCode";

        drugManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        drugManagementTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        drugManagementTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        drugManagementTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        drugManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        drugManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        drugManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        drugManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        generateNextDrugCode();

        loadDrugManagementTbl();

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
            Stage stage = (Stage) this.pharm_drugManagementAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Pharmacist's Main DashBoard");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
            trans.setFromX(-temp.getHeight());
            trans.setToX(0);
            trans.play();

            onClose();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateNextDrugCode() {
        try {
            String nextDrugCode = pharmDrugService.generateNextDrugCode();
            drugCodeTF.setText(nextDrugCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDrugManagementTbl() {
        try {
            pharmDrugManagementTMS = drugManagementTbl.getItems();
            pharmDrugManagementTMS.removeAll(pharmDrugManagementTMS);

            ArrayList<DrugDTO> drugDTOS = (ArrayList<DrugDTO>) pharmDrugService.getAllDrugs();

            for (DrugDTO drugDTO : drugDTOS) {
                pharmDrugManagementTMS.add(new Pharm_DrugManagementTM(
                        drugDTO.getDrugCode(),
                        drugDTO.getName(),
                        drugDTO.getDescription(),
                        Double.toString(drugDTO.getUnitPrice())
                ));
            }
            drugManagementTbl.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void brandTF_onAction(ActionEvent event) {
        descriptionTA.requestFocus();
    }

    @FXML
    public void deleteBtn_onAction(ActionEvent event) {
        try {
            pharmDrugManagementTMS = drugManagementTbl.getItems();

            boolean isExist = false;

            for (Pharm_DrugManagementTM drugManagementTM : pharmDrugManagementTMS) {
                if (drugManagementTM.getDrugCode().equals(drugCodeTF.getText())) {
                    isExist = true;
                    break;
                }
            }

            if (isExist) {
                boolean result = pharmDrugService.deleteDrug(observer, drugCodeTF.getText());

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Drug has been Deleted Successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "Failed to Delete the Drug.",
                            ButtonType.OK).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void drugManagementTbl_OnMouseClick(MouseEvent event) {
        try {
            Pharm_DrugManagementTM selectedItem = drugManagementTbl.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                if (pharmDrugService.reserve(selectedItem.getDrugCode())) {

                    if (!reservedDrugCode.equals(selectedItem.getDrugCode())) {
                        pharmDrugService.release(reservedDrugCode);
                        reservedDrugCode = selectedItem.getDrugCode();
                    }

                    DrugDTO drugDTO = pharmDrugService.getDrug(selectedItem.getDrugCode());

                    drugCodeTF.setText(drugDTO.getDrugCode());
                    nameTF.setText(drugDTO.getName());
                    brandTF.setText(drugDTO.getBrand());
                    descriptionTA.setText(drugDTO.getDescription());
                    unitPriceTF.setText(Double.toString(drugDTO.getUnitPrice()));
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "We are Sorry.., The Drug '"
                                    + selectedItem.getDrugCode() +
                                    "' is Reserved by Another Client.",
                            ButtonType.OK).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void nameTF_onAction(ActionEvent event) {
        brandTF.requestFocus();
    }

    @FXML
    public void newBtn_onAction(ActionEvent event) {
        drugCodeTF.setText("");
        nameTF.setText("");
        brandTF.setText("");
        descriptionTA.setText("");
        unitPriceTF.setText("");
        searchBoxTF.setText("");

        generateNextDrugCode();
        loadDrugManagementTbl();

        try {
            pharmDrugService.release(reservedDrugCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void saveBtn_onAction(ActionEvent event) {
        pharmDrugManagementTMS = drugManagementTbl.getItems();

        boolean isExist = false;

        for (Pharm_DrugManagementTM drugManagementTM : pharmDrugManagementTMS) {
            if (drugManagementTM.getDrugCode().equals(drugCodeTF.getText())) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            try {
                boolean result = pharmDrugService.saveDrug(observer, new DrugDTO(
                        drugCodeTF.getText(),
                        nameTF.getText(),
                        brandTF.getText(),
                        descriptionTA.getText(),
                        Double.parseDouble(unitPriceTF.getText())
                ));
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Drug has been Saved Successfully.",
                            ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR,
                            "Failed to Save the Drug.",
                            ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Sorry..., The Drug You are Going to Save is Already in Drug Table.",
                    ButtonType.OK).show();
        }
    }

    @FXML
    public void searchBoxTF_keyReleased(KeyEvent event) {
        if (searchBoxTF.getText().isEmpty()) {
            loadDrugManagementTbl();
        } else {
            try {
                ArrayList<DrugDTO> drugDTOS = (ArrayList<DrugDTO>) pharmDrugService.searchDrug(searchBoxTF.getText());

                if (drugDTOS.isEmpty()) {
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadDrugManagementTbl();
                } else {
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    pharmDrugManagementTMS = drugManagementTbl.getItems();
                    pharmDrugManagementTMS.removeAll(pharmDrugManagementTMS);

                    for (DrugDTO drugDTO : drugDTOS) {
                        pharmDrugManagementTMS.add(new Pharm_DrugManagementTM(
                                drugDTO.getDrugCode(),
                                drugDTO.getName(),
                                drugDTO.getDescription(),
                                Double.toString(drugDTO.getUnitPrice())
                        ));
                    }
                    drugManagementTbl.refresh();
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
    public void unitPriceTF_onAction(ActionEvent event) {
        newBtn.requestFocus();
    }

    @FXML
    public void updateBtn_onAction(ActionEvent event) {
        pharmDrugManagementTMS = drugManagementTbl.getItems();

        boolean isExist = false;

        for (Pharm_DrugManagementTM drugManagementTM : pharmDrugManagementTMS) {
            if (drugManagementTM.getDrugCode().equals(drugCodeTF.getText())) {
                isExist = true;
                break;
            }
        }

        if (isExist) {
            try {
                boolean result = pharmDrugService.updateDrug(observer, new DrugDTO(
                        drugCodeTF.getText(),
                        nameTF.getText(),
                        brandTF.getText(),
                        descriptionTA.getText(),
                        Double.parseDouble(unitPriceTF.getText())
                ));
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Drug has been Updated Successfully.", ButtonType.OK).show();
                    newBtn.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Update the Drug.", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Sorry..., Please Select an Existing Drug from Drug Table to Update", ButtonType.OK).show();
        }
    }

    public static void onClose() {
        try {
            if (observer != null) {
                pharmDrugService.unregister(observer);
            }

            if (reservedDrugCode != null) {
                pharmDrugService.release(reservedDrugCode);
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
                tableUpdateAlert.setContentText("The Drug '" + primaryKey + "' has been " + status + " by Another Client. " +
                        "\n Do You Want to Reload Drug Table Right Now?");
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
