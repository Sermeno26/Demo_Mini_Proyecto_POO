package com.abc.demo_mini_proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=figuras_db;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "SERmeno260403";

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    // Metodo para probar la conexión
    public static void testConexion() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("¡Conexion exitosa a SQL Server!");
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar conectar con la base de datos:");
            e.printStackTrace();
        }
    }

}