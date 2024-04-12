public class Visiteur {
    String nom;
    int age;
    String[] especes;
    FileEnclos enclosAVisiter;
    public Visiteur(String nom, int age, String[] especes){
        this.nom = nom;
        this.age = age;
        this.especes = especes;
        this.enclosAVisiter = new FileEnclos();

    }
    public void setEnclosChoisis(FileEnclos enclosAVisiter){
        this.enclosAVisiter = enclosAVisiter;

    }
    public Enclos prochainEnclos(){


        if(enclosAVisiter.nbElements != 0) {

            Enclos prochainEnclos = enclosAVisiter.retirer();

            System.out.println(toString() + " entre dans l'enclos '" + prochainEnclos.getNom() + "'");
            enclosAVisiter.nbElements--;
            return prochainEnclos;
        }
        return null;
    }
    public String toString() {
        return "[" + nom + ", " + age + " ans, " + enclosAVisiter.nbElements + " enclos restants]";
    }

}
