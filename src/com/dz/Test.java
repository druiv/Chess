package com.dz;

public class Test {

    public static void main(String[] args) {
        Board chessboard = new Board();

        chessboard.InitBoard();

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                chessboard.desk[i][j] = "";

        chessboard.desk[7][4] = "кр";
        chessboard.desk[3][5] = "Кр";
        chessboard.desk[1][0] = "п ";
        chessboard.desk[6][0] = "П ";

        chessboard.showBoard();

        int i = 1;
        while (chessboard.makeMovie(i++)) {
            chessboard.showBoard();
        }
    }
}
