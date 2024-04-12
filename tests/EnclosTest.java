import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnclosTest {
    Animal spike = new Animal(Famille.Poisson, "thon", "Spike", 100);
    Animal finn = new Animal(Famille.Poisson, "saumon", "Finn", 100);
    Animal zap = new Animal(Famille.Poisson, "merlan", "Zap", 50);
    Animal neptune = new Animal(Famille.Cetace, "dauphin", "Neptune", 250);
    Animal flipper = new Animal(Famille.Cetace, "marsouin", "Flipper", 250);

    Enclos poissons, cetaces;

    @BeforeEach
    void setUp() {
        cetaces = new Enclos(Famille.Cetace, "Les Cétacés", 1);
        poissons = new Enclos(Famille.Poisson, "Les Poissons", 12);
        assertTrue(poissons.ajouterAnimal(new Animal[] {spike, finn}));
    }

    @Test
    void augmenterDiminuerGardiens() {
        assertEquals(0, poissons.getNbGardiens());

        assertEquals(1, poissons.augmenterGardiens());
        assertEquals(2, poissons.augmenterGardiens());
        assertEquals(2, poissons.getNbGardiens());

        assertEquals(1, poissons.diminuerGardiens());
        assertEquals(0, poissons.diminuerGardiens());
        assertEquals(0, poissons.diminuerGardiens()); // On ne peut avoir moins de 0 gardiens.
        assertEquals(0, poissons.getNbGardiens());
    }

    @Test
    void ajouterAnimal() {
        assertEquals(2, poissons.getNbAnimaux());
        assertTrue(poissons.ajouterAnimal(zap));
        assertEquals(3, poissons.getNbAnimaux());
        assertEquals(zap, poissons.getAnimaux()[2]);
        assertFalse(poissons.ajouterAnimal(flipper)); // Flipper est un cétacé.
        assertEquals(3, poissons.getNbAnimaux());
        assertEquals(zap, poissons.getAnimaux()[2]);

        assertEquals(0, cetaces.getNbAnimaux());
        assertTrue(cetaces.ajouterAnimal(neptune));
        assertEquals(1, cetaces.getNbAnimaux());
        assertEquals(neptune, cetaces.getAnimaux()[0]);
        assertFalse(cetaces.ajouterAnimal(flipper)); // L'enclos est plein.
        assertEquals(1, cetaces.getNbAnimaux());
        assertEquals(neptune, cetaces.getAnimaux()[0]);
    }

    @Test
    void ajouterAnimaux() {
        assertEquals(2, poissons.getNbAnimaux());
        assertFalse(poissons.ajouterAnimal(new Animal[]{zap, flipper}));
        assertEquals(3, poissons.getNbAnimaux());
        assertEquals(zap, poissons.getAnimaux()[2]);

        assertEquals(0, cetaces.getNbAnimaux());
        assertFalse(cetaces.ajouterAnimal(new Animal[]{neptune, flipper}));
        assertEquals(1, cetaces.getNbAnimaux());
        assertEquals(neptune, cetaces.getAnimaux()[0]);
    }

    @Test
    void contientEspece() {
        assertTrue(poissons.contientEspece("thon"));
        assertTrue(poissons.contientEspece("saumon"));
        assertFalse(poissons.contientEspece("dauphin")); // Pas de dauphin dans cet enclos
        assertFalse(cetaces.contientEspece("dauphin")); // L'enclos est vide
    }
}