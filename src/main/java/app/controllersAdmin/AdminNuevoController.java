package app.controllersAdmin;

import app.controllers.Controller;
import app.models.excepciones.FormularioIncorrectoException;
import app.models.excepciones.UsuarioYaExisteException;
import app.models.usuarios.Administrador;
import app.models.usuarios.RegistroModel;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import static app.jsonUtils.JSONPersonas.*;

public class AdminNuevoController extends Controller {

    @FXML
    private VBox formContainer;

    @FXML
    private VBox seccionTitulo;

    private RegistroModel model;
    private Form registroForm;

    @FXML
    public void initialize() {
        insertarTitulo("Crear administrador", this.seccionTitulo);

        // 1. Crear el modelo de datos
        this.model = new RegistroModel();

        // 2. Construir el formulario con FormsFX
        this.registroForm = Form.of(
                Group.of(
                        Field.ofStringType(model.nombreProperty())
                                .label("Nombre"),
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
    private void handleCrearAdmin() {

        registroForm.persist();

        try {

            // Validar el formulario antes de procesar
            if (!registroForm.isValid())
                throw new FormularioIncorrectoException("Por favor, corrige los errores del formulario.");

            // Lógica de autenticación real
            String nombre = model.nombreProperty().get();
            String apellido = model.apellidoProperty().get();
            int dni = model.dniProperty().get();
            String email = model.emailProperty().get();
            int telefono = model.telefonoProperty().get();
            String contrasenia = model.contraseniaProperty().get();
            String repetirContrasenia = model.repetirContraseniaProperty().get();

            // Verifica si existe
            if (contrasenia.compareTo(repetirContrasenia) != 0)
                throw new FormularioIncorrectoException("Las contraseñas ingresadas no coinciden.");
            if (existeusuario(email)) throw new UsuarioYaExisteException("Ya existe un usuario con ese mail");

            // si no existe lo guerda
            Administrador administrador = new Administrador(nombre, apellido, dni, email, telefono, contrasenia);
            registrarPersona(administrador);
            mostrarAlerta(Alert.AlertType.CONFIRMATION, "Éxito", "Usuario creado con exito");

        } catch (UsuarioYaExisteException e) {
            mostrarAlerta(Alert.AlertType.CONFIRMATION, "Éxito", e.getMessage());
        } catch (FormularioIncorrectoException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de validación", e.getMessage());
        }
    }
}
