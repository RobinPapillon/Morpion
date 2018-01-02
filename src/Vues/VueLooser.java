package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueLooser extends Observable{
    private final JFrame window ;
    private JButton boutonValider;
    private JLabel looser;
    
    public VueLooser(){
        Font f = new Font("arial", 0, 100);
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
        panelCentre.add(new JLabel());
        looser = new JLabel("LOOSER", JLabel.CENTER);
        panelCentre.add(looser);
        looser.setFont(f);
        looser.setForeground(Color.red);
        panelCentre.add(new JLabel());
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        
        panelBas.add(new JLabel());
        
        
        boutonValider = new JButton("Valider");
        panelBas.add(new JLabel());
        
        panelBas.add(boutonValider);
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void fermer() {
        this.window.setVisible(false);
    }
    
    public static void main(String [] args) {
        VueLooser exemple1 = new VueLooser();
        exemple1.afficher();
   }
}
