package app.models.colecciones;

import app.App;
import app.jsonUtils.JSONTransacciones;
import app.models.transacciones.Transaccion;
import app.models.usuarios.Administrador;

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

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones.getElementos();
    }


    public void agregarTransaccion(Transaccion transaccion) {
        JSONTransacciones.agregarTransaccion(transaccion);
        App.listaTransacciones.iniciarListaTransacciones();
    }

    public  void modificarTransaccion(Transaccion transaccion){
        JSONTransacciones.grabarUnaTransaccion(transaccion);
        App.listaTransacciones.iniciarListaTransacciones();
    }

    public void borrarTransaccion(Transaccion transaccion){
        JSONTransacciones.borrarTransaccion(transaccion);
        App.listaTransacciones.iniciarListaTransacciones();
    }

    @Override
    public String toString() {
        return "ListaTransacciones {" +
                "listaTransacciones= " + listaTransacciones +
                '}';
    }
}
