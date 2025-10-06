package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class Controller {

    protected void mostrarAlerta(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected void insertarTitulo(String titulo, VBox vBox){
        Label labelTitulo = new Label(titulo);
        labelTitulo.getStyleClass().add("titulo-h1");
        vBox.getChildren().setAll(labelTitulo);
    }
}
