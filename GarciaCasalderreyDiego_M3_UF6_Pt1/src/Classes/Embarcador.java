/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author diegoplays
 */
public class Embarcador {

    String nom;

    public Embarcador(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

//    public void LlistaOrdenada() {
//        TreeSet<Categoria> ts = new TreeSet<>();
//
//        ts.addAll(categories);
//
//        System.out.println("Nom magatzem: " + this.nom);
//        ts.forEach((al) -> {
//            System.out.println(al.toString());
//        });
//    }

    @Override
    public String toString() {
        return "Magatzem{" + " nom=" + nom + '}';
    }
}
