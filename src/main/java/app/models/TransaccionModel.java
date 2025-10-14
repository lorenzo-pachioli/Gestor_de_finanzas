package app.models;

import app.enums.MetodoDePago;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.Transaccion;
import javafx.beans.property.*;

import java.time.LocalDate;

public class TransaccionModel {
    private DoubleProperty monto = new SimpleDoubleProperty();
    private StringProperty descripcion = new SimpleStringProperty("");
    private ObjectProperty<MetodoDePago> metodoDePago = new SimpleObjectProperty<>();
    private ObjectProperty<String> tipo = new SimpleObjectProperty<>();
    private StringProperty MotivoOFuente = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> fecha = new SimpleObjectProperty<>();

    public DoubleProperty montoProperty() { return monto; }
    public StringProperty descripcionProperty() { return descripcion; }
    public ObjectProperty<MetodoDePago> metodoDePagoProperty() { return metodoDePago; }
    public ObjectProperty<String> tipoProperty() { return tipo; }
    public StringProperty categoriaOFuenteProperty() { return MotivoOFuente; }
    public ObjectProperty<LocalDate> fechaProperty() { return fecha; }

    public Transaccion build() {
        Transaccion transaccion;
        if ("INGRESO".equalsIgnoreCase(tipo.get())) {
            transaccion = new Ingreso(monto.get(), descripcion.get(), metodoDePago.get(), MotivoOFuente.get());
        } else {
            transaccion = new Gasto(monto.get(), descripcion.get(), metodoDePago.get(), MotivoOFuente.get());
        }
        transaccion.setFecha(fecha.get().atTime(12, 0));
        return transaccion;
    }
}
