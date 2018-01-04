/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Morpion.*;
import Vues.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;;

/**
 *
 * @author damien
 */
public class Controle implements Observer{
    
    private Joueur j1;
    private Joueur j2; 
    
    private Joueur currentJ; //Joueur courant (celui qui joue ou vient de jouer)
    
    private Plateau plateau;
    
    //ensemble des vues
    private VueAcceuil vueAcceuil;
    private VueDuel vueDuel;
    private VueTournoi vueTournoi;
    private VueRegle vueRegle;
    private VueFinDuel vueFinDuel;
    private VueParamPlateau vueParam;
    private VueMorpion vueMorpion;
    private VueClassement vueClassement;
    
    private ArrayList<String> noms;
    
    private String vueCourante; //variable de rappelle de la vue courante
    private String modeDeJeu; //variable de rappel du mode de jeu choisi
    
    // liste des matchs d'un tournoi
    private ArrayList<Plateau> listeMatchs = new ArrayList<Plateau>();
    
    private ArrayList<Joueur> joueurs;//liste des joueurs d'un tournoi
    
    private int nbPartie; //comptage du nombre partie pour une tournoi
    
    
    public Controle(){
        vueAcceuil = new VueAcceuil();
        vueAcceuil.addObserver(this);
    }
    
    public void start() {
        vueAcceuil.afficher();
    }
    
    //Méthode qui enregistre la case dans la classe plateau 
    //cocher en fonction des coordonnées du bouton.
    public void cocherCase(Bouton b){ 
        Symbole s = getCurrentJ().getSymbole();
        int x = b.getX()-1;
        int y = b.getY()-1;
        
        plateau.addCaseCoche();
        
        if (s == Symbole.CROIX){
            plateau.ajoutMatriceCroix(x, y);
            
        }else{
            plateau.ajoutMatriceRond(x, y);
        }  
    }
    
    //Méthode pour change de joueur dès qu'une case est cochée.
    public void joueurSuivant(){
        if (getCurrentJ() == getJ1()) {
            setCurrentJ(getJ2());
            
        } else{
            setCurrentJ(getJ1());
            
        }
        vueMorpion.setCurrentSymbole(getCurrentJ().getSymbole());
    }
    
    //Test a chaque fois qu'une case est coché si la partie est fini = victoire ou nul
    //sinon envoe de continuer
    public String resultat(int x, int y){ // n coté du morpion
        
        Symbole s = getCurrentJ().getSymbole();
        if (s == Symbole.CROIX && plateau.verifCroix(x,y) || s == Symbole.ROND && plateau.verifRond(x,y) ){
            return "Partie Gagne";
        }else if(plateau.getNbCasesCochees() == plateau.getNbCase()){
            return "Match nul";
        }else{
            return "Continue";
        }
    }
      

    /**
     * @return the currentJ
     */
    public Joueur getCurrentJ() {
        return currentJ;
    }

    /**
     * @param currentJ the currentJ to set
     */
    public void setCurrentJ(Joueur currentJ) {
        this.currentJ = currentJ;
    }

    /**
     * @return the j1
     */
    public Joueur getJ1() {
        return j1;
    }

    /**
     * @param j1 the j1 to set
     */
    public void setJ1(Joueur j1) {
        this.j1 = j1;
        this.j1.setSymbole(Symbole.CROIX);
    }

    /**
     * @return the j2
     */
    public Joueur getJ2() {
        return j2;
    }

    /**
     * @param j2 the j2 to set
     */
    public void setJ2(Joueur j2) {
        this.j2 = j2;
        this.j2.setSymbole(Symbole.ROND);
    }

    /**
     * @return the plateau
     */
    public Plateau getPlateau() {
        return this.plateau;
    }
    
    //Méthode de création des différents plateaus des différents matchs d'un tournoi.
    //En fonction du nombre de joueur (nb) il y a nb*(nb-1)/2 matchs.
    //Système de la ronde simple. CF vueRègle
    public ArrayList<Plateau> creerTournoi (ArrayList<Joueur> joueurs,int taille){
        ArrayList<Plateau> listeTournoi = new ArrayList<Plateau>();
        
        for (int i = 0; i < joueurs.size()-1; i++) {
            for (int j = i+1; j < joueurs.size(); j++) {
                Plateau p = new Plateau(joueurs.get(i).getPseudo(),joueurs.get(j).getPseudo(), taille);
                listeTournoi.add(p);
            }
        }
        
        return listeTournoi;
    }

    //Fonction qui détermine le score max d'un tableau, utile pour le tri.
    public Joueur max(ArrayList<Joueur> tab){
        int i = 0;
        int max = -1;
        Joueur j = new Joueur(null) ;
        while (i < tab.size()){
            if(tab.get(i).getPoints() > max){
                max = tab.get(i).getPoints();
                j = tab.get(i);                
            }
            i++;
        }
        return j;
    }
    
    //Méthode de tri des joueurs en fonction de leur score
    //Du 1er au dernier
    
    //Cette méthode ne fonctionne pas
    public ArrayList<Joueur> trier (ArrayList<Joueur> tab){
        ArrayList<Joueur> listeTrie = new ArrayList<Joueur>();
        Joueur j = new Joueur(null);
        while (tab.isEmpty() == false){
            j = max(tab);
            tab.remove(j);
            listeTrie.add(j);
        }
        return listeTrie;
    }
    
    
    //Diférents cas de réponse en fonction des boutons cliquer
    @Override
    public void update(Observable observable, Object obj) {
         Message m = (Message) obj;
         
         switch(m.getType()){
             case EXIT: //Si bouton quitter on quitte le jeu
                 vueAcceuil.close();
                 break;
                 
             case DUEL: //Si bouton 1 contre 1
                 vueDuel = new VueDuel();
                 vueDuel.addObserver(this);
                 vueAcceuil.close();
                 vueDuel.afficher();
                 vueCourante = "vueDuel";
                 modeDeJeu = "vueDuel";
                 break;
                 
             case RETOUR_ACCEUIL: //Si un bouton retour est cliquer hormis celui dans vueParamètre
                
                     if (vueCourante == "vueDuel") {                     
                         vueDuel.close();
                     }else if (vueCourante == "vueTournoi") {
                         vueTournoi.close();
                     }else if (vueCourante == "vueRegle") {
                         vueRegle.close();
                     }else if (vueCourante == "vueFinDuel") {
                         vueFinDuel.close();
                     }else if (vueCourante == "vueMorpion") {
                         vueMorpion.close();
                     }
                     
                 vueAcceuil.afficher();
                 break;
                 
            case TOURNOI: //Si bouton tournoi cliquer
                 vueTournoi = new VueTournoi();
                 vueTournoi.addObserver(this);
                 vueAcceuil.close();
                 vueTournoi.afficher();
                 vueCourante = "vueTournoi";
                 modeDeJeu = "vueTournoi";
                 break;
                 
            case VALIDER_JOUEURS: //Bouton valider de la vue Selection des pseudos des joueurs
                 if (vueCourante == "vueDuel") {
                    MessageNoms mn = (MessageNoms)obj;
                    noms = mn.getNoms();
                    Joueur joueur1 = new Joueur(noms.get(0));
                    Joueur joueur2 = new Joueur(noms.get(1));
                    setJ1(joueur1);
                    setJ2(joueur2);
                    vueParam = new VueParamPlateau();
                    vueParam.addObserver(this);
                    vueDuel.close();
                    
                } else if(vueCourante == "vueTournoi"){
                    MessageNoms mn = (MessageNoms)obj;
                    noms = mn.getNoms();
                    joueurs = new ArrayList<Joueur>();
                    for (int i = 0; i < noms.size(); i++) {
                        Joueur j = new Joueur(noms.get(i));
                        j.setPoints(0);
                        joueurs.add(j);
                    }
                    vueParam = new VueParamPlateau();
                    vueParam.addObserver(this);
                    vueTournoi.close();
                    
                }
                vueParam.afficher();
                break;
                
                //Bouton valider de la vueParamPlateau
                //création du premier plateau
            case VALIDER_TAILLE: 
                int tailleSelect = vueParam.getTailleSelect();
                if (modeDeJeu == "vueDuel") {
                    vueMorpion = new VueMorpion(noms.get(0),noms.get(1),tailleSelect);
                    plateau = new Plateau(j1.getPseudo(), j2.getPseudo(), tailleSelect);
                    vueMorpion.addObserver(this);
                    vueParam.close();
                    vueMorpion.afficher();
                    if (plateau.getNbCasesCochees() == 0) {
                        if (vueMorpion.getS() == j1.getSymbole()) {
                            currentJ = j1;
                        } else{
                            currentJ = j2;
                        }
                    } 
                    vueCourante = "vueMorpion";
                    
                } else if (modeDeJeu == "vueTournoi") {
                    listeMatchs = creerTournoi(joueurs, tailleSelect);
                    plateau = listeMatchs.get(0);
                    vueMorpion = new VueMorpion(plateau.getJ1().getPseudo(), 
                                        plateau.getJ2().getPseudo(), tailleSelect);
                    setJ1(plateau.getJ1());
                    setJ2(plateau.getJ2());
                    vueMorpion.addObserver(this);
                    vueParam.close();
                    vueMorpion.afficher();
                    if (plateau.getNbCasesCochees() == 0) {
                        if (vueMorpion.getS() == plateau.getJ1().getSymbole()) {
                            currentJ = plateau.getJ1();
                        } else{
                            currentJ = plateau.getJ2();
                        }
                    }
                    nbPartie = 1;
                }
                break;
                 
             case REGLE: //Si bouton Règle cliquer
                 vueRegle = new VueRegle();
                 vueRegle.addObserver(this);
                 vueAcceuil.close();
                 vueRegle.afficher();
                 vueCourante = "vueRegle";
                 break;
                 
             case RETOUR: //Si bouton retour de vueParamPlateau cliquer
                 vueParam.close();
                 
                 if (modeDeJeu == "vueDuel") {
                     vueDuel.afficher();
                     
                 } else if (modeDeJeu == "vueTournoi") {
                     vueTournoi.afficher();
                 }
                 
                 break;
            
            case BOUTON: //Cas ou les boutons du morpion sont cliquer
                MessageBouton mb = (MessageBouton) obj;
                Bouton b = mb.getB();
                cocherCase(b);
                String verdict = resultat(b.getX()-1, b.getY()-1);
                
                // Dans tous les cas si pas de vainqueur ou de match nul on continue
                if (verdict == "Continue") { 
                    joueurSuivant();                    
                }
                
                //Si partie gagner ou match nul on amène à la vueFinDuel si fin duel sinon on regarde si tous les
                //matchs ont été dans le tournoi si oui vueClassment sinon nouveau match.
                else if (verdict == "Partie Gagne") {
                    if (modeDeJeu == "vueDuel") {
                        vueFinDuel = new VueFinDuel();
                        vueFinDuel.setGagnant(currentJ);
                        vueFinDuel.addObserver(this);
                        vueMorpion.close();
                        vueFinDuel.afficher();
                        
                    } else if (modeDeJeu == "vueTournoi") {
                        tailleSelect = vueParam.getTailleSelect();
                        currentJ.addPoints(2);
                        
                        if (nbPartie < listeMatchs.size()) {
                            nbPartie++;                  
                            vueMorpion.close();
                            
                            plateau = listeMatchs.get(nbPartie-1);
                            vueMorpion = new VueMorpion(plateau.getJ1().getPseudo(), 
                                                plateau.getJ2().getPseudo(), tailleSelect);
                            
                            setJ1(plateau.getJ1());
                            setJ2(plateau.getJ2());
                            vueMorpion.addObserver(this);
                            vueParam.close();
                            vueMorpion.afficher();
                            
                            if (plateau.getNbCasesCochees() == 0) {
                                if (vueMorpion.getS() == plateau.getJ1().getSymbole()) {
                                    currentJ = plateau.getJ1();
                                } else{
                                    currentJ = plateau.getJ2();
                                }
                            }
                            
                        } else{
                            vueClassement = new VueClassement(joueurs);
                            vueClassement.addObserver(this);
                            vueMorpion.close();
                            vueClassement.afficher();
                        }
                    }
                    
                }else if (verdict == "Match nul") {
                    if (modeDeJeu == "vueDuel") {
                        vueFinDuel = new VueFinDuel();
                        vueFinDuel.setGagnant(null);
                        vueFinDuel.addObserver(this);
                        vueMorpion.close();
                        vueFinDuel.afficher();
                        
                    }else if(modeDeJeu == "vueTournoi"){
                        tailleSelect = vueParam.getTailleSelect();
                        j1.addPoints(1);
                        j2.addPoints(1);
                        
                        if (nbPartie < listeMatchs.size()) {
                            nbPartie++;                           
                            vueMorpion.close();
                            plateau = listeMatchs.get(nbPartie-1);
                            vueMorpion = new VueMorpion(plateau.getJ1().getPseudo(), 
                                                plateau.getJ2().getPseudo(), tailleSelect);
                            setJ1(plateau.getJ1());
                            setJ2(plateau.getJ2());
                            vueMorpion.addObserver(this);
                            vueParam.close();
                            vueMorpion.afficher();
                            if (plateau.getNbCasesCochees() == 0) {
                                if (vueMorpion.getS() == plateau.getJ1().getSymbole()) {
                                    currentJ = plateau.getJ1();
                                } else{
                                    currentJ = plateau.getJ2();
                                }
                            }
                            
                        } else{
                            ArrayList<Joueur> classement = trier(joueurs);
                            vueClassement = new VueClassement(classement);
                            vueClassement.addObserver(this);
                            vueMorpion.close();
                            vueClassement.afficher();
                        }
                    }
                    
                }
                
                break;
                 
            case REJOUER: //Si le bouton rejouer est cliquer après un duel on refait la même partie
                tailleSelect = vueParam.getTailleSelect();
                
                vueMorpion = new VueMorpion(noms.get(0),noms.get(1),tailleSelect);
                
                plateau = new Plateau(j1.getPseudo(), j2.getPseudo(), tailleSelect);
                vueMorpion.addObserver(this);
                vueFinDuel.close();
                vueMorpion.afficher();
                
                if (plateau.getNbCasesCochees() == 0) {
                    if (vueMorpion.getS() == j1.getSymbole()) {
                        currentJ = j1;
                    } else{
                        currentJ = j2;
                    }
                }
                
                break;
         }
    }
  
}
