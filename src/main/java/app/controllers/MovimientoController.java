package app.controllers;

import app.enums.MetodoDePago;
import app.models.TransaccionModel;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.VBox;

import java.text.Normalizer;

public class MovimientoController {

    @FXML
    private VBox formContainer;

    @FXML
    private VBox seccionTitulo;

    private TransaccionModel model;
    private Form transaccionForm;

    @FXML
    public void initialize(){
        insertarTitulo();

        //1. Crear el modelo de datos
        this.model = new TransaccionModel();

        //Tipo de transferencia
        ObservableList<String> opcionesTipo = FXCollections.observableArrayList("INGRESO", "GASTO");
        ListProperty<String> tiposProperty = new SimpleListProperty<>(opcionesTipo);

        //Metodo de pago
        ObservableList<MetodoDePago> opcionesMetodo = FXCollections.observableArrayList(MetodoDePago.values());
        ListProperty<MetodoDePago> metodosProperty = new SimpleListProperty<>(opcionesMetodo);

        //2. Construir el formulario con FormsFX
        this.transaccionForm = Form.of(
                //Acomodar fecha, guardar.
                Group.of(
                        Field.ofSingleSelectionType(tiposProperty, model.tipoProperty())
                                .label("Tipo de Transacción (INGRESO / GASTO)"),
                        Field.ofDoubleType(model.montoProperty())
                                .label("Monto"),
                        Field.ofStringType(model.descripcionProperty())
                                .label("Descripción"),
                        Field.ofDate(model.fechaProperty())
                                .label("Fecha"),
                        Field.ofStringType(model.categoriaOFuenteProperty())
                                .label("Motivo / Fuente"),
                        Field.ofSingleSelectionType(metodosProperty, model.metodoDePagoProperty())
                                .label("Metodo de Pago")
                )
        ).title("Ingresar Transaccion");

        FormRenderer formRenderer = new FormRenderer(transaccionForm);
        formContainer.getChildren().add(formRenderer);
    }

    private void insertarTitulo(){
        Label titulo = new Label("Ingreso de Transacciones");
        titulo.getStyleClass().add("titulo-h1");
        seccionTitulo.getChildren().setAll(titulo);
    }
}
