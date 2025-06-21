package com.abc.demo_mini_proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=figuras_db;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "SERmeno260403";

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Intentando obtener conexión a la base de datos...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

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