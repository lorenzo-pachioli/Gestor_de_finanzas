package app.controllers;

import app.App;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.Transaccion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.*;
import java.util.stream.Collectors;

public class CategoriaController {

    @FXML
    private Label lblIngresosTotales, lblGastosTotales, lblBalanceTotal, lblCategoriaSeleccionada;

    @FXML
    private ListView<String> listaFuentesIngresos, listaMotivosGastos, listaTransacciones;

    private List<Transaccion> transacciones;

    private Map<String, List<Transaccion>> ingresosPorFuente = new HashMap<>();
    private Map<String, List<Transaccion>> gastosPorMotivo = new HashMap<>();

    @FXML
    public void initialize() {
        transacciones = App.listaTransacciones.getListaTransacciones();

        double totalIngresos = 0;
        double totalGastos = 0;

        for (Transaccion t : transacciones) {
            if (t instanceof Ingreso ingreso) {
                String fuente = (ingreso.getFuenteIngreso() == null || ingreso.getFuenteIngreso().name().isBlank())
                        ? "Otros" : ingreso.getFuenteIngreso().name();
                ingresosPorFuente.computeIfAbsent(fuente, k -> new ArrayList<>()).add(ingreso);
                totalIngresos += ingreso.getMonto();
            } else if (t instanceof Gasto gasto) {
                String motivo = (gasto.getMotivoGasto() == null || gasto.getMotivoGasto().name().isBlank())
                        ? "Otros" : gasto.getMotivoGasto().name();
                gastosPorMotivo.computeIfAbsent(motivo, k -> new ArrayList<>()).add(gasto);
                totalGastos += gasto.getMonto();
            }
        }

        lblIngresosTotales.setText("Ingresos: $" + String.format("%.2f", totalIngresos));
        lblGastosTotales.setText("Gastos: $" + String.format("%.2f", totalGastos));

        double balance = totalIngresos - totalGastos;
        lblBalanceTotal.setText(String.format("Balance: %s$%.2f",
                balance >= 0 ? "+" : "-", Math.abs(balance)));
        lblBalanceTotal.setStyle(balance >= 0 ?
                "-fx-text-fill: green; -fx-font-weight: bold;" :
                "-fx-text-fill: red; -fx-font-weight: bold;");

        listaFuentesIngresos.setItems(FXCollections.observableArrayList(
                ingresosPorFuente.entrySet().stream()
                        .map(e -> e.getKey() + " (+$" + String.format("%.2f",
                                e.getValue().stream().mapToDouble(Transaccion::getMonto).sum()) + ")")
                        .collect(Collectors.toList())
        ));

        listaMotivosGastos.setItems(FXCollections.observableArrayList(
                gastosPorMotivo.entrySet().stream()
                        .map(e -> e.getKey() + " (-$" + String.format("%.2f",
                                e.getValue().stream().mapToDouble(Transaccion::getMonto).sum()) + ")")
                        .collect(Collectors.toList())
        ));

        listaFuentesIngresos.setOnMouseClicked(event -> {
            String seleccion = listaFuentesIngresos.getSelectionModel().getSelectedItem();
            if (seleccion != null) mostrarTransaccionesCategoria(seleccion, true);
        });

        listaMotivosGastos.setOnMouseClicked(event -> {
            String seleccion = listaMotivosGastos.getSelectionModel().getSelectedItem();
            if (seleccion != null) mostrarTransaccionesCategoria(seleccion, false);
        });
    }

    private void mostrarTransaccionesCategoria(String seleccion, boolean esIngreso) {
        String categoria = seleccion.split(" \\(")[0];
        lblCategoriaSeleccionada.setText("Transacciones de: " + categoria);

        List<Transaccion> lista = esIngreso ?
                ingresosPorFuente.getOrDefault(categoria, Collections.emptyList()) :
                gastosPorMotivo.getOrDefault(categoria, Collections.emptyList());

        listaTransacciones.setItems(FXCollections.observableArrayList(
                lista.stream()
                        .map(t -> String.format("%s | %s | $%.2f",
                                t.getFecha(), t.getDescripcion(), t.getMonto()))
                        .collect(Collectors.toList())
        ));
    }
}
