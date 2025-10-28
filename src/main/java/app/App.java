package app;

import app.jsonUtils.JSONPersonas;
import app.models.colecciones.ListaPersonas;
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
    private static Persona persona;
    private static ListaPersonas listaPersonas = new ListaPersonas();

    public static void main(String[] args) {
        launch(); // Arranca la app y abre la ventana

        /*
        * Que es mas eficiente:
        * 1) modificar la lista en java y despues pisar todoe el json
        * 2) mosificar el elemnto en el json y despues pisar la lista en java
        * */
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
            scene = new Scene(sp, 620, 640);
            URL cssUrl = App.class.getResource("styles.css");
            assert cssUrl != null;
            scene.getStylesheets().add(cssUrl.toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Persona getPersona() {
        return persona;
    }

    public static void setPersona(Persona persona) {
        App.persona = persona;
    }

    public static ListaPersonas getListaPersonas() {
        if(persona instanceof Administrador){
            return listaPersonas;
        }
        return new ListaPersonas();
    }

    public static void setListaPersonas(ListaPersonas listaPersonas) {
        if(persona instanceof Administrador) App.listaPersonas = listaPersonas;
    }
}