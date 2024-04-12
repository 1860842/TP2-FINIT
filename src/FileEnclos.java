public class FileEnclos {
    class NoeudEnclos {
        Enclos enclos;
        NoeudEnclos suivant;
        public NoeudEnclos(Enclos enclos) {
            this.enclos = enclos;
        }

        public Enclos getEnclos() {
            return enclos;
        }
    }
    int nbElements;
    NoeudEnclos premier;
    NoeudEnclos dernier;
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
}
