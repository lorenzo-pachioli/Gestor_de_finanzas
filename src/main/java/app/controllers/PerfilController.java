package app.controllers;

import app.App;
import app.models.usuarios.Usuario;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

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
    public void initialize() {

        Usuario usuario = App.getUsuario();
        if(usuario != null){
            insertarTexto(usuario.getNombre(), this.seccionNombre);
            insertarTexto(usuario.getApellido(), this.seccionApellido);
            insertarTexto(Integer.toString(usuario.getDni()), this.seccionDni);
            insertarTexto(usuario.getEmail(), this.seccionEmail);
            insertarTexto(Integer.toString(usuario.getTelefono()), this.seccionTelefono);
        }
    }
}
