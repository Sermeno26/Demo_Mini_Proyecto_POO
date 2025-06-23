package com.abc.demo_mini_proyecto.Modelos;

/**
 * Utiliza el patron de diseño Factory method para facilitar la creacion de objetos segun su tipo.
 * Esta clase se encarga de validar los parametros necesarios para cada tipo de figura.
 *
 * @author Diego Otoniel Mendez Cabrera #00010023
 * @author Daniel Alexander Sermeno Chinchilla #00030022
 * @author Rene Eduardo Gonzalez Iraheta #00128624
 * @version 2.0
 */
public class FactoryFiguraGeometrica {
    /**
     * Crea una instancia de una figura geometrica segun el tipo proporcionado.
     *
     * @param tipo        Tipo de figura.
     * @param color       Color de la figura.
     * @param nombre      Nombre identificador de la figura.
     * @param dimensiones Parametros necesarios para la figura:
     * @return Una instancia de FiguraGeometrica correspondiente al tipo solicitado.
     * @throws IllegalArgumentException Si el tipo es desconocido.
     */

    public static FiguraGeometrica crearFigura(String tipo, String color, String nombre, double... dimensiones) {
        switch (tipo) {
            case "Círculo":

                if (dimensiones.length < 1) {
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