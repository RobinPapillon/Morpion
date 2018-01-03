/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Morpion;

/**
 *
 * @author Thomas
 */
public class MessageBouton extends Message {
    private Bouton b;
    public MessageBouton(MessageType t, Bouton b) {
        super(t);
        this.b = b;
    }

    public Bouton getB() {
        return b;
    }
    
    
}
