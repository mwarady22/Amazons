package amazons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static amazons.Piece.*;
import static amazons.Move.mv;
import static amazons.Square.exists;


/** The state of an Amazons Game.
 *  @author Michaela Warady
 */
class Board {

    /** The number of squares on a side of the board. */
    static final int SIZE = 10;

    /** Holds the board representation. */
    private Piece[] _board;

    /** Keeps track of moves for the board. */
    private ArrayList<Move> _moves;

    /** Initializes a game board with SIZE squares on a side in the
     *  initial position. */
    Board() {
        init();
    }

    /** Initializes a copy of MODEL. */
    Board(Board model) {
        copy(model);
    }

    /** Copies MODEL into me. */
    void copy(Board model) {
        this._board = model._board;
        this._moves = model._moves;
        this._turn = model._turn;
        this._winner = model._winner;
    }

    /** Clears the board to the initial position. */
    void init() {
        _board = new Piece[SIZE * SIZE];
        for (int x = 0; x < _board.length; x += 1) {
            this._board[x] = EMPTY;
        }
        final int three = 3;
        final int six = 6;
        final int thirty = 30;
        final int thirtynine = 39;
        final int sixty = 60;
        final int sixtynine = 69;
        final int ninetythree = 93;
        final int ninetysix = 96;

        this._board[three] = WHITE;
        this._board[six] = WHITE;
        this._board[thirty] = WHITE;
        this._board[thirtynine] = WHITE;
        this._board[sixty] = BLACK;
        this._board[sixtynine] = BLACK;
        this._board[ninetythree] = BLACK;
        this._board[ninetysix] = BLACK;

        _moves = new ArrayList<Move>();
        _turn = WHITE;
        _winner = null;
    }

    /** Return the Piece whose move it is (WHITE or BLACK). */
    Piece turn() {
        return _turn;
    }

    /** Return the number of moves (that have not been undone) for this
     *  board. */
    int numMoves() {
        return this._moves.size();
    }

    /** Return the winner in the current position, or null if the game is
     *  not yet finished. */
    Piece winner() {
        Iterator<Move> lastmove = new LegalMoveIterator(this.turn());
        if (!lastmove.hasNext() && _winner == null) {
            _winner = this._turn.opponent();
        }
        return this._winner;
    }

    /** Return the contents the square at S. */
    final Piece get(Square s) {
        return this._board[s.index()];
    }

    /** Return the contents of the square at (COL, ROW), where
     *  0 <= COL, ROW <= 9. */
    final Piece get(int col, int row) {
        return this._board[row * SIZE + col];
    }

    /** Return the contents of the square at COL ROW. */
    final Piece get(char col, char row) {
        return get(col - 'a', row - '1');
    }

    /** Set square S to P. */
    final void put(Piece p, Square s) {
        this._board[s.index()] = p;
    }

    /** Set square (COL, ROW) to P. */
    final void put(Piece p, int col, int row) {
        this._board[row * SIZE + col] = p;
        _winner = null;
    }

    /** Set square COL ROW to P. */
    final void put(Piece p, char col, char row) {
        put(p, col - 'a', row - '1');
    }

    /** Return true iff FROM - TO is an unblocked queen move on the current
     *  board, ignoring the contents of ASEMPTY, if it is encountered.
     *  For this to be true, FROM-TO must be a queen move and the
     *  squares along it, other than FROM and ASEMPTY, must be
     *  empty. ASEMPTY may be null, in which case it has no effect. */
    boolean isUnblockedMove(Square from, Square to, Square asEmpty) {
        assert from.isQueenMove(to);
        int direction = from.direction(to);
        if (!Square.exists(to.col(), to.row())) {
            return false;
        }
        Square now = from;
        while (now != null && !now.equals(to)) {
            if (!get(now).equals(EMPTY) && !now.equals(asEmpty)) {
                return false;
            } else {
                now = now.queenMove(direction, 1);
            }
        }
        return get(to).equals(EMPTY) || to.equals(asEmpty);
    }

    /** Return true iff FROM is a valid starting square for a move. */
    boolean isLegal(Square from) {
        if (exists(from.col(), from.row()) && !get(from).equals(EMPTY)
                && get(from).equals(this.turn())) {
            return true;
        }
        return false;
    }

    /** Return true iff FROM-TO is a valid first part of move, ignoring
     *  spear throwing. */
    boolean isLegal(Square from, Square to) {
        return (isLegal(from) && isUnblockedMove(from, to, from));
    }

    /** Return true iff FROM-TO(SPEAR) is a legal move in the current
     *  position. */
    boolean isLegal(Square from, Square to, Square spear) {
        return (isLegal(from, to) && !spear.equals(to)
                && isUnblockedMove(to, spear, from));
    }

    /** Return true iff MOVE is a legal move in the current
     *  position. */
    boolean isLegal(Move move) {
        return isLegal(move.from(), move.to(), move.spear());
    }

    /** Move FROM-TO(SPEAR), assuming this is a legal move. */
    void makeMove(Square from, Square to, Square spear) {
        if (isLegal(from, to, spear)) {
            Piece p = get(from);
            put(EMPTY, from);
            put(p, to);
            put(SPEAR, spear);
            _moves.add(mv(from, to, spear));
            _turn = turn().opponent();
        }
    }

    /** Move according to MOVE, assuming it is a legal move. */
    void makeMove(Move move) {
        makeMove(move.from(), move.to(), move.spear());
    }

    /** Undo one move.  Has no effect on the initial board. */
    void undo() {
        if (numMoves() != 0) {
            Move last = _moves.remove(numMoves() - 1);
            put(EMPTY, last.spear());
            put(get(last.to()), last.from());
            put(EMPTY, last.to());
            _turn = turn().opponent();
            _winner = null;
        }
    }

    /** Return an Iterator over the Squares that are reachable by an
     *  unblocked queen move from FROM. Does not pay attention to what
     *  piece (if any) is on FROM, nor to whether the game is finished.
     *  Treats square ASEMPTY (if non-null) as if it were EMPTY.  (This
     *  feature is useful when looking for Moves, because after moving a
     *  piece, one wants to treat the Square it came from as empty for
     *  purposes of spear throwing.) */
    Iterator<Square> reachableFrom(Square from, Square asEmpty) {
        return new ReachableFromIterator(from, asEmpty);
    }

    /** Return an Iterator over all legal moves on the current board. */
    Iterator<Move> legalMoves() {
        return new LegalMoveIterator(_turn);
    }

    /** Return an Iterator over all legal moves on the current board for
     *  SIDE (regardless of whose turn it is). */
    Iterator<Move> legalMoves(Piece side) {
        return new LegalMoveIterator(side);
    }

    /** An iterator used by reachableFrom. */
    private class ReachableFromIterator implements Iterator<Square> {

        /** Iterator of all squares reachable by queen move from FROM,
         *  treating ASEMPTY as empty. */
        ReachableFromIterator(Square from, Square asEmpty) {
            _from = from;
            _dir = -1;
            _steps = 0;
            _asEmpty = asEmpty;
            toNext();
        }

        @Override
        public boolean hasNext() {
            return _dir < 8;
        }

        @Override
        public Square next() {
            if (hasNext()) {
                Square next = _from.queenMove(_dir, _steps);
                toNext();
                return next;
            } else {
                return null;
            }
        }

        /** Advance _dir and _steps, so that the next valid Square is
         *  _steps steps in direction _dir from _from. */
        private void toNext() {
            if (hasNext()) {
                if (_dir == -1) {
                    _dir += 1;
                }
                _steps += 1;
                Square curr = _from.queenMove(_dir, _steps);
                while (((curr == null) || !((get(curr).equals(EMPTY))
                        || (curr.equals(_asEmpty)))) && hasNext()) {
                    _dir += 1;
                    _steps = 1;
                    curr = _from.queenMove(_dir, _steps);
                }
            }
        }

        /** Starting square. */
        private Square _from;
        /** Current direction. */
        private int _dir;
        /** Current distance. */
        private int _steps;
        /** Square treated as empty. */
        private Square _asEmpty;
    }

    /** An iterator used by legalMoves. */
    private class LegalMoveIterator implements Iterator<Move> {

        /** All legal moves for SIDE (WHITE or BLACK). */
        LegalMoveIterator(Piece side) {
            _counter = 0;
            _startingSquares = Square.iterator();
            _spearThrows = NO_SQUARES;
            _pieceMoves = NO_SQUARES;
            _fromPiece = side;
            toNext();
        }

        @Override
        public boolean hasNext() {
            return _counter <= 4;
        }

        @Override
        public Move next() {
            if (hasNext()) {
                Move next = Move.mv(_start, _nextSquare, _spearThrows.next());
                toNext();
                return next;
            }
            return null;
        }

        /** Advance so that the next valid Move is
         *  _start-_nextSquare(sp), where sp is the next value of
         *  _spearThrows. */
        private void toNext() {
            if (hasNext()) {
                if ((_pieceMoves == NO_SQUARES && _spearThrows == NO_SQUARES)
                        || (!_pieceMoves.hasNext()
                        && !_spearThrows.hasNext())) {
                    if (_startingSquares.hasNext()) {
                        _start = _startingSquares.next();
                    }
                    while (_startingSquares.hasNext() && get(_start)
                            != _fromPiece) {
                        _start = _startingSquares.next();
                    }
                    _counter += 1;
                    _pieceMoves = new ReachableFromIterator(_start, null);
                }
                if (!_spearThrows.hasNext()) {
                    _nextSquare = _pieceMoves.next();
                    if (_nextSquare != null) {
                        _spearThrows = new
                                ReachableFromIterator(_nextSquare, _start);
                    } else {
                        toNext();
                    }
                }
            }
        }

        /** Color of side whose moves we are iterating. */
        private Piece _fromPiece;
        /** Current starting square. */
        private Square _start;
        /** Remaining starting squares to consider. */
        private Iterator<Square> _startingSquares;
        /** Current piece's new position. */
        private Square _nextSquare;
        /** Remaining moves from _start to consider. */
        private Iterator<Square> _pieceMoves;
        /** Remaining spear throws from _piece to consider. */
        private Iterator<Square> _spearThrows;
        /** Counts number of pieces of this color found so far. */
        private int _counter;

    }

    @Override
    public String toString() {
        String ret = "";
        for (int y = 0; y < SIZE; y += 1) {
            String line = "";
            for (int z = 0; z < SIZE; z += 1) {
                line = line + _board[y * SIZE + z];
                if (z < SIZE - 1) {
                    line = line + " ";
                } else {
                    line = "   " + line + "\n";
                }
            }
            ret = line + ret;
        }
        return ret;
    }

    /** An empty iterator for initialization. */
    private static final Iterator<Square> NO_SQUARES =
        Collections.emptyIterator();

    /** Piece whose turn it is (BLACK or WHITE). */
    private Piece _turn;
    /** Cached value of winner on this board, or EMPTY if it has not been
     *  computed. */
    private Piece _winner;
}
