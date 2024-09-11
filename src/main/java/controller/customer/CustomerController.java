package controller.customer;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController implements CustomerService {
    @Override
    public boolean addCustomer(Customer customer) {
        try {
            String SQL = "INSERT INTO CUSTOMER (CustID, CustTitle, CustName, DOB, salary, CustAddress, City, Province, PostalCode) VALUES (?,?,?,?,?,?,?,?,?)";
            Connection connection1 = DbConnection.getInstance().getConnection();
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

            return prstm1.executeUpdate() > 0;


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
        }
return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        try {
            String SQL="delete from customer where CustID='"+id+"'";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(SQL);
            return prst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public ObservableList<Customer> getall() {
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
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                System.out.println(customer);
                list.add(customer);
            }
           return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean serchCustomer(String name) {
        return false;
    }
}
