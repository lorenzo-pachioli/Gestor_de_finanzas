package app.controllersAdmin;

import app.App;
import app.controllers.Controller;
import app.enums.NivelAcceso;
import app.models.colecciones.ListaPersonas;
import app.models.usuarios.Persona;
import app.models.usuarios.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HomeAdminController2 extends Controller {

    @FXML
    private TableView<PersonaTableData> tablaUsuarios;

    @FXML
    private TableColumn<PersonaTableData, String> colId;

    @FXML
    private TableColumn<PersonaTableData, String> colAcceso;

    @FXML
    private TableColumn<PersonaTableData, String> colNombre;

    @FXML
    private TableColumn<PersonaTableData, String> colApellido;

    @FXML
    private TableColumn<PersonaTableData, String> colDni;

    @FXML
    private TableColumn<PersonaTableData, String> colTelefono;

    @FXML
    private TableColumn<PersonaTableData, String> colEmail;

    @FXML
    private TableColumn<PersonaTableData, String> colBloqueado;

    @FXML
    private TableColumn<PersonaTableData, Void> colAcciones;

    private Stage verMovimientos;
    private ObservableList<PersonaTableData> datosUsuarios;

    @FXML
    public void initialize() {
        verMovimientos = new Stage();
        verMovimientos.initModality(Modality.APPLICATION_MODAL);

        configurarColumnas();
        cargarDatosTabla();
    }

    private void configurarColumnas() {
        // Configurar columnas con PropertyValueFactory
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAcceso.setCellValueFactory(new PropertyValueFactory<>("acceso"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colBloqueado.setCellValueFactory(new PropertyValueFactory<>("bloqueado"));

        // Configurar columna de acciones con botones personalizados
        colAcciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnBorrar = new Button("Borrar");
            private final Button btnBloquear = new Button("Bloquear");
            private final Button btnTransacciones = new Button("Ver movimientos");
            private final HBox contenedor = new HBox(10);

            {
                btnBorrar.getStyleClass().add("btn-danger");
                btnBloquear.getStyleClass().add("btn-warning");
                btnTransacciones.getStyleClass().add("btn-info");

                contenedor.setAlignment(Pos.CENTER);

                btnBorrar.setOnAction(event -> {
                    PersonaTableData data = getTableView().getItems().get(getIndex());
                    eliminarUsuario(data.getPersona());
                });

                btnBloquear.setOnAction(event -> {
                    PersonaTableData data = getTableView().getItems().get(getIndex());
                    toggleBloqueo(data.getPersona());
                });

                btnTransacciones.setOnAction(event -> {
                    PersonaTableData data = getTableView().getItems().get(getIndex());
                    mostrarHistorial(data.getPersona());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                } else {
                    PersonaTableData data = getTableView().getItems().get(getIndex());
                    Persona persona = data.getPersona();

                    // Actualizar texto del botón bloquear
                    btnBloquear.setText(persona.isBloqueado() ? "Desbloquear" : "Bloquear");

                    // Configurar botones según el tipo de usuario
                    if (persona instanceof Usuario) {
                        contenedor.getChildren().setAll(btnBorrar, btnBloquear, btnTransacciones);
                    } else {
                        contenedor.getChildren().setAll(btnBorrar, btnBloquear);
                    }

                    setGraphic(contenedor);
                }
            }
        });
    }

    private void cargarDatosTabla() {
        ListaPersonas listaPersonas = App.listaPersonas;
        datosUsuarios = FXCollections.observableArrayList();

        for (Persona persona : listaPersonas.getListaPersonas()) {
            datosUsuarios.add(new PersonaTableData(persona));
        }

        tablaUsuarios.setItems(datosUsuarios);
    }

    private void eliminarUsuario(Persona persona) {
        boolean confirmacion = mostrarAlerta(
                Alert.AlertType.WARNING,
                "Borrar usuario " + persona.getNombre(),
                "¿Está seguro que quiere borrar el usuario con mail: " + persona.getEmail() + "?"
        );

        if (confirmacion) {
            App.listaPersonas.borrarPersona(persona);
            cargarDatosTabla();
        }
    }

    private void toggleBloqueo(Persona persona) {
        persona.setBloqueado(!persona.isBloqueado());
        cargarDatosTabla();
    }

    private void mostrarHistorial(Persona persona) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("historial.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 700);
            URL cssUrl = App.class.getResource("styles.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }
            verMovimientos.setTitle("Historial de " + persona.getNombre());
            verMovimientos.setScene(scene);
            verMovimientos.showAndWait();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir el historial: " + e.getMessage());
        }
    }

    // Clase interna para representar los datos de la tabla
    public static class PersonaTableData {
        private final Persona persona;
        private final SimpleStringProperty id;
        private final SimpleStringProperty acceso;
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty apellido;
        private final SimpleStringProperty dni;
        private final SimpleStringProperty telefono;
        private final SimpleStringProperty email;
        private final SimpleStringProperty bloqueado;

        public PersonaTableData(Persona persona) {
            this.persona = persona;
            this.id = new SimpleStringProperty(persona.getId().toString().substring(0, 8));
            this.acceso = new SimpleStringProperty(
                    persona instanceof Usuario ? NivelAcceso.USUARIO.toString() : NivelAcceso.ADMINISTRADOR.toString()
            );
            this.nombre = new SimpleStringProperty(persona.getNombre());
            this.apellido = new SimpleStringProperty(persona.getApellido());
            this.dni = new SimpleStringProperty(String.valueOf(persona.getDni()));
            this.telefono = new SimpleStringProperty(String.valueOf(persona.getTelefono()));
            this.email = new SimpleStringProperty(persona.getEmail());
            this.bloqueado = new SimpleStringProperty(persona.isBloqueado() ? "Sí" : "No");
        }

        // Getters para PropertyValueFactory
        public String getId() { return id.get(); }
        public String getAcceso() { return acceso.get(); }
        public String getNombre() { return nombre.get(); }
        public String getApellido() { return apellido.get(); }
        public String getDni() { return dni.get(); }
        public String getTelefono() { return telefono.get(); }
        public String getEmail() { return email.get(); }
        public String getBloqueado() { return bloqueado.get(); }
        public Persona getPersona() { return persona; }

        // Property getters para binding
        public SimpleStringProperty idProperty() { return id; }
        public SimpleStringProperty accesoProperty() { return acceso; }
        public SimpleStringProperty nombreProperty() { return nombre; }
        public SimpleStringProperty apellidoProperty() { return apellido; }
        public SimpleStringProperty dniProperty() { return dni; }
        public SimpleStringProperty telefonoProperty() { return telefono; }
        public SimpleStringProperty emailProperty() { return email; }
        public SimpleStringProperty bloqueadoProperty() { return bloqueado; }
    }
}
