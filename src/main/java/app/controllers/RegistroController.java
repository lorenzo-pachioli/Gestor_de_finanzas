package app.controllers;

import app.models.usuarios.LoginModel;
import app.models.usuarios.RegistroModel;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class RegistroController extends Controller {

    @FXML
    private VBox formContainer;

    @FXML
    private VBox seccionTitulo;

    private RegistroModel model;
    private Form registroForm;

    @FXML
    public void initialize() {
        insertarTitulo("Crear usuario");

        // 1. Crear el modelo de datos
        this.model = new RegistroModel();

        // 2. Construir el formulario con FormsFX
        this.registroForm = Form.of(
                Group.of(
                        Field.ofStringType(model.nombrePropertyProperty())
                                .label("Usuario"),
                        Field.ofStringType(model.apellidoPropertyProperty())
                                .label("Usuario"),
                        Field.ofStringType(model.dniPropertyProperty())
                                .label("Usuario"),
                        Field.ofStringType(model.emailPropertyProperty())
                                .label("Email"),
                        Field.ofStringType(model.telefonoPropertyProperty())
                                .label("Telefono"),
                        Field.ofStringType(model.contraseniaPropertyProperty())
                                .label("Contraseña"),
                        Field.ofStringType(model.repetirContraseniaPropertyProperty())
                                .label("repetir contraseña")
                )
        ).title("Iniciar Sesión");

        // 3. Renderizar y añadir el formulario al contenedor del FXML
        FormRenderer formRenderer = new FormRenderer(loginForm);
        formContainer.getChildren().add(formRenderer);
    }

}
