import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
    // Création des animaux
    Animal spike = new Animal(Famille.Poisson, "thon", "Spike", 100);
    Animal finn = new Animal(Famille.Poisson, "saumon", "Finn", 100);
    Animal zap = new Animal(Famille.Poisson, "merlan", "Zap", 50);
    Animal neptune = new Animal(Famille.Cetace, "dauphin", "Neptune", 250);
    Animal flipper = new Animal(Famille.Cetace, "marsouin", "Flipper", 250);
    Animal simba = new Animal(Famille.Mammifere, "lion", "Simba", 200);
    Animal zara = new Animal(Famille.Mammifere, "zèbre", "Zara", 200);

    Enclos grosPoissons, petitsPoissons, cetaces, mammiferes;
    Gardien mSmith, hBourassa, fLyding, lSkelgaard;
    Visiteur alice, judith, robert, edgar;

    Zoo zoo;

    @BeforeEach
    void setUp() {
        // Création des enclos
        grosPoissons = new Enclos(Famille.Poisson, "Les gros poissons", 3);
        petitsPoissons = new Enclos(Famille.Poisson, "Les petits poissons", 12);
        cetaces = new Enclos(Famille.Cetace, "Les Cétacés", 3);
        mammiferes = new Enclos(Famille.Mammifere, "Les Mammifères", 6);

        // Ajout des animaux aux enclos
        grosPoissons.ajouterAnimal(new Animal[] {spike, finn});
        petitsPoissons.ajouterAnimal(zap);
        cetaces.ajouterAnimal(new Animal[] {neptune, flipper});
        mammiferes.ajouterAnimal(new Animal[] {simba, zara});

        // Création du zoo avec ses enclos
        zoo = new Zoo("Granby", new Enclos[] {grosPoissons, petitsPoissons, cetaces, mammiferes});

        // Création des gardiens
        mSmith = zoo.engagerGardien("M. Smith", 10, Famille.Poisson, grosPoissons);
        fLyding = zoo.engagerGardien("F. Lyding", 20, Famille.Poisson, petitsPoissons);
        lSkelgaard = zoo.engagerGardien("L. Skelgaard", 10, Famille.Mammifere, mammiferes);
        hBourassa = zoo.engagerGardien("H. Bourassa", 8, Famille.Poisson, grosPoissons);

        // Création des visiteurs
        alice = new Visiteur("Alice",13, new String[]{"saumon", "lion", "zèbre", "thon"});
        judith = new Visiteur("Judith" , 65, new String[]{"saumon", "dauphin", "thon"});
        robert = new Visiteur("Robert", 47, new String[]{"dauphin"});
        edgar = new Visiteur("Edgar", 6, new String[]{"saumon", "pieuvre", "merlan"});
    }

    @Test
    void getNbEnclos() {
        assertEquals(4, zoo.getNbEnclos());
        assertEquals(grosPoissons, zoo.getEnclos()[0]);
        assertEquals(petitsPoissons, zoo.getEnclos()[1]);
        assertEquals(cetaces, zoo.getEnclos()[2]);
        assertEquals(mammiferes, zoo.getEnclos()[3]);
    }

    @Test
    void getNbTotalAnimaux() {
        assertEquals(7, zoo.getNbTotalAnimaux());
        mammiferes.ajouterAnimal(new Animal(Famille.Mammifere, "sanglier", "Obélix", 150));
        assertEquals(8, zoo.getNbTotalAnimaux());
    }

    @Test
    void engagerGardien() {
        // Vérification initiale
        Pile gardiens = zoo.getPileGardiens();
        assertEquals(4, gardiens.getNbElements());
        assertEquals(hBourassa, gardiens.peek());
        assertEquals(grosPoissons, hBourassa.getEnclos());
        assertEquals(2, grosPoissons.getNbGardiens());

        // Engager un gardien dans un enclos choisi automatiquement
        Gardien rBerry = zoo.engagerGardien("R. Berry", 12, Famille.Mammifere);
        assertEquals(5, gardiens.getNbElements());
        assertEquals(rBerry, gardiens.peek());
        assertEquals(mammiferes, rBerry.getEnclos());
        assertEquals(2, mammiferes.getNbGardiens());

        // Engager un gardien dans un enclos choisi automatiquement (plusieurs options)
        Gardien pGrandbois = zoo.engagerGardien("P. Grandbois", 15, Famille.Poisson);
        assertEquals(6, gardiens.getNbElements());
        assertEquals(pGrandbois, gardiens.peek());
        assertEquals(petitsPoissons, pGrandbois.getEnclos());
        assertEquals(2, petitsPoissons.getNbGardiens());

        // Engager un gardien dans un enclos choisi manuellement (enclos différent mais non-vide)
        Gardien bPatel = zoo.engagerGardien("B. Patel", 5, Famille.Cetace, mammiferes);
        assertEquals(7, gardiens.getNbElements());
        assertEquals(bPatel, gardiens.peek());
        assertEquals(mammiferes, bPatel.getEnclos());
        assertEquals(3, mammiferes.getNbGardiens());

        // Enclos choisi incompatible
        assertNull(zoo.engagerGardien("P. Oak", 5, Famille.Mammifere, cetaces));
        assertEquals(7, gardiens.getNbElements());

        // Aucun enclos compatible
        assertNull(zoo.engagerGardien("P. Elm", 5, Famille.Reptile));
        assertEquals(7, gardiens.getNbElements());

        // Vérification finale
        assertEquals(bPatel, gardiens.pop());
        assertEquals(pGrandbois, gardiens.pop());
        assertEquals(rBerry, gardiens.pop());
        assertEquals(hBourassa, gardiens.pop());
        assertEquals(lSkelgaard, gardiens.pop());
        assertEquals(fLyding, gardiens.pop());
        assertEquals(mSmith, gardiens.pop());
        assertEquals(0, gardiens.getNbElements());
    }

    @Test
    void renvoyerGardien() {
        Pile gardiens = zoo.getPileGardiens();
        Gardien rBerry = zoo.engagerGardien("R. Berry", 12, Famille.Mammifere, mammiferes);
        Gardien pGrandbois = zoo.engagerGardien("P. Grandbois", 15, Famille.Poisson, petitsPoissons);

        assertEquals(pGrandbois, zoo.renvoyerGardien());
        assertEquals(5, gardiens.getNbElements());
        assertEquals(1, petitsPoissons.getNbGardiens());
        assertNull(pGrandbois.getEnclos());

        assertEquals(rBerry, zoo.renvoyerGardien());
        assertEquals(4, gardiens.getNbElements());
        assertEquals(1, mammiferes.getNbGardiens());
        assertNull(rBerry.getEnclos());

        assertEquals(hBourassa, zoo.renvoyerGardien());
        assertEquals(3, gardiens.getNbElements());
        assertEquals(1, grosPoissons.getNbGardiens());
        assertNull(hBourassa.getEnclos());

        assertNull(zoo.renvoyerGardien()); // On ne peut pas renvoyer ce gardien (enclos vide)
        assertEquals(3, gardiens.getNbElements());
        assertEquals(1, mammiferes.getNbGardiens());
        assertEquals(mammiferes, lSkelgaard.getEnclos());
    }

    @Test
    void getGardiensDe() {
        Pile gardiens;

        gardiens = zoo.getGardiensDe(grosPoissons);
        assertEquals(2, gardiens.getNbElements());
        assertEquals(hBourassa, gardiens.pop());
        assertEquals(mSmith, gardiens.pop());

        gardiens = zoo.getGardiensDe(petitsPoissons);
        assertEquals(1, gardiens.getNbElements());
        assertEquals(fLyding, gardiens.pop());

        gardiens = zoo.getGardiensDe(cetaces);
        assertEquals(0, gardiens.getNbElements());
    }

    @Test
    void accueillirVisiteur() {
        assertEquals(9.00, zoo.accueillirVisiteur(alice)); // 4 espèces, 2 enclos
        assertEquals(1, zoo.getFileVisiteurs().getNbElements());
        assertEquals(grosPoissons, alice.prochainEnclos());
        assertEquals(mammiferes, alice.prochainEnclos());
        assertNull(alice.prochainEnclos());

        assertEquals(6.25, zoo.accueillirVisiteur(judith)); // 3 espèces, 1 enclos
        assertEquals(2, zoo.getFileVisiteurs().getNbElements());
        assertEquals(grosPoissons, judith.prochainEnclos());
        assertNull(judith.prochainEnclos());

        assertEquals(7.00, zoo.accueillirVisiteur(robert)); // 1 espèce, aucun enclos
        assertEquals(3, zoo.getFileVisiteurs().getNbElements());
        assertNull(robert.prochainEnclos());

        assertEquals(0.00, zoo.accueillirVisiteur(edgar)); // 3 espèces, 2 enclos
        assertEquals(4, zoo.getFileVisiteurs().getNbElements());
        assertEquals(grosPoissons, edgar.prochainEnclos());
        assertEquals(petitsPoissons, edgar.prochainEnclos());
        assertNull(edgar.prochainEnclos());
    }

    @Test
    void prochainVisiteur() {
        assertEquals(0, zoo.getFileVisiteurs().getNbElements());
        assertNull(zoo.prochainVisiteur()); // Pas de visiteurs en attente initialement, donc null

        zoo.accueillirVisiteur(alice);
        zoo.accueillirVisiteur(judith);
        zoo.accueillirVisiteur(robert);
        zoo.accueillirVisiteur(edgar);

        assertEquals(4, zoo.getFileVisiteurs().getNbElements());
        assertEquals(alice, zoo.prochainVisiteur());

        assertEquals(3, zoo.getFileVisiteurs().getNbElements());
        assertEquals(judith, zoo.prochainVisiteur());

        assertEquals(2, zoo.getFileVisiteurs().getNbElements());
        assertEquals(robert, zoo.prochainVisiteur());

        assertEquals(1, zoo.getFileVisiteurs().getNbElements());
        assertEquals(edgar, zoo.prochainVisiteur());

        assertEquals(0, zoo.getFileVisiteurs().getNbElements());
        assertNull(zoo.prochainVisiteur()); // Plus de visiteurs, donc null
    }
}
