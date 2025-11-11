package app.enums;

public enum TipoTransaccion {
    INGRESO("Ingreso"),
    GASTO("Gasto");

    private final String tipo;

    TipoTransaccion(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
