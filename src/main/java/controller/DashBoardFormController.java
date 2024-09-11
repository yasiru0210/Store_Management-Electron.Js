package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashBoardFormController {

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/view_customer_form.fxml")))));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file", e);
        }
        stage.show();
    }


    public void btnOrderDetailOnAction(ActionEvent actionEvent) {
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/item_form.fxml")))));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file", e);
        }
        stage.show();
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) {

    }
}
