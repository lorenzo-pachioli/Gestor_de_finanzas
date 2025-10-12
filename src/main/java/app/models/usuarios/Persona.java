package app.models.usuarios;

import app.App;
import java.util.UUID;

public abstract class Persona {
    private UUID id;
    private String nombre;
    private String apellido;
    private int dni;
    private String email;
    private int telefono;
    private String contrasenia;

    public Persona() {
    }

    public Persona(String nombre, String apellido, int dni, String email, int telefono, String contrasenia) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
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
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getContrasenia() {
        if(App.getUsuario().equals(this)) return contrasenia;
        return " No tiene acceso ";
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
