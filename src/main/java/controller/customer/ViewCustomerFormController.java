package controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;


    public class ViewCustomerFormController implements Initializable {

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

        CustomerService service=new CustomerController();

        @FXML
        void btnAddOnAction(ActionEvent event) {

            Customer customer = new Customer(txtId.getText(),
                    cmbTitle.getValue(),
                    txtName.getText(),
                    dateDOB.getValue(),
                    Double.parseDouble(txtSalary.getText()),
                    txtAddress.getText(),
                    txtCity.getText(),
                    txtProvince.getText(),
                    txtPostalCode.getText());

            if(service.addCustomer(customer)){
                new Alert(Alert.AlertType.INFORMATION,"Customer added!!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Cannot add Customer").show();
            }

            clearText();
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
            tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
                setTextToValues((Customer) newValue);
            }));
        }

        public void btnDeleteOnAction(ActionEvent actionEvent) {

            if( service.deleteCustomer(txtId.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"not deleted").show();
            }

        }
        private void loadTable(){
            ObservableList<Customer> customerobservablelist = service.getall();
            tblCustomers.setItems(customerobservablelist);

        }

        public void btnReloadOnAction(ActionEvent actionEvent) {
            loadTable();
        }

        private void setTextToValues(Customer newValue) {
            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
            txtProvince.setText(newValue.getProvince());
            txtCity.setText(newValue.getCity());
            txtPostalCode.setText(newValue.getPostalcode());

        }

    }




