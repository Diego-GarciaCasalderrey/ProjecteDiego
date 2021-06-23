/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author alfredo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "projecteDiego"; //Nom de la nostra BBDD
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USERNAME = "diegogarcia"; //Nom d'usuari
    static final String PASSWORD = "P@ssw0rd"; //Contrasenya

    protected static Connection conn = null;

    /**
     * Es connecta a la BD utilitzant la URL, nom de la base de dades, nom d'usuari
     * i contrasenya donats.
     */
    public static void connect() {
        if (conn == null) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD);
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println(ex.toString());
            }
        }

    }

    /**
     * Agafa la connexió (Connection) a la base de dades.
     * @return retorna la connexió de la BD.
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * Tanca la connexió a la BD.
     * @throws SQLException si alguna cosa falla al tancar la connexió.
     */
    public static void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
        conn = null;
    }

}
