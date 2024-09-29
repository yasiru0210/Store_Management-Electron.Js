package controller.item;

import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetail;

import java.util.List;

public interface ItemService {
    boolean addItem(Item item);
    boolean deleteItem(String itemcode);
    Item searchItem(String itemcode);
    boolean updateItem(Item item);
    ObservableList<Item> getAll();
    ObservableList<String>getItemCode();
    boolean updateStock(List<OrderDetail> orderDetaillist);

}
