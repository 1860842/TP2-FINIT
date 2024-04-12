/* INSCRIVEZ VOTRE (OU VOS) PRÉNOM ET NOM CI-DESSOUS
1: Zaccaria Tahar
NE MODIFIEZ PAS LE RESTE DE CE FICHIER! (sauf pour mettre certaines sections en commentaire le temps de tester votre code)
Ce fichier ne sera pas utilisé lors de la correction, donc vos modifications ne seront pas prises en compte.
*/

// Classe principale pour simuler la gestion d'un zoo
public class Main {
    private static Zoo granby;

    public static void main(String[] args) {
        // Création des animaux
        Animal spike = new Animal(Famille.Poisson, "thon", "Spike", 100);
        Animal finn = new Animal(Famille.Poisson, "saumon", "Finn", 100);
        Animal neptune = new Animal(Famille.Cetace, "dauphin", "Neptune", 250);
        Animal flipper = new Animal(Famille.Cetace, "marsouin", "Flipper", 250);
        Animal monty = new Animal(Famille.Reptile, "python", "Monty", 300);
        Animal jaws = new Animal(Famille.Reptile, "alligator", "Jaws", 300);
        Animal polly = new Animal(Famille.Oiseau, "perroquet", "Polly", 0.75);
        Animal apollo = new Animal(Famille.Oiseau, "corbeau", "Apollo", 0.75);
        Animal simba = new Animal(Famille.Mammifere, "lion", "Simba", 200);
        Animal zara = new Animal(Famille.Mammifere, "zèbre", "Zara", 200);

        // Création des enclos
        Enclos poissons = new Enclos(Famille.Poisson, "Les Poissons", 12);
        Enclos cetaces = new Enclos(Famille.Cetace, "Les Cétacés", 3);
        Enclos reptiles = new Enclos(Famille.Reptile, "Les Reptiles", 6);
        Enclos oiseaux = new Enclos(Famille.Oiseau, "Les Oiseaux", 9);
        Enclos mammiferes = new Enclos(Famille.Mammifere, "Les Mammifères", 6);

        // Ajout d'animaux aux enclos
        poissons.ajouterAnimal(new Animal[] {spike, finn});
        cetaces.ajouterAnimal(new Animal[] {neptune, flipper});
        reptiles.ajouterAnimal(new Animal[] {monty, jaws});
        oiseaux.ajouterAnimal(new Animal[] {polly, apollo});
        mammiferes.ajouterAnimal(new Animal[] {simba, zara});

        // Création du zoo avec ses enclos
        granby = new Zoo("Granby", new Enclos[] {poissons, cetaces, reptiles, oiseaux, mammiferes});

        // Création des gardiens
        Gardien mSmith = granby.engagerGardien("M. Smith", 10, Famille.Poisson, poissons);
        Gardien bPatel = granby.engagerGardien("B. Patel", 5, Famille.Cetace, cetaces);
        Gardien fLyding = granby.engagerGardien("F. Lyding", 20, Famille.Reptile, reptiles);
        Gardien pGrandbois = granby.engagerGardien("P. Grandbois", 15, Famille.Oiseau, oiseaux);
        Gardien lSkelgaard = granby.engagerGardien("L. Skelgaard", 10, Famille.Mammifere, mammiferes);

        // Il y a quatre visiteurs à l'ouverture
        Visiteur alice = new Visiteur("Alice",13, new String[]{"saumon", "lion", "zèbre", "perroquet"} );
        Visiteur edith = new Visiteur("Judith" , 56, new String[]{"corbeau", "lion"});
        Visiteur robert = new Visiteur("Robert", 65, new String[]{"python"});
        Visiteur edgar = new Visiteur("Edgar", 6, new String[]{"marsouin", "dauphin"});

        // Gestion du zoo avant le début des visites
        System.out.println("\n======================= LE ZOO AVANT LES VISITES =======================");

        // On ajoute deux nouveaux animaux
        poissons.ajouterAnimal(new Animal(Famille.Poisson, "merlan", "Zap", 50));
        mammiferes.ajouterAnimal(new Animal(Famille.Mammifere, "sanglier", "Obélix", 150));

        // On recrute deux nouveaux gardiens
        granby.engagerGardien("H. Bourassa", 8, Famille.Oiseau);
        granby.engagerGardien("R. Berry", 12, Famille.Mammifere);

        // On ajoute les visiteurs à la file d'attente du zoo
        double revenus = 0;
        revenus += granby.accueillirVisiteur(alice);
        revenus += granby.accueillirVisiteur(robert);
        revenus += granby.accueillirVisiteur(edith);
        revenus += granby.accueillirVisiteur(edgar);
        System.out.printf("Le zoo a vendu des billets pour un total de %.2f$.\n", revenus);

        System.out.println();
        granby.afficherTout();


        // Début des visites
        System.out.println("\n======================= DÉBUT DES VISITES =======================");
        visiterZoo();

        // Fin des visites
        System.out.println("\n======================= FIN DES VISITES =======================");
        granby.renvoyerGardien();
        granby.renvoyerGardien();
        granby.renvoyerGardien();

        // État final du zoo
        System.out.println("\n======================= LE ZOO APRÈS LES VISITES =======================");
        granby.afficherTout();
    }

    private static void visiterZoo() {
        Visiteur visiteur = granby.prochainVisiteur();
        while (visiteur != null) {
            int tempsVisite = 0;
            Enclos enclos = visiteur.prochainEnclos();
            while (enclos != null) {
                tempsVisite += visiterEnclos(visiteur, enclos);
                enclos = visiteur.prochainEnclos();
            }
            System.out.println(visiteur + " quitte le zoo après un total de " + tempsVisite + " minutes.");
            visiteur = granby.prochainVisiteur();
        }
    }

    private static int visiterEnclos(Visiteur visiteur, Enclos enclos) {
        int tempsVisite = (int)(Math.random() * 30);
        Pile gardiens = granby.getGardiensDe(enclos);
        System.out.print(enclos.getNbAnimaux() + " animaux et " + enclos.getNbGardiens() + " gardiens sont présents: ");
        while (!gardiens.estVide()) {
            Gardien g = gardiens.pop();
            g.ajouterExperience(tempsVisite);
            System.out.print(g.getNom() + ", ");
        }
        System.out.println();
        System.out.println(visiteur + " quitte l'enclos après " + tempsVisite + " minutes.");
        return tempsVisite;
    }
}
