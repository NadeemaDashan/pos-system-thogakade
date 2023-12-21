package controller;

import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDto;
import dto.tm.CustomerTm;
import dto.tm.OrderTm;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ItemModel;
import model.OrderModel;
import model.impl.CustomerModelImpl;
import model.impl.ItemModelImpl;
import model.impl.OrderModelImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    private AnchorPane pane;

    @FXML
    private Label lblOrderId;

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

    private OrderModel orderModel = new OrderModelImpl();

    private CustomerModel customerModel = new CustomerModelImpl();

    private ItemModel itemModel = new ItemModelImpl();
    private List<CustomerDto> customerList;
    private List<ItemDto> itemList;
    ObservableList<OrderTm> observableList=FXCollections.observableArrayList();

    @FXML
    void btnAddToCartActionPerformed(ActionEvent event) {
        int qty=Integer.parseInt(txtQty.getText());
        double price=Double.parseDouble(txtUnitPrice.getText());
        double totPrice=qty*price;
        Button btn = new Button("Delete");
        OrderTm orderTm = new OrderTm(
                String.valueOf(cmbItemCode.getValue()),
                txtDesc.getText(),
                qty,
                totPrice,
                btn
        );
        btn.setOnAction(ActionEvent -> {
            observableList.remove(orderTm);
        });
        observableList.add(orderTm);
        tblCart.setItems(observableList);
    }

    @FXML
    public void btnBackActionPerformed(ActionEvent event) throws IOException {
        Stage stage=(Stage)pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("DashBoard");
        stage.show();
    }

    @FXML
    void btnPlaceOrderActionPerformed(ActionEvent event) {
    //TODO place Order is complex and should be done later......
    }
    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


        generateId();
        loadCustomerId();
        loadItemCodes();
        customerItemAutoCompletion();

    }
    public void customerItemAutoCompletion(){
        cmbCusId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, id) -> {
            for (CustomerDto customerDto:customerList) {
                if (customerDto.getId().equals(id)){
                    txtCusName.setText(customerDto.getId());
                }
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, code) -> { //newValue is the item code
            for (ItemDto dto : itemList) {
                if (dto.getCode().equals(code)) {
                    txtDesc.setText(dto.getDesc());
                    txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
                }
            }
        });
    }
    public void generateId() throws SQLException, ClassNotFoundException {
        OrderDto orderDto =orderModel.lastOrder();
        if (orderDto!=null){
            String id=orderDto.getOrderId();
            int num=Integer.parseInt(id.split("D")[1]);
            num++;
            lblOrderId.setText(String.format("D04%d",num));
        }
    }

    private void loadCustomerId() {
        try {
            customerList=customerModel.allCustomers();
            ObservableList list = FXCollections.observableArrayList();
            for (CustomerDto dto : customerList) {
                list.add(dto.getId());
            }
            cmbCusId.setItems((ObservableList)list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadItemCodes() {
        try {
            itemList=itemModel.allItems();
            ObservableList list = FXCollections.observableArrayList();
            for (ItemDto dto : itemList) {
                list.add(dto.getCode());
            }
            cmbItemCode.setItems((ObservableList) list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
