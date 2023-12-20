package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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


}
