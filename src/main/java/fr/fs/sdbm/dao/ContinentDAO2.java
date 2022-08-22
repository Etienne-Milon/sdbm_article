package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.Continent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ContinentDAO2 extends DAO<Continent, Continent> {
	public ContinentDAO2(Connection connexion) {
		super(connexion);
	}


	public ArrayList<Continent> getAll() {
		ArrayList<Continent> liste = new ArrayList<>();
		try (Statement stmt = connexion.createStatement()){

			// Determine the column set column

			String strCmd = "SELECT id_continent,nom_continent from continent order by nom_continent";
			ResultSet rs = stmt.executeQuery(strCmd);
			Continent continentLu ;
			while (rs.next()) {
				continentLu = new Continent(rs.getInt(1), rs.getString(2));
				continentLu.getPays().addAll(DaoFactory.getPaysDAO().getByContinent(continentLu.getId()));
				liste.add(continentLu);
			}
			rs.close();
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}

	@Override
	public Continent getByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Continent objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Continent object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Continent object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Continent> getLike(Continent objet) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
}