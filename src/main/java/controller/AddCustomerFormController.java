package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import db.DbConnection;
import model.Customer;

import java.util.List;

public class AddCustomerFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtcontactNumber;

    @FXML
    private TextField txtname;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtname.getText();
        String address = txtAddress.getText();
        String contactnumber = txtcontactNumber.getText();

        Customer customer = new Customer(id,name,address,contactnumber);

        List<Customer> customerList = DbConnection.getInstance().getConnection();
        customerList.add(customer);
        clearText();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearText();

    }
    private void clearText(){
        txtId.setText(null);
        txtname.setText(null);
        txtAddress.setText(null);
        txtcontactNumber.setText(null);

    }
}
