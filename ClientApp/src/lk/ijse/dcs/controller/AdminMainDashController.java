package lk.ijse.dcs.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dcs.proxy.ProxyHandler;
import lk.ijse.dcs.service.ServiceFactory;
import lk.ijse.dcs.service.custom.Window_SessionFactoryService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminMainDashController implements Initializable {

    @FXML
    private AnchorPane adminMainDashAnchPane;
    @FXML
    private Label mainTitle;
    @FXML
    private Label dateLbl;
    @FXML
    private Label timeLbl;
    @FXML
    private Label selectionTitleLbl;
    @FXML
    private Label selectionDescriptionLbl;
    @FXML
    private JFXHamburger mainDashHamburger;
    @FXML
    private JFXDrawer mainDashDrawer;
    @FXML
    private ImageView employeeIcon;
    @FXML
    private ImageView reportsIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        selectionTitleLbl.setText("Welcome to Admin's Main Dash.");
        selectionDescriptionLbl.setText("Please select one of above main operations to proceed...");

        Timeline dateNtime = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy  E");
            dateLbl.setText(LocalDate.now().format(formatter));
            timeLbl.setText(LocalTime.now().toString().substring(0, 8));
        }));

        dateNtime.setCycleCount(Animation.INDEFINITE);
        dateNtime.play();

        try {
            Pane drawerPane = FXMLLoader.load(getClass().getResource("/lk/ijse/dcs/view/mainDashDrawer.fxml"));
            mainDashDrawer.setSidePane(drawerPane);

            for (Node node : drawerPane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                        switch (node.getAccessibleText()) {

                            case "userHBox": {
                                try {
                                    loadUserHBoxAction();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;


                            case "notificationHBox": {
                                try {
                                    loadNotificationsHBoxAction();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;


                            case "viewMoreHBox": {
                                try {
                                    loadViewMoreHBoxAction();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;


                            case "logoutHBox":
                                loadLogoutHBoxAction();
                                break;


                            case "exitHBox":
                                loadExitHBoxAction();
                                break;


                            case "settingsHBox": {
                                try {
                                    loadSettingsHBoxAction();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;


                            case "helpHBox": {
                                try {
                                    loadHelpHBoxAction();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;


                            case "aboutHBox": {
                                try {
                                    loadAboutHBoxAction();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;
                        }
                    });
                }
            }

            HamburgerNextArrowBasicTransition hambtrans = new HamburgerNextArrowBasicTransition(mainDashHamburger);
            hambtrans.setRate(-1);
            mainDashHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (evt) -> {
                hambtrans.setRate(hambtrans.getRate() * -1);
                hambtrans.play();

                if (mainDashDrawer.isShown()) {
                    mainDashDrawer.close();
                } else {
                    mainDashDrawer.open();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void loadUserHBoxAction() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/userProfileWindow.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.setTitle("User Profile");
        stage.getIcons().add(new Image("lk/ijse/dcs/asset/newPhilipHospitalLogo.jpg"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
        trans.setFromY(-temp.getHeight());
        trans.setToY(0);
        trans.play();
    }

    private void loadNotificationsHBoxAction() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/notificationWindow.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.setTitle("Notifications");
        stage.getIcons().add(new Image("lk/ijse/dcs/asset/newPhilipHospitalLogo.jpg"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
        trans.setFromY(-temp.getHeight());
        trans.setToY(0);
        trans.play();
    }

    private void loadViewMoreHBoxAction() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/viewMoreWindow.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.setTitle("View More");
        stage.getIcons().add(new Image("lk/ijse/dcs/asset/newPhilipHospitalLogo.jpg"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
        trans.setFromY(-temp.getHeight());
        trans.setToY(0);
        trans.play();
    }

    private void loadLogoutHBoxAction() {

        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Logout?");
        logoutAlert.setHeaderText(null);
        logoutAlert.setContentText(" Are You Sure to Logout?");
        Optional<ButtonType> action = logoutAlert.showAndWait();

        if (action.get() == ButtonType.OK) {
            try {
                Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/logInAll.fxml"));
                Scene temp = new Scene(root);
                Stage stage = (Stage) this.adminMainDashAnchPane.getScene().getWindow();
                stage.setScene(temp);
                stage.centerOnScreen();


                TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
                trans.setFromY(-temp.getHeight());
                trans.setToY(0);
                trans.play();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadExitHBoxAction() {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit?");
        exitAlert.setHeaderText(null);
        exitAlert.setContentText(" Are You Sure to Exit?");
        Optional<ButtonType> action = exitAlert.showAndWait();

        if (action.get() == ButtonType.OK) {
            try {
                Window_SessionFactoryService windowSessionFactoryService=
                        (Window_SessionFactoryService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.SESSION_FACTORY);
                windowSessionFactoryService.closeSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }


    private void loadSettingsHBoxAction() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/settingsWindow.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.setTitle("Settings");
        stage.getIcons().add(new Image("lk/ijse/dcs/asset/newPhilipHospitalLogo.jpg"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
        trans.setFromY(-temp.getHeight());
        trans.setToY(0);
        trans.play();
    }

    private void loadHelpHBoxAction() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/helpWindow.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.setTitle("Help");
        stage.getIcons().add(new Image("lk/ijse/dcs/asset/newPhilipHospitalLogo.jpg"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
        trans.setFromY(-temp.getHeight());
        trans.setToY(0);
        trans.play();
    }

    private void loadAboutHBoxAction() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/aboutWindow.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.setTitle("About");
        stage.getIcons().add(new Image("lk/ijse/dcs/asset/newPhilipHospitalLogo.jpg"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
        trans.setFromY(-temp.getHeight());
        trans.setToY(0);
        trans.play();
    }

    @FXML
    public void adminMainDashIcon_onMouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "employeeIcon":
                    selectionTitleLbl.setText("Employee Management");
                    selectionDescriptionLbl.setText("Click to view, add, update & delete employees.");
                    break;
                case "reportsIcon":
                    selectionTitleLbl.setText("Reports");
                    selectionDescriptionLbl.setText("Click to view all report and details.");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.rgb(0, 153, 204));
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    @FXML
    public void adminMainDashIcon_onMouseExited(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();
        ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        icon.setEffect(null);
        selectionTitleLbl.setText("Welcome to Admin's Main Dash.");
        selectionDescriptionLbl.setText("Please select one of above main operations to proceed...");
    }

    @FXML
    public void employeeIcon_onMouseClicked(MouseEvent event) {
        loadAdminDashBoardOptions("admin_employeeManagement","Employee Management");
    }

    @FXML
    public void reportsIcon_onMouseClicked(MouseEvent event) {
//        loadAdminDashBoardOptions("admin_reports","Reports");
        new Alert(Alert.AlertType.INFORMATION,
                "We are Sorry!, This Function isn't Available Right Now.",
                ButtonType.OK).show();
    }

    public void loadAdminDashBoardOptions(String fxmlFileName, String title) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dcs/view/" + fxmlFileName + ".fxml"));
            Scene temp = new Scene(root);
            Stage stage = (Stage) this.adminMainDashAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("New Philip Hospitals - Doctor Channelling System >>> Admin's Main DashBoard >>>" + title);
            stage.setResizable(false);
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
            trans.setFromX(+temp.getHeight());
            trans.setToX(0);
            trans.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
