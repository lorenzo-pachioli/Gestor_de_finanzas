package app.models.usuarios;


import app.App;
import app.enums.NivelAcceso;

import java.util.UUID;

public class Usuario extends Persona{
    private final NivelAcceso acceso = NivelAcceso.USUARIO;


    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int dni, String email, int telefono, String contrasenia) {
        super(nombre, apellido, dni, email, telefono, contrasenia);
    }

    public Usuario(UUID id, String nombre, String apellido, int dni, String email, int telefono, String contrasenia, boolean bloqueado) {
        super(id, nombre, apellido, dni, email, telefono, contrasenia, bloqueado);
    }

    public NivelAcceso getAcceso() {
        return acceso;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Usuario= {" +
                super.toString() +
                "acceso=" + acceso +
                "} ";
    }
}
