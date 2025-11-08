package app.controllers;

import app.App;
import app.jsonUtils.JSONTransacciones;
import app.models.colecciones.ListaTransacciones;
import app.models.transacciones.Transaccion;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class HistorialController {

    @FXML private TableView<Transaccion> tablaTransacciones;
    @FXML private TableColumn<Transaccion, String> colFecha;
    @FXML private TableColumn<Transaccion, String> colDescripcion;
    @FXML private TableColumn<Transaccion, String> colMonto;

    @FXML private VBox panelDetalles;
    @FXML private Button btnFiltrarTodo;
    @FXML private Button btnFiltrarIngresos;
    @FXML private Button btnFiltrarGastos;

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private List<Transaccion> allTransacciones;
    private final ObservableList<Transaccion> displayed = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colFecha.setCellValueFactory(c -> {
            Transaccion t = c.getValue();
            String texto = t.getFecha() != null ? t.getFecha().format(formatoFecha) : "";
            return new SimpleStringProperty(texto);
        });

        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(
                Objects.toString(c.getValue().getDescripcion(), "")
        ));

        colMonto.setCellValueFactory(c -> new SimpleStringProperty(
                String.format("$ %.2f", c.getValue().getMonto())
        ));

        colMonto.setCellFactory(column -> new TableCell<Transaccion, String>() {
            @Override
            protected void updateItem(String montoStr, boolean empty) {
                super.updateItem(montoStr, empty);
                if (empty || montoStr == null) {
                    setText(null);
                    setStyle("");
                    return;
                }

                Transaccion t = getTableView().getItems().get(getIndex());
                boolean esIngreso = t instanceof Ingreso;

                double monto = t.getMonto();
                String textoFormateado = String.format("%s$ %.2f", esIngreso ? "+" : "-", monto);
                setText(textoFormateado);

                if (esIngreso) {
                    setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                } else {
                    setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                }
            }
        });

        tablaTransacciones.setItems(displayed);
        cargarDesdeJson();

        tablaTransacciones.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, transaccionSeleccionada) -> mostrarDetalles(transaccionSeleccionada)
        );

        btnFiltrarTodo.setOnAction(e -> aplicarFiltro(Filtro.TODO));
        btnFiltrarIngresos.setOnAction(e -> aplicarFiltro(Filtro.INGRESOS));
        btnFiltrarGastos.setOnAction(e -> aplicarFiltro(Filtro.GASTOS));

        tablaTransacciones.getSortOrder().clear();

        tablaTransacciones.getColumns().removeIf(col -> col.getText() == null || col.getText().isBlank());
    }

    private void cargarDesdeJson() {
        List<Transaccion> listaGenerica= App.listaTransacciones.getListaTransacciones();

        if (listaGenerica == null || listaGenerica.isEmpty()) {
            allTransacciones = List.of();
        } else {
            allTransacciones = listaGenerica;
            allTransacciones.sort(
                    Comparator.comparing(
                            Transaccion::getFecha,
                            Comparator.nullsLast(Comparator.reverseOrder())
                    )
            );
        }

        displayed.setAll(allTransacciones);
    }

    private enum Filtro { TODO, INGRESOS, GASTOS }

    private void aplicarFiltro(Filtro filtro) {
        switch (filtro) {
            case TODO -> displayed.setAll(allTransacciones);
            case INGRESOS -> displayed.setAll(
                    allTransacciones.stream().filter(t -> t instanceof Ingreso).toList()
            );
            case GASTOS -> displayed.setAll(
                    allTransacciones.stream().filter(t -> t instanceof Gasto).toList()
            );
        }
        panelDetalles.getChildren().clear();
        panelDetalles.getChildren().add(new Label("Selecciona una transacción para ver los detalles completos"));
    }

    private void mostrarDetalles(Transaccion t) {
        panelDetalles.getChildren().clear();
        if (t == null) return;

        panelDetalles.getChildren().add(new Label("Descripción: " + t.getDescripcion()));
        panelDetalles.getChildren().add(new Label(String.format("Monto: $ %.2f", t.getMonto())));
        panelDetalles.getChildren().add(new Label("Fecha: " + (t.getFecha() != null ? t.getFecha().toString() : "")));
        panelDetalles.getChildren().add(new Label("Método de pago: " + (t.getMetodoDePago() != null ? t.getMetodoDePago().name() : "")));
        panelDetalles.getChildren().add(new Label("ID: " + t.getId()));

        if (t instanceof Ingreso i) {
            panelDetalles.getChildren().add(new Label("Tipo: Ingreso"));
            panelDetalles.getChildren().add(new Label("Fuente: " + (i.getFuenteIngreso() != null ? i.getFuenteIngreso().name() : "")));
        } else if (t instanceof Gasto g) {
            panelDetalles.getChildren().add(new Label("Tipo: Gasto"));
            panelDetalles.getChildren().add(new Label("Motivo: " + (g.getMotivoGasto() != null ? g.getMotivoGasto().name() : "")));
        }
    }
}
