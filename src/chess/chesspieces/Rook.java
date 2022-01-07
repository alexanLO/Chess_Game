package chess.chesspieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Rook extends ChessPiece{

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }
    
}
