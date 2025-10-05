package app.models.excepciones;

public class PresupuestoExcedido extends RuntimeException {
    public PresupuestoExcedido(String message) {
        super(message);
    }
}
