package app.models.excepciones;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Usuario existente: " + super.getMessage();
    }
}
