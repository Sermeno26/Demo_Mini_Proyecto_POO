package com.abc.demo_mini_proyecto.Modelos;

/**
 * Representa una figura geométrica de tipo Rectangulo.
 * Hereda de la clase abstracta {@link FiguraGeometrica} y agrega el metodo para calcular el area.
 *
 * @author Diego Otoniel Mendez Cabrera #00010023
 * @author Daniel Alexander Sermeno Chinchilla #00030022
 * @author Rene Eduardo Gonzalez Iraheta #00128624
 * @version 2.0
 */
public class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Rectangulo() {
    }

    /**
     * Agrega y crea un nuevo objeto Rectangulo
     *
     * @param color  Color del rectángulo.
     * @param nombre Nombre del rectángulo.
     * @param base   Base del rectángulo.
     * @param altura Altura del rectángulo.
     */
    public Rectangulo(String color, String nombre, double base, double altura) {
        super(color, nombre);
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * Calcula el area del rectangulo usando la fórmula base * altura.
     *
     * @return area del rectangulo.
     */
    @Override
    public double calcularArea() {
        return base * altura;
    }
}