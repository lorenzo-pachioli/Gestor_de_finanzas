package app.controllers;

import app.App;
import app.models.usuarios.RegistroModel;
import app.models.usuarios.Usuario;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import static app.jsonUtils.JSONPersonas.existeusuario;
import static app.jsonUtils.JSONPersonas.registrarUsuario;

public class RegistroController extends Controller {

    @FXML
    private VBox formContainer;

    @FXML
    private VBox seccionTitulo;

    private RegistroModel model;
    private Form registroForm;

    @FXML
    public void initialize() {
        insertarTitulo("Crear usuario", this.seccionTitulo);

        // 1. Crear el modelo de datos
        this.model = new RegistroModel();

        // 2. Construir el formulario con FormsFX
        this.registroForm = Form.of(
                Group.of(
                        Field.ofStringType(model.nombreProperty())
                                .label("Usuario"),
                        Field.ofStringType(model.apellidoProperty())
                                .label("Apellido"),
                        Field.ofIntegerType(model.dniProperty())
                                .label("Dni"),
                        Field.ofStringType(model.emailProperty())
                                .label("Email"),
                        Field.ofIntegerType(model.telefonoProperty())
                                .label("Telefono"),
                        Field.ofPasswordType(model.contraseniaProperty())
                                .label("Contraseña"),
                        Field.ofPasswordType(model.repetirContraseniaProperty())
                                .label("Repetir contraseña")
                )
        ).title("Formulario de registro: ");

        // 3. Renderizar y añadir el formulario al contenedor del FXML
        FormRenderer formRenderer = new FormRenderer(registroForm);
        formContainer.getChildren().add(formRenderer);
    }

    @FXML
    private void handleRegistro() throws Exception  {

        registroForm.persist();

        // Validar el formulario antes de procesar
        if (registroForm.isValid()) {
            // Lógica de autenticación real
            String nombre = model.nombreProperty().get();
            String apellido = model.apellidoProperty().get();
            int dni = model.dniProperty().get();
            String email = model.emailProperty().get();
            int telefono = model.telefonoProperty().get();
            String contrasenia = model.contraseniaProperty().get();
            String repetirContrasenia = model.repetirContraseniaProperty().get();

            if(contrasenia.compareTo(repetirContrasenia) == 0){
                Usuario usuario = new Usuario(nombre, apellido, dni, email, telefono, contrasenia);
                App.setUsuario(usuario);
                // Verifica si existe y si no existe lo guerda
                if(!existeusuario(email)){
                    registrarUsuario(usuario);
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario creado con exito");
                    App.changeScene("logIn.fxml");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Ya existe un usuario con ese mail");
                }
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Error de validación", "Por favor, corrige los errores del formulario.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de validación", "Por favor, corrige los errores del formulario.");
        }
    }

    public void handleVolverALogin(){
        App.changeScene("logIn.fxml");
    }
}
