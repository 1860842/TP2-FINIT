public class FileEnclos {
    class NoeudEnclos {
        private Enclos enclos;
        private NoeudEnclos suivant;
        public NoeudEnclos(Enclos enclos) {
            this.enclos = enclos;
        }

        public Enclos getEnclos() {
            return enclos;
        }
    }
    private int nbElements;
    private NoeudEnclos premier;
    private NoeudEnclos dernier;
    public FileEnclos(){
        this.nbElements = 0;
        this.premier = null;
        this.dernier = null;
    }
    public boolean contient(Enclos enclos){
        for(NoeudEnclos courant = premier; courant != null; courant = courant.suivant){
            if(enclos == courant.enclos)
                return true;
        }
        return false;
    }
    public void ajouter(Enclos enclos) {
        if(!contient(enclos)) {

            if (premier == null)
                premier = new NoeudEnclos(enclos);
            else {
                NoeudEnclos dernier = null;
                for (NoeudEnclos courant = premier; courant != null; courant = courant.suivant) {
                    dernier = courant;
                }
                dernier.suivant = new NoeudEnclos(enclos);
            }
            nbElements++;
        }
    }
    public Enclos retirer(){
        if(premier == null)
            return null;

        Enclos aRetourner = premier.enclos;
        premier = premier.suivant;
        //nbElements--;
        return aRetourner;

    }

    public int getNbElements() {
        return nbElements;
    }

    public void setNbElements(int nbElements) {
        this.nbElements = nbElements;
    }

    public NoeudEnclos getPremier() {
        return premier;
    }

    public NoeudEnclos getDernier() {
        return dernier;
    }
}
