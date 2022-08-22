package fr.fs.sdbm.metier;

import java.util.ArrayList;
import java.util.List;

public class Continent
{
    private int id;
    private String libelle;
    private ArrayList <Pays> pays;
    
    public Continent()
    {
	
    }
    public Continent(Integer id, String libelle)
    {
	this.id = id;
	this.libelle = libelle;
	pays = new ArrayList <>();
    }

  
    public int getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public List<Pays> getPays()
    {
        return pays;
    }


    @Override
    public String toString()
    {
	return libelle ;
    }
    
}
