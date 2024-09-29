package controller.customer;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
    ObservableList<Customer> getall();
    boolean updateCustomer(Customer customer);
    Customer serchCustomer(String  customerID);
    ObservableList<String> getCustomerIDs();

}
