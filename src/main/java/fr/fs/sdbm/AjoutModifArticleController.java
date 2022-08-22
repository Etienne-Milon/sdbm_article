package fr.fs.sdbm;

import fr.fs.sdbm.metier.*;
import fr.fs.sdbm.service.ServiceArticle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.controlsfx.control.SearchableComboBox;

import java.math.BigDecimal;
import java.util.Objects;


public class AjoutModifArticleController {

    private  MenuApp menuApp;
    Article article;
    private ServiceArticle serviceArticle;
    @FXML
    private TextField biereTextField;
    @FXML
    private TextField titrageTextField;
    @FXML
    private TextField prixDAchatTextField;
    @FXML
    private TextField stockInitalTextField;


    @FXML
    private Label nomVerif;
    @FXML
    private Label marqueVerif;
    @FXML
    private Label prixVerif;
    @FXML
    private Label volumeVerif;
    @FXML
    private Label titrageVerif;

    @FXML
    private SearchableComboBox typeBiereSCB;

    @FXML
    private SearchableComboBox volumeSCB;

    @FXML
    private SearchableComboBox marqueSCB;

    @FXML
    private SearchableComboBox couleurSCB;

    @FXML
    private SearchableComboBox fabricantSCB;

    @FXML
    private SearchableComboBox paysSCB;


    private boolean volumeCorrect;
    private boolean prixCorrect;
    private boolean nomCorrect;
    private boolean titrageCorrect;
    private boolean marqueCorrect;

    @FXML
    private void initialize() {
        serviceArticle = new ServiceArticle();
    // Initialisation de la vue
    // Initialisation des comboBox
        if(serviceArticle.getTypeBieresFiltre() != null)
            typeBiereSCB.setItems(FXCollections.observableArrayList(serviceArticle.getTypeBieresFiltre()));
        volumeSCB.setItems(FXCollections.observableArrayList(33,75));
        marqueSCB.setItems(FXCollections.observableArrayList(serviceArticle.getMarqueFiltre()));
        fabricantSCB.setItems((FXCollections.observableArrayList(serviceArticle.getFabricantFiltre())));
        paysSCB.setItems(FXCollections.observableArrayList(serviceArticle.getPaysFiltre()));
        couleurSCB.setItems(FXCollections.observableArrayList(serviceArticle.getCouleurFiltre()));
    }
    public void setArticle(Article article){
        if (article != null){
            this.article = article;
            biereTextField.setText(article.getLibelle());
            titrageTextField.setText(String.valueOf(article.getTitrage()));

            typeBiereSCB.getSelectionModel().select(article.getTypeBiere());
            volumeSCB.getSelectionModel().select(article.getVolume());
            marqueSCB.getSelectionModel().select(article.getMarque());
            fabricantSCB.getSelectionModel().select(article.getMarque().getFabricant());
            paysSCB.getSelectionModel().select(article.getMarque().getPays());
            couleurSCB.getSelectionModel().select(article.getCouleur());

            prixDAchatTextField.setText(String.valueOf(article.getPrixAchat().floatValue()));
            stockInitalTextField.setText(String.valueOf(article.getStock()));
        }
        else {
            this.article = new Article();
            typeBiereSCB.getItems().add(0, new TypeBiere(0, "Choisir un type"));
            typeBiereSCB.getSelectionModel().select(0);
            volumeSCB.getItems().add(0, "Choisir un volume");
            volumeSCB.getSelectionModel().select(0);
            marqueSCB.getItems().add(0, new Marque(0, "Choisir une marque"));
            marqueSCB.getSelectionModel().select(0);
            fabricantSCB.getItems().add(0, new Fabricant(0, "Choisir un fabricant"));
            fabricantSCB.getSelectionModel().select(0);
            paysSCB.getItems().add(0, new Pays("", "Choisir un pays"));
            paysSCB.getSelectionModel().select(0);
            couleurSCB.getItems().add(0,new Couleur(0,"Choisir une couleur"));
            couleurSCB.getSelectionModel().select(0);
        }
    }

    public void ValiderArticle() {
        String nom = getNom();
        BigDecimal prix = getPrix();
        int volume = getVolume();
        double titrage = getTitrage();
        int stock = getStock();
        Marque marque = getMarque();
        Couleur couleur = getCouleur();
        TypeBiere typeBiere = getType();
        if (Allcorrect()) {
            article.setAll(nom,prix,volume,titrage,marque,couleur,typeBiere,stock);

            if (article.getId() != 0) {
                if (serviceArticle.UpdateArticle(article)) {
                    System.out.println("update ok");
                } else {
                    System.out.println("update fail");
                }
                Close();
            } else {
                if (serviceArticle.InsertArticle(article)) {
                    System.out.println("insert ok");
                } else {
                    System.out.println("insert fail");
                }
                Close();
            }
        }
    }
    private boolean Allcorrect() {
        return (nomCorrect && volumeCorrect && prixCorrect && titrageCorrect && marqueCorrect);
    }

    private String getNom(){
        String nom = null;
        if (biereTextField.getText() != "") {
            nom = biereTextField.getText();
            nomCorrect = true;
        }
        else {
            nomVerif.setText("(!)");
            nomVerif.setTextFill(Color.RED);
            nomCorrect = false;
        }
        return nom;
    }

    private int getVolume() {
        int volume = 0;
        if (Objects.equals(volumeSCB.getValue(), "Choisir un volume")){
            volumeVerif.setText("(!)");
            volumeVerif.setTextFill(Color.RED);
            volumeCorrect = false;
            }
        else
        {
            volumeVerif.setText("");
            //volumeVerif.setTextFill(Color.GREEN);
            volume = (int) volumeSCB.getValue();
            volumeCorrect = true;
        }
        return volume;
    }

    private Marque getMarque() {
        Marque marque = null;
        if (marqueSCB.getValue().toString().equals("Choisir une marque")){
            marqueVerif.setText("(!)");
            marqueVerif.setTextFill(Color.RED);
            marqueCorrect = false;
        }
        else {
            marque = (Marque) marqueSCB.getValue();
            marqueVerif.setText("");
            marqueCorrect = true;
        }
        return marque;
    }

    private Couleur getCouleur(){
        Couleur couleur = null;
        if(couleurSCB.getValue().toString().equals("Choisir une couleur")){
            couleur = new Couleur();
        }
        else{
            couleur = (Couleur) couleurSCB.getValue();
        }
        return couleur;
    }

    private TypeBiere getType(){
        TypeBiere typeBiere = null;
        if(typeBiereSCB.getValue().toString().equals("Choisir un type")){
            typeBiere = new TypeBiere();
        }
        else{
            typeBiere = (TypeBiere) typeBiereSCB.getValue();
        }
        return typeBiere ;
    }

    private BigDecimal getPrix() {
        BigDecimal bigdec = null;
        if (prixDAchatTextField.getText() != "" && prixDAchatTextField.getText().matches("^-?\\d*\\.{0,1}\\d+")) {
            bigdec = new BigDecimal(prixDAchatTextField.getText());
            prixCorrect = true;
            prixVerif.setText("");
        } else {
            prixCorrect = false;
            prixVerif.setText("(!)");
            prixVerif.setTextFill(Color.RED);
        }
        return bigdec;
    }

    private double getTitrage(){
        Double titrage = -1.0;
        if (titrageTextField.getText() != "" && titrageTextField.getText().matches("^-?\\d*\\.?\\d+")) {
            if (Double.parseDouble(titrageTextField.getText()) >= 0 && (Double.parseDouble(titrageTextField.getText()) <= 26)) {
                titrage = Double.parseDouble(titrageTextField.getText());
                titrageCorrect = true;
                titrageVerif.setText("");
            } else {
                titrageVerif.setText("0 à 26");
                titrageVerif.setTextFill(Color.RED);
                titrageCorrect = false;
            }
        }else {
            titrageVerif.setText("(!)");
            titrageVerif.setTextFill(Color.RED);
            titrageCorrect = false;
        }
        return titrage;
    }

    private int getStock(){
        int stock = -1;
        if (stockInitalTextField.getText() !="" && stockInitalTextField.getText().matches("^-?\\d+"))
            stock = Integer.parseInt(stockInitalTextField.getText());
        return stock;
    }

    public void Annuler(){
        Close();
    }

    public void Close(){
        menuApp.getDialogueStage().close();
    }

    public void setMenuApp(MenuApp menuApp){
        this.menuApp = menuApp;
    }
}
