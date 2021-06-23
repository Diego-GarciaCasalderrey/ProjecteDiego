/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author diegoplays
 */
public class Vaixell{

    int codi;
    int id_categoria;
    String nom;
    double rating;
    String club;
    byte tipusVaixell;
    boolean senior;
    double tempsReal;

    public Vaixell(int id_categoria, String nom, double rating, String club, byte tipusVaixell, boolean senior, double tempsReal) {
        this.id_categoria = id_categoria;
        this.nom = nom;
        this.rating = rating;
        this.club = club;
        this.tipusVaixell = tipusVaixell;
        this.senior = senior;
        this.tempsReal = tempsReal;
    }


    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public byte getTipusVaixell() {
        return tipusVaixell;
    }

    public void setTipusVaixell(byte tipusVaixell) {
        this.tipusVaixell = tipusVaixell;
    }

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    public double getTempsReal() {
        return tempsReal;
    }

    public void setTempsReal(double tempsReal) {
        this.tempsReal = tempsReal;
    }

    @Override
    public String toString() {
        return "Vaixell{" + "codi=" + codi + ", nom=" + nom + ", rating=" + rating + ", club=" + club + ", tipusVaixell=" + tipusVaixell + ", senior=" + senior + ", tempsReal=" + tempsReal + '}';
    }

}
