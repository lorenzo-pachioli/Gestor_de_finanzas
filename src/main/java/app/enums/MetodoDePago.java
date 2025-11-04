package app.enums;

public enum MetodoDePago {
    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta"),
    TRANSFERENCIA("Transferencia");

    private final String metodo;

    MetodoDePago(String metodo){
        this.metodo = metodo;
    }
    @Override
    public String toString(){
        return metodo;
    }
}
