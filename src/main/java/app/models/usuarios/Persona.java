package app.models.usuarios;

import app.App;
import app.jsonUtils.JSONPersonas;
import app.models.extras.PasswordAuth;

import java.util.UUID;

public abstract class Persona {
    private UUID id;
    private String nombre;
    private String apellido;
    private int dni;
    private String email;
    private int telefono;
    private String contrasenia;
    private boolean bloqueado = false;

    public Persona() {
    }

    public Persona(String nombre, String apellido, int dni, String email, int telefono, String contrasenia) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = PasswordAuth.hash(contrasenia);
    }

    public Persona(UUID id, String nombre, String apellido, int dni, String email, int telefono, String contrasenia, boolean bloqueado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.bloqueado = bloqueado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        JSONPersonas.grabarUnaPersona(this);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        JSONPersonas.grabarUnaPersona(this);
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
        JSONPersonas.grabarUnaPersona(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        JSONPersonas.grabarUnaPersona(this);
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
        JSONPersonas.grabarUnaPersona(this);
    }

    public String getContrasenia() {
         return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        if (App.persona instanceof Administrador) {
            this.bloqueado = bloqueado;
            JSONPersonas.grabarUnaPersona(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return nombre.compareTo(persona.getNombre()) == 0 && dni == persona.dni;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", ";
    }


    public boolean isValid(){
        if(
                this.id != null &&
                this.nombre != null &&
                this.apellido != null &&
                this.contrasenia != null &&
                this.email != null ){
            return true;
        }
        return false;
    }
}
