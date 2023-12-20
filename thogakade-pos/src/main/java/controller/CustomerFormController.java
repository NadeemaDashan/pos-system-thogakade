package controller;

import dto.CustomerDto;
import dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerModel;
import model.impl.CustomerModelImpl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colOption;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;
    private CustomerModel customerModel = new CustomerModelImpl();

    @FXML
    void reloadButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTable();
        txtId.setEditable(true);
    }

    @FXML
    void saveButtonOnAction(ActionEvent event) {
        try {
            boolean isSaved = customerModel.saveCustomer(new CustomerDto(txtId.getText(), txtName.getText(),
                    txtAddress.getText(), Double.parseDouble(txtSalary.getText())));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved").show();
                reload();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong try again !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {
        try {
            boolean isUpdated= customerModel.updateCustomer(new CustomerDto(txtId.getText(),txtName.getText(),
                    txtAddress.getText(),Double.parseDouble(txtSalary.getText())));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Info Updated!").show();
            }

        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry").show();
        } catch (SQLIntegrityConstraintViolationException ex) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {
            reload();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });

    }

    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            tblCustomer.refresh();
            txtId.setEditable(false);
            txtId.setText((newValue.getId()));
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
        }
        if (newValue==null){
            txtId.setEditable(true);
        }
    }

    public void loadTable() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
        List<CustomerDto> list = customerModel.allCustomers();
        for (CustomerDto customerDto : list) {
            Button btn = new Button("Delete");
            CustomerTm customerTm = new CustomerTm(customerDto.getId(),
                    customerDto.getName(),
                    customerDto.getAddress(),
                    customerDto.getSalary(),
                    btn);

            btn.setOnAction(ActionEvent -> {
                try {
                    customerModel.deleteCustomer(customerDto.getId());
                    reload();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            observableList.add(customerTm);
        }
        tblCustomer.setItems(observableList);
    }

    private void reload() throws SQLException, ClassNotFoundException {
        loadTable();
        txtName.clear();
        txtAddress.clear();
        txtId.clear();
        txtSalary.clear();
    }
}

