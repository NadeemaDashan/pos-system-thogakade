package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderFormController {

    @FXML
    private ComboBox<?> cmbCusId;

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblTotal;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblOrderId;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddToCartActionPerformed(ActionEvent event) {

    }

    @FXML
    void btnBackActionPerformed(ActionEvent event) throws IOException {
        Stage stage=(Stage)pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("DashBoard");
        stage.show();
    }

    @FXML
    void btnPlaceOrderActionPerformed(ActionEvent event) {

    }

}
