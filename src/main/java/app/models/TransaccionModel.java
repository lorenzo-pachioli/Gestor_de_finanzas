package app.models;

import app.enums.MetodoDePago;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.Transaccion;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransaccionModel {
    private DoubleProperty monto = new SimpleDoubleProperty();
    private StringProperty descripcion = new SimpleStringProperty("");
    private ObjectProperty<MetodoDePago> metodoDePago = new SimpleObjectProperty<>();
    private ObjectProperty<String> tipo = new SimpleObjectProperty<>();
    private StringProperty MotivoOFuente = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> fecha = new SimpleObjectProperty<>();
    private IntegerProperty horas = new SimpleIntegerProperty(LocalDateTime.now().getHour());
    private IntegerProperty minutos = new SimpleIntegerProperty(LocalDateTime.now().getMinute());


    public DoubleProperty montoProperty() { return monto; }
    public StringProperty descripcionProperty() { return descripcion; }
    public ObjectProperty<MetodoDePago> metodoDePagoProperty() { return metodoDePago; }
    public ObjectProperty<String> tipoProperty() { return tipo; }
    public StringProperty categoriaOFuenteProperty() { return MotivoOFuente; }
    public ObjectProperty<LocalDate> fechaProperty() { return fecha; }
    public IntegerProperty horasProperty() { return horas; }
    public IntegerProperty minutosProperty() { return minutos; }

    public Transaccion build() {
        Transaccion transaccion;
        if ("INGRESO".equalsIgnoreCase(tipo.get())) {
            transaccion = new Ingreso(monto.get(), descripcion.get(), metodoDePago.get(), MotivoOFuente.get());
        } else {
            transaccion = new Gasto(monto.get(), descripcion.get(), metodoDePago.get(), MotivoOFuente.get());
        }
        transaccion.setFecha(fecha.get().atTime(horas.get(), minutos.get()));
        return transaccion;
    }
}
