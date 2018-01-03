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
                 break;
                 
             case VALIDER_JOUEURS:
                 MessageNoms mn = (MessageNoms)obj; //interprète le message reçu comme un message contenant une liste de noms 
                 ArrayList<String> noms = mn.getNoms();
         }
    }
  
}





