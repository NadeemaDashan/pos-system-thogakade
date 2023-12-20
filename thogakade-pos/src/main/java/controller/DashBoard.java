package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashBoard {

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnItem;

    @FXML
    private AnchorPane pane;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        Stage stage= (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        Stage stage=(Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
