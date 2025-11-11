package app.controllers;

import app.App;
import app.enums.FuenteIngreso;
import app.enums.MetodoDePago;
import app.enums.MotivoGasto;
import app.enums.TipoTransaccion;
import app.models.transacciones.*;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.validators.DoubleRangeValidator;
import com.dlsc.formsfx.model.validators.IntegerRangeValidator;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.time.LocalDate;


public class MovimientoController extends Controller{

    public Button Gasto;
    public Button Ingreso;

    @FXML
    private VBox formContainer;

    @FXML
    private VBox seccionTitulo;

    private TransaccionModel model;
    private Form transaccionForm;

    @FXML
    public void initialize(){


        //2. Construir el formulario con FormsFX
        this.transaccionForm = seleccionarForm(TipoTransaccion.INGRESO);

        FormRenderer formRenderer = new FormRenderer(transaccionForm);
        formContainer.getChildren().add(formRenderer);
    }

    @FXML
    private void handleFormIngresoOGasto(ActionEvent event){
        Node source = (Node) event.getSource();
        formContainer.getChildren().clear();
        seccionTitulo.getChildren().clear();

        if(source.getId().equalsIgnoreCase("Ingreso")){
            this.transaccionForm = seleccionarForm(TipoTransaccion.INGRESO);
            FormRenderer formRenderer = new FormRenderer(transaccionForm);
            formContainer.getChildren().add(formRenderer);
        } else {
            this.transaccionForm = seleccionarForm(TipoTransaccion.GASTO);
            FormRenderer formRenderer = new FormRenderer(transaccionForm);
            formContainer.getChildren().add(formRenderer);
        }

    }

    @FXML
    private void handleGuardar(){
        transaccionForm.persist();

        if(transaccionForm.isValid()){
            TipoTransaccion tipo = model.tipoProperty().get();
            double monto = model.montoProperty().get();
            String descripcion = model.descripcionProperty().get();
            LocalDate fecha = model.fechaProperty().get();
            int horas = model.horasProperty().get();
            int minutos = model.minutosProperty().get();
            MetodoDePago metodoDePago = model.metodoDePagoProperty().get();

            if(tipo == TipoTransaccion.INGRESO){
                IngresoModel ingresoModel = (IngresoModel) model;
                FuenteIngreso fuente = ingresoModel.fuenteProperty().get();
                Ingreso ingreso = new Ingreso(monto, fecha, horas, minutos, descripcion, metodoDePago, fuente.toString());
                App.listaTransacciones.agregarTransaccion(ingreso);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Ingreso agregado con exito");
            }else{
                GastoModel gastoModel = (GastoModel) model;
                MotivoGasto motivo = gastoModel.motivoProperty().get();
                Gasto gasto = new Gasto(monto, fecha, horas, minutos, descripcion, metodoDePago, motivo.toString());
                App.listaTransacciones.agregarTransaccion(gasto);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Gasto agregado con exito");
            }
        }
    }

    private void insertarTitulo(String textoTitulo){
        Label titulo = new Label(textoTitulo);
        titulo.getStyleClass().add("titulo-h1");
        seccionTitulo.getChildren().setAll(titulo);
    }

    private Form seleccionarForm(TipoTransaccion tipo){
        if(tipo == TipoTransaccion.GASTO) {
            insertarTitulo("Agregar Gasto");
            return iniciarFormGasto();
        }else {
            insertarTitulo("Agregar Ingreso");
            return iniciarFormIngreso();
        }
    }

    private Form iniciarFormIngreso(){
        IngresoModel modelIngreso = new IngresoModel();

        //Metodo de pago
        ObservableList<MetodoDePago> opcionesMetodo = FXCollections.observableArrayList(MetodoDePago.values());
        ListProperty<MetodoDePago> metodosProperty = new SimpleListProperty<>(opcionesMetodo);

        //Fuente de ingreso
        ObservableList<FuenteIngreso> opcionesFuente = FXCollections.observableArrayList(FuenteIngreso.values());
        ListProperty<FuenteIngreso> fuentesProperty = new SimpleListProperty<>(opcionesFuente);

        Form ingresoForm =  Form.of(
                //Guardar.
                Group.of(

                        Field.ofDoubleType(modelIngreso.montoProperty())
                                .label("Monto")
                                .validate(
                                        DoubleRangeValidator.atLeast(0,"El monto no puede ser negativo.")
                                ),
                        Field.ofStringType(modelIngreso.descripcionProperty())
                                .label("Descripción"),
                        Field.ofDate(modelIngreso.fechaProperty())
                                .label("Fecha"),
                        Field.ofIntegerType(modelIngreso.horasProperty())
                                .label("Horas")
                                .validate(
                                        IntegerRangeValidator.between(0,23,"La hora debe estar entre 0 y 23.")
                                ),
                        Field.ofIntegerType(modelIngreso.minutosProperty())
                                .label("Minutos")
                                .validate(
                                        IntegerRangeValidator.between(0,59,"Los minutos deben estar entre 0 y 59.")
                                ),
                        Field.ofSingleSelectionType(fuentesProperty, modelIngreso.fuenteProperty())
                                .label("Fuente"), // Actualizar dependiendo de el valor de tipoProperty
                        Field.ofSingleSelectionType(metodosProperty, modelIngreso.metodoDePagoProperty())
                                .label("Metodo de Pago")
                )
        ).title("Agregar Ingreso");

        this.model = modelIngreso;
        return ingresoForm;
    }

    private Form iniciarFormGasto(){
        GastoModel modelIngreso = new GastoModel();

        //Metodo de pago
        ObservableList<MetodoDePago> opcionesMetodo = FXCollections.observableArrayList(MetodoDePago.values());
        ListProperty<MetodoDePago> metodosProperty = new SimpleListProperty<>(opcionesMetodo);

        //Motivo de gasto
        ObservableList<MotivoGasto> opcionesMotivo = FXCollections.observableArrayList(MotivoGasto.values());
        ListProperty<MotivoGasto> motivoProperty = new SimpleListProperty<>(opcionesMotivo);
        Form gastoForm = Form.of(
                //Guardar.
                Group.of(

                        Field.ofDoubleType(modelIngreso.montoProperty())
                                .label("Monto")
                                .validate(
                                        DoubleRangeValidator.atLeast(0,"El monto no puede ser negativo.")
                                ),
                        Field.ofStringType(modelIngreso.descripcionProperty())
                                .label("Descripción"),
                        Field.ofDate(modelIngreso.fechaProperty())
                                .label("Fecha"),
                        Field.ofIntegerType(modelIngreso.horasProperty())
                                .label("Horas")
                                .validate(
                                        IntegerRangeValidator.between(0,23,"La hora debe estar entre 0 y 23.")
                                ),
                        Field.ofIntegerType(modelIngreso.minutosProperty())
                                .label("Minutos")
                                .validate(
                                        IntegerRangeValidator.between(0,59,"Los minutos deben estar entre 0 y 59.")
                                ),
                        Field.ofSingleSelectionType(motivoProperty, modelIngreso.motivoProperty())
                                .label("Motivo"), // Actualizar dependiendo de el valor de tipoProperty
                        Field.ofSingleSelectionType(metodosProperty, modelIngreso.metodoDePagoProperty())
                                .label("Metodo de Pago")
                )
        ).title("Agregar Gasto");

        this.model = modelIngreso;
        return gastoForm;
    }
}
