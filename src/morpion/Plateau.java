package morpion;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private int cote;
    private int nbCases;
    private int nbCasesCochees;
    private ArrayList<Case> cases;
    private int[][] matriceRond;
    private int[][] matriceCroix;
    private ModeDeJeu modeJeu;
           
    
    public Plateau(){
        this.nbCasesCochees = 0;
        
    }
    
    public void setPlateau(int n){
        setCote(n);
        setNbCase(n^2);
        this.matriceRond = new int[n][n];
        this.matriceCroix = new int [n][n];
        
        for (int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                cases.add(new Case(i+1, j+1, null));
                matriceCroix[i][j] = 0;
                matriceRond[i][j] = 0;

            }
        }
        
        
    }
    
    public void addCaseCoche(){
        nbCasesCochees += 1;
    }
    
    public boolean verifCroix(int x, int y){
        
        int total = 0;
        // verification ligne       
        for (int j = 0; j< getCote(); j++){
            total += matriceCroix[x][j];
        }
        // verification colonne
        if (total < getCote()){
            total = 0;
            for (int i = 0; i< getCote(); i++){
                total += matriceCroix[i][y];
            }
        }
        // verification diagonale principale
        if (total < getCote() && x==y){
            total = 0;
            for (int i = 0; i< getCote(); i++){
                total += matriceCroix[i][i];
            }
        }
        // verification anti-diagonale
        if (total < getCote() && x+y+1 == getCote()){
            total = 0;
            for (int i =0; i< getCote(); i++){
                total+= matriceCroix[getCote()-1-i][i];
            }
        }
        return(total == getCote());           
    }
    
    public boolean verifRond(int x, int y){
        
        int total = 0;
        // verification ligne       
        for (int j = 0; j< getCote(); j++){
            total += matriceRond[x][j];
        }
        // verification colonne
        if (total < getCote()){
            total = 0;
            for (int i = 0; i< getCote(); i++){
                total += matriceRond[i][y];
            }
        }
        // verification diagonale principale
        if (total < getCote() && x==y){
            total = 0;
            for (int i = 0; i< getCote(); i++){
                total += matriceRond[i][i];
            }
        }
        // verification anti-diagonale
        if (total < getCote() && x+y+1 == getCote()){
            total = 0;
            for (int i =0; i< getCote(); i++){
                total+= matriceRond[getCote()-1-i][i];
            }
        }
        return(total == getCote());           
    }
    

    /**
     * @return the cote
     */
    public int getCote() {
        return cote;
    }

    /**
     * @param cote the cote to set
     */
    public void setCote(int cote) {
        this.cote = cote;
    }

    /**
     * @return the nbCase
     */
    public int getNbCase() {
        return nbCases;
    }

    /**
     * @param nbCase the nbCase to set
     */
    public void setNbCase(int nbCase) {
        this.nbCases = nbCase;
    }

    /**
     * @return the cases
     */
    public ArrayList<Case> getCases() {
        return cases;
    }

    /**
     * @param cases the cases to set
     */
    public void setCases(ArrayList<Case> cases) {
        this.cases = cases;
    }

    /**
     * @return the nbCasesCochees
     */
    public int getNbCasesCochees() {
        return nbCasesCochees;
    }

    /**
     * @param nbCasesCochees the nbCasesCochees to set
     */
    public void setNbCasesCochees(int nbCasesCochees) {
        this.nbCasesCochees = nbCasesCochees;
    }

    /**
     * @return the matriceRond
     */
    public int[][] getMatriceRond() {
        return matriceRond;
    }

    /**
     * @param matriceRond the matriceRond to set
     */
    public void ajoutMatriceRond(int x,int y) {
        this.matriceRond[x][y] = 1;
    }

    /**
     * @return the matriceCroix
     */
    public int[][] getMatriceCroix() {
        return matriceCroix;
    }

    /**
     * @param matriceCroix the matriceCroix to set
     */
    public void ajoutMatriceCroix(int x,int y) {
        this.matriceCroix[x][y] = 1;
    }

    /**
     * @return the modeJeu
     */
    public ModeDeJeu getModeJeu() {
        return modeJeu;
    }

    /**
     * @param modeJeu the modeJeu to set
     */
    public void setModeJeu(ModeDeJeu modeJeu) {
        this.modeJeu = modeJeu;
    }
}
