package model;

public class Log {

    private String action;
    private String adressMachine;
    private String nomMachine;
    private String dateEtHeure;

    public Log(String action, String adressMachine, String nomMachine, String dateEtHeure) {
	super();
	this.action = action;
	this.adressMachine = adressMachine;
	this.nomMachine = nomMachine;
	this.dateEtHeure = dateEtHeure;
    }

    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }

    public String getAdressMachine() {
	return adressMachine;
    }

    public void setAdressMachine(String adressMachine) {
	this.adressMachine = adressMachine;
    }

    public String getNomMachine() {
	return nomMachine;
    }

    public void setNomMachine(String nomMachine) {
	this.nomMachine = nomMachine;
    }

    public String getDateEtHeure() {
	return dateEtHeure;
    }

    public void setDateEtHeure(String dateEtHeure) {
	this.dateEtHeure = dateEtHeure;
    }

}
