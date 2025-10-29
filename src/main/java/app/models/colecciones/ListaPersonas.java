package app.models.colecciones;

import app.App;
import app.jsonUtils.JSONPersonas;
import app.models.usuarios.Administrador;
import app.models.usuarios.Persona;
import java.util.ArrayList;

public class ListaPersonas {

    private ListaGenerica<Persona> listaPersonas;

    public ListaPersonas() {
        listaPersonas = new ListaGenerica<>();
    }

    public void iniciarListaPersonas() {
        this.listaPersonas = JSONPersonas.leerPersonasArray();
    }

    public void guardarListaPersonas() {
        JSONPersonas.grabarPersonas(this);
    }

    public ArrayList<Persona> getListaPersonas() {
        if (App.persona instanceof Administrador) return listaPersonas.getElementos();
        return new ArrayList<>();
    }

    public void setListaPersonas(ListaGenerica<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
        this.guardarListaPersonas();
    }

    public void agregarPersona(Persona persona) {
        this.listaPersonas.agregar(persona);
        this.guardarListaPersonas();
    }

    public  void modificarPersona(Persona persona){
        JSONPersonas.grabarUnaPersona(persona);
        iniciarListaPersonas();
    }

    public void borrarPersona(Persona persona){
        JSONPersonas.borrarUnaPersona(persona);
        iniciarListaPersonas();
    }

    @Override
    public String toString() {
        return "ListaPersonas {" +
                "listaPersonas= " + listaPersonas +
                '}';
    }
}
