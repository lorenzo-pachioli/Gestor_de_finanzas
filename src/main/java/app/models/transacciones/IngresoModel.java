package app.models.transacciones;

import app.enums.FuenteIngreso;
import app.enums.TipoTransaccion;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class IngresoModel extends TransaccionModel{

    private final ObjectProperty<FuenteIngreso> fuente = new SimpleObjectProperty<>();

    public ObjectProperty<FuenteIngreso> fuenteProperty() { return fuente; }

    @Override
    public ObjectProperty<TipoTransaccion> tipoProperty() {
        return new SimpleObjectProperty<>(TipoTransaccion.INGRESO);
    }
}
