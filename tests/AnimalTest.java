import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal spike;

    @BeforeEach
    void setUp() {
        spike = new Animal(Famille.Poisson, " THON ", " Spike ", 100);
    }

    @Test
    void validerAttributs() {
        assertEquals(Famille.Poisson, spike.getFamille());
        assertEquals("thon", spike.getEspece());
        assertEquals("Spike", spike.getNom());
        assertEquals(100.0, spike.getPoids());
    }

    @Test
    void setPoids() {
        spike.setPoids(102.5);
        assertEquals(102.5, spike.getPoids());
    }
}
