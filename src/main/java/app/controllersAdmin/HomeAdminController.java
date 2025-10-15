package app.controllersAdmin;

import app.controllers.Controller;
import app.jsonUtils.JSONPersonas;
import app.models.usuarios.Persona;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HomeAdminController extends Controller {

    @FXML
    private VBox listaUsuarios;
    private ArrayList<Persona> listaPersonas = JSONPersonas.leerPersonasArray();

    @FXML
    public void initialize() {
        //listaPersonas = JSONPersonas.leerPersonasArray();
        for(int i=0 ; i<listaPersonas.size() ; i++){
            VBox persona = new VBox();
            insertarTexto("nombre: " + listaPersonas.get(i).getNombre(), persona);
            listaUsuarios.getChildren().add(persona);
        }
    }
}
