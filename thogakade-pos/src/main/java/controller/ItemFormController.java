package controller;

import dto.ItemDto;
import dto.tm.ItemTm;
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
    void btnDeleteActionPerformed(ActionEvent event) {

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

    }
    public void initialize() throws SQLException, ClassNotFoundException {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        coUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadTable();
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
}
