/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Morpion;

import javax.swing.JButton;

/**
 *
 * @author damien
 */
public class Bouton {
    private JButton butt;
    private Boolean bool;
    private int x;
    private int y;
    
    public Bouton(JButton b, Boolean bool, int x, int y) {
        setButt(b);
        setBool(bool);
        setX(x);
        setY(y);
    }

    /**
     * @return the b
     */
    public JButton getButt() {
        return butt;
    }

    /**
     * @param b the b to set
     */
    public void setButt(JButton b) {
        this.butt = b;
    }

    /**
     * @return the bool
     */
    public Boolean getBool() {
        return bool;
    }

    /**
     * @param bool the bool to set
     */
    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
}
