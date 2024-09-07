package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import db.DbConnection;

import model.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddCustomerFormController {
    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtDOB;

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
    private JFXTextField txtTitle;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dob;

    {
        try {
            dob = dateFormat.parse(txtDOB.getText());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        Customer customer= new Customer(txtName.getText(),
                txtId.getText(),
                txtTitle.getText(),
                dob,
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtPostalCode.getText(),
                txtCity.getText(),
                txtProvince.getText());

        System.out.println(customer);

        List<Customer> customerList = DbConnection.getInstance().getConnection();
        customerList.add(customer);
        clearText();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearText();

    }
    private void clearText(){
        txtTitle.setText(null);
        txtId.setText(null);
        txtCity.setText(null);
        txtDOB.setText(null);
        txtSalary.setText(null);
        txtAddress.setText(null);
        txtPostalCode.setText(null);
        txtProvince.setText(null);
    }

}
