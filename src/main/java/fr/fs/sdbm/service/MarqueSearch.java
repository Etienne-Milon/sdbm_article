package fr.fs.sdbm.service;

import fr.fs.sdbm.metier.*;


public class MarqueSearch
{
	private int id;
	private String libelle;
	private Pays pays;
	private Continent continent;
	private Fabricant fabricant;

	public MarqueSearch()
	{
		pays = new Pays();
		continent = new Continent();
		fabricant = new Fabricant();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getLibelle()
	{
		return libelle;
	}
	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}
	public Pays getPays()
	{
		return pays;
	}
	public void setPays(Pays pays)
	{
		this.pays = pays;
	}
	public Continent getContinent()
	{
		return continent;
	}
	public void setContinent(Continent continent)
	{
		this.continent = continent;
	}
	public Fabricant getFabricant()
	{
		return fabricant;
	}
	public void setFabricant(Fabricant fabricant)
	{
		this.fabricant = fabricant;
	}

}