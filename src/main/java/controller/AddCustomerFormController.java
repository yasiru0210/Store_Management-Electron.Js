package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    public ComboBox<String> cmbTitle;
    public DatePicker dateDOB;
    public TableView tblCustomers;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colDOB;
    public TableColumn colName;
    public TableColumn colSalary;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (validateInput()) {
                String id = txtId.getText();
                String title = cmbTitle.getValue();
                String name = txtName.getText();
                double salary = Double.parseDouble(txtSalary.getText());
                LocalDate localDate = dateDOB.getValue();
                java.sql.Date dob = java.sql.Date.valueOf(localDate);

                String address = txtAddress.getText();
                String postalCode = txtPostalCode.getText();
                String city = txtCity.getText();
                String province = txtProvince.getText();

                // Create the customer object
                Customer customer = new Customer(id, title, name, dob, salary, address, city, province, postalCode);


                try {
                    String SQL = "INSERT INTO CUSTOMER (CustID, CustTitle, CustName, DOB, salary, CustAddress, City, Province, PostalCode) VALUES (?,?,?,?,?,?,?,?,?)";
                    Connection connection1 =DbConnection.getInstance().getConnection();
                    PreparedStatement prstm1 = connection1.prepareStatement(SQL);

                    prstm1.setObject(1, customer.getId());
                    prstm1.setObject(2, customer.getTitle());
                    prstm1.setObject(3, customer.getName());
                    prstm1.setObject(4, customer.getDob());
                    prstm1.setObject(5, customer.getSalary());
                    prstm1.setObject(6, customer.getAddress());
                    prstm1.setObject(7, customer.getCity());
                    prstm1.setObject(8, customer.getProvince());
                    prstm1.setObject(9, customer.getPostalcode());

                    boolean isAdded = prstm1.executeUpdate() > 0;

                    if (isAdded) {
                        new Alert(Alert.AlertType.INFORMATION, "Customer Added Successfully!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Add Customer").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
                }

                clearText();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid salary value").show();
        }
    }

    private boolean validateInput() {
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtSalary.getText().isEmpty() ||
                txtAddress.getText().isEmpty() || txtCity.getText().isEmpty() || txtPostalCode.getText().isEmpty() ||
                txtProvince.getText().isEmpty() || dateDOB.getValue() == null || cmbTitle.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields").show();
            return false;
        }
        return true;
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearText();
    }

    private void clearText() {
        cmbTitle.setValue(null);
        txtId.clear();
        txtCity.clear();
        dateDOB.setValue(null);
        txtSalary.clear();
        txtAddress.clear();
        txtPostalCode.clear();
        txtProvince.clear();
        txtName.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titleList = FXCollections.observableArrayList("Mr.", "Mrs.", "Miss.");
        cmbTitle.setItems(titleList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        loadTable();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            String SQL="delete from customer where CustID='"+txtId.getText()+"'";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(SQL);
            boolean b = prst.executeUpdate() > 0;
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Customer").show();
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private void loadTable(){

        ObservableList<Customer> list = FXCollections.observableArrayList();

        try {
            String SQL="SELECT * FROM CUSTOMER";
            Connection connection1 =DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection1.prepareStatement(SQL);
            ResultSet resultSet = prst.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                System.out.println(customer);
                list.add(customer);
                tblCustomers.setItems(list);

            }

            System.out.println(connection1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        loadTable();
    }
}
