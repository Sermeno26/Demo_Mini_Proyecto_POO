package com.abc.demo_mini_proyecto;

public abstract class FiguraGeometrica implements IMedible {
    private String color;
    private String nombre;

    public FiguraGeometrica() {
    }

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

    public final void mostrarNombre(){
        System.out.println("Nombre: " + getNombre());
    }
    public final void mostrarColor(){
        System.out.println("Color: " + getColor());
    }
    public abstract double calcularArea();
}