package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Pharm_PreviousSendOutsController implements Initializable {

    @FXML
    private AnchorPane pharm_previousSendOutsAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private ImageView backToMainDashIcon;
    @FXML
    private TextField mySendOutsSearchBoxTF;
    @FXML
    private TableView<?> appointmentDrugDetailsTbl;
    @FXML
    private JFXTextArea appointmentOtherDescriptionTA;
    @FXML
    private JFXTextField patientIDTF;
    @FXML
    private JFXTextField appointmentNumberTF;
    @FXML
    private JFXTextField appointmentTimeTF;
    @FXML
    private JFXTextField appointmentDateTF;
    @FXML
    private JFXTextArea drugOtherDetailsTA;
    @FXML
    private TableView<?> mySendOutsTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();
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
            Stage stage = (Stage) this.pharm_previousSendOutsAnchPane.getScene().getWindow();
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
    public void mySendOutsSearchBoxTF_keyReleased(KeyEvent event) {

    }

    @FXML
    public void mySendOutsSearchBoxTF_onMouseClick(MouseEvent event) {

    }

    @FXML
    public void mySendOutsTbl_OnMouseClick(MouseEvent event) {

    }

}
