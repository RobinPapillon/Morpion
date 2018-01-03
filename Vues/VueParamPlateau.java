/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import Morpion.*;

/**
 *
 * @author carrierd
 */
public class VueParamPlateau extends Observable{
    private JPanel mainPanel ;
    private HashMap<Integer, JRadioButton> ensembleDesBoutonsRadios ;
    private final JFrame window;
    
    public VueParamPlateau(){
        
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
        //panelHaut.setSize(695, 95);
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelHaut.add(new JLabel("Choix du Plateau")) ;
 
        // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel(new GridLayout(6,3));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        
        JRadioButton boutonRadio;
        ButtonGroup groupeEspeces = new ButtonGroup();
        ensembleDesBoutonsRadios = new HashMap<>();
        
        for (int i = 0; i < 18; i++) {
            if (i == 4) {
                panelCentre.add(new JLabel("Taille du plateau :", JLabel.CENTER));
            }else if (i == 7) {
                boutonRadio = new JRadioButton("3 X 3");
                panelCentre.add(boutonRadio);
                groupeEspeces.add(boutonRadio);
                ensembleDesBoutonsRadios.put(ensembleDesBoutonsRadios.size(), boutonRadio);
                boutonRadio.setSelected(true);
            }else if (i == 10) {
                boutonRadio = new JRadioButton("4 X 4");
                panelCentre.add(boutonRadio);
                groupeEspeces.add(boutonRadio);
                ensembleDesBoutonsRadios.put(ensembleDesBoutonsRadios.size(), boutonRadio);
            }else if (i == 13) {
                boutonRadio = new JRadioButton("5 X 5");
                panelCentre.add(boutonRadio);
                groupeEspeces.add(boutonRadio);
                ensembleDesBoutonsRadios.put(ensembleDesBoutonsRadios.size(), boutonRadio);
            }else{
                panelCentre.add(new JLabel());
            }
        }
    
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        panelBas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        JButton boutonRetour = new JButton("Retour");        
        
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();                                  
                notifyObservers(new Message(MessageType.RETOUR));
                clearChanged();}
        });
        
        panelBas.add(boutonRetour);
        panelBas.add(new JLabel()) ;
        
        JButton boutonValider = new JButton("Valider");
        
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();               
                notifyObservers(new Message(MessageType.VALIDER_TAILLE));
                clearChanged();}
        });
        
        panelBas.add(boutonValider);
        
    }
    
        public static void main(String [] args) {
        VueParamPlateau exemple1 = new VueParamPlateau();
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
    
 

