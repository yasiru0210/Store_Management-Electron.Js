package controller.item;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    public TableView tblItem;
    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItem;

    @FXML
    private TableColumn<?, ?> colPackage;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItem;

    @FXML
    private JFXTextField txtPacksize;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtUnitPrice;

     ItemService service=new ItemController();

    @FXML
    void btnAddOnaction(ActionEvent event) {
        Item item = new Item(txtItem.getText(),
                txtDescription.getText(),
                txtPacksize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Double.parseDouble(txtQuantity.getText()));


        if( service.addItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item is added").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer is not added").show();
        }

       clearText();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
         clearText();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

         if( service.deleteItem(txtItem.getText())){
             new Alert(Alert.AlertType.INFORMATION,"Item is deleted!!").show();
         }else {
             new Alert(Alert.AlertType.ERROR,"Item is not deleted!!!!").show();
         }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadtable();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Item> observableList = FXCollections.observableArrayList();
        tblItem.setItems(observableList);
        colItem.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackage.setCellValueFactory(new PropertyValueFactory<>("packagesize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadtable();

    }

    private void loadtable() {
        ObservableList<Item> list =service .getAll();
        tblItem.setItems(list);

    }
    private void clearText() {
        txtItem.setText(null);
        txtDescription.setText(null);
        txtPacksize.setText(null);
        txtUnitPrice.setText(null);
        txtQuantity.setText(null);
    }

}