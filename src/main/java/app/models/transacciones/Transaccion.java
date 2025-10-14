package app.models.transacciones;

import app.enums.MetodoDePago;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Transaccion {
    private UUID id;
    private double monto;
    private LocalDateTime fecha;
    private String descripcion;
    private MetodoDePago metodoDePago;

    public Transaccion() {
        this.id = UUID.randomUUID();
        this.fecha = LocalDateTime.now();
    }

    public Transaccion(double monto, String descripcion, MetodoDePago metodoDePago) {
        this.id = UUID.randomUUID();
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.descripcion = descripcion;
        this.metodoDePago = metodoDePago;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", metodoDePago=" + metodoDePago +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }
}
