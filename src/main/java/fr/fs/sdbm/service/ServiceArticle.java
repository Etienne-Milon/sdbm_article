package fr.fs.sdbm.service;
import fr.fs.sdbm.dao.DaoFactory;
import fr.fs.sdbm.metier.*;
import java.util.ArrayList;

public class ServiceArticle extends ServiceMarque
{
    private ArrayList<Couleur> couleurFiltre;
    private ArrayList<Marque> marqueFiltre;
    private ArrayList<TypeBiere> typeBieresFiltre;
    private ArrayList<Pays> paysFiltre;
    private ArrayList<Continent> continentFiltre;
    private ArrayList<Fabricant> fabricantFiltre;

    public ServiceArticle(){
        couleurFiltre = DaoFactory.getCouleurDAO().getAll();
        marqueFiltre = DaoFactory.getMarqueDAO().getAll();
        typeBieresFiltre = DaoFactory.getTypeBiereDAO().getAll();
        paysFiltre = DaoFactory.getPaysDAO().getAll();
        continentFiltre = DaoFactory.getContinentDAO().getAll();
        fabricantFiltre = DaoFactory.getFabricantDAO().getAll();

    }

    public ArrayList<Couleur> getCouleurFiltre() {
        return couleurFiltre;
    }

    public ArrayList<Marque> getMarqueFiltre() {
        return marqueFiltre;
    }

    public ArrayList<TypeBiere> getTypeBieresFiltre() {
        return typeBieresFiltre;
    }

    public ArrayList<Pays> getPaysFiltre() {
        return paysFiltre;
    }

    public ArrayList<Continent> getContinentFiltre() {
        return continentFiltre;
    }

    public ArrayList<Fabricant> getFabricantFiltre() {
        return fabricantFiltre;
    }
    public ArrayList<Article> getFilteredArticles (ArticleSearch articleSearch)
    {
        return DaoFactory.getArticleDAO().getLike(articleSearch);
    }


    public boolean InsertArticle (Article article){
        return DaoFactory.getArticleDAO().insert(article);

    }
    public boolean UpdateArticle(Article article) {
        return DaoFactory.getArticleDAO().update(article);
    }

    public boolean DeleteArticle(Article article) {
        return DaoFactory.getArticleDAO().delete(article);
    }
}
