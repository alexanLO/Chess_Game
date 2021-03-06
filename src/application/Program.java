package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exception.ChessException;

class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Peça da posição: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Para posição: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }

                if(chessMatch.getPromoted() != null){
                    System.out.print("Digite a letra do tipo da peça (B/C/T/Q): ");
                    String type = sc.nextLine().toUpperCase();
                    while(!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("Q")){
                        System.out.println("Invalide value! Digite a letra do tipo da peça (B/C/T/Q): ");
                        type = sc.nextLine().toUpperCase();    
                    }
                    chessMatch.replacePromotedPiece(type);
                }

            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.flush();
            }

        }

        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}