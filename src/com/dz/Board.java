package com.dz;

public class Board {
    public String[][] board = new String[8][8];
    public final String[] NOTEROW = "A,B,C,D,E,F,G,H".split(",");
    final String[] INITROW = "Л ,К ,С ,Ф ,Кр,С ,К ,Л ".split(",");

    public Board(){

        for(int i = 2; i<6; i++)
            for (int j = 0; j<8; j++)
                board [i][j] = "";

        for (int j = 0; j<8; j++){
            board[6][j] = "п ";
            board[1][j] = "П ";
            board[0][j] = INITROW[j];
            board[7][j] = INITROW[j].toLowerCase();
        }
    }

    public void showBoard(){
        StringBuilder sb = new StringBuilder("   ");

        String noterow = "";
        for(String s: NOTEROW)
            sb = sb.append(s).append(" ");
        noterow = sb.toString();
        sb.delete(0, sb.length()-1);

        for(int i = 7; i >=0; i--){
            if(i == 7)
                System.out.println(noterow); //подпишем доску
            for (int j = 0; j<8; j++){
                if(j == 0)
                    sb = sb.append(i+1).append(" ");
                if (board[i][j].trim().isEmpty())
                    sb = sb.append("..");
                else
                    sb = sb.append(board[i][j]);
                if(j == 7)
                    sb = sb.append(i+1).append(" ");
            }
            System.out.println(sb);
            sb.delete(0, sb.length()-1);
        }
        System.out.println(noterow); //подпишем доску
    }
}
