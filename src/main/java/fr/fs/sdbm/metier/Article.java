package fr.fs.sdbm.metier;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class Article
{

	private IntegerProperty id;
	private StringProperty libelle;
	private Integer volume;
	private Double titrage;
	private BigDecimal prixAchat;
	private Marque marque;
	private Couleur couleur;
	private TypeBiere typeBiere;
	private int stock;
	
	public Article()
	{
		id = new SimpleIntegerProperty();
		libelle = new SimpleStringProperty();
		couleur = new Couleur();
		typeBiere = new TypeBiere();
		marque = new Marque();
	}

	public Article(IntegerProperty  id, StringProperty  libelle)
	{
		this.id = id;
		this.libelle = libelle;
		couleur = new Couleur();
		typeBiere = new TypeBiere();
		marque = new Marque();
	}

	public IntegerProperty idProperty()
	{
		return id;
	}


	public Integer getId()
	{
		return id.get();
	}

	public void setId(int id)
	{
		this.id.setValue(id);
	}

	public StringProperty libelleProperty()
	{
		return libelle;
	}

	public String getLibelle()
	{
		return libelle.get();
	}

	public void setLibelle(String libelle)
	{
		this.libelle.set(libelle);
	}

	public IntegerProperty volumeProperty()
	{
		return new SimpleIntegerProperty(volume);
	}

	public Integer getVolume()
	{
		return volume;
	}

	public void setVolume(Integer volume)
	{
		this.volume = volume;
	}

	public DoubleProperty titrageProperty()
	{
		return new SimpleDoubleProperty(titrage);
	}

	public Double getTitrage()
	{
		return titrage;
	}

	public void setTitrage(double titrage)
	{
		this.titrage = titrage;
	}

	public Couleur getCouleur()
	{
		return couleur;
	}

	public void setCouleur(Couleur couleur)
	{
		this.couleur = couleur;
	}

	public Marque getMarque()
	{
		return marque;
	}

	public void setMarque(Marque marque)
	{
		this.marque = marque;
	}

	public TypeBiere getTypeBiere()
	{
		return typeBiere;
	}

	public void setTypeBiere(TypeBiere typeBiere)
	{
		this.typeBiere = typeBiere;
	}

	public BigDecimal getPrixAchat()
	{
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat)
	{
		this.prixAchat = prixAchat;
	}

	public int getStock() { return stock;}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setAll(String libelle, BigDecimal prixDAchat, int volume, double titrage,
					   Marque marque, Couleur couleur, TypeBiere typeBiere, int stock) {
		setLibelle(libelle);
		setPrixAchat(prixDAchat);
		setVolume(volume);
		setTitrage(titrage);
		setMarque(marque);
		setCouleur(couleur);
		setTypeBiere(typeBiere);
		setStock(stock);
	}

}


