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

/**
 *
 * @author damien
 */
public class VueFinDuel extends Observable{
    private final JPanel mainPanel ;
    private JButton boutonRejouer;
    private JButton boutonRage;
    private JLabel bienvenue;
    private String pseudoGagnant;
    private String pseudoPerdant;
    private final JFrame window ;
    
    
    public VueFinDuel(Joueur gagnant, Joueur perdant){
        pseudoGagnant = gagnant.getPseudo();
        pseudoPerdant = perdant.getPseudo();
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
        bienvenue = new JLabel("FIN DE LA PARTIE");
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
        boutonRage = new JButton("Retour");
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
    
//        public static void main(String [] args) {
//        VueFinDuel exemple1 = new VueFinDuel("Thomas","Damien");
//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//        window.setSize(700, 500);
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
//        window.setTitle("MORPION");
//        window.add(exemple1.getMainPanel());
//        window.setVisible(true);
//   }

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
