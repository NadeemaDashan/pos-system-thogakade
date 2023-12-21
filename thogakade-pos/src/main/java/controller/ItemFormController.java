package controller;

import dto.ItemDto;
import dto.tm.CustomerTm;
import dto.tm.ItemTm;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import model.ItemModel;
import model.impl.ItemModelImpl;

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
    private ItemModel itemModel = new ItemModelImpl();

    @FXML
    void btnDeleteActionPerformed(ActionEvent event) throws SQLException, ClassNotFoundException {
    boolean isDeleted=itemModel.deleteItem(txtId.getText());
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
            Boolean isSaved=itemModel.saveItem(itemDto);
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
        boolean isUpdated=itemModel.updateItem(itemDto);
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
        List<ItemDto> dtoList = itemModel.allItems();
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
}
