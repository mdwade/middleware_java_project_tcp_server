package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Serveur;

public class ServerManager {

    AccessDB cnx = new AccessDB();
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public void createServeur(Serveur s) {
	try {
	    st = cnx.connect()
		    .prepareStatement("insert into serveur(numServeur,nom, nomSalle, pseudoAdmin) values(?,?,?,?)");
	    st.setInt(1, s.getNumero());
	    st.setString(2, s.getNomServeur());
	    st.setString(3, s.getNomSalle());
	    st.setString(4, s.getPseudoAdmin());
	    st.executeUpdate();
	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

    }

    public ArrayList<Serveur> readServeur() {
	ArrayList<Serveur> liste = new ArrayList<Serveur>();
	try {
	    st = cnx.connect().prepareStatement("select * from serveur");
	    rs = st.executeQuery();
	    while (rs.next()) {
		Serveur s = new Serveur();
		s.setNumero(rs.getInt("numServeur"));
		s.setNomServeur(rs.getString("nom"));
		s.setNomSalle(rs.getString("nomSalle"));
		s.setPseudoAdmin(rs.getString("pseudoAdmin"));

		liste.add(s);
	    }

	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

	return liste;
    }

    public Serveur readServeur(int numServeur) {

	Serveur s = null;
	try {
	    st = cnx.connect().prepareStatement("select * from serveur where numServeur = ?");
	    st.setInt(1, numServeur);
	    rs = st.executeQuery();

	    if (rs.next()) {
		s = new Serveur();
		s.setNumero(rs.getInt("numServeur"));
		s.setNomServeur(rs.getString("nom"));
		s.setNomSalle(rs.getString("idSalle"));
		s.setPseudoAdmin(rs.getString("idAdmin"));
	    }

	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

	return s;
    }

    public void updateServeur(Serveur s) {
	try {
	    st = cnx.connect()
		    .prepareStatement("update serveur set nom=?, nomSalle=?, pseudoAdmin=? where numServeur=?");
	    st.setString(1, s.getNomServeur());
	    st.setString(2, s.getNomSalle());
	    st.setString(3, s.getPseudoAdmin());
	    st.setInt(4, s.getNumero());
	    st.executeUpdate();
	} catch (Exception ex) {
	    System.out.println("!!!" + ex.getMessage());
	}
    }

    public void deleteServeur(int numServeur) {
	try {
	    st = cnx.connect().prepareStatement("delete from serveur where numServeur = ?");
	    st.setInt(1, numServeur);
	    st.executeUpdate();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
}
