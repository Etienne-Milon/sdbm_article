package fr.fs.sdbm.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO <T, S>
{
	protected Connection connexion;

	protected DAO(Connection connexion)
	{
		this.connexion = connexion;
	}
	public abstract T getByID(int id);
	public abstract ArrayList <T> getAll();
	public abstract ArrayList <T> getLike(S objet);
	public abstract boolean insert(T objet);
	public abstract boolean update(T object);
	public abstract boolean delete( T object);
}
