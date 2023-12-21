package controller;

import dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import model.ItemModel;
import model.impl.ItemModelImpl;

import java.sql.SQLException;

public class ItemFormController {

    @FXML
    private TreeTableColumn<?, ?> colCode;

    @FXML
    private TreeTableColumn<?, ?> colDesc;

    @FXML
    private TreeTableColumn<?, ?> colQty;

    @FXML
    private TreeTableColumn<?, ?> colUnitPrice;

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
    void btnUpdateActionPerformed(ActionEvent event) {

    }

}
