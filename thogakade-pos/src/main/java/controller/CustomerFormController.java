package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import dto.CustomerDto;
import dto.tm.CustomerTm;

import java.sql.*;

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
    private TableView tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void reloadButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        initialize();
    }

    @FXML
    void saveButtonOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        CustomerDto customerDto = new CustomerDto(txtId.getText(),txtName.getText(),
                txtAddress.getText(),Double.valueOf(txtSalary.getText()));
        String sql = "INSERT INTO customer VALUES('"+ customerDto.getId()+"','"+ customerDto.getName()+"','"+ customerDto.getAddress()+"',"+ customerDto.getSalary()+")";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345678");
        Statement statement =connection.createStatement();
        int i=statement.executeUpdate(sql);
        if (i>0){
            System.out.println("Success");
        }else {
            System.out.println("Error");
        }
        connection.close();
    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {
        CustomerDto customerDto = new CustomerDto(txtId.getText(),txtName.getText(),
                txtAddress.getText(),Double.valueOf(txtSalary.getText()));
        String sql = "UPDATE customer SET id=";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int i= 0;
        try {
            i = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (i>0){
            System.out.println("Success");
        }else {
            System.out.println("Error");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize() throws SQLException, ClassNotFoundException {
//        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
//        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
//        try {
//            loadTable();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//            setData((CustomerTm) newValue);
//        });
    }

    private void setData(CustomerTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtAddress.setText(String.valueOf(newValue.getSalary()));
    }

    public void loadTable() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> tmList =FXCollections.observableArrayList();
        String sql = "SELECT *FROM customer";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345678");
        Statement statement =connection.createStatement();
        ResultSet resultSet =statement.executeQuery(sql);

        while (resultSet.next()){
            JFXButton btn = new JFXButton("Delete");
            CustomerTm customer = new CustomerTm();
            customer.setId(resultSet.getString(1));
            customer.setName(resultSet.getString(2));
            customer.setAddress(resultSet.getString(3));
            customer.setSalary(resultSet.getDouble(4));
            customer.setButton(btn);

            tmList.add(customer);
            btn.setOnAction(actionEvent -> {
                deleteCustomer(customer.getId());
            });
        }

        connection.close();
        tblCustomer.setItems(tmList);
    }

    private void deleteCustomer(String id) {
        String sql = "DELETE FROM customer WHERE id='"+id+"'";
        try {
            Class.forName("com.mysql.jdbc.cj.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","12345678");
            Statement statement=connection.createStatement();
            int i=statement.executeUpdate(sql);
            if (i>0){
                System.out.println("success");
            }else{
                System.out.println("error");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

