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
    private VBox headerComponent;

    @FXML
    private HeaderController headerComponentController; // El controlador del header

    @FXML
    public void initialize() {
        // Establece la comunicación con el controlador del header
        if (headerComponentController != null) {
            headerComponentController.setParentController(this);
        }
        // Carga la página inicial por defecto
        loadPage("home.fxml");
    }

    // Método principal para cargar una página dinámicamente
    public void loadPage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
            Node page = loader.load();
            centerPane.getChildren().setAll(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
