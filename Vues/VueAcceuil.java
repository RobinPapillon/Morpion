/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Morpion.ModeDeJeu;
 

/**
 *
 * @author damien
 */
public class VueAcceuil extends Observable{
    private final JPanel mainPanel ;
    private JButton boutonValider;
    private JButton boutonRetour;
    private JButton boutonUnContreUn;
    private JButton boutonTournoi;
    private JButton boutonRegle;
    private JLabel bienvenue;
    
    
    public VueAcceuil(){
        Font f = new Font("arial", 0, 50);               
        mainPanel = new JPanel(new BorderLayout());
        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        bienvenue = new JLabel("BIENVENUE");
        panelHaut.add(bienvenue);
        bienvenue.setFont(f);
        
        // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel();
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
        // =================================================================================
        // EST
        JPanel panelEst = new JPanel();
        mainPanel.add(panelEst, BorderLayout.EAST);
        
        // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel(new GridLayout(5,5));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        boutonUnContreUn = new JButton("1 vs 1");
        boutonUnContreUn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();                                   
                notifyObservers(ModeDeJeu.Duel);
                clearChanged();}
        });
        
        boutonTournoi = new JButton("Tournoi");
        boutonTournoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();                                   
                notifyObservers(ModeDeJeu.Tournoi);
                clearChanged();}
        });
        boutonRegle = new JButton("Règle");
        boutonRegle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();                                   
                notifyObservers("Regle");
                clearChanged();}
        });
        for (int i = 0; i <= 5; i++) {
            panelCentre.add(new JLabel());
        }
        
        panelCentre.add(boutonUnContreUn);
        panelCentre.add(new JLabel());
        panelCentre.add(boutonTournoi);
        
        for (int i = 0; i <= 7; i++) {
            panelCentre.add(new JLabel());
        }
        
        panelCentre.add(boutonRegle);
        
        for (int i = 0; i <= 6; i++) {
            panelCentre.add(new JLabel());
        }
        
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
//        boutonRetour = new JButton("Retour");        
//        
//        boutonRetour.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                setChanged();
//                String message = "Retour";                   
//                notifyObservers(message);
//                clearChanged();}
//        });
//        
//        panelBas.add(boutonRetour);
//        panelBas.add(new JLabel()) ;
//        
//        boutonValider = new JButton("Valider");
//        
//        boutonValider.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                setChanged();
//                String message = "Valider";                   
//                notifyObservers(message);
//                clearChanged();}
//        });
//        
//        panelBas.add(boutonValider);
    }

    /**
     * @return the mainPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
    
    public static void main(String [] args) {
        VueAcceuil exemple1 = new VueAcceuil();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("MORPION");
        window.add(exemple1.getMainPanel());
        window.setVisible(true);
   }
    public void afficher(){
       this.window.setVisible(true);
    }
    
    public void close() {
        window.dispose();
    }
}
