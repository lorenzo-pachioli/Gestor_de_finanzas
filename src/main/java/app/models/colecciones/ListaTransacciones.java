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
        if(App.persona instanceof Administrador){
            this.listaTransacciones = JSONTransacciones.leerTransaccionesArray();
        } else {
            this.listaTransacciones = JSONTransacciones.leerTransaccionesArrayPorUsuario();
        }
    }

    public void guardarListaTransacciones() {
        JSONTransacciones.grabarTransacciones(this);
        App.listaTransacciones.iniciarListaTransacciones();
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones.getElementos();
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
