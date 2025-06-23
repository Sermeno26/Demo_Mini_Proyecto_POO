package com.abc.demo_mini_proyecto.controllers;

import com.abc.demo_mini_proyecto.Modelos.FactoryFiguraGeometrica;
import com.abc.demo_mini_proyecto.Modelos.FiguraGeometrica;
import com.abc.demo_mini_proyecto.dao.FiguraDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la vista del formulario de figuras geometricas.
 * Administra la logica de creacion de figuras (Circulo o Rectangulo),
 * la insercion de datos y la visualizacion en una tabla.
 *
 * @author Diego Otoniel Mendez Cabrera #00010023
 * @author Daniel Alexander Sermeno Chinchilla #00030022
 * @author Rene Eduardo Gonzalez Iraheta #00128624
 * @version 2.0
 */

public class FigureFormController {

    @FXML
    private ComboBox<String> cmbTipoFigura;
    @FXML
    private TextField txtNombre;
    @FXML
    private ColorPicker colorPicker;
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

    private FiguraDAO figuraDAO;

    @FXML
    private TableView<FiguraGeometrica> tablaFiguras;
    @FXML
    private TableColumn<FiguraGeometrica, String> colNombre;
    @FXML
    private TableColumn<FiguraGeometrica, String> colTipo;
    @FXML
    private TableColumn<FiguraGeometrica, String> colColor;
    @FXML
    private TableColumn<FiguraGeometrica, String> colArea;

    private ObservableList<FiguraGeometrica> figurasObservableList = FXCollections.observableArrayList();

    /**
     * Es un metodo que se ejecuta automáticamente al cargar la vista.
     * Inicializa valores predeterminados, configura el comportamiento del pedido de datos
     * de las figuras
     */

    @FXML
    public void initialize() {
        try {
            System.out.println("Inicializando vista...");

            figuraDAO = new FiguraDAO();

            cmbTipoFigura.getSelectionModel().selectFirst();
            colorPicker.setValue(Color.WHITE);


            cmbTipoFigura.valueProperty().addListener((observable, oldValue, newValue) -> {
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
            });

            // Inicializa tabla
            tablaFiguras.setItems(figurasObservableList);

            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colTipo.setCellValueFactory(cellData -> javafx.beans.binding.Bindings.createStringBinding(() ->
                    cellData.getValue().getClass().getSimpleName()));
            colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
            colArea.setCellValueFactory(cellData -> javafx.beans.binding.Bindings.createStringBinding(() ->
                    String.format("%.2f", cellData.getValue().calcularArea())));

        } catch (Exception e) {
            System.out.println("Error al inicializar:");
            e.printStackTrace();
        }
    }

    /**
     * Agrega una nueva figura geometrica segun lo que ingrese el usuario
     * Verifica que los datos sean validos, guarda la figura usando DAO(Data acces object).
     */
    @FXML
    private void agregarFigura() {
        String tipo = cmbTipoFigura.getValue();
        String nombre = txtNombre.getText();
        String colorHexWithAlpha = colorPicker.getValue().toString();
        String color = "#" + colorHexWithAlpha.substring(2, 8);

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
                boolean guardadoExitoso = figuraDAO.guardarFigura(nuevaFigura);

                if (guardadoExitoso) {
                    System.out.println("Figura agregada: " + nuevaFigura.getNombre() +
                            ", Tipo: " + nuevaFigura.getClass().getSimpleName() +
                            ", Color: " + nuevaFigura.getColor() +
                            ", Area: " + String.format("%.2f", nuevaFigura.calcularArea()));
                    mostrarAlerta("Éxito", "Figura '" + nuevaFigura.getNombre() + "' agregada correctamente a la base de datos.", Alert.AlertType.INFORMATION);
                    figurasObservableList.add(nuevaFigura);

                    limpiarCampos();
                } else {
                    mostrarAlerta("Error de Base de Datos", "No se pudo guardar la figura en la base de datos. Revisa la consola para más detalles.", Alert.AlertType.ERROR);
                }
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Entrada", "Por favor, ingrese valores numéricos válidos para las dimensiones (radio, base, altura).", Alert.AlertType.ERROR);
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Error de Creación", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Limpia los campos de la interfaz grafica.
     */

    private void limpiarCampos() {
        txtNombre.clear();
        txtRadio.clear();
        txtBase.clear();
        txtAltura.clear();
        cmbTipoFigura.getSelectionModel().selectFirst();
        colorPicker.setValue(Color.BLACK);
    }

    /**
     * Muestra una alerta al usuario con un breve mensaje
     *
     * @param titulo  Titulo de la alerta
     * @param mensaje Contenido del mensaje
     * @param tipo    Tipo de alerta
     */

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}