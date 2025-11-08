package app.controllersAdmin;

import app.App;
import app.models.usuarios.Administrador;
import app.models.usuarios.Persona;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MasterLayoutAdminController {

    @FXML
    private StackPane centerPane;

    @FXML
    private VBox headerAdminComponent;

    @FXML
    private HeaderAdminController headerAdminComponentController; // El controlador del header

    @FXML
    public void initialize() {

        //Verifica si realmente tiene acceso, y si no lo tiene lo redirecciona a la app de Usuarios
        Persona persona = App.persona;
        if(!(persona instanceof Administrador)) App.changeScene("masterLayout.fxml");

        // Carga la lista de usuarios
        App.listaPersonas.iniciarListaPersonas();

        // Establece la comunicación con el controlador del header
        if (headerAdminComponentController != null) headerAdminComponentController.setParentController(this);
        loadPage("admin/homeAdmin.fxml");
    }

    // Metodo para cambiar la escena dentro de masterLayoutAdmin
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
