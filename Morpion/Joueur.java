package Morpion;

public class Joueur {
    private String pseudo;
    private Symbole symbole;
    private int points;
    
    public Joueur(String p){
        setPseudo(p);
    }
    
    public Joueur(String p, int pts){
        pseudo=p;
        points=pts;
    }
    
    public Joueur(String p, Symbole s){
        setPseudo(p);
        setSymbole(s);
    }

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * @return the symbole
     */
    public Symbole getSymbole() {
        return symbole;
    }

    /**
     * @param symbole the symbole to set
     */
    public void setSymbole(Symbole symbole) {
        this.symbole = symbole;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int x){
        this.points += x ;
    }
}
