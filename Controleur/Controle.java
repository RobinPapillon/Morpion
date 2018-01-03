/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Morpion.Plateau;
import Morpion.Joueur;
import Morpion.ModeDeJeu;
import Morpion.Symbole;
import Vues.VueMorpion;
import Vues.VueAcceuil;
import Vues.VueParamPlateau;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Morpion.Bouton;

/**
 *
 * @author damien
 */
public class Controle implements Observer{
    
    private Joueur j1 = new Joueur("toto", Symbole.CROIX);
    private Joueur j2 = new Joueur("titi", Symbole.ROND);
//    private Morpion morpion = new Morpion(j1, j2, 3);
    
    private Joueur currentJ;
    private Plateau plateau;
    private JFrame vue;
    private JPanel panelActif;
    
    
    public Controle(){
        vue = new JFrame();
        vue.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        vue.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        vue.setLocation(dim.width/2-vue.getSize().width/2, dim.height/2-vue.getSize().height/2);
        vue.setTitle("MORPION");       
    }
    
    public void start() {
        panelActif = new VueAcceuil().getMainPanel();
        vue.add(panelActif);
        vue.setVisible(true);
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
        if (observable instanceof VueAcceuil){
            if(obj instanceof ModeDeJeu){
                plateau = new Plateau((ModeDeJeu) obj);                
                
            }
            // bouton regle
        }
        
        else if (observable instanceof VueParamPlateau){
            if (obj instanceof JPanel){
                 plateau.setPlateau(0);
            }
        }
                
        else if (obj instanceof Bouton) {
            Bouton bouton = (Bouton) obj;
            cocherCase(bouton);
            if (resultat(bouton.getX()-1, bouton.getY()-1)== "Partie Gagne"){
                
            }
        }
        
        joueurSuivant(); 
    }
 

    /**
     * @param panelActif the panelActif to set
     */
    public void switchVueActive() {
        vue.
    }    
}





