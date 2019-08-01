package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Administrateur;

public class AdminManager {

    AccessDB cnx = new AccessDB();
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public int createAdministrateur(Administrateur a) {
	int response;
	try {
	    st = cnx.connect().prepareStatement("insert into administrateur(pseudo,nom,prenom) values(?,?,?)");
	    st.setString(1, a.getPseudo());
	    st.setString(2, a.getNom());
	    st.setString(3, a.getPrenom());
	    response = st.executeUpdate();
	} catch (Exception e) {
	    response = 0;
	    System.out.println("!!!" + e.getMessage());
	}
	return response;
    }

    public ArrayList<Administrateur> readAdministrateur() {
	ArrayList<Administrateur> liste = new ArrayList<>();

	try {
	    st = cnx.connect().prepareStatement("select * from administrateur");
	    rs = st.executeQuery();

	    while (rs.next()) {
		Administrateur a = new Administrateur(null, null, null);
		a.setPseudo(rs.getString("pseudo"));
		a.setNom(rs.getString("nom"));
		a.setPrenom(rs.getString("prenom"));
		a.setId(rs.getInt("id"));

		liste.add(a);
	    }
	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

	return liste;
    }

    // read Administrateur by pseudo
    public Administrateur readAdministrateur(String pseudo) {
	Administrateur a = null;
	try {
	    st = cnx.connect().prepareStatement("select * from administrateur where pseudo=?");
	    st.setString(1, pseudo);
	    rs = st.executeQuery();

	    if (rs.next()) {
		a = new Administrateur(null, null, null);
		a.setPseudo(rs.getString("pseudo"));
		a.setNom(rs.getString("nom"));
		a.setPrenom(rs.getString("prenom"));
	    }
	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

	return a;
    }

    public void updateAdministrateur(Administrateur a) {
	try {
	    st = cnx.connect().prepareStatement("update administrateur set nom=? , prenom=? where pseudo=?");
	    st.setString(1, a.getNom());
	    st.setString(2, a.getPrenom());
	    st.setString(4, a.getPseudo());
	    st.executeUpdate();

	} catch (Exception ex) {
	    System.out.println("!!!" + ex.getMessage());
	}
    }

    public void deleteAdministrateur(String pseudo) {
	try {
	    st = cnx.connect().prepareStatement("delete from administrateur where pseudo = ?");
	    st.setString(1, pseudo);
	    st.executeUpdate();
	} catch (Exception e) {
	    System.out.println(e.getMessage());

	}
    }
}
