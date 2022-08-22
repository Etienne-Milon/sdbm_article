package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.Continent;
import fr.fs.sdbm.metier.Pays;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PaysDAO extends DAO<Pays, Pays> {
	public PaysDAO(Connection connexion) {
		super(connexion);
	}

	private ResultSet rs;

	public ArrayList<Pays> getAll() {
		ArrayList<Pays> liste = new ArrayList<>();
		try (Statement stmt = connexion.createStatement()){

			// Determine the column set column

			String strCmd = "SELECT id_pays,nom_pays,id_continent,(select nom_continent from continent where id_continent = P.id_continent) from pays as P order by nom_pays";
			rs = stmt.executeQuery(strCmd);

			while (rs.next()) {
				liste.add(new Pays(rs.getString(1), rs.getString(2), new Continent(rs.getInt(3), rs.getString(4))));
			}
			rs.close();

		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	public ArrayList<Pays> getByContinent(int continent) {
		ArrayList<Pays> liste = new ArrayList<>();
		try {

			PreparedStatement pStmt = connexion
					.prepareStatement("SELECT id_pays,nom_pays from pays where id_continent =  ?  order by nom_pays");
			// Determine the column set column

			pStmt.setInt(1, continent);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				liste.add(new Pays(rs.getString(1), rs.getString(2)));
			}
			rs.close();
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public Pays getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Pays objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pays object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Pays object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Pays> getLike(Pays objet) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
}