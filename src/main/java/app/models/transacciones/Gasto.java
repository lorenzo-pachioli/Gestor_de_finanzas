package app.models.transacciones;

import app.enums.MetodoDePago;
import app.enums.MotivoGasto;

import java.time.LocalDate;

public class Gasto extends Transaccion{
    private MotivoGasto motivoGasto;

    public Gasto(double monto, LocalDate fecha, int horas, int minutos, String descripcion, MetodoDePago metodoDePago, String motivoGasto) {
        super(monto, fecha, horas, minutos, descripcion, metodoDePago);
        this.motivoGasto = MotivoGasto.valueOf(motivoGasto);
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "motivoGasto=" + motivoGasto +
                "} " + super.toString();
    }

    public MotivoGasto getMotivoGasto() {
        return motivoGasto;
    }

    public void setMotivoGasto(MotivoGasto motivoGasto) {
        this.motivoGasto = motivoGasto;
    }
}
