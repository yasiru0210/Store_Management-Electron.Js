package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ViewCustomerFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colcontactnumber;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableView<Customer> tblcustomers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcontactnumber.setCellValueFactory(new PropertyValueFactory<>("contactnumber"));
        loadTable();
    }
    @FXML
     public void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }
    private void loadTable(){

        ObservableList<Customer> list = FXCollections.observableArrayList();
        List<Customer> connection = DbConnection.getInstance().getConnection();

        for (Customer obj : connection) {
            list.add(obj);
        }


        list.add(new Customer("001","Yasiru","Panadura","0898697"));
        list.add(new Customer("002","Kasun","Matara","089869743"));

        tblcustomers.setItems(list);

    }






}

