package app.models.excepciones;

public class FormularioIncorrectoException extends RuntimeException {
    public FormularioIncorrectoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Formaulario incorrecto: " +  super.getMessage();
    }
}
