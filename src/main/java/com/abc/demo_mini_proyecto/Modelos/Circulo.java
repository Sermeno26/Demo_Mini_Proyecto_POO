package com.abc.demo_mini_proyecto.Modelos;

/**
 * Representa una figura geométrica de tipo Círculo.
 * Hereda de la clase abstracta FiguraGeometrica e implementa el método para calcular el área.
 *
 * @author Diego Otoniel Mendez Cabrera #00010023
 * @author Daniel Alexander Sermeno Chinchilla #00030022
 * @author Rene Eduardo Gonzalez Iraheta #00128624
 * @version 2.0
 */
public class Circulo extends FiguraGeometrica {
    private double radio;

    public Circulo() {
    }

    /**
     * Crea un nuevo objeto Círculo con color, nombre y radio.
     *
     * @param color  Color del circulo en formato hexadecimal.
     * @param nombre Nombre identificador del circulo.
     * @param radio  Radio del circulo.
     */
    public Circulo(String color, String nombre, double radio) {
        super(color, nombre);
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    /**
     * Calcula el area del circulo usando la formula: PI * radio elevado al cuadrado.
     * @return area del círculo.
     */
    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }
}