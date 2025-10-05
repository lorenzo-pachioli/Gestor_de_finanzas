package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(); // Arranca la app y abre la ventana
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            stage = primaryStage;
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("logIn.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 640);
            URL cssUrl = App.class.getResource("styles.css");
            assert cssUrl != null;
            scene.getStylesheets().add(cssUrl.toExternalForm());
            stage.setTitle("Gestor de Finanzas");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void changeScene(String fxml) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 620, 640);
        stage.setScene(scene);
    }
}