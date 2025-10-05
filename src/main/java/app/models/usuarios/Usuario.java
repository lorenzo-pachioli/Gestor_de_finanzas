package app.models.usuarios;


import app.enums.NivelAcceso;

public class Usuario extends Persona{
    private final NivelAcceso acceso = NivelAcceso.USUARIO;

    public Usuario(String nombre, String apellido, int dni, String email, int telefono) {
        super(nombre, apellido, dni, email, telefono);
    }

    public NivelAcceso getAcceso() {
        return acceso;
    }
}
