package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class AccessDB {

    private Connection cnx = null;

    public Connection connect() {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/parcinformatique", "root", "passer");
	} catch (Exception e) {
	    System.out.println("Probl�me de connexion !!!" + e.getMessage());
	}

	return cnx;
    }
}
