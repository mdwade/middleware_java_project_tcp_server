package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Salle;

public class SalleManager {
    AccessDB cnx = new AccessDB();
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public void createSalle(Salle s) {
	try {
	    st = cnx.connect().prepareStatement("insert into salle(numSalle,nom) values(?,?)");
	    st.setInt(1, s.getNumero());
	    st.setString(2, s.getNom());
	    st.executeUpdate();
	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

    }

    // read all salle
    public ArrayList<Salle> readSalle() {
	ArrayList<Salle> liste = new ArrayList<Salle>();
	try {
	    st = cnx.connect().prepareStatement("select * from salle");
	    rs = st.executeQuery();
	    while (rs.next()) {
		Salle s = new Salle();
		s.setNumero(rs.getInt("numSalle"));
		s.setNom(rs.getString("nom"));

		liste.add(s);
	    }

	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

	return liste;
    }

    // read salle by id
    public Salle readSalle(int numSalle) {

	Salle s = null;
	try {
	    st = cnx.connect().prepareStatement("select * from salle where numSalle = ?");
	    st.setInt(1, numSalle);
	    rs = st.executeQuery();

	    if (rs.next()) {
		s = new Salle();
		s.setNumero(rs.getInt("numSalle"));
		s.setNom(rs.getString("nom"));
	    }

	} catch (Exception e) {
	    System.out.println("!!!" + e.getMessage());
	}

	return s;
    }

    // update salle
    public void updateSalle(Salle s) {
	try {
	    st = cnx.connect().prepareStatement("update salle set nom=? where numSalle=?");
	    st.setString(1, s.getNom());
	    st.setInt(2, s.getNumero());
	    st.executeUpdate();
	} catch (Exception ex) {
	    System.out.println("!!!" + ex.getMessage());
	}
    }

    // delete salle
    public void deleteSalle(int numSalle) {
	try {
	    st = cnx.connect().prepareStatement("delete from salle where numSalle = ?");
	    st.setInt(1, numSalle);
	    st.executeUpdate();
	} catch (Exception e) {
	    System.out.println(e.getMessage());

	}

    }
}
