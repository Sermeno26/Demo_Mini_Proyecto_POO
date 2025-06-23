package com.abc.demo_mini_proyecto.Modelos;

/**
 * Clase abstracta que representa una figura geométrica generica.
 * Define propiedades comunes como color y nombre, y obliga a implementar el calculo de area.
 * Implementa la interfaz {@link IMedible}.
 *
 * @author Diego Otoniel Mendez Cabrera #00010023
 * @author Daniel Alexander Sermeno Chinchilla #00030022
 * @author Rene Eduardo Gonzalez Iraheta #00128624
 * @version 2.0
 */
public abstract class FiguraGeometrica implements IMedible {
    private String color;
    private String nombre;

    public FiguraGeometrica() {
    }

    /**
     * Constructor que inicializa la figura con color y nombre.
     *
     * @param color  Color de la figura.
     * @param nombre Nombre de la figura.
     */
    public FiguraGeometrica(String color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Muestra el nombre de la figura
     */

    public final void mostrarNombre() {
        System.out.println("Nombre: " + getNombre());
    }

    /**
     * Muestra el color de la figura.
     */
    public final void mostrarColor() {
        System.out.println("Color: " + getColor());
    }

    /**
     * Metodo abstracto para calcular el area de la figura.
     *
     * @return area de la figura.
     */
    public abstract double calcularArea();
}