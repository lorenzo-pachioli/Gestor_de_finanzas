package app.controllers;

import app.enums.MetodoDePago;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.TransaccionModel;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.validators.IntegerRangeValidator;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

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
        ObservableList<String> opcionesTipo = FXCollections.observableArrayList("Ingreso", "Gasto");
        ListProperty<String> tiposProperty = new SimpleListProperty<>(opcionesTipo);

        //Metodo de pago
        ObservableList<MetodoDePago> opcionesMetodo = FXCollections.observableArrayList(MetodoDePago.values());
        ListProperty<MetodoDePago> metodosProperty = new SimpleListProperty<>(opcionesMetodo);

        //2. Construir el formulario con FormsFX
        this.transaccionForm = Form.of(
                //Guardar.
                Group.of(
                        Field.ofSingleSelectionType(tiposProperty, model.tipoProperty())
                                .label("Tipo de Transacción (INGRESO / GASTO)"),
                        Field.ofDoubleType(model.montoProperty())
                                .label("Monto"),
                        Field.ofStringType(model.descripcionProperty())
                                .label("Descripción"),
                        Field.ofDate(model.fechaProperty())
                                .label("Fecha"),
                        Field.ofIntegerType(model.horasProperty())
                                .label("Horas")
                                .validate(
                                        IntegerRangeValidator.between(0,23,"La hora debe estar entre 0 y 23")
                                ),
                        Field.ofIntegerType(model.minutosProperty())
                                .label("Minutos")
                                .validate(
                                        IntegerRangeValidator.between(0,59,"Los minutos deben estar entre 0 y 59")
                                ),
                        Field.ofStringType(model.categoriaOFuenteProperty())
                                .label("Motivo / Fuente"), // Actualizar dependiendo de el valor de tipoProperty
                        Field.ofSingleSelectionType(metodosProperty, model.metodoDePagoProperty())
                                .label("Metodo de Pago")
                )
        ).title("Ingresar Transaccion");

        FormRenderer formRenderer = new FormRenderer(transaccionForm);
        formContainer.getChildren().add(formRenderer);
    }

    private void handleGuardar(){
        transaccionForm.persist();

        if(transaccionForm.isValid()){
            String tipo = model.tipoProperty().get();
            double monto = model.montoProperty().get();
            String descripcion = model.descripcionProperty().get();
            LocalDate fecha = model.fechaProperty().get();
            int horas = model.horasProperty().get();
            int minutos = model.minutosProperty().get();
            String categoriaOFuente = model.categoriaOFuenteProperty().get();
            MetodoDePago metodoDePago = model.metodoDePagoProperty().get();

            if(tipo.equals("Ingreso")){
                Ingreso ingreso = new Ingreso(monto, fecha, horas, minutos, descripcion, metodoDePago, categoriaOFuente);
            }else{
                Gasto gasto = new Gasto(monto, fecha, horas, minutos, descripcion, metodoDePago, categoriaOFuente);
            }
        }
    }

    private void insertarTitulo(){
        Label titulo = new Label("Ingreso de Transacciones");
        titulo.getStyleClass().add("titulo-h1");
        seccionTitulo.getChildren().setAll(titulo);
    }
}
