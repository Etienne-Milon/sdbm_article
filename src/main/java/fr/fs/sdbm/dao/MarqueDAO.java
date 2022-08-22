package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.MarqueSearch;

import java.sql.*;
import java.util.ArrayList;


public class MarqueDAO extends DAO<Marque, MarqueSearch>
{
	public MarqueDAO(Connection connexion)
	{
		super(connexion);
	}

	public ArrayList<Marque> getLike(MarqueSearch marqueSearch)
	{
		ResultSet rs;
		ArrayList<Marque> liste = new ArrayList<>();
		String procedureStockee = "{call dbo.SP_MARQUE_QBE (?,?,?,?,?,?)}";
		try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee))
		{
			cStmt.setString(1, marqueSearch.getLibelle());
			cStmt.setInt(2, marqueSearch.getFabricant().getId());
			cStmt.setString(3, marqueSearch.getPays().getId());
			cStmt.setInt(4, marqueSearch.getContinent().getId());
			cStmt.setNull(5, Types.INTEGER);
			cStmt.setNull(6, Types.INTEGER);

			cStmt.execute();
			rs = cStmt.getResultSet();

			while (rs.next())
			{
				// création d'une nouvelle marque à partir d'une ligne du resultset
				Marque newMarque = new Marque();
				newMarque.setId(rs.getInt(1));
				newMarque.setLibelle(rs.getString(2));
				newMarque.setPays(new Pays(rs.getString(3), rs.getString(4), new Continent(rs.getInt(5), rs.getString(6))));
				newMarque.setFabricant(new Fabricant());
				newMarque.getFabricant().setId(rs.getInt(7));
				newMarque.getFabricant().setLibelle(rs.getString(8));
				liste.add(newMarque);
			}
			rs.close();
		}

		// Handle any errors that may have occurred.
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public ArrayList<Marque> getAll()
	{
		ArrayList<Marque> liste = new ArrayList<>();
		try (Statement stmt = connexion.createStatement()){
			String strCmd = "SELECT * FROM MARQUE order by nom_marque";
			ResultSet rs = stmt.executeQuery(strCmd);
			while (rs.next()){
				Marque marque = new Marque();
				marque.setId(rs.getInt(1));
				marque.setLibelle(rs.getString(2));
				liste.add(marque);
			}
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public Marque getByID(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Marque objet)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Marque object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Marque object)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
