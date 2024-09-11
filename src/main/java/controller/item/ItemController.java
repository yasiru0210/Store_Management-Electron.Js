package controller.item;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService{
    @Override
    public boolean addItem(Item item) {

        String sql="insert into item(ItemCode, Description,PackSize,UnitPrice,QtyOnHand) values (?,?,?,?,?)";

        try {
            Connection connection  = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setObject(1,item.getItemcode());
            prst.setObject(2,item.getDescription());
            prst.setObject(3,item.getPackagesize());
            prst.setObject(4,item.getUnitprice());
            prst.setObject(5,item.getQuantity());

            return prst.executeUpdate() > 0;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteItem(String itemcode) {
        try {
            String sql="delete from item where ItemCode='"+itemcode+"'";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            System.out.println(prst.executeUpdate() > 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean searchItem(String name) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public ObservableList<Item> getAll() {
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
            }
            return observableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
