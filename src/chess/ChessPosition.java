package chess;

import boardgame.Position;

public class ChessPosition {
    private char column;
    private int row;

    // construct
    public ChessPosition(int row, char column) {
        this.row = row;
        this.column = column;
        this.row = row;
    }

    // methods
    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition(8 - position.getRow(), (char) ('a' + position.getColumn()));
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
