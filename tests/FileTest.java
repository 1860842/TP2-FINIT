import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
    Visiteur alice = new Visiteur("Alice",13, new String[]{"saumon", "lion", "zèbre", "perroquet"} );
    Visiteur edith = new Visiteur("Judith" , 56, new String[]{"corbeau", "lion"});
    Visiteur robert = new Visiteur("Robert", 65, new String[]{"python"});

    FileVisiteur file; //TODO Modifiez cette ligne pour utiliser votre classe File

    @BeforeEach
    void setUp() {
        file = new FileVisiteur();
        file.ajouter(alice);
        file.ajouter(edith);
    }

    @Test
    void contient() {
        assertTrue(file.contient(alice));
        assertTrue(file.contient(edith));
        assertFalse(file.contient(robert));

        FileVisiteur vide = new FileVisiteur(); //TODO Modifiez cette ligne pour utiliser votre classe File
        assertFalse(vide.contient(alice));
    }

    @Test
    void peek() {
        assertEquals(2, file.getNbElements());
        assertEquals(alice, file.getPremier());
        assertEquals(2, file.getNbElements());
        assertEquals(alice, file.getPremier());
    }

    @Test
    void ajouter() {
        assertEquals(2, file.getNbElements());
        assertEquals(alice, file.getPremier());
        file.ajouter(robert);
        assertEquals(3, file.getNbElements());
        assertEquals(alice, file.getPremier());
    }

    @Test
    void retirer() {
        assertEquals(2, file.getNbElements());
        assertEquals(alice, file.getPremier());
        assertEquals(alice, file.retirer());
        assertEquals(1, file.getNbElements());
        assertEquals(edith, file.getPremier());
        assertEquals(edith, file.retirer());
        assertEquals(0, file.getNbElements());
    }

    @Test
    void testToString() {
        System.err.println("Voici la file des visiteurs:\n\t" + file);
        // Devrait ressembler à:
        //	2 éléments: [Alice, 13 ans, 0 enclos restants] -> [Judith, 56 ans, 0 enclos restants] -> <null>
    }
}
