package com.dz;

public class Test {

    public static void main(String[] args) {
        Board chessboard = new Board();
        chessboard.InitBoard();
        chessboard.desk[1][0] = "";
        chessboard.desk[6][0] = "";

        chessboard.showBoard();

        int i = 1;
        while (chessboard.makeMovie(i++)) {
            chessboard.showBoard();
        }
    }
}
