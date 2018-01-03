package Vues;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import static java.awt.SystemColor.window;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;
import Morpion.*;

public class VueMorpion extends Observable{
    private final JPanel mainPanel ;
    private ArrayList<Bouton> buttons = new ArrayList<>(); 
    private final JFrame window;
    
    
    public VueMorpion(String pseudo1, String pseudo2, int n){
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
        panelHaut.add(new JLabel("Joueur 1 VS Joueur 2")) ;
        
        // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel(new GridLayout(3,1));
        mainPanel.add(panelOuest, BorderLayout.WEST);
        //panelOuest.setSize(145, 345);
        panelOuest.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelOuest.add(new JLabel("       Joueur 1       ", JLabel.CENTER));
        panelOuest.add(new JLabel("\n" + pseudo1, JLabel.CENTER));
        panelOuest.add(new JLabel("\n CROIX", JLabel.CENTER));
        
        // =================================================================================
        // EST
        JPanel panelEst = new JPanel(new GridLayout(3,1));
        mainPanel.add(panelEst, BorderLayout.EAST);
        //panelEst.setSize(145, 345);
        panelEst.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelEst.add(new JLabel("       Joueur 2       ", JLabel.CENTER));
        panelEst.add(new JLabel("\n" + pseudo2, JLabel.CENTER));
        panelEst.add(new JLabel("\n ROND", JLabel.CENTER));
        
        // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel(new GridLayout(n,n));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        //panelOuest.setSize(395, 345);
        panelCentre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        for (int i=1; i<=n*n; i++) {
            JButton boutton = new JButton();
            boutton.setBackground(Color.white);
            panelCentre.add(boutton);
            buttons.add(new Bouton(boutton, false, (i-1)/n+1, (i-1)%n+1));
        }
        
        for (Bouton b : buttons) {
            JButton bTemp = b.getButt();
            bTemp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setChanged();
                    Font f = new Font("arial", 0, 90);
                    bTemp.setText("O");
                    bTemp.setForeground(Color.blue);
                    bTemp.setFont(f);
                    bTemp.setBackground(Color.white);
                    b.setBool(true);
                    notifyObservers(new MessageBouton(MessageType.BOUTON, b));
                    clearChanged();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (b.getBool() == false) {
                        Font f = new Font("arial", 0, 90);
                        bTemp.setText("O");
                        bTemp.setForeground(Color.blue);
                        bTemp.setFont(f);
                        bTemp.setBackground(Color.LIGHT_GRAY);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (b.getBool() == false) {
                        bTemp.setText(null);
                        bTemp.setBackground(Color.white);
                    }
                }
            })
                    ;
        }
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,5)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        //panelOuest.setSize(695, 45);
        panelBas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JButton btnRetour = new JButton("Retour");
        
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();                  
                notifyObservers(new Message(MessageType.RETOUR_ACCEUIL));
                clearChanged();}
        });
        
        panelBas.add(btnRetour);
        panelBas.add(new JLabel());
        panelBas.add(new JLabel("Bon match")) ;
        panelBas.add(new JLabel());
        
        
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
