package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.Couleur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CouleurDAO extends DAO<Couleur,Couleur> {
    public CouleurDAO(Connection connexion) {super(connexion);
    }

    private ResultSet rs;

    @Override
    public Couleur getByID(int id) {
        return null;
    }

    public ArrayList<Couleur> getAll() {
        ArrayList<Couleur> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){
            String strCmd = "SELECT id_couleur,nom_couleur FROM Couleur";
            rs = stmt.executeQuery(strCmd);

            while (rs.next()) {
                liste.add(new Couleur(rs.getInt(1),rs.getString(2)));
            }
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Couleur> getLike(Couleur objet) {
        return null;
    }

    @Override
    public boolean insert(Couleur objet) {
        return false;
    }

    @Override
    public boolean update(Couleur object) {
        return false;
    }

    @Override
    public boolean delete(Couleur object) {
        return false;
    }
}
