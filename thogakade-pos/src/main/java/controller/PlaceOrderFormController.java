package controller;

import dto.tm.OrderTm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PlaceOrderFormController {

    @FXML
    private ComboBox<?> cmbCusId;

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private TableColumn colAmount;

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colOption;

    @FXML
    private TableColumn colQty;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<OrderTm> tblCart;

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
    void btnPlaceOrderActionPerformed(ActionEvent event) {

    }

}
