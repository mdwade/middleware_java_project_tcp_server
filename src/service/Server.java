package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.ServerController;
import dao.AdminManager;
import dao.SalleManager;
import dao.ServerManager;
import model.Administrateur;
import model.Log;
import model.Salle;
import model.Serveur;

public class Server implements Runnable {

    DataOutputStream out;
    DataInputStream in;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    private Socket socket;
    private AdminManager am;
    private SalleManager sm;
    private ServerManager servm;

    public Server() {
    }

    public Server(Socket socket) {
	am = new AdminManager();
	sm = new SalleManager();
	servm = new ServerManager();

	this.socket = socket;
    }

    public void run() {
	ServerController.currentUser++;
	try {
	    do {
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());

		String mode = (String) ois.readObject();

		switch (mode) {
		case "addAdmin":
		    Administrateur a = (Administrateur) ois.readObject();
		    am.createAdministrateur(a);
		    this.logAction("Ajout admin");
		    break;
		case "listAdmin":
		    ArrayList<Administrateur> listeAdministrateur = new ArrayList<>();
		    listeAdministrateur = am.readAdministrateur();
		    oos.writeObject(listeAdministrateur);
		    oos.flush();
		    this.logAction("Récuperation liste Admins");
		    break;
		case "searchAdmin":
		    String pseudo = (String) ois.readObject();
		    Administrateur a1 = am.readAdministrateur(pseudo);
		    oos.writeObject(a1);
		    oos.flush();
		    break;
		case "updateAdmin":
		    Administrateur a2 = (Administrateur) ois.readObject();
		    am.updateAdministrateur(a2);
		    break;
		case "removeAdmin":
		    String pseudo1 = (String) ois.readObject();
		    am.deleteAdministrateur(pseudo1);
		    break;
		case "addSalle":
		    Salle s = (Salle) ois.readObject();
		    sm.createSalle(s);
		    this.logAction("Ajout d'une Salle");
		    break;
		case "listSalle":
		    ArrayList<Salle> listeSalle;
		    listeSalle = sm.readSalle();
		    oos.writeObject(listeSalle);
		    oos.flush();
		    this.logAction("VRécueration liste des Salles");
		    break;
		case "searchSalle":
		    int numSalle = in.readInt();
		    Salle s1 = sm.readSalle(numSalle);
		    oos.writeObject(s1);
		    oos.flush();
		    break;
		case "updateSalle":
		    Salle s2 = (Salle) ois.readObject();
		    sm.updateSalle(s2);
		    break;
		case "removeSalle":
		    int numSalle1 = in.readInt();
		    sm.deleteSalle(numSalle1);
		    break;
		case "addServeur":
		    Serveur serv = (Serveur) ois.readObject();
		    servm.createServeur(serv);
		    this.logAction("Ajout d'un Serveur");
		    break;
		case "listServeur":
		    ArrayList<Serveur> liste;
		    liste = servm.readServeur();
		    oos.writeObject(liste);
		    oos.flush();
		    this.logAction("Récuperation liste Serveurs");
		    break;
		case "searchServeur":
		    int numServ1 = in.readInt();
		    Serveur serv1 = servm.readServeur(numServ1);
		    oos.writeObject(serv1);
		    oos.flush();
		    break;
		case "updateServeur":
		    Serveur serv2 = (Serveur) ois.readObject();
		    servm.updateServeur(serv2);
		    break;
		case "removeServeur":
		    int numServ2 = in.readInt();
		    servm.deleteServeur(numServ2);
		    break;
		default:
		    break;
		}
	    } while (true);

	} catch (Exception e) {
	    ServerController.currentUser--;
	    System.out.println(e.getMessage());
	}
    }

    public void logAction(String message) {
	SimpleDateFormat formater;
	formater = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss");
	Log log = new Log(message, socket.getInetAddress().getHostAddress(), socket.getInetAddress().getHostName(),
		formater.format(new Date()));

	ServerController.logs.add(log);
	ServerController.setTable();
    }
}
