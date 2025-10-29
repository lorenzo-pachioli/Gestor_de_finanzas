package app.controllersAdmin;

import app.App;
import app.controllers.Controller;
import app.enums.NivelAcceso;
import app.jsonUtils.JSONPersonas;
import app.models.colecciones.ListaPersonas;
import app.models.usuarios.Persona;
import app.models.usuarios.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HomeAdminController extends Controller {

    @FXML
    private Stage verMovimientos;
    @FXML
    private VBox listaUsuarios;

    @FXML
    public void initialize() {
        this.iniciarListaPersonas();
        verMovimientos = new Stage();
        verMovimientos.initModality(Modality.APPLICATION_MODAL);
    }


    private void iniciarListaPersonas() {
        this.listaUsuarios.getChildren().clear();
        ListaPersonas listaPersonas = App.listaPersonas;
        ;
        VBox titulo = new VBox();
        insertarTitulo2("Lista de usuarios", titulo);
        listaUsuarios.getChildren().add(titulo);

        for (Persona persona : listaPersonas.getListaPersonas()) {
            VBox bloquePersona = creacionFilaPersona(persona);
            listaUsuarios.getChildren().add(bloquePersona);
        }
    }

    private VBox creacionFilaPersona(Persona persona) {
        VBox bloquePersona = insertarBox();

        VBox id = new VBox();
        insertarTexto("ID: " + persona.getId(), id);
        id.setStyle("-fx-font-weight: bold;");

        VBox acceso = new VBox();
        insertarTexto("acceso: " + (persona instanceof Usuario ? NivelAcceso.USUARIO : NivelAcceso.ADMINISTRADOR), acceso);

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

        VBox bloqueado = new VBox();
        insertarTexto("Esta bloqueado: " + (persona.isBloqueado() ? "Si" : "No"), bloqueado);

        Button borrar = new Button("Borrar");
        borrar.setOnMouseClicked(event -> {
            boolean confirmacion = mostrarAlerta(Alert.AlertType.WARNING, ("Borrar usuario " + persona.getNombre()), ("Esta seguro que quiere borrar el usuario con mail: " + persona.getEmail()));
            if (confirmacion) {
                App.listaPersonas.borrarPersona(persona);
                this.iniciarListaPersonas();
            }
        });

        Button bloquear = new Button(persona.isBloqueado() ? "Desbloquear" : "Bloquear");
        bloquear.setOnMouseClicked(event -> {
            persona.setBloqueado(!persona.isBloqueado());
            this.iniciarListaPersonas();
        });

        HBox botones = new HBox();
        if (persona instanceof Usuario) {
            Button transacciones = new Button("Ver movivmientos");
            transacciones.setOnMouseClicked(event -> {
                Node source = (Node) event.getSource();
                showHistorialDialog(source.getId());
            });

            botones.getChildren().setAll(borrar, bloquear, transacciones);
        } else {
            botones.getChildren().setAll(borrar, bloquear);
        }

        botones.setSpacing(15);
        bloquePersona.getChildren().setAll(id, acceso, nombre, apellido, dni, telefono, email, bloqueado, botones);

        return bloquePersona;
    }

    public void showHistorialDialog(String nombreAtributo) {

        try {
            System.out.println("nombreAtributo: " + nombreAtributo);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("historial.fxml"));
            Scene scene = new Scene(loader.load(), 600, 300);
            URL cssUrl = App.class.getResource("styles.css");
            assert cssUrl != null;
            scene.getStylesheets().add(cssUrl.toExternalForm());
            verMovimientos.setScene(scene);
            verMovimientos.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
