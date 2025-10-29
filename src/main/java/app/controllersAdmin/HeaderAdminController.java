package app.controllersAdmin;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HeaderAdminController {
    public MasterLayoutAdminController parentController;

    public void setParentController(MasterLayoutAdminController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void handleHomeAdminSelected(ActionEvent event) {
        if (parentController != null) {
            parentController.loadPage("admin/homeAdmin.fxml");
        }
    }

    @FXML
    private void handlePerfilAdminSelected(ActionEvent event) {
        if (parentController != null) {
            parentController.loadPage("perfil.fxml");
        }
    }


    @FXML
    private void handleCerrarSesionAdminSelected(ActionEvent event) {
        if (parentController != null) {
            App.setPersona(null);
            App.changeScene("logIn.fxml");
        }
    }
}
