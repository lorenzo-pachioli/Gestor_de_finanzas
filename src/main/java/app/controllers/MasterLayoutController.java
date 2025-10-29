package app.controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MasterLayoutController {

    @FXML
    private StackPane centerPane;

    @FXML
    private VBox headerUserComponent;

    @FXML
    private HeaderUserController headerUserComponentController; // El controlador del header

    @FXML
    public void initialize() {
        // Establece la comunicación con el controlador del header
        if (headerUserComponentController != null) headerUserComponentController.setParentController(this);
        loadPage("home.fxml");
    }

    // Metodo para cambiar la escena dentro de masterLayout
    public void loadPage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
            Node page = loader.load();
            centerPane.getChildren().setAll(page);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
