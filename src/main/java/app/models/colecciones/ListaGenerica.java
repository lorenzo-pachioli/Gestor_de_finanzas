package app.models.colecciones;

import java.time.LocalDate;
import java.util.ArrayList;

abstract class ListaGenerica <T> {
    private ArrayList<T> elementos;
    private LocalDate fechaCreacion;

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public ArrayList<T> filtrar() {
        return null;
    }

    public ArrayList<T> ordenar() {
        return null;
    }
}
