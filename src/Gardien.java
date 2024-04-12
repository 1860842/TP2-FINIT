public class Gardien {
    private int id;
    private static int mille = 1000;
    private String nom;
    private Famille specialite;
    private double hrsExperience;
    private Enclos enclosAssigner;
    public Gardien(String nom, double hrsExperience, Famille specialite){
        this.nom = nom;
        this.hrsExperience = hrsExperience;
        this.specialite = specialite;
        this.id = mille++;
    }
    public void ajouterExperience(int minutes) {
        double heure = minutes / 60.0;
        hrsExperience += heure;
    }
    public boolean assignerA(Enclos enclos){
        if(this.specialite.equals(enclos.getFamille()) || enclos.getNbGardiens() > 0) {
            quitterEnclos();
            this.enclosAssigner = enclos;
            enclos.augmenterGardiens();
            System.out.println("Le gardien " + this.nom + " (#" + id + ") a été assigné à l'enclos " + "'" + this.enclosAssigner.getNom() + "' ("+ enclos.getNbGardiens() + " gardiens).");
            return true;
        } else
            return false;
    }
    public void quitterEnclos(){
        if(this.enclosAssigner != null) {
            enclosAssigner.diminuerGardiens();
            this.enclosAssigner = null;
        }
    }
    public String toString() {
        String str = "[" +
                nom + " (#" +
                id + "), " +
                String.format("%.1f hrs", hrsExperience);
        if (enclosAssigner != null)
            str += ", enclos '" + enclosAssigner.getNom() + "']";
        else
            str += ", enclos 'null']";

        return str;
    }
    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public Famille getSpecialite() {
        return specialite;
    }

    public double getHrsExperience() {
        return hrsExperience;
    }

    public Enclos getEnclos() {
        return enclosAssigner;
    }
}
