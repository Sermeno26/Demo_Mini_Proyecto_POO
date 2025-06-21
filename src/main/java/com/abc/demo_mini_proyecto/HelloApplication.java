package com.abc.demo_mini_proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Cargar la vista principal (main-view.fxml)
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