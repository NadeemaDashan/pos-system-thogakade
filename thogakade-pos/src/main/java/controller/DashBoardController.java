package controller;

import com.sun.tools.jconsole.JConsoleContext;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashBoardController {

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnPlaceOrder;

    public void initialize(){
        dateTime();
    }

    public void dateTime() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    @FXML
    void btnExitActionPerformed(ActionEvent event) {
        javafx.application.Platform.exit();
    }

    @FXML
    void btnCustomerActionPerformed(ActionEvent event) {
        Stage stage =(Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemsActionPerformed(ActionEvent event) {
        Stage stage=(Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"))));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPlaceOrderActionPerformed(ActionEvent event) {
        Stage stage=(Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PlaceOrderForm.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Place Order");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
