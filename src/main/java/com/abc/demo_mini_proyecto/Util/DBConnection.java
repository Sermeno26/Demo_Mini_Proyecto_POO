package com.abc.demo_mini_proyecto.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexion a la base de datos SQL Server.
 * Proporciona métodos estaticos para obtener una conexion y probarla
 * Conecta a la base de datos <strong>figuras_db</strong> utilizando el driver JDBC.
 *
 * @author Diego Otoniel Mendez Cabrera #00010023
 * @author Daniel Alexander Sermeno Chinchilla #00030022
 * @author Rene Eduardo Gonzalez Iraheta #00128624
 * @version 2.0
 */
public class DBConnection {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=figuras_db;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "Dieguito2004_";

    private DBConnection() {
    }

    /**
     * Establece y devuelve una conexion con la base de datos.
     *
     * @return Conexion activa a la base de datos.
     * @throws SQLException Si ocurre un error al intentar conectarse.
     */
    public static Connection getConnection() throws SQLException {
        System.out.println("Intentando obtener conexión a la base de datos...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Metodo para comprobar si el estado de conexion tiene exito.
     * Imprime el estado de la conexion en consola.
     */

    public static void testConexion() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("¡Conexión exitosa a SQL Server!");
            } else {
                System.out.println("No se pudo conectar a la base de datos. La conexión es nula o está cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar conectar con la base de datos:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Un error inesperado ocurrió durante la prueba de conexión:");
            e.printStackTrace();
        }
    }
}