package other;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Administrateur;
import model.Salle;

public class Server extends Thread {
    static final int PORT = 8888;
    private Socket sockets;

    public Server(Socket socket) {
	this.sockets = socket;
    }

    public static void main(String[] args) {
	try {
	    ServerSocket serv = new ServerSocket(PORT);
	    System.out.println("Lancement du serveur....");

	    while (true) {
		Socket socket = serv.accept();
		Server s = new Server(socket);
		s.start();
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void run() {
	System.out.println("request");
	try {
	    ObjectInputStream ois = new ObjectInputStream(this.sockets.getInputStream());
	    do {
		try {
		    String action = (String) ois.readObject();
		    switch (action) {
		    case "admin":
			Administrateur admin = (Administrateur) ois.readObject();
			System.out.println(admin.getNom());
			break;
		    case "salle":
			Salle salle = new Salle();
			System.out.println("creation d'une salle");
		    default:
			break;
		    }
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	    } while (true);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
