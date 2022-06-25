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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.dto.PatientDTO;
import lk.ijse.dcs.dto.PaymentDTO;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Cashier_PaymentService;
import lk.ijse.dcs.service.custom.Recep_PatientService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Cashier_PaymentManagementController implements Initializable {

    @FXML
    private AnchorPane cashier_paymentManagementAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private TextField patientIDTF;
    @FXML
    private JFXButton searchPatientBtn;
    @FXML
    private TextField appointmentNOTF;
    @FXML
    private TextField patientNameTF;
    @FXML
    private Pane cardPaymentPane;
    @FXML
    private JFXRadioButton cardPane_radioBtn;
    @FXML
    private ToggleGroup paymentRBtnGroup;
    @FXML
    private TextField cardPane_paymentNOTF;
    @FXML
    private TextField cardPane_paymentTotalTF;
    @FXML
    private TextField cardPane_discountTF;
    @FXML
    private TextField cardPane_paybleTotalTF;
    @FXML
    private JFXComboBox<String> cardPane_cardTypeComboBox;
    @FXML
    private JFXDatePicker cardPane_expireDateDP;
    @FXML
    private JFXTextField cardPane_cardNOTF;
    @FXML
    private JFXButton cardPane_printBillBtn;
    @FXML
    private JFXButton cardPane_clearBtn;
    @FXML
    private Pane cashPaymentPane;
    @FXML
    private TextField cashPane_paymentNOTF;
    @FXML
    private TextField cashPane_paymentTotalTF;
    @FXML
    private JFXButton cashPane_printBillBtn;
    @FXML
    private JFXButton cashPane_clearBtn;
    @FXML
    private JFXRadioButton cashPane_radioBtn;
    @FXML
    private TextField cashPane_discountTF;
    @FXML
    private TextField cashPane_paybleTotalTF;
    @FXML
    private TextField cashPane_cashPaymentTF;
    @FXML
    private TextField cashPane_cashBalanceTF;
    @FXML
    private JFXButton doneBtn;
    @FXML
    private JFXButton confirmBtn;
    @FXML
    private JFXButton cancelBtn;

    private Cashier_PaymentService cashierPaymentService;

    private String myEmployeeID = null;

    private String searchedPatientID = null;

    private Integer printCashBill = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            cashierPaymentService =
                    (Cashier_PaymentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER_PAYMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.myEmployeeID = LogInAllController.loggedEmployee.getEmployeeID();

        this.printCashBill = 0;

        loadCardTypeComboBox();

        confirmBtn.setDisable(true);

        cashPaymentPane.setDisable(true);
        cardPaymentPane.setDisable(true);
        doneBtn.setDisable(true);

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
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/cashierMainDash.fxml")));
            Stage stage = (Stage) this.cashier_paymentManagementAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Cashier's Main DashBoard");
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

    public void loadCardTypeComboBox() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Visa Card");
        types.add("Master Card");
        cardPane_cardTypeComboBox.setItems(types);
    }

    @FXML
    public void cancelBtn_onAction(ActionEvent event) {
        printCashBill = 0;
        cashPaymentPane.setDisable(false);
        cardPaymentPane.setDisable(false);
        cashPane_radioBtn.fire();
        cashPaymentPane.setDisable(false);
        cardPaymentPane.setDisable(false);
        cardPane_radioBtn.fire();
        cashPaymentPane.setDisable(false);
        cardPaymentPane.setDisable(false);
        cashPane_clearBtn.fire();
        cardPane_clearBtn.fire();
        cashPane_paymentNOTF.setText("");
        cardPane_paymentNOTF.setText("");
        cashPane_paymentTotalTF.setText("");
        cardPane_paymentTotalTF.setText("");
        cashPane_paybleTotalTF.setText("");
        cardPane_paybleTotalTF.setText("");
        cashPane_discountTF.setEditable(true);
        cashPane_discountTF.setMouseTransparent(false);
        cashPane_cashPaymentTF.setEditable(true);
        cashPane_cashPaymentTF.setMouseTransparent(false);
        cashPane_radioBtn.setSelected(false);
        cardPane_radioBtn.setSelected(false);
        doneBtn.setDisable(true);
        patientIDTF.setDisable(false);
        searchPatientBtn.setDisable(false);
        patientNameTF.setText("");
        confirmBtn.setDisable(true);
        appointmentNOTF.setText("");
        patientIDTF.requestFocus();
        cashPaymentPane.setDisable(true);
        cardPaymentPane.setDisable(true);
    }

    @FXML
    public void cardPane_cardTypeComboBox_onAction(ActionEvent event) {
        // Do Nothing
    }

    @FXML
    public void cardPane_clearBtn_onAction(ActionEvent event) {
        cardPane_discountTF.setText("0.0");
        cardPane_cardTypeComboBox.setValue("");
        cardPane_expireDateDP.setValue(null);
        cardPane_cardNOTF.setText("");
    }

    @FXML
    public void cardPane_discountTF_onKeyReleased(KeyEvent event) {
        if (!cardPane_discountTF.getText().isEmpty()) {
            if (Double.parseDouble(cardPane_discountTF.getText()) < 100) {
                Double dis =
                        Double.parseDouble(cardPane_paymentTotalTF.getText()) * Double.parseDouble(cardPane_discountTF.getText()) / 100;
                Double payble = Double.parseDouble(cardPane_paymentTotalTF.getText()) - dis;
                cardPane_paybleTotalTF.setText(Double.toString(payble));
            } else {
                cardPane_discountTF.setText("0.0");
                Double dis =
                        Double.parseDouble(cardPane_paymentTotalTF.getText()) * Double.parseDouble(cardPane_discountTF.getText());
                Double payble = Double.parseDouble(cardPane_paymentTotalTF.getText()) - dis;
                cardPane_paybleTotalTF.setText(Double.toString(payble));
            }
        }
    }

    @FXML
    public void cardPane_expireDateDP_onAction(ActionEvent event) {
        cardPane_cardNOTF.requestFocus();
    }

    @FXML
    public void cardPane_printBillBtn_onAction(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION,
                "Ooops Sorry.., Card Payment Feature isn't Available. We are Still Working on it...",
                ButtonType.OK).show();
        cashPane_radioBtn.fire();
    }

    @FXML
    public void cardPane_radioBtn_onAction(ActionEvent event) {
        cashPane_clearBtn.fire();

        cashPane_paymentNOTF.setDisable(true);
        cashPane_paymentTotalTF.setDisable(true);
        cashPane_paybleTotalTF.setDisable(true);
        cashPane_discountTF.setDisable(true);
        cashPane_cashPaymentTF.setDisable(true);
        cashPane_clearBtn.setDisable(true);
        cashPane_printBillBtn.setDisable(true);

        cardPane_paymentNOTF.setDisable(false);
        cardPane_paymentTotalTF.setDisable(false);
        cardPane_paybleTotalTF.setDisable(false);
        cardPane_discountTF.setDisable(false);
        cardPane_cardTypeComboBox.setDisable(false);
        cardPane_expireDateDP.setDisable(false);
        cardPane_cardNOTF.setDisable(false);
        cardPane_clearBtn.setDisable(false);
        cardPane_printBillBtn.setDisable(false);

        cardPane_clearBtn.fire();

        cardPane_discountTF.requestFocus();

        // cashPane_radioBtn.fire();

    }

    @FXML
    public void cashPane_cashPaymentTF_onKeyReleased(KeyEvent event) {
        if (!cashPane_cashPaymentTF.getText().isEmpty()) {
            if (Double.parseDouble(cashPane_cashPaymentTF.getText()) >= Double.parseDouble(cashPane_paybleTotalTF.getText())) {
                cashPane_cashBalanceTF.setText(Double.toString(Double.parseDouble(cashPane_cashPaymentTF.getText()) - Double.parseDouble(cashPane_paybleTotalTF.getText())));
            } else {
                cashPane_cashBalanceTF.setText("0.00");
            }
        }
    }

    @FXML
    public void cashPane_clearBtn_onAction(ActionEvent event) {
        cashPane_discountTF.setText("0.0");
        cashPane_paybleTotalTF.setText(cashPane_paymentTotalTF.getText());
        cashPane_cashPaymentTF.setText("0.00");
        cashPane_cashBalanceTF.setText("0.00");
    }

    @FXML
    public void cashPane_discountTF_onKeyReleased(KeyEvent event) {
        if (!cashPane_discountTF.getText().isEmpty()) {
            if (Double.parseDouble(cashPane_discountTF.getText()) < 100) {
                Double dis =
                        Double.parseDouble(cashPane_paymentTotalTF.getText()) * Double.parseDouble(cashPane_discountTF.getText()) / 100;
                Double payble = Double.parseDouble(cashPane_paymentTotalTF.getText()) - dis;
                cashPane_paybleTotalTF.setText(Double.toString(payble));
            } else {
                cashPane_discountTF.setText("0.0");
                Double dis =
                        Double.parseDouble(cashPane_paymentTotalTF.getText()) * Double.parseDouble(cashPane_discountTF.getText());
                Double payble = Double.parseDouble(cashPane_paymentTotalTF.getText()) - dis;
                cashPane_paybleTotalTF.setText(Double.toString(payble));
            }
        }
    }

    @FXML
    public void cashPane_printBillBtn_onAction(ActionEvent event) {
        if (printCashBill == 0) {
            new Alert(Alert.AlertType.INFORMATION,
                    "Bill is Ready to Print. \n Please Click Done Button to execute Payment Transaction and Print Bill. ",
                    ButtonType.OK).show();
            printCashBill = 1;
            doneBtn.setDisable(false);
            cashPane_discountTF.setEditable(false);
            cashPane_discountTF.setMouseTransparent(true);
            cashPane_cashPaymentTF.setEditable(false);
            cashPane_cashPaymentTF.setMouseTransparent(true);
            cashPane_clearBtn.setDisable(true);
        } else {
            new Alert(Alert.AlertType.INFORMATION,
                    "Bill is Ready to Print Already.\n Please Click Done Button to execute Payment Transaction and Print Bill.",
                    ButtonType.OK).show();
        }
    }

    @FXML
    public void cashPane_radioBtn_onAction(ActionEvent event) {
        cardPane_clearBtn.fire();

        cashPane_paymentNOTF.setDisable(false);
        cashPane_paymentTotalTF.setDisable(false);
        cashPane_paybleTotalTF.setDisable(false);
        cashPane_discountTF.setDisable(false);
        cashPane_cashPaymentTF.setDisable(false);
        cashPane_clearBtn.setDisable(false);
        cashPane_printBillBtn.setDisable(false);

        cashPane_clearBtn.fire();

        cardPane_paymentNOTF.setDisable(true);
        cardPane_paymentTotalTF.setDisable(true);
        cardPane_paybleTotalTF.setDisable(true);
        cardPane_discountTF.setDisable(true);
        cardPane_cardTypeComboBox.setDisable(true);
        cardPane_expireDateDP.setDisable(true);
        cardPane_cardNOTF.setDisable(true);
        cardPane_clearBtn.setDisable(true);
        cardPane_printBillBtn.setDisable(true);

        cashPane_discountTF.requestFocus();
    }

    @FXML
    public void doneBtn_onAction(ActionEvent event) {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(
                    cashPane_paymentNOTF.getText(),
                    "Cash",
                    Double.parseDouble(cashPane_paymentTotalTF.getText()),
                    Double.parseDouble(cashPane_discountTF.getText()),
                    Double.parseDouble(cashPane_paybleTotalTF.getText()),
                    appointmentNOTF.getText()
            );

            System.out.println(paymentDTO);

            boolean result = cashierPaymentService.savePayment(this.myEmployeeID, paymentDTO);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Payment has been saved Successfully.",
                        ButtonType.OK).show();
                cancelBtn.fire();
            } else {
                new Alert(Alert.AlertType.ERROR,
                        "failed to Save the Payment.",
                        ButtonType.OK).show();
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
                this.searchedPatientID = searchedPatient.getPatientID();
                patientNameTF.setText(searchedPatient.getName());
                confirmBtn.setDisable(false);
            } else {
                new Alert(Alert.AlertType.ERROR, "Please insert a valid existing patientID.", ButtonType.OK).show();
                patientIDTF.setText("");
                patientIDTF.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void confirmBtn_onAction(ActionEvent event) {
        try {
            String appointmentNO = cashierPaymentService.getAppointmentNO(this.searchedPatientID, LocalDate.now().toString());

            if (!appointmentNO.equals("empty")) {
                appointmentNOTF.setText(appointmentNO);
                patientIDTF.setDisable(true);
                searchPatientBtn.setDisable(true);
                confirmBtn.setDisable(true);
                cashPaymentPane.setDisable(false);
                cardPaymentPane.setDisable(false);
                cashPane_radioBtn.fire();
                generatePaymentNO();
                setPaymentTotalAndPaybleTotal();
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry..., There isn't a Appointment Reservation to this Patient on Today.", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generatePaymentNO() {
        try {
            String paymentNO = cashierPaymentService.generatePaymentNO();
            cashPane_paymentNOTF.setText(paymentNO);
            cardPane_paymentNOTF.setDisable(false);
            cardPane_paymentNOTF.setText(paymentNO);
            cardPane_paymentNOTF.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPaymentTotalAndPaybleTotal() {
        try {
            String paymentTotal = cashierPaymentService.getPaymentTotal(appointmentNOTF.getText());
            cashPane_paymentTotalTF.setText(paymentTotal);
            cashPane_paybleTotalTF.setText(paymentTotal);
            cardPane_paymentTotalTF.setDisable(false);
            cardPane_paybleTotalTF.setDisable(false);
            cardPane_paymentTotalTF.setText(paymentTotal);
            cardPane_paybleTotalTF.setText(paymentTotal);
            cardPane_paybleTotalTF.setDisable(true);
            cardPane_paymentTotalTF.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
