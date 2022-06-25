package lk.ijse.dcs.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Cashier_PaymentHistoryController implements Initializable {

    @FXML
    private AnchorPane cashier_paymentHistoryAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private TextField cashPaymentSearchBoxTF;
    @FXML
    private TextField cardPaymentSearchBoxTF;
    @FXML
    private TableView<?> cardPaymentHistoryTbl;
    @FXML
    private TableView<?> cashPaymentHistoryTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();
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
            Stage stage = (Stage) this.cashier_paymentHistoryAnchPane.getScene().getWindow();
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

    @FXML
    public void cardPaymentSearchBoxTF_keyReleased(KeyEvent event) {

    }

    @FXML
    public void cardPaymentSearchBoxTF_onMouseClick(MouseEvent event) {

    }

    @FXML
    public void cashPaymentSearchBoxTF_keyReleased(KeyEvent event) {

    }

    @FXML
    public void cashPaymentSearchBoxTF_onMouseClick(MouseEvent event) {

    }
    
}
