import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GardienTest {
    Enclos grosPoissons, petitsPoissons, cetaces;
    Gardien mSmith, bPatel;

    @BeforeEach
    void setUp() {
        grosPoissons = new Enclos(Famille.Poisson, "Les gros poissons", 3);
        petitsPoissons = new Enclos(Famille.Poisson, "Les petits poissons", 12);
        cetaces = new Enclos(Famille.Cetace, "Les Cétacés", 3);
        mSmith = new Gardien("M. Smith", 10, Famille.Poisson);
        bPatel = new Gardien("B. Patel", 5, Famille.Cetace);
    }

    @Test
    void validerAttributs() {
        assertNotEquals(mSmith.getId(), bPatel.getId());
        assertEquals("M. Smith", mSmith.getNom());
        assertEquals(Famille.Poisson, mSmith.getSpecialite());
        assertEquals(10.0, mSmith.getHrsExperience());
        assertNull(mSmith.getEnclos());

        mSmith.assignerA(grosPoissons);
        //assertEquals("[M. Smith (#1000), 10,0 hrs, enclos 'Les gros poissons']", mSmith.toString());
        //TODO: Il est difficile de valider la méthode toString() car le id est généré automatiquement,
        // et le formatage du nombre d'heures dépend de la langue de votre ordinateur (',' en français
        // vs '.' en anglais). Pour l'instant, vous devrez valider son fonctionnement manuellement.
        System.out.println(mSmith.toString());
    }

    @Test
    void ajouterExperience() {
        assertEquals(10.0, mSmith.getHrsExperience());
        mSmith.ajouterExperience(15);
        assertEquals(10.25, mSmith.getHrsExperience());
        mSmith.ajouterExperience(75);
        assertEquals(11.50, mSmith.getHrsExperience());
    }

    @Test
    void assignerA() {
        assertTrue(mSmith.assignerA(grosPoissons));
        assertEquals(grosPoissons, mSmith.getEnclos());
        assertEquals(1, grosPoissons.getNbGardiens());
        assertEquals(0, petitsPoissons.getNbGardiens());

        assertTrue(mSmith.assignerA(petitsPoissons));
        assertEquals(petitsPoissons, mSmith.getEnclos());
        assertEquals(0, grosPoissons.getNbGardiens());
        assertEquals(1, petitsPoissons.getNbGardiens());

        assertTrue(bPatel.assignerA(cetaces));
        assertEquals(cetaces, bPatel.getEnclos());
        assertEquals(1, cetaces.getNbGardiens());

        assertFalse(bPatel.assignerA(grosPoissons)); // Spécialité incompatible et enclos vide
        assertEquals(cetaces, bPatel.getEnclos());
        assertEquals(0, grosPoissons.getNbGardiens());
        assertEquals(1, cetaces.getNbGardiens());

        assertTrue(bPatel.assignerA(petitsPoissons));
        assertEquals(petitsPoissons, bPatel.getEnclos());
        assertEquals(2, petitsPoissons.getNbGardiens());
        assertEquals(0, cetaces.getNbGardiens());
    }

    @Test
    void quitterEnclos() {
        mSmith.assignerA(grosPoissons);
        assertEquals(grosPoissons, mSmith.getEnclos());
        assertEquals(1, grosPoissons.getNbGardiens());

        mSmith.quitterEnclos();
        assertNull(mSmith.getEnclos());
        assertEquals(0, grosPoissons.getNbGardiens());
    }
}
