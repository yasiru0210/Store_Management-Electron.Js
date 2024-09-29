package controller.item;

import Utill.CrudUtill;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemController implements ItemService{

    private ItemController(){}

    private static ItemController instance;

    public static ItemController getInstance() {
        return instance==null?new ItemController():instance;
    }

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
    public Item searchItem(String itemcode) {
        try {
            String sql="select * from item where ItemCode='"+itemcode+"'";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            ResultSet resultSet = prst.executeQuery();

            while (resultSet.next()) {
                return new Item(resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand"));



            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



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
                        resultSet.getInt(5)));

            }

            return observableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public ObservableList<String> getItemCode() {
        ObservableList<String> itemCode = FXCollections.observableArrayList();
        ObservableList<Item> itemObservableList = getAll();
        itemObservableList.forEach(item -> {
            itemCode.add(item.getItemcode());
        });


        return itemCode;
    }


    public boolean updateStock(List<OrderDetail> orderDetaillist) {
        for(OrderDetail orderDetail:orderDetaillist){
            try {
                boolean isUpdate = updateStock(orderDetail);
                if(!isUpdate){
                    return false;
                }else{
                    return true;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    public boolean updateStock( OrderDetail orderDetail) throws SQLException {
        String sql="Update item set QtyOnHand=QtyOnHand-? where ItemCode=?";
           return CrudUtill.execute(sql,orderDetail.getOrderQTY(),orderDetail.getItemCode());

    }
}

