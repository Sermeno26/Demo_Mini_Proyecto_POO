package com.abc.demo_mini_proyecto.controllers;

import com.abc.demo_mini_proyecto.Modelos.*;
import com.abc.demo_mini_proyecto.dao.FiguraDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainViewController {

    @FXML private ComboBox<String> cmbTipoFigura;
    @FXML private TextField txtNombre;
    @FXML private ColorPicker colorPicker;
    @FXML private VBox vboxCirculoCampos;
    @FXML private TextField txtRadio;
    @FXML private VBox vboxRectanguloCampos;
    @FXML private TextField txtBase;
    @FXML private TextField txtAltura;
    @FXML private Button btnAgregarFigura;

    @FXML private TableView<FiguraGeometrica> tablaFiguras;
    @FXML private TableColumn<FiguraGeometrica, String> colNombre;
    @FXML private TableColumn<FiguraGeometrica, String> colTipo;
    @FXML private TableColumn<FiguraGeometrica, String> colColor;
    @FXML private TableColumn<FiguraGeometrica, String> colDimensiones;
    @FXML private TableColumn<FiguraGeometrica, String> colArea;

    private final FiguraDAO figuraDAO = new FiguraDAO();
    private final ObservableList<FiguraGeometrica> figuras = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarFormulario();
        configurarTabla();
        cargarFiguras();
    }

    private void configurarFormulario() {
        cmbTipoFigura.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean esCirculo = "Círculo".equals(newVal);
            vboxCirculoCampos.setVisible(esCirculo);
            vboxCirculoCampos.setManaged(esCirculo);
            vboxRectanguloCampos.setVisible(!esCirculo);
            vboxRectanguloCampos.setManaged(!esCirculo);
        });
        cmbTipoFigura.getSelectionModel().selectFirst();
        colorPicker.setValue(Color.WHITE);
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colTipo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClass().getSimpleName()));
        colColor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getColor()));

        colDimensiones.setCellValueFactory(cell -> {
            FiguraGeometrica figura = cell.getValue();
            if (figura instanceof Circulo) {
                return new SimpleStringProperty("Radio: " + ((Circulo)figura).getRadio());
            } else if (figura instanceof Rectangulo) {
                return new SimpleStringProperty("Base: " + ((Rectangulo)figura).getBase() +
                        ", Altura: " + ((Rectangulo)figura).getAltura());
            }
            return new SimpleStringProperty("");
        });

        colArea.setCellValueFactory(cell ->
                new SimpleStringProperty(String.format("%.2f u²", cell.getValue().calcularArea())));

        tablaFiguras.setItems(figuras);
    }

    private void cargarFiguras() {
        figuras.setAll(figuraDAO.obtenerTodasFiguras());
    }

    @FXML
    private void agregarFigura() {
        try {
            String tipo = cmbTipoFigura.getValue();
            String nombre = txtNombre.getText().trim();
            String color = "#" + colorPicker.getValue().toString().substring(2, 8); // Hex sin alpha

            if (nombre.isEmpty()) {
                mostrarAlerta("Error", "El nombre es requerido", Alert.AlertType.ERROR);
                return;
            }

            FiguraGeometrica figura = null;

            if ("Círculo".equals(tipo)) {
                double radio = Double.parseDouble(txtRadio.getText());
                figura = new Circulo(color, nombre, radio);
            } else if ("Rectángulo".equals(tipo)) {
                double base = Double.parseDouble(txtBase.getText());
                double altura = Double.parseDouble(txtAltura.getText());
                figura = new Rectangulo(color, nombre, base, altura);
            }

            if (figura != null && figuraDAO.guardarFigura(figura)) {
                cargarFiguras();
                limpiarFormulario();
                mostrarAlerta("Éxito", "Figura agregada correctamente", Alert.AlertType.INFORMATION);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Valores numéricos inválidos", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtRadio.clear();
        txtBase.clear();
        txtAltura.clear();
        cmbTipoFigura.getSelectionModel().selectFirst();
        colorPicker.setValue(Color.WHITE);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}