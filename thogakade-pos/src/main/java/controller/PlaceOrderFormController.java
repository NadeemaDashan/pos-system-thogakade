package controller;

import bo.BoFactory;
import bo.CustomerBo;
import bo.ItemBo;
import bo.PLaceOrderBo;
import bo.impl.CustomerBoImpl;
import bo.impl.ItemBoImpl;
import bo.impl.PlaceOrderBoImpl;
import dao.custom.util.BoType;
import dto.CustomerDto;
import dto.ItemDto;

import dto.OrderDetailsDto;
import dto.OrderDto;
import dto.tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.custom.OrderDetailsDao;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDetailsDaoImpl;
import dao.custom.impl.OrderDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private OrderDao orderDao = new OrderDaoImpl();

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    PLaceOrderBo pLaceOrderBo = BoFactory.getInstance().getBo(BoType.ORDER_DETAILS);
    private ItemBo<ItemDto> itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    private OrderDetailsDao orderDetailsDao = new OrderDetailsDaoImpl();
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
            double amou=0;
            for (OrderTm ord :observableList) {
                double amount=ord.getAmount();
                amou+=amount;
            }
            lblTotal.setText("TOTAL : "+amou);
        });
        observableList.add(orderTm);
        tblCart.setItems(observableList);
        double amou=0;
        for (OrderTm ord :observableList) {
            double amount=ord.getAmount();
            amou+=amount;
        }
        lblTotal.setText("TOTAL : "+amou);
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
    void btnPlaceOrderActionPerformed(ActionEvent event)  {
        List<OrderDetailsDto> list=orderDetailsCreate();
        Boolean isSaved=pLaceOrderBo.saveOrderDetails(list);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Order details saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wron try again...").show();
            }
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
        OrderDto orderDto = orderDao.lastOrder();
        if (orderDto!=null){
            String id=orderDto.getOrderId();
            int num=Integer.parseInt(id.split("D")[1]);
            num++;
            lblOrderId.setText(String.format("D04%d",num));
        }
    }

    private void loadCustomerId() {
        try {
            customerList= customerBo.allCustomers();
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
            itemList= itemBo.allItems();
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

    public List<OrderDetailsDto> orderDetailsCreate() {
        List<OrderDetailsDto> orderDetailsList = null;
        for (OrderTm order : observableList) {
            double unitPrice = order.getAmount() / order.getQty();
            OrderDetailsDto orderDetailsDto = new OrderDetailsDto(
                    lblOrderId.getText(),
                    order.getCode(),
                    order.getQty(),
                    unitPrice
            );
            orderDetailsList = new ArrayList<>();
            orderDetailsList.add(orderDetailsDto);
        }
        return orderDetailsList;
    }

}
