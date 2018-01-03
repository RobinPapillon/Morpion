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
import javax.swing.JPanel;;

/**
 *
 * @author damien
 */
public class Controle implements Observer{
    
    private Joueur j1 = new Joueur("toto", Symbole.CROIX);
    private Joueur j2 = new Joueur("titi", Symbole.ROND);
    
    private Joueur currentJ;
    private Plateau plateau;
    private VueAcceuil vueAcceuil;
    private VueDuel vueDuel;
    private VueTournoi vueTournoi;
    private VueRegle vueRegle;
    private VueFinDuel vueFinDuel;
    private VueParamPlateau vueParam;
    private VueMorpion vueMorpion;
    
    private Message lastMessage;
    
    private String vueCourante;
    private String vueCourante2; //vue courante n-1
    
    
    public Controle(){
        vueAcceuil = new VueAcceuil();
        vueAcceuil.addObserver(this);
    }
    
    public void start() {
        vueAcceuil.afficher();
    }
    
    
    public void cocherCase(Bouton b){
        Symbole s = getCurrentJ().getSymbole();
        int x = b.getX();
        int y = b.getY();
        plateau.addCaseCoche();
        if (s == Symbole.CROIX){
            plateau.ajoutMatriceCroix(x, y);
        }else{
            plateau.ajoutMatriceRond(x, y);
        }
        
    }
    
    public void joueurSuivant(){
        if (getCurrentJ() == getJ1()) {
            setCurrentJ(getJ2());
        } else{
            setCurrentJ(getJ1());
        }
    }
    
    public String resultat(int x, int y) // n coté du morpion 
    {
        
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
    }

    /**
     * @return the plateau
     */
    public Plateau getPlateau() {
        return this.plateau;
    }


    
    @Override
    public void update(Observable observable, Object obj) {
         Message m = (Message) obj;
         
         switch(m.getType()){
             case EXIT: 
                 vueAcceuil.close();
                 break;
                 
             case DUEL:
                 vueDuel = new VueDuel();
                 vueDuel.addObserver(this);
                 vueAcceuil.close();
                 vueDuel.afficher();
                 vueCourante = "vueDuel";
                 vueCourante2 = "vueDuel";
                 break;
                 
             case RETOUR_ACCEUIL:
                 if (vueCourante == "vueDuel") {
                     vueDuel.close();
                 }else if (vueCourante == "vueTournoi") {
                     vueTournoi.close();
                 }else if (vueCourante == "vueRegle") {
                     vueRegle.close();
                 }
                 vueAcceuil.afficher();
                 break;
                 
            case TOURNOI:
                 vueTournoi = new VueTournoi();
                 vueTournoi.addObserver(this);
                 vueAcceuil.close();
                 vueTournoi.afficher();
                 vueCourante = "vueTournoi";
                 vueCourante2 = "vueTournoi";
                 break;
                 
            case VALIDER_JOUEURS:
                 if (vueCourante == "vueDuel") {
                    String joueur1 = "";
                    String joueur2 = "";
                    vueParam = new VueParamPlateau();
                    vueParam.addObserver(this);
                    vueDuel.close();
                    
                    } else if(vueCourante == "vueTournoi"){
                        if (vueTournoi.getNumListeDeroulante() == 3) {
                            String joueur1 = "";
                            String joueur2 = "";
                            String joueur3 = "";
                        }else if (vueTournoi.getNumListeDeroulante() == 4) {
                            String joueur1 = "";
                            String joueur2 = "";
                            String joueur3 = "";
                            String joueur4 = "";
                        }else{
                            String joueur1 = "";
                            String joueur2 = "";
                            String joueur3 = "";
                            String joueur4 = "";
                            String joueur5 = "";
                        }
                        vueTournoi.close();
                }
                 vueParam.afficher();
                 break;
                 
             case REGLE:
                 vueRegle = new VueRegle();
                 vueRegle.addObserver(this);
                 vueAcceuil.close();
                 vueRegle.afficher();
                 vueCourante = "vueRegle";
                 vueCourante2 = "vueAcceuil";
                 break;
                 
             case RETOUR:
                 vueParam.close();
                 if (vueCourante2 == "vueDuel") {
                     vueDuel.afficher();
                 } else if (vueCourante2 == "vueTournoi") {
                     vueTournoi.afficher();
                 } else{
                     vueAcceuil.afficher();
                 }
         }
    }
  
}





