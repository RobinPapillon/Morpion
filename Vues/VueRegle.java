package Vues;

import Morpion.Message;
import Morpion.MessageType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueRegle extends Observable{
    private final JPanel mainPanel ;
    private JButton boutonRetour;
    private final JFrame window;
    
    public VueRegle(){
       
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
        panelCentre.add(new JLabel("Pour gagner au morpion il faut aligner autant de symbole ", JLabel.LEFT));
        panelCentre.add(new JLabel("que le nombre de case sur un coté de la grille. ", JLabel.LEFT));
        panelCentre.add(new JLabel("exemple : sur une grille 5x5 il faut aligner 5 symboles.", JLabel.LEFT)); 
        panelCentre.add(new JLabel("On peut aligner les symboles en ligne colones ou diagonales.", JLabel.LEFT));
        panelCentre.add(new JLabel("Pour le système du tournoi chaque joueur rencontre chaque autre joueur une fois.", JLabel.LEFT));
        panelCentre.add(new JLabel("Une victoire rapporte 2points au vainqueur, un nul rapporte 1 point au deux , joueurs.", JLabel.LEFT));
        
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
