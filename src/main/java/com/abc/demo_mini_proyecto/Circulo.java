package com.abc.demo_mini_proyecto;

public class Circulo extends FiguraGeometrica {
    private double radio;

    public Circulo() {
    }

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
    @Override
    public double calcularArea(){
        return Math.PI * radio * radio;
    }
}