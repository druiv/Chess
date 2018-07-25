package com.dz;

public class Main {

    public static void main(String[] args) {

        Board chessboard = new Board();
        chessboard.InitBoard();
        chessboard.showBoard();

        int i = 1;
        while (chessboard.makeMovie(i++)) {
            chessboard.showBoard();
        }
    }
}
