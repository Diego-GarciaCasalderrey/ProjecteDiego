/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diegoplays
 */
public class CategoriaDAO extends BaseDAO {

    PreparedStatement stmt;
    String query;
    ResultSet rs;

    /**
     * Constructor de CategoriaDAO. Es connecta a la BD.
     */
    public CategoriaDAO() {
        connect();
    }

    /**
     * Inserta una Categoria a la BD.
     *
     * @param c objecte Categoria.
     * @return retorna un boolean indicant si s'ha inserit correctament.
     * @throws SQLException si alguna cosa falla a l'insertar la categoria a la BD.
     */
    public boolean insertar(Categoria c) throws SQLException {
        stmt = conn.prepareStatement("INSERT INTO CATEGORIES(nom) values (?)");
        stmt.setString(1, c.getNom());
        int count = stmt.executeUpdate();
        return count == 1;
    }

    /**
     * Agafa una Categoria segons la seva ID.
     *
     * @param id id de la Categoria
     * @return objecte Categoria.
     * @throws SQLException si alguna cosa falla al obtenir una categoria de la BD.
     */
    public Categoria getCategoria(int id) throws SQLException {

        Categoria categoria = null;

        query = "SELECT * FROM CATEGORIES WHERE ID = ?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        if (rs.next()) {
            categoria = new Categoria(rs.getString("nom"));
            categoria.setId(rs.getInt("ID"));
        }
        rs.close();
        stmt.close();
        return categoria;
    }

    /**
     * Agafa una ArrayList amb totes les categories (Categoria) de la BD.
     *
     * @param ordre ordre segons s'ordenen les categories.
     * @return retorna una ArrayList amb les categories de la BD.
     * @throws SQLException si alguna cosa falla al obtenir les categories de la BD.
     */
    public ArrayList<Categoria> getCategories(String ordre) throws SQLException {

        query = "Select * from CATEGORIES ORDER BY " + ordre;
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        ArrayList<Categoria> categories = new ArrayList();
        while (rs.next()) {
            Categoria c = new Categoria(rs.getString("nom"));
            c.setId(rs.getInt("ID"));
            categories.add(c);
        }
        return categories;
    }

    /**
     * Submètode. Invoca al mètode getCategories(ordre) però ordenant de forma
     * predeterminada per la ID de la Categoria.
     *
     * @return retorna una ArrayList amb les categories de la BD.
     * @throws SQLException si alguna cosa falla al obtenir les categories de la BD.
     */
    public ArrayList<Categoria> getCategories() throws SQLException {
        return getCategories("ID");
    }

    /**
     * Submètode. Invoca al mètode getCategories(ordre) però ordenant de forma
     * predeterminada pel nom de la Categoria.
     *
     * @return retorna una ArrayList amb les categories de la BD.
     * @throws SQLException si alguna cosa falla al obtenir les categories de la BD.
     */
    public ArrayList<Categoria> getCategoriesByName() throws SQLException {
        return getCategories("nom");
    }

    /**
     * Agafa una Categoria segons la posició en que es troba aquesta ordenada
     * pel nom
     *
     * @param n posició en que es troba la categoria.
     * @return retorna la Categoria, o si falla alguna cosa, null.
     * @throws SQLException si alguna cosa falla al obtenir la categoria de la BD.
     */
    public Categoria getCatByOrderIndex(int n) throws SQLException {
        Categoria categoria = null;
        query = "Select * FROM CATEGORIES ORDER BY NOM LIMIT ?, 1";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, n);
        rs = stmt.executeQuery();
        if (rs.next()) {
            categoria = new Categoria(rs.getString("nom"));
            categoria.setId(rs.getInt("ID"));
        }
        rs.close();
        stmt.close();
        return categoria;
    }

    /**
     * Indica la posició en que es troba una categoria ordenada pel nom, segons
     * la ID d'aquesta i el nombre de categories que hi ha a la BD.
     *
     * @param c objecte Categoria
     * @param numCat nombre de categories a la BD
     * @return retorna la posició de la categoria
     * @throws SQLException si alguna cosa falla al obtenir les categories de la BD.
     */
    public int getPosicioByCategoria(Categoria c, int numCat) throws SQLException {
        for (int i = 0; i < numCat; i++) {
            if (getCatByOrderIndex(i).getId() == c.getId()) {
                return i;
            }
        }
        return -1;
    }
}
