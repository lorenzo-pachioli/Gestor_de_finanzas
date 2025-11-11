package app.models.transacciones;

import app.App;
import app.Interfaces.Exportable;
import app.enums.MetodoDePago;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class Transaccion implements Exportable {
    private UUID id;
    private UUID personaID;
    private double monto;
    private LocalDateTime fecha;
    private String descripcion;
    private MetodoDePago metodoDePago;

/*    public Transaccion() {
        this.id = UUID.randomUUID();
        this.fecha = LocalDateTime.now();
    }*/

    public Transaccion(UUID id, UUID personaID, double monto, LocalDateTime fecha, String descripcion, MetodoDePago metodoDePago) {
        this.id = id;
        this.personaID = personaID;
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.metodoDePago = metodoDePago;
    }

    public Transaccion(double monto, LocalDate fecha, int horas, int minutos, String descripcion, MetodoDePago metodoDePago) {
        this.id = UUID.randomUUID();
        this.personaID = App.persona.getId();
        this.monto = monto;
        this.fecha = fecha.atTime(horas, minutos);
        this.descripcion = descripcion;
        this.metodoDePago = metodoDePago;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPersonaID() {
        return personaID;
    }

    public void setPersonaID(UUID personaID) {
        this.personaID = personaID;
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

    // Implementación de Exportable

    @Override
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%s||%s||%.2f||%s||%s||%s",
                this.id.toString().substring(0, 8),
                this.personaID.toString().substring(0, 8),
                this.monto,
                this.fecha.format(formatter),
                this.descripcion.replace(",", ";"), // Evitar problemas con comas en descripción
                this.metodoDePago.toString()
        );
    }

    @Override
    public String getCSVHeaders() {
        return "ID||PersonaID||Monto||Fecha||Descripcion||MetodoDePago";
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", personaID=" + personaID +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", metodoDePago=" + metodoDePago +
                '}';
    }
}
