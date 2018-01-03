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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author damien
 */
public class VueDuel extends Observable{
    private final JPanel mainPanel ;
    private JButton boutonValider;
    private JButton boutonRetour;
    private JLabel titre;
    private JPanel panelJoueurs;
    
    
    public VueDuel(){
        Font f = new Font("arial", 0, 50);
        
        mainPanel = new JPanel(new BorderLayout());
        
        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        titre = new JLabel("1 VS 1");
        panelHaut.add(titre);
        titre.setFont(f);
        
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
        JPanel panelCentre = new JPanel(new GridLayout(3,1));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        panelCentre.add(new JLabel("SELECTION DES PSEUDOS", JLabel.CENTER));
        panelCentre.add(panelJoueurs = new JPanel(new GridLayout(4,2)));
        for (int i = 0; i < 8; i++) {
            if (i == 2) {
                panelJoueurs.add(new JLabel("Joueur 1", JLabel.CENTER));
            }else if (i == 3) {
                JTextField pseudo1 = new JTextField();
                panelJoueurs.add(pseudo1);
            }else if (i == 4) {
                panelJoueurs.add(new JLabel("Joueur 2", JLabel.CENTER));
            }else if (i == 5) {
                JTextField pseudo2 = new JTextField();
                panelJoueurs.add(pseudo2);
            }else{
                panelJoueurs.add(new JLabel());
            }
        }
        panelCentre.add(new JLabel());
        
        // =================================================================================
        // SUD
        
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonRetour = new JButton("Retour");        
        
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();
                String message = "Taille Plateau";                   
                notifyObservers(message);
                clearChanged();}
        });
        
        panelBas.add(boutonRetour);
        panelBas.add(new JLabel()) ;
        
        boutonValider = new JButton("Valider");
        
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();
                String message = "Grille";                   
                notifyObservers(message);
                clearChanged();}
        });
        
        panelBas.add(boutonValider);
    }
    
    
    // =================================================================================
    // Déclaratioon des autre méthodes.
    
       public static void main(String [] args) {
        VueDuel exemple1 = new VueDuel();
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
