package fr.fs.sdbm;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ArticleSearch;
import fr.fs.sdbm.service.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.RangeSlider;

import java.math.BigDecimal;


public class GestionArticleController {
    // Description de la table
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, Integer> idColumn;
    @FXML
    private TableColumn<Article, String> nomColumn;
    @FXML
    private TableColumn<Article,Integer> volumeColumn;
    @FXML
    private TableColumn<Article,Double> titrageColumn;



    // description des champs de recherche
    @FXML
    private TextField libelleSearch;
    @FXML
    private TextField titreLow;
    @FXML
    private TextField titreHigh;
    @FXML
    private TextField prixLow;
    @FXML
    private TextField prixHigh;
    @FXML
    private ComboBox<Fabricant> fabricantSearch;
    @FXML
    private ComboBox<Pays> paysSearch;
    @FXML
    private ComboBox<Couleur> couleurSearch;
    @FXML
    private ComboBox<TypeBiere> typeBiereSearch;
    @FXML
    private ComboBox<Continent> continentSearch;
    @FXML
    private ComboBox<Marque> marqueSearch;

    // description des selecteurs

    @FXML
    private RadioButton volume33;
    @FXML
    private RadioButton volume75;
    @FXML
    private RangeSlider titrageRangeSlider;
    @FXML
    private RangeSlider prixRangeSlider;

    //labels
    @FXML
    private Label biereLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label couleurLabel;
    @FXML
    private Label titrageLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private Label marqueLabel;
    @FXML
    private Label fabricantLabel;
    @FXML
    private Label paysLabel;
    @FXML
    private Label continentLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label stockLabel;


    @FXML
    private MenuApp menuApp;

    private ServiceArticle serviceArticle;
    private Article selectedArticle;

    public GestionArticleController() {
    }


    // Initialisation de la vue
    @FXML
    private void initialize() {

        serviceArticle = new ServiceArticle();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty().asObject());
        titrageColumn.setCellValueFactory(cellData -> cellData.getValue().titrageProperty().asObject());

        // Initialisation des comboBox
        continentSearch.setItems(FXCollections.observableArrayList(serviceArticle.getContinentFiltre()));
        continentSearch.getItems().add(0, new Continent(0, "Choisir un continent"));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        paysSearch.getItems().add(0, new Pays("", "Choisir un pays"));
        //paysSearch.valueProperty().addListener(observable -> filtrerPays());
        paysSearch.valueProperty().addListener(observable -> filterArticle());

        fabricantSearch.setItems(FXCollections.observableArrayList(serviceArticle.getFabricantFiltre()));
        fabricantSearch.getItems().add(0, new Fabricant(0, "Choisir un fabricant"));
        fabricantSearch.valueProperty().addListener(observable -> filterArticle());

        marqueSearch.setItems(FXCollections.observableArrayList(serviceArticle.getMarqueFiltre()));
        marqueSearch.getItems().add(0,new Marque(0,"Choisir une marque"));
        marqueSearch.valueProperty().addListener(observable -> filterArticle());

        couleurSearch.setItems(FXCollections.observableArrayList(serviceArticle.getCouleurFiltre()));
        couleurSearch.getItems().add(0, new Couleur(0, "Choisir une couleur"));
        couleurSearch.valueProperty().addListener(observable -> filterArticle());

        typeBiereSearch.setItems(FXCollections.observableArrayList(serviceArticle.getTypeBieresFiltre()));
        typeBiereSearch.getItems().add(0, new TypeBiere(0, "Choisir un type de biere"));
        typeBiereSearch.valueProperty().addListener(observable -> filterArticle());

        // Initialisation des RangeSliders
        titrageRangeSlider.setMin(0);
        titrageRangeSlider.setMax(26);
        titrageRangeSlider.setLowValue(0);
        titrageRangeSlider.setHighValue(26);
        titrageRangeSlider.setBlockIncrement(1);
        titrageRangeSlider.highValueProperty().addListener(observable -> filterArticle());
        titrageRangeSlider.lowValueProperty().addListener(observable -> filterArticle());

        prixRangeSlider.setMin(0);
        prixRangeSlider.setMax(5);
        prixRangeSlider.setLowValue(0);
        prixRangeSlider.setHighValue(5);
        prixRangeSlider.setBlockIncrement(0.1);
        prixRangeSlider.highValueProperty().addListener(observable -> filterArticle());
        prixRangeSlider.lowValueProperty().addListener(observable -> filterArticle());


        // Initialisation des TextFields
        libelleSearch.textProperty().addListener(observable -> filterArticle());
        titreLow.setText(String.valueOf(titrageRangeSlider.getLowValue()));
        titreHigh.setText(String.valueOf(titrageRangeSlider.getHighValue()));
        prixLow.setText(String.valueOf(prixRangeSlider.getLowValue()));
        prixHigh.setText(String.valueOf(prixRangeSlider.getHighValue()));


        // Initialisation des RadioButtons
        volume33.setSelected(true);
        volume75.setSelected(true);

        //tableView
        articleTable.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalue)-> afficherArticle(newvalue));

        selectedArticle = articleTable.getSelectionModel().getSelectedItem();
    }

    private void afficherArticle(Article article) {
        if (article != null)
        {
        biereLabel.setText(article.getLibelle());
        typeLabel.setText(article.getTypeBiere().getLibelle());
        couleurLabel.setText(article.getCouleur().getLibelle());
        titrageLabel.setText(String.valueOf(article.getTitrage()));
        volumeLabel.setText(String.valueOf(article.getVolume().intValue()));
        marqueLabel.setText(article.getMarque().getLibelle());
        fabricantLabel.setText(article.getMarque().getFabricant().getLibelle());
        paysLabel.setText(article.getMarque().getPays().getLibelle());
        continentLabel.setText(article.getMarque().getPays().getContinent().getLibelle());
        prixLabel.setText(String.valueOf(article.getPrixAchat().doubleValue()));
        stockLabel.setText(String.valueOf(article.getStock()));
        selectedArticle = article;
        }
    }

    private void resetAfficherArticle(){
        biereLabel.setText("");
        typeLabel.setText("");
        couleurLabel.setText("");
        titrageLabel.setText("");
        volumeLabel.setText("");
        marqueLabel.setText("");
        fabricantLabel.setText("");
        paysLabel.setText("");
        continentLabel.setText("");
        prixLabel.setText("");
        stockLabel.setText("");
        selectedArticle = null;
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
        filterArticle();
    }

    private void filterContinent() {
        if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getId() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (continentSearch.getSelectionModel().getSelectedItem()).getPays()));
        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        }
        paysSearch.getItems().add(0, new Pays("", "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(0);
        filterArticle();
    }

    private void filtrerPays(){
        if (paysSearch.getSelectionModel().getSelectedItem() != null
                && (paysSearch.getSelectionModel().getSelectedItem()).getId() != ""){
            marqueSearch.setItems(FXCollections.observableArrayList(
                    (paysSearch.getSelectionModel().getSelectedItem()).getMarque()));
        } else {
            marqueSearch.setItems(FXCollections.observableArrayList(serviceArticle.getMarqueFiltre()));
        }
        marqueSearch.getItems().add(0,new Marque(0, "Choisir une marque",new Pays()));
        marqueSearch.getSelectionModel().select(0);
        filterArticle();
    }

    @FXML
    private void filterArticle() {
        resetAfficherArticle();
        ArticleSearch articleSearch = new ArticleSearch();
        articleSearch.setLibelle(libelleSearch.getText());
        if(volume33.isSelected()&&!volume75.isSelected())
            articleSearch.setVolume(33);
        if(!volume33.isSelected()&&volume75.isSelected())
            articleSearch.setVolume(75);
        if(volume33.isSelected()&&volume75.isSelected())
            articleSearch.setVolume(0);
        if(!volume33.isSelected()&&!volume75.isSelected())
            articleSearch.setVolume(1);
        if (paysSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setPays(paysSearch.getSelectionModel().getSelectedItem());
        if (continentSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setContinent(continentSearch.getSelectionModel().getSelectedItem());
        if (fabricantSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setFabricant(fabricantSearch.getSelectionModel().getSelectedItem());
        if (couleurSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setCouleur(couleurSearch.getSelectionModel().getSelectedItem());
        if (typeBiereSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setTypeBiere(typeBiereSearch.getSelectionModel().getSelectedItem());
        if (marqueSearch.getSelectionModel().getSelectedItem() != null)
            articleSearch.setMarque(marqueSearch.getSelectionModel().getSelectedItem());
        articleSearch.setTitrageMin(titrageRangeSlider.getLowValue());
        articleSearch.setTitrageMax(titrageRangeSlider.getHighValue());
        titreLow.setText(String.valueOf(titrageRangeSlider.getLowValue()));
        titreHigh.setText(String.valueOf(titrageRangeSlider.getHighValue()));
        articleSearch.setPrixAchatMin(BigDecimal.valueOf(prixRangeSlider.getLowValue()));
        articleSearch.setPrixAchatMax(BigDecimal.valueOf(prixRangeSlider.getHighValue()));
        prixLow.setText(String.valueOf(prixRangeSlider.getLowValue()));
        prixHigh.setText(String.valueOf(prixRangeSlider.getHighValue()));

        articleTable.setItems(FXCollections.observableArrayList(serviceArticle.getFilteredArticles(articleSearch)));
    }

    @FXML
    private void update(){
        if (selectedArticle !=null)
            menuApp.showAjoutModifArticle(selectedArticle);
    }
    @FXML
    private void create(){
        selectedArticle = null;
        menuApp.showAjoutModifArticle(selectedArticle);
    }

    @FXML
    private void delete() {
        if (selectedArticle != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression d'un article");
            alert.setHeaderText("Confirmer la suppression d'un article");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK)
                serviceArticle.DeleteArticle(selectedArticle);
        }
    }
}
