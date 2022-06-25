package lk.ijse.dcs.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {

    @FXML
     private AnchorPane loadingAnchPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();
        applyFadeTransition(loadingAnchPane, Duration.seconds(2), (evt) -> {
            try {
                Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/logInAll.fxml")));
                Stage stage = (Stage) this.loadingAnchPane.getScene().getWindow();
                stage.setTitle("New Philip Hospitals - Doctor Channelling System - Log In");
                stage.setScene(temp);
                stage.centerOnScreen();
                stage.show();

                TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
                trans.setFromY(-temp.getHeight());
                trans.setToY(0);
                trans.play();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void applyFadeTransition(Node node, Duration duration, EventHandler<ActionEvent> event) {
        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(duration, node);
        fadeIn.setCycleCount(1);
        fadeIn.setFromValue(0.4);
        fadeIn.setToValue(1);
        fadeIn.setAutoReverse(true);
        fadeIn.setOnFinished(event);

        javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(duration, node);
        fadeOut.setCycleCount(1);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0.6);
        fadeOut.setAutoReverse(true);

        fadeOut.play();
        fadeOut.setOnFinished((evt) -> {
            fadeIn.play();
        });
    }

}
