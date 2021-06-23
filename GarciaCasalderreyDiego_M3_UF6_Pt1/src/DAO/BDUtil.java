/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author diegoplays
 */
public class BDUtil {

    /**
     * Crea les taules de la BD.
     * @throws SQLException si alguna cosa falla al crear l'estructura de la BD
     */
    public static void crearEstructuraBD() throws SQLException {
        BaseDAO.connect();
        PreparedStatement stmt;
        String query;
        Connection conn = BaseDAO.getConn();
        query = "CREATE TABLE CATEGORIES("
                + "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(50))";
        stmt = conn.prepareStatement(query);
        stmt.executeUpdate();
        stmt.close();
        query = "CREATE TABLE VAIXELLS("
                + "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "categoria_id INT, "
                + "nom VARCHAR(30), "
                + "rating DECIMAL(4,2), "
                + "club VARCHAR(50), "
                + "tipus TINYINT, "
                + "senior BOOLEAN, "
                + "tempsReal DOUBLE, "
                + "CONSTRAINT VAIXELLS_CATEGORIES_CODE_FKEY FOREIGN KEY (categoria_id) "
                + "REFERENCES CATEGORIES (ID) MATCH SIMPLE "
                + "ON UPDATE NO ACTION ON DELETE NO ACTION"
                + ")";
        stmt = conn.prepareStatement(query);
        stmt.executeUpdate();
        stmt.close();

    }

    /**
     * Neteja les taules de la BD
     */
    public static void netejaTaules() {
        BaseDAO.connect();
        Connection conn = BaseDAO.getConn();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM VAIXELLS");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("DELETE FROM CATEGORIES");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
