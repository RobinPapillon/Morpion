/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Morpion.*;
import java.util.ArrayList;

/**
 *
 * @author damien
 */
public class VueClassement extends Observable{
    private final JPanel mainPanel ;
    private JButton boutonRejouer;
    private JButton boutonRetour;
    private JLabel bienvenue;
    private final JFrame window ;
    
    
    public VueClassement(ArrayList<Joueur> classement){
       
        Font f = new Font("arial", 0, 50); 
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("MORPION");
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);

        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        bienvenue = new JLabel("FIN DU TOURNOI");
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
        JPanel panelCentre = new JPanel(new GridLayout(6,1));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        for (int i = 0; i < classement.size(); i++) {
            Joueur j = classement.get(i);
            JLabel pJ = new JLabel(j.getPseudo()+i+" place : "+j.getPoints()+" points", JLabel.CENTER);
            panelCentre.add(pJ);
        }
        for (int j = 0; j < 6-classement.size(); j++) {
            JLabel label = new JLabel();
            panelCentre.add(label);
        }



        
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonRetour = new JButton("Retour");
        panelBas.add(boutonRetour);
        panelBas.add(new JLabel());
        panelBas.add(new JLabel());
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();                   
                notifyObservers(new Message(MessageType.RETOUR_ACCEUIL));
                clearChanged();}
        });

    }
    
        public static void main(String [] args) {
        Joueur j1 = new Joueur("tom", 0);
        Joueur j2 = new Joueur("toto", 2);
        Joueur j3 = new Joueur("titi", 3); 
        ArrayList<Joueur> liste = new ArrayList<Joueur>();
        liste.add(j1);
        liste.add(j2);
        liste.add(j3);
        VueClassement exemple1 = new VueClassement(liste);
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("MORPION");
        window.add(exemple1.getMainPanel());
        window.setVisible(true);
   }

    /**
     * @return the mainPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void afficher(){
       this.window.setVisible(true);
    }
    
    public void close() {
        window.dispose();
    }
}
