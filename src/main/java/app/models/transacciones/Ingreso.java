package app.models.transacciones;

import app.enums.FuenteIngreso;
import app.enums.MetodoDePago;

public class Ingreso extends Transaccion{
    private FuenteIngreso fuenteIngreso;

    public Ingreso(double monto, String descripcion, MetodoDePago metodoDePago, String fuenteIngreso) {
        super(monto, descripcion, metodoDePago);
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
