package app.models.excepciones;

public class SaldoInsuficiente extends RuntimeException {
    public SaldoInsuficiente(String message) {
        super(message);
    }
}
