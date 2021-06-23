/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Vaixell;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diegoplays
 */
public class VaixellDAO extends BaseDAO {

    PreparedStatement stmt;
    ResultSet rs;

    /**
     * Constructor de VaixellDAO. Es connecta a la BD.
     */
    public VaixellDAO() {
        VaixellDAO.connect();
    }

    /**
     * Agafa un Vaixell segons la seva ID.
     *
     * @param id id del Vaixell.
     * @return objecte Vaixell.
     * @throws SQLException si alguna cosa falla al obtenir el vaixell de la BD.
     */
    public Vaixell getVaixell(int id) throws SQLException {
        Vaixell vaixell = null;

        String query = "SELECT * FROM VAIXELLS WHERE ID = ?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            vaixell = new Vaixell(rs.getInt("categoria_id"),
                    rs.getString("nom"), rs.getDouble("rating"),
                    rs.getString("club"), rs.getByte("tipus"),
                    rs.getBoolean("senior"), rs.getDouble("tempsReal"));
        }
        rs.close();
        stmt.close();
        return vaixell;
    }

    /**
     * Inserta un Vaixell a la BD.
     *
     * @param v objecte Vaixell.
     * @return retorna un boolean indicant si s'ha inserit correctament.
     * @throws SQLException si alguna cosa falla al insertar el vaixell dins la BD.
     */
    public boolean insertar(Vaixell v) throws SQLException {
        stmt = conn.prepareStatement("INSERT INTO VAIXELLS("
                + "categoria_id, nom, rating, club, tipus, senior, tempsReal)"
                + " values (?,?,?,?,?,?,?)");

        stmt.setInt(1, v.getId_categoria());
        stmt.setString(2, v.getNom());

        stmt.setDouble(3, v.getRating());
        stmt.setString(4, v.getClub());
        stmt.setShort(5, v.getTipusVaixell());
        stmt.setBoolean(6, v.isSenior());
        stmt.setDouble(7, v.getTempsReal());
        int count = stmt.executeUpdate();
        return count == 1;
    }

    /**
     * esborra un Vaixell de la BD.
     * @param id ID del Vaixell a esborrar.
     * @return retorna el nombre de vaixells que s'han esborrat.
     * @throws SQLException si alguna cosa falla en esborrar el vaixell de la BD.
     */
    public int delete(int id) throws SQLException {
        stmt = conn.prepareStatement("DELETE FROM VAIXELLS WHERE ID = ?");
        stmt.setInt(1, id);
        int count = stmt.executeUpdate();
        stmt.close();
        return count;
    }

    /**
     * Actualitza un Vaixell de la BD.
     * @param v objecte Vaixell a actualitzar.
     * @return retorna el nombre de Vaixells que s'han actualitzat.
     * @throws SQLException si alguna cosa falla en actualitzar el vaixell de la BD.
     */
    public int update(Vaixell v) throws SQLException {
        String query = "UPDATE VAIXELLS SET "
                + "nom = ?,"
                + " categoria_id = ?,"
                + " rating = ?,"
                + " club = ?,"
                + " tipus = ?,"
                + " senior = ?,"
                + " tempsReal = ?"
                + " WHERE ID = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, v.getNom());
        stmt.setInt(2, v.getId_categoria());
        stmt.setDouble(3, v.getRating());
        stmt.setString(4, v.getClub());
        stmt.setShort(5, v.getTipusVaixell());
        stmt.setBoolean(6, v.isSenior());
        stmt.setDouble(7, v.getTempsReal());
        stmt.setInt(8, v.getCodi());
        int count = stmt.executeUpdate();
        stmt.close();
        return count;
    }

    /**
     * Agafa una ArrayList amb tots els Vaixells de la base de dades segons un
     * ordre i que es trobin dins d'un filtre indicat.
     * 
     * @param ordre ordre segons com ordenar els vaixells.
     * @param idCat id de la Categoria segons filtrar els vaixells. -1 si es volen tots.
     * @return retorna una ArrayList amb els Vaixells de la BD inclosos als filtres.
     * @throws SQLException si alguna cosa falla al obtenir els vaixells de la BD.
     */
    public ArrayList<Vaixell> getVaixells(String ordre, int idCat) throws SQLException {
        ArrayList<Vaixell> vaixells = new ArrayList<>();

        CategoriaDAO ctDao = new CategoriaDAO();
        String query = "SELECT * FROM VAIXELLS";

        if (idCat != -1) {
            query = query.concat(" WHERE categoria_id = " + idCat);
        }
        switch (ordre) {
            case "Temps compensat":
                query = query.concat(" ORDER BY tempsReal * rating");
                break;
            case "Categoria":
                query = query.concat(" ORDER BY categoria_id");
                break;
            default:
                query = query.concat(" ORDER BY " + ordre);
                break;
        }
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Vaixell v = new Vaixell(rs.getInt("categoria_id"),
                    rs.getString("nom"), rs.getDouble("rating"), rs.getString("club"),
                    rs.getByte("tipus"), rs.getBoolean("senior"),
                    rs.getDouble("tempsReal"));
            v.setCodi(rs.getInt("ID"));
            vaixells.add(v);
        }
        rs.close();
        stmt.close();
        return vaixells;

    }

    /**
     * Submètode. Invoca getVaixells(ordre, idCat) ordenant per ID i mostrant
     * totes les categories (-1).
     * @return retorna una ArrayList amb tots els vaixells de la BD.
     * @throws SQLException si alguna cosa falla al obtenir els vaixells de la BD.
     */
    public ArrayList<Vaixell> getVaixells() throws SQLException {
        return getVaixells("ID", -1);
    }

    /**
     * Indica si no hi ha vaixells a la base de dades.
     * @return retorna si la BD està buida de vaixells.
     * @throws SQLException si alguna cosa falla al comptar els vaixells de la BD.
     */
    public boolean isEmpty() throws SQLException {
        String query = "SELECT COUNT(*) total FROM VAIXELLS";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total") == 0;
        }
        return true;
    }
}
