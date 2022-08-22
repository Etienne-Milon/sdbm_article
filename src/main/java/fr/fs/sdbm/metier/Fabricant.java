package fr.fs.sdbm.metier;

public class Fabricant
{
    private int id;
    private String libelle;

    public Fabricant(){}

    public Fabricant(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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
    @Override
    public String toString()
    {
	return libelle;
    }

}
