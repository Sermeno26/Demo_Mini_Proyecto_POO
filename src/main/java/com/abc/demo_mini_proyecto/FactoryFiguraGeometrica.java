package com.abc.demo_mini_proyecto;

public class FactoryFiguraGeometrica {

    public static FiguraGeometrica crearFigura(String tipo, String color, String nombre, double... dimensiones) {
        switch (tipo) {
            case "Círculo":

                if (dimensiones.length == 1) {
                    throw new IllegalArgumentException("Se requiere el radio para un Círculo.");
                }
                return new Circulo(color, nombre, dimensiones[0]);
            case "Rectángulo":

                if (dimensiones.length < 2) {
                    throw new IllegalArgumentException("Se requieren la base y la altura para un Rectángulo.");
                }
                return new Rectangulo(color, nombre, dimensiones[0], dimensiones[1]);
            default:
                throw new IllegalArgumentException("Tipo de figura desconocido: " + tipo);
        }
    }
}