package com.abc.demo_mini_proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Clase principal que lanza la aplicacion JavaFX para gestionar figuras geometricas.
 * Carga la vista principal desde el archivo FXML
 * Esta clase extiende {@link Application} y define el punto de entrada grafico.
 *
 * @author Diego Otoniel Mendez Cabrera #00010023
 * @author Daniel Alexander Sermeno Chinchilla #00030022
 * @author Rene Eduardo Gonzalez Iraheta #00128624
 * @version 2.0
 */
public class HelloApplication extends Application {

    /**
     * Metodo principal de arranque de la aplicación JavaFX.
     * Carga el archivo FXML y configura la ventana principal.
     *
     * @param primaryStage La ventana primaria del scene builder
     * @throws IOException Si el archivo FXML no se puede cargar.
     */

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL mainViewLocation = getClass().getResource("/com/abc/demo_mini_proyecto/Iconos/main-view.fxml");

        if (mainViewLocation == null) {
            System.err.println("Error: No se pudo encontrar main-view.fxml en la ruta: /com/abc/demo_mini_proyecto/main-view.fxml");
            throw new IOException("FXML file not found!");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(mainViewLocation);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Gestor de Figuras Geométricas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}