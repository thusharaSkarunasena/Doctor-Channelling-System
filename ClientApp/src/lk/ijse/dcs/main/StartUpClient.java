package lk.ijse.dcs.main;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.controller.Admin_EmployeeManagementController;
import lk.ijse.dcs.controller.Pharm_DrugManagementController;
import lk.ijse.dcs.controller.Recep_PatientManagementController;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Window_SessionFactoryService;

import java.util.Optional;

public class StartUpClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/loading.fxml"));
        Scene temp = new Scene(root);
        primaryStage.setScene(temp);
        primaryStage.getIcons().add(new Image("lk/ijse/dcs/asset/newPhilipHospitalLogo.jpg"));
        primaryStage.setTitle("New Philip Hospitals - Doctor Channelling System - Loading...");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
            exitAlert.setTitle("Exit?");
            exitAlert.setHeaderText(null);
            exitAlert.setContentText(" Are You Sure to Exit?");
            Optional<ButtonType> action = exitAlert.showAndWait();

            if (action.get() == ButtonType.OK) {
                try {
                    Window_SessionFactoryService windowSessionFactoryService= (Window_SessionFactoryService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.SESSION_FACTORY);
                    windowSessionFactoryService.closeSessionFactory();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Pharm_DrugManagementController.onClose();
                Recep_PatientManagementController.onClose();
                Admin_EmployeeManagementController.onClose();

                System.exit(0);
            }
        });

        FadeTransition trans = new FadeTransition(Duration.millis(500), root);
        trans.setFromValue(0.02);
        trans.setToValue(1);
        trans.play();

    }

}
