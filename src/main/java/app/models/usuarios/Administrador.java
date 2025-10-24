package app.models.usuarios;


import app.enums.NivelAcceso;

import java.util.UUID;

public class Administrador extends Persona{
    private final NivelAcceso acceso = NivelAcceso.ADMINISTRADOR;

    public Administrador(String nombre, String apellido, int dni, String email, int telefono, String contrasenia) {
        super(nombre, apellido, dni, email, telefono, contrasenia);
    }

    public Administrador(UUID id, String nombre, String apellido, int dni, String email, int telefono, String contrasenia) {
        super(id, nombre, apellido, dni, email, telefono, contrasenia);
    }


    public NivelAcceso getAcceso() {
        return acceso;
    }
}
