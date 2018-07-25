package com.dz;

import java.util.Scanner;

public class Board {
    public String[][] desk = new String[8][8];
    public final String[] NOTEROW = "A,B,C,D,E,F,G,H".split(",");
    final String[] INITROW = "Л ,К ,С ,Ф ,Кр,С ,К ,Л ".split(",");
    final String FIGURES = "П,Л,К,С,Ф,Кр";

    public Board() {
    }

    public void InitBoard(){
        for (int i = 2; i < 6; i++)
            for (int j = 0; j < 8; j++)
                desk[i][j] = "";

        for (int j = 0; j < 8; j++) {
            desk[6][j] = "п ";
            desk[1][j] = "П ";
            desk[0][j] = INITROW[j];
            desk[7][j] = INITROW[j].toLowerCase();
        }
    }

    public void showBoard() {
        StringBuilder sb = new StringBuilder("   ");

        String noterow = "";
        for (String s : NOTEROW)
            sb = sb.append(s).append(" ");
        noterow = sb.toString();
        sb.delete(0, sb.length() - 1);

        for (int i = 7; i >= 0; i--) {
            if (i == 7)
                System.out.println(noterow); //подпишем доску
            for (int j = 0; j < 8; j++) {
                if (j == 0)
                    sb = sb.append(i + 1).append(" ");
                if (desk[i][j].trim().isEmpty())
                    sb = sb.append(". ");
                else
                    sb = sb.append(desk[i][j]);
                if (j == 7)
                    sb = sb.append(i + 1).append(" ");
            }
            System.out.println(sb);
            sb.delete(0, sb.length() - 1);
        }
        System.out.println(noterow); //подпишем доску
    }

    public boolean makeMovie(int movieNumber) {
        Move move = new Move(this, movieNumber);

        move.readMove();
        move.makeMove();

        return true;
    }

}
