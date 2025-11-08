package app.controllers;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HeaderUserController {
    public MasterLayoutController parentController;

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
        try {
            if (parentController != null) {
                parentController.loadPage("historial.fxml");
            }
        } catch (RuntimeException e) {
            System.out.println(e);
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
    @FXML
    private void handleCerrarSesionSelected(ActionEvent event) {
        if (parentController != null) {
            App.setPersona(null);
            App.changeScene("logIn.fxml");
        }
    }
}
