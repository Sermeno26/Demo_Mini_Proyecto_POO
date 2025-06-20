package com.abc.demo_mini_proyecto.controllers;

import com.abc.demo_mini_proyecto.Circulo;
import com.abc.demo_mini_proyecto.FactoryFiguraGeometrica;
import com.abc.demo_mini_proyecto.Rectangulo;
import com.abc.demo_mini_proyecto.FiguraGeometrica;
import com.abc.demo_mini_proyecto.FactoryFiguraGeometrica;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;

public class FigureFormController {

    @FXML
    private ComboBox<String> cmbTipoFigura;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cmbColor;
    @FXML
    private VBox vboxCirculoCampos;
    @FXML
    private TextField txtRadio;
    @FXML
    private VBox vboxRectanguloCampos;
    @FXML
    private TextField txtBase;
    @FXML
    private TextField txtAltura;
    @FXML
    private Button btnAgregarFigura;


    @FXML
    public void initialize() {
        cmbTipoFigura.getSelectionModel().selectFirst();
        cmbColor.getSelectionModel().selectFirst();

        cmbTipoFigura.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("Círculo".equals(newValue)) {
                    vboxCirculoCampos.setVisible(true);
                    vboxCirculoCampos.setManaged(true);
                    vboxRectanguloCampos.setVisible(false);
                    vboxRectanguloCampos.setManaged(false);
                } else if ("Rectángulo".equals(newValue)) {
                    vboxCirculoCampos.setVisible(false);
                    vboxCirculoCampos.setManaged(false);
                    vboxRectanguloCampos.setVisible(true);
                    vboxRectanguloCampos.setManaged(true);
                }
            }
        });
    }

    @FXML
    private void agregarFigura() {
        String tipo = cmbTipoFigura.getValue();
        String nombre = txtNombre.getText();
        String color = cmbColor.getValue();

        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarAlerta("Error de Validación", "El nombre de la figura no puede estar vacío.", Alert.AlertType.ERROR);
            return;
        }

        try {
            FiguraGeometrica nuevaFigura = null;

            if ("Círculo".equals(tipo)) {
                double radio = Double.parseDouble(txtRadio.getText());

                nuevaFigura = FactoryFiguraGeometrica.crearFigura(tipo, color, nombre, radio);
            } else if ("Rectángulo".equals(tipo)) {
                double base = Double.parseDouble(txtBase.getText());
                double altura = Double.parseDouble(txtAltura.getText());

                nuevaFigura = FactoryFiguraGeometrica.crearFigura(tipo, color, nombre, base, altura);
            }

            if (nuevaFigura != null) {
                System.out.println("Figura agregada: " + nuevaFigura.getNombre() +
                        ", Tipo: " + nuevaFigura.getClass().getSimpleName() +
                        ", Color: " + nuevaFigura.getColor() +
                        ", Área: " + String.format("%.2f", nuevaFigura.calcularArea()));

                mostrarAlerta("Éxito", "Figura '" + nuevaFigura.getNombre() + "' agregada correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Entrada", "Por favor, ingrese valores numéricos válidos para las dimensiones.", Alert.AlertType.ERROR);
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Error de Creación", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtRadio.clear();
        txtBase.clear();
        txtAltura.clear();
        cmbTipoFigura.getSelectionModel().selectFirst();
        cmbColor.getSelectionModel().selectFirst();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}