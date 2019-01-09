package amazons;

import java.util.Iterator;

import static java.lang.Math.*;

import static amazons.Piece.*;

/** A Player that automatically generates moves.
 *  @author Michaela Warady
 */
class AI extends Player {

    /** A position magnitude indicating a win (for white if positive, black
     *  if negative). */
    private static final int WINNING_VALUE = Integer.MAX_VALUE - 1;
    /** A magnitude greater than a normal value. */
    private static final int INFTY = Integer.MAX_VALUE;

    /** A new AI with no piece or controller (intended to produce
     *  a template). */
    AI() {
        this(null, null);
    }

    /** A new AI playing PIECE under control of CONTROLLER. */
    AI(Piece piece, Controller controller) {
        super(piece, controller);
    }

    @Override
    Player create(Piece piece, Controller controller) {
        return new AI(piece, controller);
    }

    @Override
    String myMove() {
        System.out.println("enter mymove");
        Move move = findMove();
        _controller.reportMove(move);
        return move.toString();
    }

    /** Return a move for me from the current position, assuming there
     *  is a move. */
    private Move findMove() {
        Board b = new Board(board());
        if (_myPiece == WHITE) {
            findMove(b, maxDepth(b), true, 1, -INFTY, INFTY);
        } else {
            findMove(b, maxDepth(b), true, -1, -INFTY, INFTY);
        }
        return _lastFoundMove;
    }

    /** The move found by the last call to one of the ...FindMove methods
     *  below. */
    private Move _lastFoundMove;

    /** Find a move from position BOARD and return its value, recording
     *  the move found in _lastFoundMove iff SAVEMOVE. The move
     *  should have maximal value or have value > BETA if SENSE==1,
     *  and minimal value or value < ALPHA if SENSE==-1. Searches up to
     *  DEPTH levels.  Searching at level 0 simply returns a static estimate
     *  of the board value and does not set _lastMoveFound. */
    private int findMove(Board board, int depth, boolean saveMove,
                         int sense, int alpha, int beta) {
        if (depth == 0 || board.winner() != null) {
            return staticScore(board);
        } else {
            Piece side;
            if (sense < 0) {
                side = BLACK;
            } else {
                side = WHITE;
            }
            Iterator<Move> babygoats = board.legalMoves(side);
            int score = findMove(board, depth - 1, false, -sense, alpha, beta);
            while (babygoats.hasNext()) {
                Move next = babygoats.next();
                if (saveMove) {
                    _lastFoundMove = next;
                }
                board.makeMove(next);
                int temp;
                if (sense == 1) {
                    temp = findMove(board, depth - 1, false,
                            -sense, max(alpha, score), beta);
                    if (temp > score) {
                        score = temp;
                        if (saveMove) {
                            _lastFoundMove = next;
                        }
                    }
                } else {
                    temp = findMove(board, depth - 1, false,
                            -sense, alpha, min(beta, score));
                    if (temp < score) {
                        score = temp;
                        if (saveMove) {
                            _lastFoundMove = next;
                        }
                    }
                }
                if (alpha >= beta) {
                    break;
                }
                board.undo();
            }
            return score;
        }
    }

    /** Return a heuristically determined maximum search depth
     *  based on characteristics of BOARD. */
    private int maxDepth(Board board) {
        final int ten = 10;
        final int two = 2;
        final int twenty = 20;
        final int four = 4;
        final int one = 1;
        int N = board.numMoves();
        if (N < ten) {
            return one;
        } else if (N < twenty) {
            return one;
        } else {
            return one;
        }
    }


    /** Return a heuristic value for BOARD. */
    private int staticScore(Board board) {
        Piece winner = board.winner();
        if (winner == BLACK) {
            return -WINNING_VALUE;
        } else if (winner == WHITE) {
            return WINNING_VALUE;
        }
        int sumwhite = 0;
        int sumblack = 0;
        int counterwhite = 0;
        int counterblack = 0;
        for (int x = 0; x < 100 && counterwhite < 4
                && counterblack < 4; x += 1) {
            Piece mypiece = board.get(Square.sq(x));
            if (mypiece.equals(WHITE)) {
                counterwhite += 1;
                Iterator<Square> whiteiterator =
                        board.reachableFrom(Square.sq(x), null);
                int whitemoves = 0;
                while (whiteiterator.hasNext()) {
                    whitemoves += 1;
                    whiteiterator.next();
                }
                sumwhite += whitemoves;
            } else if (mypiece.equals(BLACK)) {
                counterblack += 1;
                Iterator<Square> blackiterator =
                        board.reachableFrom(Square.sq(x), null);
                int blackmoves = 0;
                while (blackiterator.hasNext()) {
                    blackmoves += 1;
                    blackiterator.next();
                }
                sumblack += blackmoves;
            }
        }
        return sumwhite - sumblack;
    }


}
