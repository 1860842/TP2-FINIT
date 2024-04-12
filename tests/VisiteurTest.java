import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisiteurTest {
    Enclos poissons = new Enclos(Famille.Poisson, "Les Poissons", 12);
    Enclos cetaces = new Enclos(Famille.Cetace, "Les Cétacés", 3);

    Visiteur alice;

    @BeforeEach
    void setUp() {
        alice = new Visiteur("Alice",13, new String[]{"saumon", "lion", "zèbre", "perroquet"});
    }

    @Test
    void prochainEnclos() {
        assertNull(alice.prochainEnclos()); // Pas d'enclos à visiter initialement, donc null

        FileEnclos enclos = new FileEnclos(); //TODO Modifiez cette ligne pour utiliser votre classe File
        enclos.ajouter(poissons);
        enclos.ajouter(cetaces);
        alice.setEnclosChoisis(enclos);

        assertEquals(poissons, alice.prochainEnclos());
        assertEquals(cetaces, alice.prochainEnclos());
        assertNull(alice.prochainEnclos()); // File vide, donc null
    }

    @Test
    void testToString() {
        assertEquals("[Alice, 13 ans, 0 enclos restants]", alice.toString());

        FileEnclos enclos = new FileEnclos(); //TODO Modifiez cette ligne pour utiliser votre classe File
        enclos.ajouter(poissons);
        enclos.ajouter(cetaces);
        alice.setEnclosChoisis(enclos);

        assertEquals("[Alice, 13 ans, 2 enclos restants]", alice.toString());
    }
}
