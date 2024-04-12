public class FileVisiteur {
    class NoeudVisiteur {
        private Visiteur visiteur;
        private NoeudVisiteur suivant;
        public NoeudVisiteur(Visiteur visiteur) {
            this.visiteur = visiteur;
        }
        public Visiteur getVisiteur() {
            return visiteur;
        }

        @Override
        public String toString() {
            return visiteur.toString();
        }
    }
    private int nbElements;
    private NoeudVisiteur premier;
    private NoeudVisiteur dernier;
    public FileVisiteur(){
        this.nbElements = 0;
        this.premier = null;
        this.dernier = null;
    }
    public boolean contient(Visiteur visiteur){
        for(NoeudVisiteur courant = premier; courant != null; courant = courant.suivant)
            if(visiteur == courant.visiteur)
                return true;

        return false;
    }
    public void ajouter(Visiteur visiteur) {
        if (premier == null)
            premier = new NoeudVisiteur(visiteur);
        else {
            NoeudVisiteur dernier = null;
            for (NoeudVisiteur courant = premier; courant != null; courant = courant.suivant)
                dernier = courant;

            dernier.suivant = new NoeudVisiteur(visiteur);
        }
        nbElements++;
    }
    public Visiteur retirer(){
        Visiteur aRetourner = premier.visiteur;
        premier = premier.suivant;
        nbElements--;
        return aRetourner;

    }
    public String toString() {
        String result = "";
        NoeudVisiteur current = premier;
        while (current != null) {
            result += current.visiteur + " -> ";
            current = current.suivant;
        }
        result += "<null>";
        return result;
    }

    public int getNbElements() {
        return nbElements;
    }

    public NoeudVisiteur getPremier() {
        return premier;
    }

    public NoeudVisiteur getDernier() {
        return dernier;
    }
}
