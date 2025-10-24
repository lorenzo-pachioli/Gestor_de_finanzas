package app.models.colecciones;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListaGenerica <T> {
    private ArrayList<T> elementos;
    private LocalDate ultimaActualizacion;

    public ListaGenerica() {
        ultimaActualizacion = LocalDate.now();
        this.elementos = new ArrayList<>();
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<T> elementos) {
        ultimaActualizacion = LocalDate.now();
        this.elementos = elementos;
    }

    public LocalDate getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDate ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public void agregar(T elemento) {
        ultimaActualizacion = LocalDate.now();
        elementos.add(elemento);
    }

    public void remover(T elemento){
        ultimaActualizacion = LocalDate.now();
        elementos.remove(elemento);
    }

    @Override
    public String toString() {
        return "{" +
                "ultimaActualizacion= " + ultimaActualizacion +
                ",\n elementos= " + elementos +
                '}';
    }
}
