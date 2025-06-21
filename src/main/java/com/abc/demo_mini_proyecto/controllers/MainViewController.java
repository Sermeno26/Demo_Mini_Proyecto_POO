package com.abc.demo_mini_proyecto.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox; // Importa VBox
import java.io.IOException;
import java.net.URL;

public class MainViewController {

    @FXML
    private VBox addFigurePanel;

    @FXML
    public void initialize() {
        try {
            // Cargar el formulario de agregar figura en el panel
            // Asegúrate de que la ruta sea correcta para add-figure-form.fxml
            URL formFxmlLocation = getClass().getResource("/com/abc/demo_mini_proyecto/Iconos/add-figure-form.fxml");

            if (formFxmlLocation == null) {
                System.err.println("Error: No se pudo encontrar add-figure-form.fxml en la ruta: /com/abc/demo_mini_proyecto/add-figure-form.fxml");
                return; // O lanza una excepción para detener la aplicación
            }

            FXMLLoader formLoader = new FXMLLoader(formFxmlLocation);
            VBox formContent = formLoader.load(); // Carga el contenido como VBox

            if (addFigurePanel != null) {
                addFigurePanel.getChildren().add(formContent);
            } else {
                System.err.println("Error: El fx:id 'addFigurePanel' no se encontró en main-view.fxml");
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el formulario de agregar figura: " + e.getMessage());
            e.printStackTrace();
        }
    }
}