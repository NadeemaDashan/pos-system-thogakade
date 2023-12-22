package controller;

import bo.BoFactory;
import bo.ItemBo;
import bo.impl.ItemBoImpl;
import dao.custom.util.BoType;
import dto.ItemDto;
import dto.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {

    @FXML
    private TableColumn<?, ?> coUnitPrice;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private AnchorPane pane;

    private ItemBo<ItemDto> itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    @FXML
    void btnDeleteActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException {
    boolean isDeleted= itemBo.deleteItem(txtId.getText());
    if (isDeleted){
        new Alert(Alert.AlertType.INFORMATION,"Item deleted");
        refresh();
    }
    }

    @FXML
    void btnSaveActionPerformed(ActionEvent event) {
        ItemDto itemDto = new ItemDto(
                txtId.getText(),
                txtDesc.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        try {
            Boolean isSaved= itemBo.saveItem(itemDto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Saved").show();
                refresh();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong try again...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException {
        ItemDto itemDto = new ItemDto(
                txtId.getText(),
                txtDesc.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        boolean isUpdated= itemBo.updateItem(itemDto);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"ITEM UPDATED");
            refresh();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong try again...");
        }
    }
    public void initialize() throws SQLException, ClassNotFoundException {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        coUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadTable();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, itemTm, newValue) -> {
            setData(newValue);
        });

    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {
            tblItem.refresh();
            txtId.setEditable(false);
            txtId.setText(newValue.getCode());
            txtDesc.setText(newValue.getDesc());
            txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            txtQty.setText(String.valueOf(newValue.getQty()));
        }
        if (newValue==null){
            txtId.setEditable(true);
        }
    }

    public void loadTable() throws SQLException, ClassNotFoundException {
        List<ItemDto> dtoList = itemBo.allItems();
        ObservableList<ItemTm> observableList= FXCollections.observableArrayList();
        for (ItemDto dto:dtoList) {

            ItemTm itemTm = new ItemTm(
                    dto.getCode(),
                    dto.getDesc(),
                    dto.getUnitPrice(),
                    dto.getQty()
            );

            observableList.add(itemTm);
        }
        tblItem.setItems(observableList);
    }
    public void refresh() throws SQLException, ClassNotFoundException {
        txtQty.clear();
        txtId.clear();
        txtUnitPrice.clear();
        txtDesc.clear();
        loadTable();
    }

    @FXML
    void btnBackActionPerformed(ActionEvent event) {
        Stage stage=(Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"))));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
