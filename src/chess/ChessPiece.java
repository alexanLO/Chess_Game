package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;

    // construct
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    // gets
    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    // methods
    public void increaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }
}
