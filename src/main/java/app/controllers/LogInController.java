package app.controllers;

import app.App;
import app.models.usuarios.LoginModel;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;

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
                        Field.ofStringType(model.usernameProperty())
                                .label("Usuario"),
                        Field.ofStringType(model.passwordProperty())
                                .label("Contraseña")
                )
        ).title("Iniciar Sesión");

        // 3. Renderizar y añadir el formulario al contenedor del FXML
        FormRenderer formRenderer = new FormRenderer(loginForm);
        formContainer.getChildren().add(formRenderer);
    }

    @FXML
    private void handleLogin() throws Exception  {

        loginForm.persist();

        // Validar el formulario antes de procesar
        if (loginForm.isValid()) {
            // Lógica de autenticación
            String username = model.usernameProperty().get();
            String password = model.passwordProperty().get();

            //Falta agregar la logica que verifica en el JSON si existe el ususario
            if (username.compareTo("admin") == 0 && password.compareTo("pass123") == 0) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Inicio de sesión correcto.");
                App.changeScene("masterLayout.fxml");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Usuario o contraseña incorrectos.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de validación", "Por favor, corrige los errores del formulario.");
        }
    }

    @FXML
    private void handleRegistro() throws Exception  {
        App.changeScene("registro.fxml");
    }
}
