package app.controllers;

import app.App;
import app.jsonUtils.JSONTransacciones;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.Transaccion;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;
public class HomeController {
    @FXML
    private Text ingresos;

    @FXML
    private Text gastos;

    @FXML
    private Text total;

    @FXML
    public void initialize() {
        cargarDatosFinancieros();
    }

    private void cargarDatosFinancieros() {
        // Verificar que haya un usuario logueado
        if (App.persona == null) {
            mostrarValoresPorDefecto();
            return;
        }

        // Obtener todas las transacciones del usuario actual
        ArrayList<Transaccion> transacciones = App.listaTransacciones.getListaTransacciones();

        // Calcular totales
        double totalIngresos = 0.0;
        double totalGastos = 0.0;

        for (Transaccion transaccion : transacciones) {
            if (transaccion instanceof Ingreso) {
                totalIngresos += transaccion.getMonto();
            } else if (transaccion instanceof Gasto) {
                totalGastos += transaccion.getMonto();
            }
        }

        // Calcular balance total
        double balanceTotal = totalIngresos - totalGastos;

        // Actualizar los textos en la interfaz
        actualizarInterfaz(totalIngresos, totalGastos, balanceTotal);
    }

    private void actualizarInterfaz(double totalIngresos, double totalGastos, double balanceTotal) {
        // Formatear y mostrar ingresos
        if (ingresos != null) {
            ingresos.setText(String.format("$%.2f", totalIngresos));
            // Color verde para ingresos
            ingresos.setStyle("-fx-fill: #00bc09;");
        }

        // Formatear y mostrar gastos
        if (gastos != null) {
            gastos.setText(String.format("$%.2f", totalGastos));
            // Color rojo para gastos
            gastos.setStyle("-fx-fill: #e10404;");
        }

        // Formatear y mostrar total con color según balance
        if (total != null) {
            total.setText(String.format("$%.2f", balanceTotal));

            // Cambiar color según si es positivo o negativo
            if (balanceTotal >= 0) {
                total.setStyle("-fx-fill: #00bc09;"); // Verde si es positivo o cero
            } else {
                total.setStyle("-fx-fill: #e10404;"); // Rojo si es negativo
            }
        }
    }

    private void mostrarValoresPorDefecto() {
        if (ingresos != null) {
            ingresos.setText("$0.00");
            ingresos.setStyle("-fx-fill: #00bc09;");
        }
        if (gastos != null) {
            gastos.setText("$0.00");
            gastos.setStyle("-fx-fill: #e10404;");
        }
        if (total != null) {
            total.setText("$0.00");
            total.setStyle("-fx-fill: #00bc09;");
        }
    }

    public void refrescarDatos() {
        cargarDatosFinancieros();
    }

}

