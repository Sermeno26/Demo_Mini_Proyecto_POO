package com.abc.demo_mini_proyecto;

public class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Rectangulo() {
    }

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

    @Override
    public double calcularArea(){
        return base * altura;
    }
}