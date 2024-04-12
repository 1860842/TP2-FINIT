import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PileTest {
    Gardien mSmith = new Gardien("M. Smith", 10, Famille.Poisson);
    Gardien bPatel = new Gardien("B. Patel", 5, Famille.Cetace);
    Gardien fLyding = new Gardien("F. Lyding", 20, Famille.Reptile);

    Pile pile;

    @BeforeEach
    void setUp() {
        pile = new Pile();
        pile.push(mSmith);
        pile.push(bPatel);
    }

    @Test
    void peek() {
        assertEquals(2, pile.getNbElements());
        assertEquals(bPatel, pile.peek());
        assertEquals(2, pile.getNbElements());
        assertEquals(bPatel, pile.peek());
    }

    @Test
    void push() {
        assertEquals(2, pile.getNbElements());
        assertEquals(bPatel, pile.peek());
        pile.push(fLyding);
        assertEquals(3, pile.getNbElements());
        assertEquals(fLyding, pile.peek());
    }

    @Test
    void pop() {
        assertEquals(2, pile.getNbElements());
        assertEquals(bPatel, pile.peek());
        assertEquals(bPatel, pile.pop());
        assertEquals(1, pile.getNbElements());
        assertEquals(mSmith, pile.peek());
        assertEquals(mSmith, pile.pop());
        assertEquals(0, pile.getNbElements());
    }

    @Test
    void testToString() {
        System.err.println("Voici la pile des gardiens:\n\t" + pile);
        // Devrait ressembler à:
        //	2 gardiens: [M. Smith (#1036), 10,0 hrs, enclos 'null'] | [B. Patel (#1037), 5,0 hrs, enclos 'null'] | [+ 3 cellules vides]
    }
}
