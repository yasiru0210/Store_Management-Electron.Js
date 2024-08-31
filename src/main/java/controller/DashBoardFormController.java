package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashBoardFormController {

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            // Corrected path to the FXML file
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/add_customer_form.fxml")))));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file", e);
        }
        stage.show();
    }

    public void btnViewCustomerOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            // Corrected path to the FXML file
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/view_customer_form.fxml")))));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file", e);
        }
        stage.show();
    }
}
