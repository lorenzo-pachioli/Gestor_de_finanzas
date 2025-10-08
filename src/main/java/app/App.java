package app;

import app.models.usuarios.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private static Stage stage;
    private static Usuario usuario;

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

    public static void changeScene(String fxml) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 620, 640);
            URL cssUrl = App.class.getResource("styles.css");
            assert cssUrl != null;
            scene.getStylesheets().add(cssUrl.toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setUsuario(Usuario nuevoUsuario){
        usuario = nuevoUsuario;
    }

    public static Usuario getUsuario() {
        return usuario;
    }
}