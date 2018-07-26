package com.dz;

public class Test {

    public static void main(String[] args) {
        Board chessboard = new Board();
        chessboard.InitBoard();
        chessboard.desk[4][0] = "л ";
        chessboard.desk[3][7] = "Кр";
        chessboard.desk[3][5] = "кр";

        chessboard.showBoard();

        int i = 1;
        while (chessboard.makeMovie(i++)) {
            chessboard.showBoard();
        }
    }
}
