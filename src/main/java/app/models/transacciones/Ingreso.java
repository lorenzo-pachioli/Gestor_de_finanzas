package app.models.transacciones;

import app.enums.FuenteIngreso;
import app.enums.MetodoDePago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ingreso extends Transaccion{
    private FuenteIngreso fuenteIngreso;

    public Ingreso(UUID id, UUID personaID, double monto, LocalDateTime fecha, String descripcion, MetodoDePago metodoDePago, FuenteIngreso fuenteIngreso) {
        super(id, personaID, monto, fecha, descripcion, metodoDePago);
        this.fuenteIngreso = fuenteIngreso;
    }

    public Ingreso(double monto, LocalDate fecha, int horas, int minutos, String descripcion, MetodoDePago metodoDePago, String fuenteIngreso) {
        super(monto, fecha, horas, minutos, descripcion, metodoDePago);
        this.fuenteIngreso = FuenteIngreso.valueOf(fuenteIngreso);
    }

    @Override
    public String toString() {
        return "Ingreso{" +
                "fuenteIngreso=" + fuenteIngreso +
                "} " + super.toString();
    }

    public FuenteIngreso getFuenteIngreso() {
        return fuenteIngreso;
    }

    public void setFuenteIngreso(FuenteIngreso fuenteIngreso) {
        this.fuenteIngreso = fuenteIngreso;
    }
}
