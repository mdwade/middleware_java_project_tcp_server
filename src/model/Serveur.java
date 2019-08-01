package model;

import java.io.Serializable;

public class Serveur implements Serializable {

    private static final long serialVersionUID = 5202989752617574999L;
    private int numero;
    private String nomSalle;
    private String nomServeur;
    private String pseudoAdmin;

    public Serveur(int numero, String nomServeur, String nomSalle, String pseudoAdmin) {
	this.numero = numero;
	this.nomServeur = nomServeur;
	this.nomSalle = nomSalle;
	this.pseudoAdmin = pseudoAdmin;
    }

    public Serveur() {

    }

    public int getNumero() {
	return numero;
    }

    public void setNumero(int numero) {
	this.numero = numero;
    }

    public String getNomServeur() {
	return nomServeur;
    }

    public void setNomServeur(String nomServeur) {
	this.nomServeur = nomServeur;
    }

    public String getPseudoAdmin() {
	return pseudoAdmin;
    }

    public void setPseudoAdmin(String pseudoAdmin) {
	this.pseudoAdmin = pseudoAdmin;
    }

    public String getNomSalle() {
	return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
	this.nomSalle = nomSalle;
    }

}
