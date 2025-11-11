package app.models.transacciones;

import app.enums.MotivoGasto;
import app.enums.TipoTransaccion;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class GastoModel extends TransaccionModel{

    private final ObjectProperty<MotivoGasto> motivo = new SimpleObjectProperty<>();

    public ObjectProperty<MotivoGasto> motivoProperty() { return motivo; }

    @Override
    public ObjectProperty<TipoTransaccion> tipoProperty() {
        return new SimpleObjectProperty<>(TipoTransaccion.GASTO);
    }
}
