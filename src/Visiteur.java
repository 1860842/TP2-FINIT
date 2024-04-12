public class Visiteur {
    private String nom;
    private int age;
    private String[] especes;
    private FileEnclos enclosAVisiter;
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


        if(enclosAVisiter.getNbElements() != 0) {

            Enclos prochainEnclos = enclosAVisiter.retirer();

            System.out.println(toString() + " entre dans l'enclos '" + prochainEnclos.getNom() + "'");
            enclosAVisiter.setNbElements(enclosAVisiter.getNbElements() -1);;
            return prochainEnclos;
        }
        return null;
    }
    public String toString() {
        return "[" + nom + ", " + age + " ans, " + enclosAVisiter.getNbElements() + " enclos restants]";
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public String[] getEspeces() {
        return especes;
    }

    public FileEnclos getEnclosAVisiter() {
        return enclosAVisiter;
    }
}
