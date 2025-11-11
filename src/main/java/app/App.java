package app;

import app.models.colecciones.ListaPersonas;
import app.models.colecciones.ListaTransacciones;
import app.models.usuarios.Administrador;
import app.models.usuarios.Persona;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private static Stage stage;
    public static Persona persona;
    public static ListaPersonas listaPersonas = new ListaPersonas();
    public static ListaTransacciones listaTransacciones = new ListaTransacciones();

    public static void main(String[] args) {
        launch(); // Arranca la app y abre la ventana
    }

    @Override
    public void start(Stage primaryStage) {
            stage = primaryStage;
            stage.setTitle("Gestor de Finanzas");
            App.changeScene("logIn.fxml");
            stage.show();
    }

    // Metodo para cambiar la escena dentro de App(ventana completa)
    public static void changeScene(String fxml) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = null;
            ScrollPane sp = new ScrollPane(fxmlLoader.load());
            sp.setFitToWidth(true);
            sp.setFitToHeight(true);
            scene = new Scene(sp, 1200, 700);
            URL cssUrl = App.class.getResource("styles.css");
            assert cssUrl != null;
            scene.getStylesheets().add(cssUrl.toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPersona(Persona persona) {
        App.persona = persona;
    }

    public static void setListaPersonas(ListaPersonas listaPersonas) {
        if(persona instanceof Administrador) App.listaPersonas = listaPersonas;
    }
}