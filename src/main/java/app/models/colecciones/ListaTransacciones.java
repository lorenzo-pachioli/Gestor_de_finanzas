package app.models.colecciones;

import app.App;
import app.jsonUtils.JSONPersonas;
import app.jsonUtils.JSONTransacciones;
import app.models.transacciones.Transaccion;
import app.models.usuarios.Administrador;
import app.models.usuarios.Persona;

import java.util.ArrayList;

public class ListaTransacciones {
    private ListaGenerica<Transaccion> listaTransacciones;

    public ListaTransacciones() {
        listaTransacciones = new ListaGenerica<>();
    }

    public void iniciarListaTransacciones() {
        this.listaTransacciones = JSONTransacciones.leerTransaccionesArray();
    }

    public void guardarListaTransacciones() {
        JSONTransacciones.grabarTransacciones(this);
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        if (App.persona instanceof Administrador) return listaTransacciones.getElementos();
        return new ArrayList<>();
    }

    public void setListaTransacciones(ListaGenerica<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
        this.guardarListaTransacciones();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        this.listaTransacciones.agregar(transaccion);
        this.guardarListaTransacciones();
    }

    public  void modificarTransaccion(Persona persona){
        JSONPersonas.grabarUnaPersona(persona);
        guardarListaTransacciones();
    }

    public void borrarTransaccion(Persona persona){
        JSONPersonas.borrarUnaPersona(persona);
        guardarListaTransacciones();
    }

    @Override
    public String toString() {
        return "ListaTransacciones {" +
                "listaTransacciones= " + listaTransacciones +
                '}';
    }
}
