package com.abc.demo_mini_proyecto.dao;

import com.abc.demo_mini_proyecto.Circulo;
import com.abc.demo_mini_proyecto.DBConnection;
import com.abc.demo_mini_proyecto.FiguraGeometrica;
import com.abc.demo_mini_proyecto.Rectangulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FiguraDAO {

    public boolean guardarFigura(FiguraGeometrica figura) {
        Connection conn = null;
        PreparedStatement pstmtFigura = null;
        PreparedStatement pstmtTipo = null;
        ResultSet rs = null;
        boolean guardadoExitoso = false;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Iniciar transacción

            String sqlFigura = "INSERT INTO figuras (nombre, color, tipo, area) VALUES (?, ?, ?, ?);";
            pstmtFigura = conn.prepareStatement(sqlFigura, Statement.RETURN_GENERATED_KEYS);

            pstmtFigura.setString(1, figura.getNombre());
            pstmtFigura.setString(2, figura.getColor());
            pstmtFigura.setString(3, figura.getClass().getSimpleName());
            pstmtFigura.setDouble(4, figura.calcularArea());

            int affectedRows = pstmtFigura.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La creación de la figura principal falló, no se afectaron filas.");
            }

            int figuraId = -1;
            rs = pstmtFigura.getGeneratedKeys();
            if (rs.next()) {
                figuraId = rs.getInt(1);
            } else {
                throw new SQLException("La creación de la figura principal falló, no se obtuvo el ID.");
            }

            if (figura instanceof Circulo) {
                Circulo circulo = (Circulo) figura;
                String sqlCirculo = "INSERT INTO circulos (figura_id, radio) VALUES (?, ?);";
                pstmtTipo = conn.prepareStatement(sqlCirculo);
                pstmtTipo.setInt(1, figuraId);
                pstmtTipo.setDouble(2, circulo.getRadio());
            } else if (figura instanceof Rectangulo) {
                Rectangulo rectangulo = (Rectangulo) figura;
                String sqlRectangulo = "INSERT INTO rectangulos (figura_id, base, altura) VALUES (?, ?, ?);";
                pstmtTipo = conn.prepareStatement(sqlRectangulo);
                pstmtTipo.setInt(1, figuraId);
                pstmtTipo.setDouble(2, rectangulo.getBase());
                pstmtTipo.setDouble(3, rectangulo.getAltura());
            } else {
                throw new IllegalArgumentException("Tipo de figura desconocido para guardar en la base de datos.");
            }

            if (pstmtTipo != null) {
                pstmtTipo.executeUpdate();
            }

            conn.commit();
            guardadoExitoso = true;
            System.out.println("Figura guardada exitosamente en la base de datos. ID: " + figuraId);

        } catch (SQLException e) {
            System.err.println("Error al guardar la figura en la base de datos: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    System.err.println("Realizando rollback de la transacción.");
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error al realizar rollback: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmtFigura != null) pstmtFigura.close();
                if (pstmtTipo != null) pstmtTipo.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos de la base de datos: " + e.getMessage());
            }
        }
        return guardadoExitoso;
    }

    /**
     * Obtiene todas las figuras geométricas de la base de datos.
     * @return Una lista de objetos FiguraGeometrica, que pueden ser Circulo o Rectangulo.
     */
    public List<FiguraGeometrica> getAllFiguras() {
        List<FiguraGeometrica> figuras = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT f.id, f.nombre, f.color, f.tipo, f.area, " +
                "c.radio, r.base, r.altura " +
                "FROM figuras f " +
                "LEFT JOIN circulos c ON f.id = c.figura_id " +
                "LEFT JOIN rectangulos r ON f.id = r.figura_id " +
                "ORDER BY f.id;";

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String color = rs.getString("color");
                String tipo = rs.getString("tipo");
                double area = rs.getDouble("area");

                FiguraGeometrica figura = null;

                if ("Circulo".equals(tipo)) {
                    double radio = rs.getDouble("radio");
                    figura = new Circulo(color, nombre, radio);
                    // Opcional: podrías establecer el ID si tus modelos lo tienen
                    // ((Circulo)figura).setId(id);
                } else if ("Rectangulo".equals(tipo)) {
                    double base = rs.getDouble("base");
                    double altura = rs.getDouble("altura");
                    figura = new Rectangulo(color, nombre, base, altura);
                    // Opcional: podrías establecer el ID si tus modelos lo tienen
                    // ((Rectangulo)figura).setId(id);
                }

                if (figura != null) {
                    figuras.add(figura);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las figuras de la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos al obtener figuras: " + e.getMessage());
            }
        }
        return figuras;
    }
}