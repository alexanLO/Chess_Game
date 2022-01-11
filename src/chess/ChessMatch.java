package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;
import chess.exception.ChessException;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    // Poder imprimir as posições possíveis apartir de uma posição de origem.
    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("Não existe peça na posição de origem.");
        }
        if(currentPlayer != ((ChessPiece) board.piece(position)).getColor()){
            throw new ChessException("Não é sua vez de jogar");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Não existe movimentos possiveis para a peça escolhida");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("A peça escolhida não pode se mover para a posição de destino");
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(row, column).toPosition());
    }

    private void initialSetup() {
        // PEÃO
        // placeNewPiece('a', 7, new Pawn(board, Color.WHITE));
        // placeNewPiece('b', 7, new Pawn(board, Color.WHITE));
        // placeNewPiece('c', 7, new Pawn(board, Color.WHITE));
        // placeNewPiece('d', 7, new Pawn(board, Color.WHITE));
        // placeNewPiece('e', 7, new Pawn(board, Color.WHITE));
        // placeNewPiece('f', 7, new Pawn(board, Color.WHITE));
        // placeNewPiece('g', 7, new Pawn(board, Color.WHITE));
        // placeNewPiece('h', 7, new Pawn(board, Color.WHITE));
        // TORRE
        placeNewPiece('a', 8, new Rook(board, Color.WHITE));
        placeNewPiece('h', 8, new Rook(board, Color.WHITE));
        // CAVALO
        placeNewPiece('b', 8, new Knight(board, Color.WHITE));
        placeNewPiece('g', 8, new Knight(board, Color.WHITE));
        // Bispo
        placeNewPiece('c', 8, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 8, new Bishop(board, Color.WHITE));
        // Rainha
        placeNewPiece('d', 8, new Queen(board, Color.WHITE));
        // REI
        placeNewPiece('e', 8, new King(board, Color.WHITE));

        // PEÃO
        // placeNewPiece('a', 2, new Pawn(board, Color.BLACK));
        // placeNewPiece('b', 2, new Pawn(board, Color.BLACK));
        // placeNewPiece('c', 2, new Pawn(board, Color.BLACK));
        // placeNewPiece('d', 2, new Pawn(board, Color.BLACK));
        // placeNewPiece('e', 2, new Pawn(board, Color.BLACK));
        // placeNewPiece('f', 2, new Pawn(board, Color.BLACK));
        // placeNewPiece('g', 2, new Pawn(board, Color.BLACK));
        // placeNewPiece('h', 2, new Pawn(board, Color.BLACK));
        // TORRE
        placeNewPiece('a', 1, new Rook(board, Color.BLACK));
        placeNewPiece('h', 1, new Rook(board, Color.BLACK));
        // CAVALO
        placeNewPiece('b', 1, new Knight(board, Color.BLACK));
        placeNewPiece('g', 1, new Knight(board, Color.BLACK));
        // Bispo
        placeNewPiece('c', 1, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 1, new Bishop(board, Color.BLACK));
        // Rainha
        placeNewPiece('e', 1, new Queen(board, Color.BLACK));
        // REI
        placeNewPiece('d', 1, new King(board, Color.BLACK));

    }
}
