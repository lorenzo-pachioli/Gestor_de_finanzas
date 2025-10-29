package app.controllers;

import app.App;
import app.models.usuarios.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PerfilController extends Controller {

    @FXML
    private VBox seccionNombre;
    @FXML
    private VBox seccionApellido;
    @FXML
    private VBox seccionDni;
    @FXML
    private VBox seccionEmail;
    @FXML
    private VBox seccionTelefono;
    @FXML
    private Stage editarAtributo;

    @FXML
    public void initialize() {

        cargarInformacion();
        editarAtributo = new Stage();
        editarAtributo.initModality(Modality.APPLICATION_MODAL);
    }

    private void cargarInformacion() {

        Persona persona = App.getPersona();
        if (persona != null) {
            insertarTexto(persona.getNombre(), this.seccionNombre);
            insertarTexto(persona.getApellido(), this.seccionApellido);
            insertarTexto(Integer.toString(persona.getDni()), this.seccionDni);
            insertarTexto(persona.getEmail(), this.seccionEmail);
            insertarTexto(Integer.toString(persona.getTelefono()), this.seccionTelefono);
        }
    }

    @FXML
    private void handleEditarAtributo(ActionEvent event) {

        Node source = (Node) event.getSource();
        showEditarDialog(source.getId());
        cargarInformacion();
    }

    public void showEditarDialog(String nombreAtributo) {

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editarAtributo.fxml"));
            Scene scene = new Scene(loader.load(), 600, 300);
            URL cssUrl = App.class.getResource("styles.css");
            assert cssUrl != null;
            scene.getStylesheets().add(cssUrl.toExternalForm());
            editarAtributo.setScene(scene);
            EditarAtributoController controller = loader.getController();
            controller.initData(nombreAtributo);
            editarAtributo.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
