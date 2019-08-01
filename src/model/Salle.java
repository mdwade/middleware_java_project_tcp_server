package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Salle implements Serializable {
    private static final long serialVersionUID = -6650641589318613944L;
    private int numero;
    private String nom;
    public static ArrayList<Salle> listSalles = new ArrayList<>();

    public Salle() {

    }

    public Salle(int numero, String nom) {
	this.numero = numero;
	this.nom = nom;
    }

    public int getNumero() {
	return numero;
    }

    public void setNumero(int numero) {
	this.numero = numero;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }
}
