package Application;


import Controleur.*;
import Vues.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Thomas
 */
public class Application {
    public static void main(String [] args){
        Controle c = new Controle();
        c.start();
    }
}
