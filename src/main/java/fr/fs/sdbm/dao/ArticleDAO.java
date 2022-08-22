package fr.fs.sdbm.dao;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ArticleSearch;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class ArticleDAO extends DAO<Article,ArticleSearch>
{
    public ArticleDAO(Connection connexion) {super(connexion);}

    public ArrayList<Article> getLike(ArticleSearch articleSearch)
    {
        ResultSet rs;
        ArrayList<Article> liste = new ArrayList<>();
        String procedureStockee = "{call dbo.sp_QBE_Vue_Article (?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee))
        {
            cStmt.setString(1,articleSearch.getLibelle());
            cStmt.setInt(2,articleSearch.getVolume());
            cStmt.setDouble(3,articleSearch.getTitrageMin());
            cStmt.setDouble(4,articleSearch.getTitrageMax());
            cStmt.setInt(5,articleSearch.getMarque().getId());
            cStmt.setInt(6,articleSearch.getFabricant().getId());
            cStmt.setString(7,articleSearch.getPays().getId());
            cStmt.setInt(8,articleSearch.getContinent().getId());
            cStmt.setInt(9,articleSearch.getCouleur().getId());
            cStmt.setInt(10,articleSearch.getType().getId());
            cStmt.setBigDecimal(11,articleSearch.getPrixMin());
            cStmt.setBigDecimal(12,articleSearch.getPrixMax());
            // pagination 13,14...

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next())
            {
                // création d'un nouvel article à partir d'une ligne du resultset
                Article newArticle = new Article();
                newArticle.setId(rs.getInt(1));
                newArticle.setLibelle(rs.getString(2));
                newArticle.setPrixAchat(rs.getBigDecimal(3));
                newArticle.setVolume(rs.getInt(4));
                newArticle.setTitrage(rs.getDouble(5));
                newArticle.setMarque(new Marque());
                newArticle.getMarque().setId(rs.getInt(6));
                newArticle.getMarque().setLibelle(rs.getString(7));
                newArticle.getMarque().setFabricant(new Fabricant());
                newArticle.getMarque().getFabricant().setId(rs.getInt(8));
                newArticle.getMarque().getFabricant().setLibelle(rs.getString(9));
                newArticle.getMarque().setPays(new Pays());
                newArticle.getMarque().getPays().setId(rs.getString(10));
                newArticle.getMarque().getPays().setLibelle(rs.getString(11));
                newArticle.getMarque().getPays().setContinent(new Continent());
                newArticle.getMarque().getPays().getContinent().setId(rs.getInt(12));
                newArticle.getMarque().getPays().getContinent().setLibelle(rs.getString(13));
                newArticle.setCouleur(new Couleur(rs.getInt(14),rs.getString(15)));
                newArticle.setTypeBiere(new TypeBiere(rs.getInt(16),rs.getString(17)));
                newArticle.setStock(rs.getInt(18));
                liste.add(newArticle);
            }
            rs.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public Article getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Article> getAll() {
        return null;
    }
    @Override
    public boolean insert(Article article) {
        String Statement = "INSERT INTO ARTICLE (NOM_ARTICLE,PRIX_ACHAT,VOLUME,TITRAGE,ID_MARQUE,ID_COULEUR,ID_TYPE,Stock) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(Statement))
             {
            pStmt.setString(1, article.getLibelle());
            pStmt.setBigDecimal(2, article.getPrixAchat());
            pStmt.setInt(3,article.getVolume());
            pStmt.setDouble(4,article.getTitrage());
            pStmt.setInt(5,article.getMarque().getId());
            if (article.getCouleur().getId() == 0) {
                pStmt.setNull(6, Types.INTEGER);
            } else {
                pStmt.setInt(6, article.getCouleur().getId());
            }

            if (article.getTypeBiere().getId()==0)
                pStmt.setNull(7,Types.INTEGER);
            else
               pStmt.setInt(7,article.getTypeBiere().getId());

            pStmt.setInt(8,article.getStock());
            pStmt.execute();

            return true;
            }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Article article) {
        String Statement = "UPDATE ARTICLE SET NOM_ARTICLE = ?, PRIX_ACHAT = ?,VOLUME = ?,TITRAGE = ?,ID_MARQUE = ?,ID_COULEUR = ?,ID_TYPE = ?,Stock = ? WHERE ID_ARTICLE = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(Statement))
        {
            pStmt.setString(1, article.getLibelle());
            pStmt.setBigDecimal(2, article.getPrixAchat());
            pStmt.setInt(3,article.getVolume());
            pStmt.setDouble(4,article.getTitrage());
            pStmt.setInt(5,article.getMarque().getId());
            pStmt.setInt(6,article.getCouleur().getId());
            pStmt.setInt(7,article.getTypeBiere().getId());
            pStmt.setInt(8,article.getStock());
            pStmt.setInt(9,article.getId());
            pStmt.execute();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Article article) {
        String Statement = "DELETE FROM ARTICLE WHERE ID_ARTICLE = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(Statement))
        {
            pStmt.setInt(1,article.getId());
            pStmt.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
