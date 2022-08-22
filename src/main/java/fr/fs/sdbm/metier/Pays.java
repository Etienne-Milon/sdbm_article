package fr.fs.sdbm.metier;

import java.util.ArrayList;
import java.util.List;

public class Pays
{
    private String id;
    private String libelle;
    private Continent continent;
    private ArrayList <Marque> marques;

    public Pays()
    {
    	id="";
    	continent = new Continent();
    }
    public Pays(String id, String libelle)
    {
	this.id = id;
	this.libelle = libelle;
	this.continent = new Continent();
    }

    public Pays(String id, String libelle, Continent continent)
    {
	this.id = id;
	this.libelle = libelle;
	this.continent = continent;
    marques = new ArrayList<>();
    }

    public String getId()
    {
	return id;
    }

    public void setId(String id)
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

    public Continent getContinent()
    {
	return continent;
    }

    public void setContinent(Continent continent)
    {
	this.continent = continent;
    }

    @Override
    public String toString()
    {
	return libelle;
    }

    public List<Marque> getMarque() { return marques;}

}
