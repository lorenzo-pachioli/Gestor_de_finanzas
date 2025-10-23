package app.controllersAdmin;

import app.controllers.Controller;
import app.enums.NivelAcceso;
import app.jsonUtils.JSONPersonas;
import app.models.usuarios.Persona;
import app.models.usuarios.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HomeAdminController extends Controller {

    @FXML
    private VBox listaUsuarios;
    private final ArrayList<Persona> listaPersonas = JSONPersonas.leerPersonasArray();

    @FXML
    public void initialize() {
        VBox titulo = new VBox();
        insertarTitulo2("Lista de usuarios", titulo);
        listaUsuarios.getChildren().add(titulo);
        for (Persona persona : listaPersonas) {
            VBox bloquePersona = creacionFilaPersona(persona);
            listaUsuarios.getChildren().add(bloquePersona);
        }
    }


    private VBox creacionFilaPersona(Persona persona){
        VBox bloquePersona = insertarBox();

        VBox id = new VBox();
        insertarTexto("ID: " + persona.getId(), id);
        id.setStyle("-fx-font-weight: bold;");

        VBox acceso = new VBox();
        insertarTexto("acceso: " + (persona instanceof Usuario ? NivelAcceso.USUARIO:NivelAcceso.ADMINISTRADOR), acceso);

        VBox nombre = new VBox();
        insertarTexto("nombre: " + persona.getNombre(), nombre);

        VBox apellido = new VBox();
        insertarTexto("apellido: " + persona.getApellido(), apellido);

        VBox dni = new VBox();
        insertarTexto("dni: " + persona.getDni(), dni);

        VBox telefono = new VBox();
        insertarTexto("telefono: " + persona.getTelefono(), telefono);

        VBox email = new VBox();
        insertarTexto("email: " + persona.getEmail(), email);

        bloquePersona.getChildren().setAll(id, acceso, nombre, apellido, dni, telefono, email);
        return bloquePersona;
    }
}
