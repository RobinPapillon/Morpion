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
import morpion.Joueur;

/**
 *
 * @author damien
 */
public class VueFinDuel extends Observable{
    private final JFrame window ;
    private JButton boutonRejouer;
    private JButton boutonRage;
    private JLabel bienvenue;
    private String pseudoGagnant;
    private String pseudoPerdant;
    
    
    public VueFinDuel(String gagnant, String perdant){
        Font f = new Font("arial", 0, 50);
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("MORPION");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
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
        JPanel panelCentre = new JPanel(new GridLayout(3,5));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        boutonRejouer = new JButton("Rejouer");
        boutonRage = new JButton();
        boutonRage.setLayout(new GridLayout(4,1));
            boutonRage.add(new JLabel());
            boutonRage.add(new JLabel("Rage quit de"));
            boutonRage.add(new JLabel(perdant));
            boutonRage.add(new JLabel());
        for (int i = 0; i <= 5; i++) {
            panelCentre.add(new JLabel());
        }
        
        panelCentre.add(boutonRejouer);
        panelCentre.add(new JLabel());
        panelCentre.add(boutonRage);
        
        for (int i = 0; i <= 5; i++) {
            panelCentre.add(new JLabel());
        }
        
        
  
        
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);

    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void fermer() {
        this.window.setVisible(false);
    }
    
    public static void main(String [] args) {
        VueFinDuel exemple1 = new VueFinDuel("Thomas", "Damien");
        exemple1.afficher();
   }
    
}
