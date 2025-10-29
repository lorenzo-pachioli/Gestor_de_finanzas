package app.models.usuarios;


import app.enums.NivelAcceso;

public class Usuario extends Persona{
    private final NivelAcceso acceso = NivelAcceso.USUARIO;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int dni, String email, int telefono, String contrasenia) {
        super(nombre, apellido, dni, email, telefono, contrasenia);
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
