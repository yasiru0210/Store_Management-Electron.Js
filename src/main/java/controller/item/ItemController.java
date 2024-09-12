package controller.item;

import Utill.CrudUtill;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            return CrudUtill.execute(sql,
                    item.getItemcode(),
                    item.getDescription(),
                    item.getPackagesize(),
                    item.getUnitprice(),
                    item.getQuantity());

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
            return prst.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean searchItem(String itemcode) {
        try {
            String sql="select * from item where ItemCode='"+itemcode+"'";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            ResultSet resultSet = prst.executeQuery();

            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getDouble("QtyOnHand"));

                System.out.println(item);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        String SQL = "Update item set  Description=? ,PackSize=? , UnitPrice=? ,QtyOnHand=? where ItemCode=? ";
        try {
           return CrudUtill.execute(SQL,

                    item.getDescription(),
                    item.getPackagesize(),
                    item.getUnitprice(),
                    item.getQuantity(),
                   item.getItemcode()
           );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Item> getAll() {
        String SQL="Select * from item";
        ObservableList<Item> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtill.execute(SQL);
            while(resultSet.next()){
                observableList.add(new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5)));

            }

            return observableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

