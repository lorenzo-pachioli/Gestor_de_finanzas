package app.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class Controller {

    protected boolean mostrarAlerta(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> data = alert.showAndWait();
        return data.map(buttonType -> buttonType.getButtonData().isDefaultButton()).orElse(false);
    }

    protected void insertarTitulo(String titulo, VBox vBox) {
        Label labelTitulo = new Label(titulo);
        labelTitulo.getStyleClass().add("titulo-h1");
        vBox.getChildren().setAll(labelTitulo);
    }

    protected void insertarTitulo2(String titulo, VBox vBox) {
        Label labelTitulo = new Label(titulo);
        labelTitulo.getStyleClass().add("titulo-h2");
        vBox.getChildren().setAll(labelTitulo);
    }

    protected void insertarTexto(String titulo, VBox vBox) {
        Label labelTitulo = new Label(titulo);
        labelTitulo.getStyleClass().add("titulo-p");
        vBox.getChildren().setAll(labelTitulo);
    }

    protected VBox insertarBox() {
        VBox contenedor = new VBox();
        contenedor.getStyleClass().add("perfil-box");
        return contenedor;
    }

    protected VBox insertarBox(VBox contenido) {
        VBox contenedor = new VBox();
        contenedor.getStyleClass().add("perfil-box");
        contenedor.getChildren().setAll(contenido);
        return contenedor;
    }

    public void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
