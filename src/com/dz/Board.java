package com.dz;

import java.util.Scanner;

public class Board {
    public String[][] desk = new String[8][8];
    public final String[] NOTEROW = "A,B,C,D,E,F,G,H".split(",");
    final String[] INITROW = "Л ,К ,С ,Ф ,Кр,С ,К ,Л ".split(",");
    final String FIGURES = "П,Л,К,С,Ф,Кр";

    public Board() {
    }

    public void InitBoard() {
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

    //проверяем, атаковано ли данное поле указанной стороной, color = 1 белые, -1 черные
    public boolean checkAttackSquare(int[] square, int color) {
        int matrix[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},//горизонтали и вертикали
                {1, 1}, {-1, 1}, {-1, -1}, {1, -1},//диагонали
                {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};//ход коня

        int[] tmp = {0, 0};
        int colortmp;
        String fig;
        int step = 0;
        String figtempl;
        int sign;

        for (int[] m : matrix) {
            tmp[0] = square[0];
            tmp[1] = square[1];
            step = 0;

            while (true) {
                tmp[0] = tmp[0] + m[0];
                tmp[1] = tmp[1] + m[1];
                step++;

                if (tmp[0] < 0 || tmp[0] > 7 || tmp[1] < 0 || tmp[1] > 7) //вышли за границу
                    break;

                figtempl = getFigure(m);

                if (figtempl.indexOf("К ") >= 0 && step > 1) //ход коня
                    break;

                if (!desk[tmp[0]][tmp[1]].isEmpty()) {

                    colortmp = FIGURES.indexOf(desk[tmp[0]][tmp[1]].trim());
                    if (colortmp < 0 && color > 0 || colortmp >= 0 && color < 0)//наткнулись на фигуру другого цвета
                        break;

                    fig = desk[tmp[0]][tmp[1]].toUpperCase();
                    if (figtempl.indexOf(fig) >= 0) {
                        if ("П КР".indexOf(fig) < 0)
                            return true;
                        else if ("КР".equals(fig) && step == 1)
                            return true;
                        else if ("П ".equals(fig) && step == 1) {
                            sign = desk[tmp[0]][tmp[1]].trim().equals("П") ? 1 : -1;
                            if (colortmp == sign)//пешка бьет только вперед
                                return true;
                            else
                                break;
                        } else
                            break;
                    } else
                        break;
                }
            }
        }
        return false;
    }

    private String getFigure(int[] t) {
        String result = "";

        if ((Math.abs(t[0]) == 1 && Math.abs(t[1]) == 2) || (Math.abs(t[0]) == 2 && Math.abs(t[1]) == 1)) //ход коня
            result = "К "; //кириллица
        else if (Math.abs(t[0]) == 1 && Math.abs(t[1]) == 1) // ход слона
            result = "П С Ф КР"; //кириллица
        else if ((Math.abs(t[0]) == 1 && Math.abs(t[1]) == 0) || (Math.abs(t[0]) == 0 && Math.abs(t[1]) == 1)) // ход ладьи
            result = "Л Ф КР"; //кириллица
        return result;
    }
}
