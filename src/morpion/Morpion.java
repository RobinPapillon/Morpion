/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import java.util.ArrayList;

/**
 *
 * @author Damien
 */
public class Morpion {

    private Joueur currentJ;
    private Joueur j1;
    private Joueur j2;
    private Plateau plateau;
    
    
    public Morpion(Joueur j1, Joueur j2, int n){
        setJ1(j1);
        setJ2(j2);
        this.plateau = new Plateau();
        this.plateau.setPlateau(n);
    }
    
    public void cocherCase(Bouton b){
        Symbole s = getCurrentJ().getSymbole();
        int x = b.getX();
        int y = b.getY();
    }
    
    public void joueurSuivant(){
        if (getCurrentJ() == getJ1()) {
            setCurrentJ(getJ2());
        } else{
            setCurrentJ(getJ1());
        }
    }
    
    public String resultat(int n){ // n coté du morpion
        int o = 0;
        int x = 0;
        //on parcourt ligne première case si x : x++ si o, o++, deuxième case
        // si x et x avant x++ si o et o avant o++ si x et o avant o=0 et x++ etc
        //si x ==3 ou o == 3 victoire
        // sinon on parcourt en diago idem
        // idem pour les colonnes
        ArrayList<Case> cases = getPlateau().getCases();
        
        //lignes
        for (int i = 0; i < n; i++) {
            x = 0;
            o = 0;
            for (int j = 0; j < n; j++) {
                if (cases.get(i-1 + n + j - 1).getSymbole() == Symbole.CROIX && o > 0) {
                    x++;
                    o = 0;
                }else if (cases.get(i-1 + n + j - 1).getSymbole() == Symbole.ROND && x >= 0) {
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
            }
            if (x == 3) {
                return "X";
            }else if (o == 3) {
                return "O";
            }
        }
        
        //colonnes
        for (int j = 0; j < n; j++) {
            x = 0;
            o = 0;
            for (int i = 0; i < n; i++) {
                if (cases.get(i-1 + n + j - 1).getSymbole() == Symbole.CROIX && o >= 0) {
                    x++;
                    o = 0;
                }else if (cases.get(i-1 + n + j - 1).getSymbole() == Symbole.ROND && x >= 0) {
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
            }
            if (x == 3) {
                return "X";
            }else if (o == 3) {
                return "O";
            }
        }
        
        //diagonales descendantes de gauche à droite
        for (int i = 0; i < n; i++) {
            x = 0;
            o = 0;
            if (cases.get(i-1 + n + i - 1).getSymbole() == Symbole.CROIX && o >= 0) {
                    x++;
                    o = 0;
                }else if (cases.get(i-1 + n + i - 1).getSymbole() == Symbole.ROND && o > 0) {
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
        }
        
        //diagonales montantes de gauche à droite
        for (int i = n; i > 0; i--) {
            if (cases.get(i-1 + n + i - 1).getSymbole() == Symbole.CROIX && o > 0) {
                    x++;
                    o = 0;
                }else if (cases.get(i-1 + n + i - 1).getSymbole() == Symbole.ROND && o > 0) {
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
        }
        
        if (x == 3) {
                return "X";
            }else if (o == 3) {
                return "O";
            }else{
                return "Match Nul";
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

}
