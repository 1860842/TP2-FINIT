public class Animal {
    private Famille famille;
    private String espece, nom;
    private double poids;
    public Animal(Famille famille, String espece, String nom, double poids){
        this.famille = famille;
        this.espece = espece.toLowerCase().trim();
        this.nom = nom.trim();
        this.poids = poids;

    }
    public double setPoids(double poids){
        this.poids = poids;
        return poids;
    }
    public void imprimer(){
        System.out.printf("  Le %s nommé %s pèse %.1f kg\n", espece, nom, poids);
    }

    public Famille getFamille() {
        return famille;
    }

    public String getEspece() {
        return espece;
    }

    public String getNom() {
        return nom;
    }

    public double getPoids() {
        return poids;
    }
}
