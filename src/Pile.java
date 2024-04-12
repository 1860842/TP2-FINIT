public class Pile {
    private final int TAILLE_INITIALE = 5;
    private final int RATIO_AGRANDISSEMENT = 2;
    private int nbElements;
    private Gardien[] gardiens;
    public Pile(){
        this.nbElements = 0;
        this.gardiens = new Gardien[TAILLE_INITIALE];
    }
    public Gardien get(int index){
        for(int i = 0; i < gardiens.length; i++){
            if(i == index)
                return gardiens[i];
        }
        return null;
    }
    public void push(Gardien gardien){
        if(nbElements == gardiens.length)
            resize();
        gardiens[nbElements++] = gardien;

    }
    private void resize() {
        int nouvelleTaille = gardiens.length * RATIO_AGRANDISSEMENT;
        Gardien[] nouvellePile = new Gardien[nouvelleTaille];
        for (int i = 0; i < nbElements; i++) {
            nouvellePile[i] = gardiens[i];
        }
        gardiens = nouvellePile;
    }
    public Gardien pop(){

        Gardien dernierGardien = gardiens[nbElements - 1];
        gardiens[nbElements - 1] = null;
        nbElements--;
        return dernierGardien;
    }
    public Gardien peek(){
        Gardien dernierGardien = gardiens[nbElements - 1];
        return dernierGardien;
    }
    public String toString() {
        String resultat = "";
        for (int i = 0; i < nbElements; i++) {
            resultat += gardiens[i];
            if (i < nbElements - 1) {
                resultat += " | ";
            }
        }
        int cellulesVides = gardiens.length - nbElements;
        if(cellulesVides > 0)
            resultat += " | [+ " + cellulesVides + " cellules vides]";
        resultat += "";
        return resultat;
    }
    public boolean estVide(){
        return nbElements == 0;
    }

    public int getNbElements() {
        return nbElements;
    }
}
