package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.Continent;
import fr.fs.sdbm.metier.Pays;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class ContinentDAO extends DAO <Continent,Continent>
{
    public ContinentDAO(Connection connexion)
	{
		super(connexion);
	}

	public ArrayList<Continent> getAll()
    {
	ArrayList<Continent> liste = new ArrayList<>();
	try (Statement stmt = connexion.createStatement())
	{

	    // Determine the column set column

	    String strCmd = "SELECT C.id_continent,nom_continent ,id_pays,nom_pays from continent as C join pays on C.id_continent=pays.ID_CONTINENT order by nom_continent,nom_pays";
	    ResultSet rs = stmt.executeQuery(strCmd);
	    Continent continentLu = new Continent(0,"");
	    while (rs.next())
	    {
		if (continentLu.getId() != rs.getInt(1))
		{
		    continentLu = new Continent(rs.getInt(1), rs.getString(2));
		    liste.add(continentLu);
		}
		continentLu.getPays().add(new Pays(rs.getString(3),rs.getString(4)));
	    }
	    rs.close();
	}

	// Handle any errors that may have occurred.
	catch (Exception e)
	{
	    System.out.println(e.getMessage());
	}
	return liste;
    }

	@Override
	public Continent getByID(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Continent objet)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Continent object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Continent object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Continent> getLike(Continent objet)
	{
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
}