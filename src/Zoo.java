public class Zoo {
    private String nom;
    private Enclos[] enclos;
    private Pile pileGardiens;
    private FileVisiteur fileVisiteurs;
    public Zoo(String nom, Enclos[] enclos){
        this.nom = nom;
        this.enclos = enclos;
        this.pileGardiens = new Pile();
        this.fileVisiteurs = new FileVisiteur();
    }
    public int getNbEnclos(){
        return enclos.length;
    }
    public int getNbTotalAnimaux(){
        int nbTotalAnimaux = 0;
        for(int i = 0; i < enclos.length; i++)
            nbTotalAnimaux += enclos[i].getNbAnimaux();

        return nbTotalAnimaux;
    }
    public Gardien engagerGardien(String nom, double hrsExperience, Famille specialiste, Enclos enclos){
        Gardien gardien = new Gardien(nom, hrsExperience, specialiste);
        if(specialiste.equals(enclos.getFamille())) {
            gardien.assignerA(enclos);
            pileGardiens.push(gardien);
            return gardien;
        } else
            return null;
    }
    public Gardien engagerGardien(String nom, double hrsExperience, Famille specialiste){
        Gardien gardien = new Gardien(nom, hrsExperience, specialiste);
        for(int i = 0; i < enclos.length; i++)
            if(enclos[i].getFamille().equals(specialiste) || enclos[i].getNbGardiens() == 0) {
                gardien.assignerA(enclos[i]);
                pileGardiens.push(gardien);
                return gardien;
            }

        return null;
    }
    public Gardien renvoyerGardien(){
        Gardien derniergardien = pileGardiens.peek();
        Enclos enclosDuDernier = pileGardiens.peek().getEnclos();
        if(derniergardien.getEnclos().getNbGardiens() <= 1) {
            System.out.println("Le gardien " + derniergardien.toString() + " ne peut pas être congédié car son enclos serait vide.");
            return null;
        } else {
            derniergardien.quitterEnclos();
            System.out.println("Le gardien " + derniergardien.getNom() + " (#" + derniergardien.getId() + ") a été retiré de l'enclos " +
                    "'" + enclosDuDernier.getNom() + "' ("+ enclosDuDernier.getNbGardiens() + " gardiens).");
            System.out.println("Le dernier gardien arrivé au zoo " + derniergardien.toString() + " a été congédié.");
            pileGardiens.pop();
            return derniergardien;
        }
    }
    public Pile getGardiensDe(Enclos enclos) {
        Pile gardiensDeEnclos = new Pile();
        for (int j = 0; j < pileGardiens.getNbElements(); j++) {
            Gardien gardien = pileGardiens.get(j);
            if (gardien.getEnclos() != null && gardien.getEnclos().equals(enclos))
                gardiensDeEnclos.push(gardien);
        }
        return gardiensDeEnclos;
    }
    public double accueillirVisiteur(Visiteur visiteur){
        fileVisiteurs.ajouter(visiteur);
        double coutDentre = 0;
        coutDentre += getNbTotalAnimaux();


        for(int i = 0; i < visiteur.getEspeces().length; i++) {
            String espece = visiteur.getEspeces()[i];
            for(int j = 0; j < enclos.length; j++) {
                Enclos enclo = enclos[j];
                if(enclo.contientEspece(espece) && enclo.getNbGardiens() > 0) {
                    visiteur.getEnclosAVisiter().ajouter(enclo);
                    coutDentre += 5.50;
                }
            }
        }

        if(visiteur.getAge() <= 6)
            coutDentre = 0;

        if(visiteur.getAge() >= 7 && visiteur.getAge() <= 13 || visiteur.getAge() >= 65)
            coutDentre *= 0.5;
        System.out.printf("[%s, %d ans] arrive au zoo; Son entrée coûte %.2f$ et donne accès à %d enclos.\n", visiteur.getNom(),
                visiteur.getAge(), coutDentre, visiteur.getEnclosAVisiter().getNbElements());

        visiteur.setEnclosChoisis(visiteur.getEnclosAVisiter());
        return coutDentre;
    }
    public Visiteur prochainVisiteur(){
        if(fileVisiteurs.getNbElements() == 0)
            return null;

        Visiteur retirer = fileVisiteurs.retirer();
        System.out.println(retirer.toString() + " entre dans le zoo.");

        return retirer;
    }
    public void afficherTout() {
        System.out.println("Bienvenue au zoo de " + nom + "!");

        System.out.println("Voici la pile des gardiens:");
        System.out.print("  " + pileGardiens.getNbElements() + " gardiens: ");
        System.out.print(pileGardiens.toString());
        System.out.println();

        System.out.println("Et la file des visiteurs:");
        System.out.print("  " + fileVisiteurs.getNbElements() + " éléments: ");
        System.out.println(fileVisiteurs.toString());

        System.out.println("Le zoo est peuplé par " + getNbTotalAnimaux() + " animaux répartis dans " + getNbEnclos() + " enclos:");
        for (Enclos chaqueEnclos : enclos)
            chaqueEnclos.afficherEnclos();
    }

    public Enclos[] getEnclos() {
        return enclos;
    }

    public Pile getPileGardiens() {
        return pileGardiens;
    }

    public FileVisiteur getFileVisiteurs() {
        return fileVisiteurs;
    }
}
