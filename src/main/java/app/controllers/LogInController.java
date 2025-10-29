package app.controllers;

import app.App;
import app.models.usuarios.Administrador;
import app.models.usuarios.LoginModel;
import app.models.usuarios.Persona;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;

import static app.jsonUtils.JSONPersonas.logInPersona;

public class LogInController extends Controller {

    @FXML
    private VBox formContainer;

    @FXML
    private VBox seccionTitulo;

    private LoginModel model;
    private Form loginForm;

    @FXML
    public void initialize() {

        insertarTitulo("Gestor de finanzas", this.seccionTitulo);

        // 1. Crear el modelo de datos
        this.model = new LoginModel();

        // 2. Construir el formulario con FormsFX
        this.loginForm = Form.of(
                Group.of(
                        Field.ofStringType(model.emailProperty())
                                .label("Email"),
                        Field.ofStringType(model.passwordProperty())
                                .label("Contraseña")
                )
        ).title("Iniciar Sesión");

        // 3. Renderizar y añadir el formulario al contenedor del FXML
        FormRenderer formRenderer = new FormRenderer(loginForm);
        formContainer.getChildren().add(formRenderer);
    }

    @FXML
    private void handleLogin() {

        loginForm.persist();

        // Validar el formulario antes de procesar
        if (loginForm.isValid()) {
            // Lógica de autenticación
            String email = model.emailProperty().get();
            String contrasenia = model.passwordProperty().get();

            Persona persona = logInPersona(email, contrasenia);
            if(persona.isBloqueado()){
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "La cuenta del usuario ingresado está bloqueada.");
            }else if(persona.isValid()) {
                App.setPersona(persona);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Inicio de sesión correcto.");
                redireccionSegunAcceso(persona);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Usuario o contraseña incorrectos.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de validación", "Por favor, corrige los errores del formulario.");
        }
    }

    private void redireccionSegunAcceso(Persona persona){
        if(persona instanceof Administrador) {
            //Ingreso a aplicacion Admin
            App.changeScene("admin/masterLayoutAdmin.fxml");
        } else {
            //Ingreso a aplicacion Usuario
            App.changeScene("masterLayout.fxml");
        }
    }

    @FXML
    private void handleRegistro() {
        App.changeScene("registro.fxml");
    }
}
