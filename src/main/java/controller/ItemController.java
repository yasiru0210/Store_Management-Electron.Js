package controller;

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

public class ItemController implements Initializable {

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

    @FXML
    void btnAddOnaction(ActionEvent event) {

        Item item = new Item(txtItem.getText(),
                txtDescription.getText(),
                txtPacksize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Double.parseDouble(txtQuantity.getText()));


        String sql="insert into item(ItemCode, Description,PackSize,UnitPrice,QtyOnHand) values (?,?,?,?,?)";

        try {
            Connection connection  = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setObject(1,item.getItemcode());
            prst.setObject(2,item.getDescription());
            prst.setObject(3,item.getPackagesize());
            prst.setObject(4,item.getUnitprice());
            prst.setObject(5,item.getQuantity());

            boolean b = prst.executeUpdate() > 0;
            System.out.println(b);

            if(b){
                new Alert(Alert.AlertType.INFORMATION,"item is added!!!!").show();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"item is not added!!!!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        try {
            String sql="delete from item where ItemCode='"+txtItem.getText()+"'";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            System.out.println(prst.executeUpdate() > 0);
            if(prst.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION,"Item code is deleted!!!").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Failed to delete Item Code").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        colItem.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackage.setCellValueFactory(new PropertyValueFactory<>("packagesize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadtable();

    }

    private void loadtable() {
        ObservableList<Item> observableList = FXCollections.observableArrayList();

        try {
            String SQL = "Select * from item";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement prstm = connection.prepareStatement(SQL);
            ResultSet resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getDouble("QtyOnHand"));

                System.out.println(item);
                observableList.add(item);
                tblItem.setItems(observableList);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}