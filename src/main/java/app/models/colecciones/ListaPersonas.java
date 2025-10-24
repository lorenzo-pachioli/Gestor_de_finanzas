package app.models.colecciones;

import app.jsonUtils.JSONPersonas;
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

    public void cerrarListaPersonas() {
        JSONPersonas.grabarPersonas(listaPersonas);
    }

    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas.getElementos();
    }

    public void setListaPersonas(ListaGenerica<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public void agregarPersona(Persona persona) {
        this.listaPersonas.agregar(persona);
    }


}
