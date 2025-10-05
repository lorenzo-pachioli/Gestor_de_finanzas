package app.models.usuarios;


import app.enums.NivelAcceso;

public class Administrador extends Persona{
    private final NivelAcceso acceso = NivelAcceso.ADMINISTRADOR;

    public Administrador(String nombre, String apellido, int dni, String email, int telefono) {
        super(nombre, apellido, dni, email, telefono);
    }

    public NivelAcceso getAcceso() {
        return acceso;
    }
}
