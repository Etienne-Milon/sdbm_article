package fr.fs.sdbm.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marque
{

    private SimpleIntegerProperty id;
    private SimpleStringProperty libelle;
    private Pays pays;
    private Fabricant fabricant;
    private  Continent continent;

    public Marque(){
        id = new SimpleIntegerProperty();
        libelle = new SimpleStringProperty();
    }
    public Marque(Integer id, String libelle) {
        this.id = new SimpleIntegerProperty(id);
        this.libelle = new SimpleStringProperty(libelle);
    }
    public Marque(Integer id, String libelle, Pays pays){
        this.id = new SimpleIntegerProperty(id);
        this.libelle = new SimpleStringProperty(libelle);
        this.pays = pays;
    }



    public int getId()
    {
	return id.getValue();
    }

    public void setId(Integer id)
    {
	this.id.setValue(id);
    }

    public String getLibelle()
    {
	return libelle.getValue();
    }

    public void setLibelle(String libelle)
    {
	this.libelle.set(libelle);
    }

    public Pays getPays()
    {
	return pays;
    }

    public void setPays(Pays pays)
    {
	this.pays = pays;
    }

    public Fabricant getFabricant()
    {
	return fabricant;
    }

    public void setFabricant(Fabricant fabricant)
    {
	this.fabricant = fabricant;
    }

    @Override
    public String toString()
    {
	return libelle.getValue();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty libelleProperty() {
        return libelle;
    }
    public Continent getContinent() {return continent;}

}
