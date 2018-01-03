package Morpion;

import java.awt.Color;

public enum Symbole {
    CROIX("X", new Color(0,0,255)),
    ROND("O", new Color(255,0,0));
    
    private final String type;
    private final Color c;
    
    Symbole(String type, Color c){
        this.type = type;
        this.c = c;
    }
    
    @Override
    public String toString(){
        return this.type;
    }
    
    public Color getColor(){
        return this.c;
    }
    
    public static Symbole getFromName(String name) {
            if (CROIX.name().equals(name)) return CROIX ;
            if (ROND.name().equals(name)) return ROND ;
            return null ;
        }
}
