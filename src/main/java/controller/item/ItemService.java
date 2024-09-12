package controller.item;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemService {
    boolean addItem(Item item);
    boolean deleteItem(String itemcode);
    boolean searchItem(String itemcode);
    boolean updateItem(Item item);
    ObservableList<Item> getAll();

}
