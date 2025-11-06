package app.models.transacciones;

import app.enums.MetodoDePago;
import app.enums.TipoTransaccion;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class TransaccionModel {
    private final DoubleProperty monto = new SimpleDoubleProperty();
    private final StringProperty descripcion = new SimpleStringProperty("");
    private final ObjectProperty<MetodoDePago> metodoDePago = new SimpleObjectProperty<>();
    private final ObjectProperty<TipoTransaccion> tipo = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> fecha = new SimpleObjectProperty<>();
    private final IntegerProperty horas = new SimpleIntegerProperty(LocalDateTime.now().getHour());
    private final IntegerProperty minutos = new SimpleIntegerProperty(LocalDateTime.now().getMinute());


    public DoubleProperty montoProperty() { return monto; }
    public StringProperty descripcionProperty() { return descripcion; }
    public ObjectProperty<MetodoDePago> metodoDePagoProperty() { return metodoDePago; }
    public ObjectProperty<TipoTransaccion> tipoProperty() { return tipo; }
    public ObjectProperty<LocalDate> fechaProperty() { return fecha; }
    public IntegerProperty horasProperty() { return horas; }
    public IntegerProperty minutosProperty() { return minutos; }
}
