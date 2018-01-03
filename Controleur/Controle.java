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
    
    private Joueur currentJ;
    private Plateau plateau;
    private VueAcceuil vueAcceuil;
    private VueDuel vueDuel;
    private VueTournoi vueTournoi;
    private VueRegle vueRegle;
    private VueFinDuel vueFinDuel;
    private VueParamPlateau vueParam;
    private VueMorpion vueMorpion;
    
    private ArrayList<String> noms;
    
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
        
        plateau = new Plateau(j1.getPseudo(), j2.getPseudo());
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
        vueMorpion.setCurrentJoueur(getCurrentJ().getSymbole());
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
        this.j1.setSymbole(Symbole.ROND);
    }

    /**
     * @return the plateau
     */
    public Plateau getPlateau() {
        return this.plateau;
    }
    
    public ArrayList<Plateau> creerTournoi (ArrayList<String> noms,int taille){
        ArrayList<Plateau> listeTournoi = new ArrayList<Plateau>();
        for (int i = 0; i < noms.size()-1; i++) {
            for (int j = i+1; j < noms.size(); j++) {
                Plateau p = new Plateau(noms.get(i),noms.get(j), taille);
                listeTournoi.add(p);
            }
        }
        return listeTournoi;
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
                 
             case FIN_DUEL:
                 Joueur gagnant = currentJ;
                 if (vueCourante2 == "vueDuel") {
                     vueFinDuel = new VueFinDuel(gagnant);
                     vueFinDuel.addObserver(this);
                     vueMorpion.close();
                     vueFinDuel.afficher();
                     vueCourante = "vueFinDuel";
                 }else if (vueCourante2 == "vueTournoi") {
                     
                 }
                 
                 break;
                 
             case RETOUR_ACCEUIL:
                
                     if (vueCourante == "vueDuel") {                     
                         vueDuel.close();
                     }else if (vueCourante == "vueTournoi") {
                         vueTournoi.close();
                     }else if (vueCourante == "vueRegle") {
                         vueRegle.close();
                     }else if (vueCourante == "vueFinDuel") {
                         vueFinDuel.close();
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
                    vueParam = new VueParamPlateau();
                    vueParam.addObserver(this);
                    vueTournoi.close();
                    
                }
                vueParam.afficher();
                break;
                
            case VALIDER_TAILLE:
                int tailleSelect = vueParam.getTailleSelect();
                if (vueCourante2 == "vueDuel") {
                    vueMorpion = new VueMorpion(noms.get(0),noms.get(1),tailleSelect);
                    plateau = new Plateau(j1.getPseudo(), j2.getPseudo(), tailleSelect);
                    vueMorpion.addObserver(this);
                    vueParam.close();
                    vueMorpion.afficher();
                    
                    if (vueMorpion.getS() == j1.getSymbole()) {
                        currentJ = j1;
                    } else{
                        currentJ = j2;
                    }
                }
                break;
                 
             case REGLE:
                 vueRegle = new VueRegle();
                 vueRegle.addObserver(this);
                 vueAcceuil.close();
                 vueRegle.afficher();
                 vueCourante = "vueRegle";
                 break;
                 
             case RETOUR:
                 vueParam.close();
                 if (vueCourante2 == "vueDuel") {
                     vueDuel.afficher();
                 } else if (vueCourante2 == "vueTournoi") {
                     vueTournoi.afficher();
                 }
                 break;
            
             case BOUTON:
                 MessageBouton mb = (MessageBouton) obj;
                 Bouton b = mb.getB();
                 cocherCase(b);
                 if (resultat(b.getX()-1, b.getY()-1)== "Continue") {
                     joueurSuivant();                    
                 }
                 else if (resultat(b.getX()-1, b.getY()-1)== "Partie Gagné") {
                     if (true) {
                         
                     }
                 }
         }
    }
  
}
