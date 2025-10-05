package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HeaderController {
    private MasterLayoutController parentController;


    // Método para establecer la referencia al controlador principal
    public void setParentController(MasterLayoutController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void handleHomeSelected(ActionEvent event) {
        if (parentController != null) {
            parentController.loadPage("home.fxml");
        }
    }

    @FXML
    private void handleMovimientoSelected(ActionEvent event) {
        if (parentController != null) {
            parentController.loadPage("movimiento.fxml");
        }
    }

    @FXML
    private void handleHistorialSelected(ActionEvent event) {
        if (parentController != null) {
            parentController.loadPage("historial.fxml");
        }
    }
    @FXML
    private void handleCategoriasSelected(ActionEvent event) {
        if (parentController != null) {
            parentController.loadPage("categorias.fxml");
        }
    }
    @FXML
    private void handlePerfilSelected(ActionEvent event) {
        if (parentController != null) {
            parentController.loadPage("perfil.fxml");
        }
    }
}
