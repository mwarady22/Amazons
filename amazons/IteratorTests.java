package amazons;
import org.junit.Test;
import static org.junit.Assert.*;
import ucb.junit.textui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** Junit tests for our Board iterators.
 *  @author Michaela Warady
 */
public class IteratorTests {

    /** Run the JUnit tests in this package. */
    public static void main(String[] ignored) {
        textui.runClasses(IteratorTests.class);
    }

    /** Tests reachableFromIterator to make sure it returns all reachable
     *  Squares. This method may need to be changed based on
     *   your implementation. */
    @Test
    public void testReachableFrom() {
        Board b = new Board();
        buildBoard(b, REACHABLEFROMTESTBOARD);
        int numSquares = 0;
        Set<Square> squares = new HashSet<>();
        Iterator<Square> reachableFrom = b.reachableFrom(Square.sq(5, 4), null);
        while (reachableFrom.hasNext()) {
            Square s = reachableFrom.next();
            assertTrue(REACHABLEFROMTESTSQUARES.contains(s));
            numSquares += 1;
            squares.add(s);
        }
        assertEquals(REACHABLEFROMTESTSQUARES.size(), numSquares);
        assertEquals(REACHABLEFROMTESTSQUARES.size(), squares.size());

        Board b1 = new Board();
        buildBoard(b1, REACHABLEFROMTESTBOARD1);
        int numSquares1 = 0;
        Set<Square> squares1 = new HashSet<>();
        Iterator<Square> reachableFrom1 =
                b1.reachableFrom(Square.sq(9, 9), null);
        while (reachableFrom1.hasNext()) {
            Square s1 = reachableFrom1.next();
            assertTrue(REACHABLEFROMTESTSQUARES1.contains(s1));
            numSquares1 += 1;
            squares1.add(s1);
        }
        assertEquals(REACHABLEFROMTESTSQUARES1.size(), numSquares1);
        assertEquals(REACHABLEFROMTESTSQUARES1.size(), squares1.size());

        Board b2 = new Board();
        buildBoard(b2, REACHABLEFROMTESTBOARD2);
        int numSquares2 = 0;
        Set<Square> squares2 = new HashSet<>();
        Iterator<Square> reachableFrom2 =
                b2.reachableFrom(Square.sq(1, 6), null);
        while (reachableFrom2.hasNext()) {
            Square s2 = reachableFrom2.next();
            assertTrue(REACHABLEFROMTESTSQUARES2.contains(s2));
            numSquares2 += 1;
            squares2.add(s2);
        }
        assertEquals(REACHABLEFROMTESTSQUARES2.size(), numSquares2);
        assertEquals(REACHABLEFROMTESTSQUARES2.size(), squares2.size());

        Board b3 = new Board();
        buildBoard(b3, REACHABLEFROMTESTBOARD3);
        int numSquares3 = 0;
        Set<Square> squares3 = new HashSet<>();
        Iterator<Square> reachableFrom3 =
                b3.reachableFrom(Square.sq(0, 0), Square.sq(3, 0));
        while (reachableFrom3.hasNext()) {
            Square s3 = reachableFrom3.next();
            assertTrue(REACHABLEFROMTESTSQUARES3.contains(s3));
            numSquares3 += 1;
            squares3.add(s3);
        }
        assertEquals(REACHABLEFROMTESTSQUARES3.size(), numSquares3);
        assertEquals(REACHABLEFROMTESTSQUARES3.size(), squares3.size());
    }

    /** Tests legalMovesIterator to make sure it returns all legal Moves.
     *  This method needs to be finished and may need to be changed
     *  based on your implementation. */
    @Test
    public void testLegalMoves() {
        Board b = new Board();
        buildBoard(b, LEGALMOVEITERATORTESTBOARD);
        int numMoves = 0;
        Set<Move> moves = new HashSet<>();
        Iterator<Move> legalMoves = b.legalMoves(Piece.WHITE);
        while (legalMoves.hasNext()) {
            Move m = legalMoves.next();
            assertTrue(LEGALMOVEITERATORTESTMOVES.contains(m));
            numMoves += 1;
            moves.add(m);
        }
        assertEquals(LEGALMOVEITERATORTESTMOVES.size(), numMoves);
        assertEquals(LEGALMOVEITERATORTESTMOVES.size(), moves.size());

        Board b1 = new Board();
        buildBoard(b1, LEGALMOVEITERATORTESTBOARD1);
        int numMoves1 = 0;
        Set<Move> moves1 = new HashSet<>();
        Iterator<Move> legalMoves1 = b1.legalMoves(Piece.WHITE);
        while (legalMoves1.hasNext()) {
            Move m1 = legalMoves1.next();
            assertTrue(LEGALMOVEITERATORTESTMOVES1.contains(m1));
            numMoves1 += 1;
            moves1.add(m1);
        }
        assertEquals(LEGALMOVEITERATORTESTMOVES1.size(), numMoves1);
        assertEquals(LEGALMOVEITERATORTESTMOVES1.size(), moves1.size());
    }


    private void buildBoard(Board b, Piece[][] target) {
        for (int col = 0; col < Board.SIZE; col++) {
            for (int row = Board.SIZE - 1; row >= 0; row--) {
                Piece piece = target[Board.SIZE - row - 1][col];
                b.put(piece, Square.sq(col, row));
            }
        }
        System.out.println(b);
    }

    static final Piece E = Piece.EMPTY;

    static final Piece W = Piece.WHITE;

    static final Piece B = Piece.BLACK;

    static final Piece S = Piece.SPEAR;

    static final Piece[][] REACHABLEFROMTESTBOARD =
        {
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, W, W },
        { E, E, E, E, E, E, E, S, E, S },
        { E, E, E, S, S, S, S, E, E, S },
        { E, E, E, S, E, E, E, E, B, E },
        { E, E, E, S, E, W, E, E, B, E },
        { E, E, E, S, S, S, B, W, B, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        };

    static final Set<Square> REACHABLEFROMTESTSQUARES =
            new HashSet<>(Arrays.asList(
                    Square.sq(5, 5),
                    Square.sq(4, 5),
                    Square.sq(4, 4),
                    Square.sq(6, 4),
                    Square.sq(7, 4),
                    Square.sq(6, 5),
                    Square.sq(7, 6),
                    Square.sq(8, 7)));

    static final Piece[][] REACHABLEFROMTESTBOARD1 =
        {
        { E, E, W, E, E, E, E, E, E, B },
        { E, E, E, E, E, E, E, E, E, W },
        { E, E, E, E, E, E, E, W, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        };

    static final Set<Square> REACHABLEFROMTESTSQUARES1 =
            new HashSet<>(Arrays.asList(
                    Square.sq(8, 8),
                    Square.sq(8, 9),
                    Square.sq(7, 9),
                    Square.sq(6, 9),
                    Square.sq(5, 9),
                    Square.sq(4, 9),
                    Square.sq(3, 9)));

    static final Piece[][] REACHABLEFROMTESTBOARD2 =
        {
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { B, B, B, E, E, E, E, E, E, E },
        { B, B, B, E, E, E, E, E, E, E },
        { B, B, B, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        };

    static final Set<Square> REACHABLEFROMTESTSQUARES2 =
            new HashSet<>(Arrays.asList());


    static final Piece[][] REACHABLEFROMTESTBOARD3 =
        {
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, S, E, E, E, E, E, E },
        { W, E, E, E, E, E, E, E, E, E },
        { E, E, E, E, E, E, E, E, E, E },
        { E, E, E, B, E, B, E, E, E, E },
        };

    static final Set<Square> REACHABLEFROMTESTSQUARES3 =
            new HashSet<>(Arrays.asList(
                    Square.sq(0, 1),
                    Square.sq(1, 1),
                    Square.sq(2, 2),
                    Square.sq(1, 0),
                    Square.sq(2, 0),
                    Square.sq(3, 0),
                    Square.sq(4, 0)));


    static final Piece[][] LEGALMOVEITERATORTESTBOARD =
        {
        { B, B, B, B, B, B, B, B, B, W },
        { B, W, B, B, B, B, B, B, B, E },
        { B, B, B, B, B, B, B, B, B, E },
        { B, B, B, B, B, B, B, B, B, E },
        { B, B, B, B, B, B, B, B, B, E },
        { B, B, B, B, B, B, B, B, B, E },
        { E, E, E, B, B, B, B, B, B, E },
        { E, B, B, B, B, B, B, E, B, E },
        { E, E, B, B, B, B, S, W, B, E },
        { W, E, E, B, B, B, B, B, B, E },
        };

    static final Set<Move> LEGALMOVEITERATORTESTMOVES =
            new HashSet<>(Arrays.asList(
                    Move.mv(Square.sq(0, 0), Square.sq(0, 1), Square.sq(0, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 1), Square.sq(0, 2)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 1), Square.sq(0, 3)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 1), Square.sq(1, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 1), Square.sq(1, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 2), Square.sq(0, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 2), Square.sq(0, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 2), Square.sq(0, 3)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 2), Square.sq(1, 3)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 2), Square.sq(1, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 2), Square.sq(2, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 3), Square.sq(0, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 3), Square.sq(0, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 3), Square.sq(0, 2)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 3), Square.sq(1, 3)),
                    Move.mv(Square.sq(0, 0), Square.sq(0, 3), Square.sq(2, 3)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 1), Square.sq(0, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 1), Square.sq(0, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 1), Square.sq(0, 2)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 1), Square.sq(1, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 1), Square.sq(2, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 0), Square.sq(0, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 0), Square.sq(0, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 0), Square.sq(1, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(1, 0), Square.sq(2, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(2, 0), Square.sq(0, 0)),
                    Move.mv(Square.sq(0, 0), Square.sq(2, 0), Square.sq(1, 1)),
                    Move.mv(Square.sq(0, 0), Square.sq(2, 0), Square.sq(0, 2)),
                    Move.mv(Square.sq(0, 0), Square.sq(2, 0), Square.sq(1, 0)),

                    Move.mv(Square.sq(7, 1), Square.sq(7, 2), Square.sq(7, 1)),

                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 1)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 8), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 1)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 7), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 1)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 6), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 1)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 5), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 1)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 4), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 1)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 3), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 1)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 2), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 1), Square.sq(9, 0)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 7)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 6)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 5)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 4)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 3)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 2)),
                    Move.mv(Square.sq(9, 9), Square.sq(9, 0), Square.sq(9, 1))
            ));

    static final Piece[][] LEGALMOVEITERATORTESTBOARD1 =
        {
        { B, B, B, B, B, B, B, B, B, B },
        { B, W, B, B, B, B, B, B, W, B },
        { B, B, B, B, B, B, B, B, B, B },
        { B, B, B, B, B, B, B, B, B, B },
        { B, B, B, B, B, B, B, B, B, B },
        { B, B, B, B, B, B, B, B, B, B },
        { B, B, B, B, B, B, B, B, B, B },
        { B, B, B, B, B, B, B, B, B, B },
        { B, W, B, B, B, B, B, B, W, B },
        { B, B, B, B, B, B, B, B, B, B }
        };

    static final Set<Move> LEGALMOVEITERATORTESTMOVES1 =
            new HashSet<>(Arrays.asList());

}
