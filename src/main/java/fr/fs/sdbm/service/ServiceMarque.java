package fr.fs.sdbm.service;


import fr.fs.sdbm.dao.DaoFactory;
import fr.fs.sdbm.metier.*;

import java.util.ArrayList;


public class ServiceMarque
{
	private ArrayList<Pays> paysFiltre;
	private ArrayList<Continent> continentFiltre;
	private ArrayList<Fabricant> fabricantFiltre;
	public ServiceMarque()
	{
		paysFiltre = DaoFactory.getPaysDAO().getAll();
		continentFiltre = DaoFactory.getContinentDAO().getAll();
		fabricantFiltre = DaoFactory.getFabricantDAO().getAll();
		Fabricant fabricant = new Fabricant();
		fabricant.setLibelle("Choisir un fabricant");
		fabricantFiltre.add(0, fabricant);
	}


	public ArrayList<Pays> getPaysFiltre()
	{
		return paysFiltre;
	}

	public ArrayList<Continent> getContinentFiltre()
	{
		return continentFiltre;
	}

	public ArrayList<Fabricant> getFabricantFiltre()
	{
		return fabricantFiltre;
	}

	public ArrayList<Marque> getFilteredMarques(MarqueSearch marqueSearch)
	{
		return DaoFactory.getMarqueDAO().getLike(marqueSearch);
	}

}
