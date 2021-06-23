/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Categoria;
import Classes.Embarcador;
import Classes.Vaixell;
import DAO.BDUtil;
import DAO.CategoriaDAO;
import DAO.VaixellDAO;
import Interface.AfegirCategoria;
import Interface.Form;
import Interface.Inici;
import static java.lang.Double.parseDouble;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author diegoplays
 */
public final class Controlador {

    Embarcador magatzem;
    Inici formulari;
    String nomArxiu;
    VaixellDAO vDao;
    CategoriaDAO cDao;

    /**
     * Constructor del Controlador; aquest comprova la base de dades i
     * inicialitza la interfície d'inici.
     */
    public Controlador() {
        vDao = new VaixellDAO();
        cDao = new CategoriaDAO();
        setDB();
        this.formulari = new Inici(this);
        this.formulari.setVisible(true);
    }

    /**
     * Comprova si les taules de la BD s'han creat i, si no és el cas, les crea.
     * (la BD ha d'existir)
     */
    private void setDB() {
        if (!areTablesCreated()) {
            try {
                BDUtil.crearEstructuraBD();
                estructuraGenerica();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    /**
     * Comprova si les taules de la BD s'han creat i pregunta a l'usuari si vol
     * utilitzar les dades d'aquestes; si aquest no les vol, les neteja.
     *
     * @return retorna si les taules de la BD existeixen.
     */
    private boolean areTablesCreated() {
        boolean exist;
        try {
            vDao.getVaixells();
            exist = true;
            int confirmDialog = JOptionPane.showConfirmDialog(null, "Sembla que ja hi ha dades a la base de dades, vols utilitzar-les?");
            if (JOptionPane.NO_OPTION == confirmDialog) {
                int confirmDialog2 = JOptionPane.showConfirmDialog(null, "Estàs segur? Això esborrarà totes les dades de la base de dades.");
                if (JOptionPane.OK_OPTION == confirmDialog2) {
                    BDUtil.netejaTaules();
                    estructuraGenerica();

                }
            }
        } catch (SQLException ex) {
            exist = false;
        } catch (NullPointerException ex) {
            System.out.println("[!] No s'ha pogut accedir a la BD");
            exist = false;
        }
        return exist;
    }

    /**
     * Crea dades genèriques per a afegir a la BD quan aquesta s'acaba de crear
     * o de netejar de forma automàtica.
     */
    private void estructuraGenerica() throws SQLException {
        Categoria c1 = new Categoria("Crussier");
        Categoria c2 = new Categoria("High Level");
        Categoria c3 = new Categoria("Perfomance");
        cDao.insertar(c1);
        c1.setId(getIdCategoria(getCatByOrderIndex(0)));
        cDao.insertar(c2);
        c2.setId(getIdCategoria(getCatByOrderIndex(1)));
        cDao.insertar(c3);
        c3.setId(getIdCategoria(getCatByOrderIndex(2)));
        Vaixell v1 = new Vaixell(c1.getId(), "Vaixell1", 40, "BCN", (byte) 0, false, 50.4);
        Vaixell v2 = new Vaixell(c2.getId(), "Vaixell2", 35.2, "ZAM", (byte) 1, true, 44);
        Vaixell v3 = new Vaixell(c3.getId(), "Vaixell3", 58.3, "ALM", (byte) 2, false, 50.4);
        Vaixell v4 = new Vaixell(c1.getId(), "AAA-Vaixell4", 97.8, "NYC", (byte) 1, true, 25.5);
        Vaixell v5 = new Vaixell(c2.getId(), "Vaixell5", 70.4, "BCN", (byte) 0, false, 34);
        Vaixell v6 = new Vaixell(c3.getId(), "Vaixell6", 65.1, "BCN", (byte) 2, false, 20.1);
        Vaixell v7 = new Vaixell(c1.getId(), "Vaixell7", 99, "BCN", (byte) 0, true, 10.5);
        vDao.insertar(v1);
        vDao.insertar(v2);
        vDao.insertar(v3);
        vDao.insertar(v4);
        vDao.insertar(v5);
        vDao.insertar(v6);
        vDao.insertar(v7);
    }

    /**
     * Agafa les categories de la BD ordenades per la seva ID i les retorna.
     *
     * @return retorna una ArrayList de totes les categories que hi ha a la BD,
     * i null si té algún problema.
     */
    public ArrayList<Categoria> getCategories() {
        return getCategories("id");
    }

    /**
     * Agafa les categories de la BD ordenades per un filtre donat i les
     * retorna.
     *
     * @param ordre ordre segons el qual s'ordenen les categories
     * @return retorna una ArrayList de totes les categories que hi ha a la BD,
     * i null si té algún problema.
     */
    public ArrayList<Categoria> getCategories(String ordre) {
        try {
            return cDao.getCategories(ordre);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * Agafa un Vaixell de la BD segons un codi de vaixell donat.
     *
     * @param codi codi del vaixell a buscar
     * @return retorna el Vaixell amb el codi indicat o, si no es troba, null.
     */
    public Vaixell getVaixell(int codi) {
        try {
            return vDao.getVaixell(codi);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * Verifica si el rating d'un vaixell és un double entre 0 i 100.
     *
     * @param rating String del rating introduït.
     * @return retorna un boolean dient si el rating és vàlid (si és un nombre
     * real entre 0 i 100).
     */
    public boolean verificaRating(String rating) {
        try {
            double nRating = parseDouble(rating);
            return nRating <= 100 && nRating >= 0 && rating.length() > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica si el nom d'un club és un alfanumèric d'entre 3 i 50 caràcters.
     *
     * @param club nom del club donat.
     * @return retorna un boolean dient si el nom del club és vàlid (alfanumèric
     * entre 3 i 50 caràcters).
     */
    public boolean verificaClub(String club) {
        return club.matches("[a-zA-Z0-9]{3,50}");
    }

    /**
     * Crea una taula de vaixells ordenada segons un ordre i filtrada per una id
     * de categoria (-1 per a mostrar-les totes).
     *
     * @param ordre ordre segons el qual ordenar els Vaixells
     * @param idCat id de la Categoria segons la qual filtrar
     * @return retorna el DefaultTableModel a mostrar.
     * @throws SQLException si alguna cosa falla al obtenir els vaixells de la BD.
     */
    public TableModel getVaixellsTable(String ordre, int idCat) throws SQLException {
        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("ID");
        tm.addColumn("Nom");
        tm.addColumn("Rating");
        tm.addColumn("Club");
        tm.addColumn("Tipus");
        tm.addColumn("Categoria");
        tm.addColumn("Senior");
        tm.addColumn("TempsReal");
        tm.addColumn("Temps compensat");
        for (Vaixell v : vDao.getVaixells(ordre, idCat)) {
            Object[] fila = new Object[9];
            fila[0] = v.getCodi();
            fila[1] = v.getNom();
            fila[2] = String.format(Locale.US, "%.2f", v.getRating());
            fila[3] = v.getClub();
            fila[4] = v.getTipusVaixell() == 0 ? "Regata" : v.getTipusVaixell() == 1 ? "Creuer" : "R-C";
            fila[5] = cDao.getCategoria(v.getId_categoria()).getNom();
            fila[6] = v.isSenior();
            fila[7] = v.getTempsReal();
            fila[8] = String.format(Locale.US, "%.2f", v.getTempsReal() * v.getRating());
            tm.addRow(fila);
        }

        return tm;
    }

    /**
     * Submètode de getVaixellsTable que l'executa ordenant per ID i mostrant
     * totes les categories.
     *
     *
     * @return retorna el DefaultTableModel a mostrar.
     * @throws SQLException si alguna cosa falla al obtenir els vaixells de la BD.
     */
    public TableModel getVaixellsTable() throws SQLException {
        return getVaixellsTable("ID", -1);
    }

    /**
     * Introdueix tots els noms de columnes del TableModel i les introdueix a un
     * Array d'String.
     *
     * @param tm DefaultTableModel del que obtenir els noms de columna.
     * @return retorna un array d'String amb els noms de columna del TableModel
     */
    public String[] getTaulaOrdre(TableModel tm) {
        String[] array = new String[tm.getColumnCount()];
        for (int i = 0; i < tm.getColumnCount(); i++) {
            array[i] = tm.getColumnName(i);
        }
        return array;
    }
    
    /**
     * Agafa la ID d'una Categoria. Retorna -1 si aquesta categoria és nul·la.
     *
     * @param c Categoria de la qual es vol obtenir la ID.
     * @return retorna la ID de la Categoria introduïda, o -1 si és null.
     */
    public int getIdCategoria(Categoria c) {
        if (c == null) {
            return -1;
        } else {
            return c.getId();
        }
    }

    /**
     * Agafa una Categoria segons la posició en que es troba aquesta ordenada
     * pel nom
     *
     * @param n posició en que es troba la categoria.
     * @return retorna la Categoria, o si falla alguna cosa, null.
     */
    public Categoria getCatByOrderIndex(int n) {
        try {
            return cDao.getCatByOrderIndex(n);
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     * Indica la posició en que es troba una categoria ordenada pel nom, segons
     * la ID d'aquesta i el nombre de categories que hi ha a la BD.
     *
     * @param categoria id de la Categoria
     * @param nCategories nombre de categories a la BD
     * @return retorna la posició de la categoria
     */
    public int getPosicioByIdCategoria(int categoria, int nCategories) {
        for (int i = 0; i < nCategories; i++) {
            if (getIdCategoria(getCatByOrderIndex(i)) == categoria) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Mostra si no hi han Vaixells a la BD.
     *
     * @return retorna si la BD està buida de Vaixells. En cas d'error retorna
     * true.
     */
    public boolean isEmptyVaixells() {
        try {
            return vDao.isEmpty();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return true;
        }
    }

    /**
     * Afegeix una Categoria a la BD segons el nom de la Categoria introduït.
     *
     * @param txt nom de la Categoria.
     */
    public void afegirUnaCategoria(String txt) {
        Categoria c = new Categoria(txt);
        try {
            cDao.insertar(c);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Obre un JDialog AfegirCategoria per a afegir una nova Categoria.
     */
    public void mostraCrearCategoria() {
        AfegirCategoria dialog = new AfegirCategoria(this, this.formulari, true);
        dialog.setVisible(true);
    }

    /**
     * Mostra un missatge confirmant que s'ha afegit un nou Vaixell.
     */
    public void VaixellAfegit() {
        JOptionPane.showMessageDialog(null, "Vaixell afegit correctament");
    }

    /**
     * Obre el Formulari de Creació/Modificació d'un Vaixell. Si aquest és -1,
     * per a crear un Vaixell, si no, la ID del Vaixell a modificar.
     *
     * @param parent JFrame que crida al formulari.
     * @param codi codi del Vaixell a modificar, o -1 si és vol introduïr un de
     * nou.
     */
    public void ObrirFormulari(java.awt.Frame parent, int codi) {
        Form dialog = new Form(this, parent, true, codi);
        dialog.setVisible(true);
    }

    /**
     * Afegeix un Vaixell a la BD amb tots els valors que aquest requereix.
     *
     * @param idCat ID de la Categoria a la que afegir el Vaixell.
     * @param nom nom del Vaixell.
     * @param rating rating del Vaixell.
     * @param club club al que pertany el Vaixell.
     * @param tipusVaixell tipus del Vaixell.
     * @param senior si el vaixell és senior o no.
     * @param tempsReal temps real del vaixell.
     * @return retorna si ha pogut introduir el vaixell a la BD o no.
     */
    public boolean afegirVaixell(int idCat, String nom,
            double rating, String club, byte tipusVaixell, boolean senior,
            double tempsReal) {
        try {
            Vaixell vaixell = new Vaixell(idCat, nom, rating, club,
                    tipusVaixell, senior, tempsReal);
            vDao.insertar(vaixell);
            System.out.println(vaixell);
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Elimina un Vaixell segons la seva ID.
     *
     * @param id ID del Vaixell a eliminar.
     */
    public void eliminarVaixell(int id) {
        try {
            vDao.delete(id);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Actualitza un Vaixell a la BD segons els paràmetres indicats.
     *
     * @param codi codi del Vaixell a modificar.
     * @param idCat nova id de Categoria del Vaixell.
     * @param nom nou nom del Vaixell
     * @param rating nou rating del Vaixell.
     * @param ubicacio nova ubicació del Vaixell.
     * @param tipusVaixell nou tipus de Vaixell.
     * @param senior si després de modificar el Vaixell aquest és senior o no.
     * @param tempsReal nou temps real del Vaixell.
     */
    public void actualitzarVaixell(int codi, int idCat, String nom, double rating, String ubicacio, byte tipusVaixell, boolean senior, double tempsReal) {
        int count = 0;
        try {
            Vaixell v = new Vaixell(idCat, nom, rating, ubicacio, tipusVaixell, senior, tempsReal);
            v.setCodi(codi);
            count = vDao.update(v);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            if (count == 1) {
                System.out.println("Vaixell actualitzat");
            } else {
                System.out.println("Alguna cosa ha fallat al modificar el vaixell");
            }
        }
    }

}
