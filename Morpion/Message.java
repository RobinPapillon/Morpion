/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Morpion;

/**
 *
 * @author damien
 */
public class Message {
    private MessageType type;
    
    public Message(MessageType t){
            type = t;
    }

    /**
     * @return the act
     */
    public MessageType getType() {
        return type;
    }

    /**
     * @param act the act to set
     */
    public void setType(MessageType t) {
        type = t;
    }
}
