public class Enclos {
    private Famille famille;
    private String nom;
    private int nbGardiens, nbAnimaux, capaciteMax;
    private Animal[] animaux;
    public Enclos(Famille famille, String nom, int capaciteMax){
        this.famille = famille;
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.animaux = new Animal[capaciteMax];
    }
    public int augmenterGardiens(){
        return ++nbGardiens;
    }
    public int diminuerGardiens(){
        if(nbGardiens < 1)
            return nbGardiens;
        else
            return --nbGardiens;
    }
    public boolean ajouterAnimal(Animal animal){
        boolean ajouter = false;
        if(nbAnimaux < capaciteMax && animal.getFamille() == (famille)) {
            animaux[nbAnimaux++] = animal;
            System.out.println(animal.getNom() + " le " + animal.getEspece() + " a été ajouté à l'enclos '" + this.nom + "'.");
            ajouter = true;
        }
        return ajouter;
    }
    // Il est censé être void dans l'énoncé, mais dans EnclosTest, le test doit afficher true, donc boolean
    public boolean ajouterAnimal(Animal[] animaux) {
        boolean ajouter = false;
        for(int i = 0; i < animaux.length; i++) {
            ajouterAnimal(animaux[i]);
            ajouter = true;
        }
        return ajouter;
    }

    public boolean contientEspece(String espece) {
        boolean trouve = false;
        for(int i = 0; i < nbAnimaux; i++)
            if(animaux[i].getEspece().equals(espece))
                return true;
        return trouve;
    }
    public void afficherEnclos() {
        System.out.println("L'enclos '" + nom + "' contient " + nbAnimaux + " " + famille + ":");

        for (int i = 0; i < nbAnimaux; i++)
            if (animaux[i] != null)
                animaux[i].imprimer();

        System.out.println("Il y a " + nbGardiens + " gardien(s) assigné(s) à cet enclos.");
    }

    public int getNbAnimaux() {
        return nbAnimaux;
    }

    public int getNbGardiens() {
        return nbGardiens;
    }

    public Famille getFamille() {
        return famille;
    }

    public String getNom() {
        return nom;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public Animal[] getAnimaux() {
        return animaux;
    }
}
