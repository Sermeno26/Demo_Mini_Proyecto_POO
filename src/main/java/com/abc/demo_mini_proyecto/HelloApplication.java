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
        URL fxmlLocation = getClass().getResource("/com/abc/demo_mini_proyecto/Iconos/hello-view.fxml");

        if (fxmlLocation == null) {
            System.err.println("Error: No se pudo encontrar el archivo FXML en la ruta: /com/abc/demo_mini_proyecto/Iconos/hello-view.fxml");
            throw new IOException("FXML file not found!");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Gestor de Figuras Geometricas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}