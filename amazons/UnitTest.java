package amazons;

import org.junit.Test;

import static amazons.Board.SIZE;
import static org.junit.Assert.*;
import ucb.junit.textui;


import static amazons.Piece.*;


/** The suite of all JUnit tests for the amazons package.
 *  @author Michaela Warady
 */
public class UnitTest {

    /** Run the JUnit tests in this package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(UnitTest.class, IteratorTests.class);
    }

    /** Tests basic correctness of put and get on the initialized board. */
    @Test
    public void testBasicPutGet() {
        Board b = new Board();
        b.put(BLACK, Square.sq(3, 5));
        assertEquals(b.get(3, 5), BLACK);
        b.put(WHITE, Square.sq(9, 9));
        assertEquals(b.get(9, 9), WHITE);
        b.put(EMPTY, Square.sq(3, 5));
        assertEquals(b.get(3, 5), EMPTY);
    }

    /** Tests proper identification of legal/illegal queen moves. */
    @Test
    public void testIsQueenMove() {
        assertFalse(Square.sq(1, 5).isQueenMove(Square.sq(1, 5)));
        assertFalse(Square.sq(1, 5).isQueenMove(Square.sq(2, 7)));
        assertFalse(Square.sq(0, 0).isQueenMove(Square.sq(5, 1)));
        assertTrue(Square.sq(1, 1).isQueenMove(Square.sq(9, 9)));
        assertTrue(Square.sq(2, 7).isQueenMove(Square.sq(8, 7)));
        assertTrue(Square.sq(3, 0).isQueenMove(Square.sq(3, 4)));
        assertTrue(Square.sq(0, 2).isQueenMove(Square.sq(7, 9)));
        assertTrue(Square.sq(0, 5).isQueenMove(Square.sq(2, 3)));
        assertTrue(Square.sq(2, 6).isQueenMove(Square.sq(2, 2)));
        assertTrue(Square.sq(5, 4).isQueenMove(Square.sq(1, 0)));
        assertTrue(Square.sq(6, 1).isQueenMove(Square.sq(0, 1)));

    }

    /** Tests toString for initial board state and a smiling board state. :) */
    @Test
    public void testToString() {
        Board b = new Board();
        assertEquals(INIT_BOARD_STATE, b.toString());
        makeSmile(b);
        assertEquals(SMILE, b.toString());
    }

    private void makeSmile(Board b) {
        b.put(EMPTY, Square.sq(0, 3));
        b.put(EMPTY, Square.sq(0, 6));
        b.put(EMPTY, Square.sq(9, 3));
        b.put(EMPTY, Square.sq(9, 6));
        b.put(EMPTY, Square.sq(3, 0));
        b.put(EMPTY, Square.sq(3, 9));
        b.put(EMPTY, Square.sq(6, 0));
        b.put(EMPTY, Square.sq(6, 9));
        for (int col = 1; col < 4; col += 1) {
            for (int row = 6; row < 9; row += 1) {
                b.put(SPEAR, Square.sq(col, row));
            }
        }
        b.put(EMPTY, Square.sq(2, 7));
        for (int col = 6; col < 9; col += 1) {
            for (int row = 6; row < 9; row += 1) {
                b.put(SPEAR, Square.sq(col, row));
            }
        }
        b.put(EMPTY, Square.sq(7, 7));
        for (int lip = 3; lip < 7; lip += 1) {
            b.put(WHITE, Square.sq(lip, 2));
        }
        b.put(WHITE, Square.sq(2, 3));
        b.put(WHITE, Square.sq(7, 3));
    }

    static final String INIT_BOARD_STATE =
            "   - - - B - - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   W - - - - - - - - W\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - W - - W - - -\n";

    static final String SMILE =
            "   - - - - - - - - - -\n"
                    + "   - S S S - - S S S -\n"
                    + "   - S - S - - S - S -\n"
                    + "   - S S S - - S S S -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - W - - - - W - -\n"
                    + "   - - - W W W W - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n";

    @Test
    public void testSqposn() {
        Board b = new Board();
        b.put(BLACK, Square.sq("d6"));
        assertEquals(b.get(3, 5), BLACK);
        b.put(WHITE, Square.sq("j10"));
        assertEquals(b.get(9, 9), WHITE);
        b.put(EMPTY, Square.sq("d6"));
        assertEquals(b.get(3, 5), EMPTY);
        b.put(BLACK, Square.sq("a1"));
        assertEquals(b.get(0, 0), BLACK);
    }

    @Test
    public void testDirection() {
        Board b = new Board();
        Square from = Square.sq(87);
        Square to = Square.sq(97);
        assertEquals(from.direction(to), 0);
        to = Square.sq(98);
        assertEquals(from.direction(to), 1);
        to = Square.sq(88);
        assertEquals(from.direction(to), 2);
        to = Square.sq(69);
        assertEquals(from.direction(to), 3);
        to = Square.sq(7);
        assertEquals(from.direction(to), 4);
        to = Square.sq(43);
        assertEquals(from.direction(to), 5);
        to = Square.sq(82);
        assertEquals(from.direction(to), 6);
        to = Square.sq(96);
        assertEquals(from.direction(to), 7);
    }

    @Test
    public void testQueenmove() {
        Board b = new Board();
        Square from = Square.sq(42);
        Square to = Square.sq(82);
        assertEquals(from.queenMove(0, 4), to);
        to = Square.sq(64);
        assertEquals(from.queenMove(1, 2), to);
        to = Square.sq(48);
        assertEquals(from.queenMove(2, 6), to);
        to = Square.sq(33);
        assertEquals(from.queenMove(3, 1), to);
        to = Square.sq(22);
        assertEquals(from.queenMove(4, 2), to);
        to = Square.sq(31);
        assertEquals(from.queenMove(5, 1), to);
        to = Square.sq(40);
        assertEquals(from.queenMove(6, 2), to);
        to = Square.sq(60);
        assertEquals(from.queenMove(7, 2), to);
        assertNull(from.queenMove(1, 0));
        assertNull(from.queenMove(-1, 2));
        assertNull(from.queenMove(8, 2));
        assertNull(from.queenMove(10, 0));
        assertNull(from.queenMove(6, 3));

    }

    @Test
    public void testIsunblockedmove() {
        Board b = new Board();
        Square from = Square.sq(44);
        Square to = Square.sq(84);
        Square block = from;
        assertTrue(b.isUnblockedMove(from, to, null));
        b.put(BLACK, block);
        to = Square.sq(99);
        assertTrue(b.isUnblockedMove(from, to, block));
        to = Square.sq(48);
        assertTrue(b.isUnblockedMove(from, to, block));
        b.put(EMPTY, block);
        to = Square.sq(8);
        assertTrue(b.isUnblockedMove(from, to, null));
        block = Square.sq(34);
        b.put(BLACK, block);
        to = Square.sq(24);
        assertTrue(b.isUnblockedMove(from, to, block));
        block = Square.sq(22);
        b.put(BLACK, block);
        to = Square.sq(0);
        assertFalse(b.isUnblockedMove(from, to, null));
        Square block1 = Square.sq(42);
        b.put(BLACK, block1);
        to = Square.sq(40);
        assertFalse(b.isUnblockedMove(from, to, block));
        block = from;
        b.put(BLACK, block);
        block1 = Square.sq(71);
        b.put(BLACK, block1);
        to = Square.sq(80);
        assertFalse(b.isUnblockedMove(from, to, from));
        to = Square.sq(88);
        b.put(BLACK, to);
        assertFalse(b.isUnblockedMove(from, to, from));
    }

    @Test
    public void testIslegal() {
        Board b = new Board();
        Square white = Square.sq(10);
        Square black = Square.sq(90);
        Square empty = Square.sq(64);
        Square blockat74 = Square.sq(74);
        b.put(WHITE, white);
        b.put(BLACK, black);
        b.put(BLACK, blockat74);
        assertTrue(b.isLegal(white));
        assertFalse(b.isLegal(black));
        assertFalse(b.isLegal(empty));

        assertTrue(b.isLegal(white, Square.sq(14)));
        assertFalse(b.isLegal(white, Square.sq(80)));
        assertFalse(b.isLegal(white, black));
        assertFalse(b.isLegal(black, Square.sq(72)));
        assertFalse(b.isLegal(empty, Square.sq(14)));

        assertTrue(b.isLegal(white, Square.sq(14), white));
        assertTrue(b.isLegal(white, Square.sq(14), Square.sq(12)));
        assertTrue(b.isLegal(white, Square.sq(14), Square.sq(32)));
        assertFalse(b.isLegal(white, Square.sq(80), Square.sq(30)));
        assertFalse(b.isLegal(black, Square.sq(72), Square.sq(42)));
        assertFalse(b.isLegal(white, Square.sq(14), Square.sq(84)));
        assertFalse(b.isLegal(white, Square.sq(14), Square.sq(14)));

        Move m = Move.mv(white, Square.sq(14), white);
        assertTrue(b.isLegal(m));
        Move m1 = Move.mv(white, Square.sq(14), Square.sq(12));
        assertTrue(b.isLegal(m1));
        Move m2 = Move.mv(white, Square.sq(14), Square.sq(32));
        assertTrue(b.isLegal(m2));
        Move m3 = Move.mv(white, Square.sq(80), Square.sq(30));
        assertFalse(b.isLegal(m3));
        Move m4 = Move.mv(black, Square.sq(72), Square.sq(42));
        assertFalse(b.isLegal(m4));
        Move m5 = Move.mv(white, Square.sq(14), Square.sq(84));
        assertFalse(b.isLegal(m5));
        Move m6 = Move.mv(white, Square.sq(14), Square.sq(14));
        assertFalse(b.isLegal(m6));
    }

    @Test
    public void testMakemove() {
        Board b = new Board();
        b.put(BLACK, Square.sq(6));
        b.put(BLACK, Square.sq(30));
        Board d = new Board();
        d.put(BLACK, Square.sq(6));
        d.put(BLACK, Square.sq(30));

        Square three = Square.sq(3);
        Square six = Square.sq(6);
        Square thirty = Square.sq(30);
        Square thirtynine = Square.sq(39);

        Square four = Square.sq(4);
        Square sixteen = Square.sq(16);
        Square twentythree = Square.sq(23);
        Square thirtythree = Square.sq(33);
        Square fortyeight = Square.sq(48);

        b.makeMove(three, twentythree, three);
        assertEquals(b.toString(), C1);

        b.makeMove(six, four, fortyeight);
        assertEquals(b.toString(), C2);

        b.makeMove(thirtynine, six, sixteen);
        assertEquals(b.toString(), C3);

        b.makeMove(thirty, thirtythree, thirtynine);
        assertEquals(b.toString(), C4);

        Move m1 = Move.mv(three, twentythree, three);
        d.makeMove(m1);
        assertEquals(d.toString(), C1);

        Move m2 = Move.mv(six, four, fortyeight);
        d.makeMove(m2);
        assertEquals(d.toString(), C2);

        Move m3 = Move.mv(thirtynine, six, sixteen);
        d.makeMove(m3);
        assertEquals(d.toString(), C3);

        Move m4 = Move.mv(thirty, thirtythree, thirtynine);
        d.makeMove(m4);
        assertEquals(d.toString(), C4);

    }

    static final String C1 =
            "   - - - B - - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - W\n"
                    + "   - - - W - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - S - - B - - -\n";

    static final String C2 =
            "   - - - B - - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - S -\n"
                    + "   B - - - - - - - - W\n"
                    + "   - - - W - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - S B - - - - -\n";

    static final String C3 =
            "   - - - B - - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - S -\n"
                    + "   B - - - - - - - - -\n"
                    + "   - - - W - - - - - -\n"
                    + "   - - - - - - S - - -\n"
                    + "   - - - S B - W - - -\n";

    static final String C4 =
            "   - - - B - - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - S -\n"
                    + "   - - - B - - - - - S\n"
                    + "   - - - W - - - - - -\n"
                    + "   - - - - - - S - - -\n"
                    + "   - - - S B - W - - -\n";

    @Test
    public void testUndo() {
        Board b = new Board();
        b.put(BLACK, Square.sq(39));
        assertEquals(b.numMoves(), 0);
        b.undo();
        assertEquals(b.toString(), E1);
        b.makeMove(Square.sq(3), Square.sq(25), Square.sq(43));
        assertEquals(b.numMoves(), 1);
        b.undo();
        assertEquals(b.toString(), E1);
        b.makeMove(Square.sq(3), Square.sq(25), Square.sq(43));
        assertEquals(b.numMoves(), 1);
        assertEquals(b.numMoves(), 1);
        b.makeMove(Square.sq(39), Square.sq(49), Square.sq(44));
        assertEquals(b.numMoves(), 2);
        b.makeMove(Square.sq(30), Square.sq(74), Square.sq(83));
        assertEquals(b.numMoves(), 3);
        b.undo();
        assertEquals(b.numMoves(), 2);
        assertEquals(b.toString(), E2);

    }

    static final String E1 =
            "   - - - B - - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   W - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - W - - W - - -\n";

    static final String E2 =
            "   - - - B - - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - S S - - - - B\n"
                    + "   W - - - - - - - - -\n"
                    + "   - - - - - W - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - - W - - -\n";

    @Test
    public void testCopy() {
        Board b = new Board();
        b.put(BLACK, Square.sq(6));

        b.makeMove(Square.sq(3), Square.sq(58), Square.sq(55));
        b.makeMove(Square.sq(6), Square.sq(86), Square.sq(84));
        b.makeMove(Square.sq(39), Square.sq(33), Square.sq(15));

        assertEquals(b.toString(), COPYSTRING);
    }

    static final String COPYSTRING =
            "   - - - B - - B - - -\n"
                    + "   - - - - S - B - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   B - - - - - - - - B\n"
                    + "   - - - - - S - - W -\n"
                    + "   - - - - - - - - - -\n"
                    + "   W - - W - - - - - -\n"
                    + "   - - - - - - - - - -\n"
                    + "   - - - - - S - - - -\n"
                    + "   - - - - - - - - - -\n";

    @Test
    public void testWinner() {
        Board b = new Board();
        for (int x = 0; x < SIZE * SIZE; x += 1) {
            b.put(BLACK, Square.sq(x));
        }
        b.put(WHITE, Square.sq(10));
        assertEquals(b.winner(), BLACK);
    }

}


