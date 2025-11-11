package app.models.transacciones;

import app.Interfaces.Exportable;
import app.enums.MetodoDePago;
import app.enums.MotivoGasto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Gasto extends Transaccion  implements Exportable {
    private MotivoGasto motivoGasto;

    public Gasto(UUID id, UUID personaID, double monto, LocalDateTime fecha, String descripcion, MetodoDePago metodoDePago, MotivoGasto motivoGasto) {
        super(id, personaID, monto, fecha, descripcion, metodoDePago);
        this.motivoGasto = motivoGasto;
    }

    public Gasto(double monto, LocalDate fecha, int horas, int minutos, String descripcion, MetodoDePago metodoDePago, String motivoGasto) {
        super(monto, fecha, horas, minutos, descripcion, metodoDePago);
        this.motivoGasto = MotivoGasto.valueOf(motivoGasto);
    }

    @Override
    public String toCSV() {
        return super.toCSV() + String.format("||%s",this.motivoGasto);
    }

    @Override
    public String getCSVHeaders() {
        return super.getCSVHeaders() + "||Motivo o Fuente";
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
