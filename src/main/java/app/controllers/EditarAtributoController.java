package app.controllers;

import app.App;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class EditarAtributoController extends Controller {
    @FXML
    private VBox formContainer;

    @FXML
    private VBox seccionTitulo;

    private StringProperty atributoString = new SimpleStringProperty();
    private IntegerProperty atributoInteger = new SimpleIntegerProperty();
    private Form editForm;
    private String atributo;

    @FXML
    void initialize() {}

    @FXML
    public void initData(String nombreAtributo) {

        insertarTitulo("Editar " + nombreAtributo, this.seccionTitulo);
        atributo = nombreAtributo;
        setAtributoPropertyInicial();

        generarFormulario();

        // 3. Renderizar y añadir el formulario al contenedor del FXML
        FormRenderer formRenderer = new FormRenderer(editForm);
        formContainer.getChildren().add(formRenderer);
    }

    private void generarFormulario(){
        if(atributo.equalsIgnoreCase("dni") || atributo.equalsIgnoreCase("telefono")){
            // 2. Construir el formulario con FormsFX si es integer
            this.editForm = Form.of(
                    Group.of(
                            Field.ofIntegerType(atributoInteger)
                    )
            );
        } else {
            // 2. Construir el formulario con FormsFX si es string
            this.editForm = Form.of(
                    Group.of(
                            Field.ofStringType(atributoString)
                    )
            );
        }
    }

    @FXML
    private void handleConfirmar(ActionEvent event){
        editForm.persist();
        if(atributo.equalsIgnoreCase("nombre")){
            App.getUsuario().setNombre(atributoString.get());
        } else if(atributo.equalsIgnoreCase("apellido")){
            App.getUsuario().setApellido(atributoString.get());
        } else if(atributo.equalsIgnoreCase("email")){
            App.getUsuario().setEmail(atributoString.get());
        } else if(atributo.equalsIgnoreCase("contrasenia")){
            App.getUsuario().setContrasenia(atributoString.get());
        } else if(atributo.equalsIgnoreCase("dni")){
            App.getUsuario().setDni(atributoInteger.get());
        } else if(atributo.equalsIgnoreCase("telefono")){
            App.getUsuario().setTelefono(atributoInteger.get());
        }
        closeWindow(event);
    }


    private void setAtributoPropertyInicial(){

        if(atributo.equalsIgnoreCase("nombre")){
            atributoString.set(App.getUsuario().getNombre());
        } else if(atributo.equalsIgnoreCase("apellido")){
            atributoString.set(App.getUsuario().getApellido());
        } else if(atributo.equalsIgnoreCase("email")){
            atributoString.set(App.getUsuario().getEmail());
        } else if(atributo.equalsIgnoreCase("contrasenia")){
            atributoString.set(App.getUsuario().getContrasenia());
        } else if(atributo.equalsIgnoreCase("dni")){
            atributoInteger.set(App.getUsuario().getDni());
        } else if(atributo.equalsIgnoreCase("telefono")){
            atributoInteger.set(App.getUsuario().getTelefono());
        }
    }

}
