package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.TypeBiere;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeBiereDAO extends DAO<TypeBiere,TypeBiere>{

    protected TypeBiereDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<TypeBiere> getAll(){
        ArrayList<TypeBiere> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){
            String strCmd = "SELECT * FROM TYPEBIERE order by NOM_TYPE";
            ResultSet rs = stmt.executeQuery(strCmd);
        while (rs.next()){
            TypeBiere typeBiere = new TypeBiere();
            typeBiere.setId(rs.getInt(1));
            typeBiere.setLibelle(rs.getString(2));
            liste.add(typeBiere);
        }
        rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public TypeBiere getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<TypeBiere> getLike(TypeBiere objet) {
        return null;
    }

    @Override
    public boolean insert(TypeBiere objet) {
        return false;
    }

    @Override
    public boolean update(TypeBiere object) {
        return false;
    }

    @Override
    public boolean delete(TypeBiere object) {
        return false;
    }
}
