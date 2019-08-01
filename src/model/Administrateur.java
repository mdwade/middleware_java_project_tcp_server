package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Administrateur implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4924690529440889397L;
    private String nom;
    private String prenom;
    private String pseudo;
    private int id;

    public static ArrayList<Administrateur> listAdmins = new ArrayList<>();

    public Administrateur() {

    }

    public Administrateur(String pseudo, String nom, String prenom) {
	this.nom = nom;
	this.prenom = prenom;
	this.pseudo = pseudo;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public String getPseudo() {
	return pseudo;
    }

    public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

}
