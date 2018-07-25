package com.dz;

import java.util.Scanner;

public class Move {
    Board board = new Board();

    IMoveCoord moveCoord;
    int movieNumber;

    public Move(Board board, int movieNumber) {
        this.board = board;
        this.movieNumber = movieNumber;
    }

    public void readMove() {
        final String INCORR = "Некорректный ввод. Пример: e2e4";

        Scanner in = new Scanner(System.in);

        try {
            boolean isCheked = false;

            while (!isCheked && in.hasNextLine()) {
                String mv = in.nextLine().trim().toUpperCase();

                if ("СТОП".equals(mv) || "STOP".equals(mv)) { // ввели стоп или stop -- конец игре
                    System.out.println("Игра окончена");
                    System.exit(0);
                }

                // проверка на корректность ввода хода
                // длина введенной строки должна быть 4 символа
                if (mv.length() != 4) {
                    System.out.println(INCORR);
                    continue;
                }

                char[] mvc = mv.toCharArray();

                String[] mvs = new String[4];
                for (int k = 0; k < 4; k++)
                    mvs[k] = String.valueOf(mvc[k]);

                int[] r = {-1, -1, -1, -1};

                for (int i = 0; i < board.NOTEROW.length; i++) {
                    if (board.NOTEROW[i].equals(mvs[0]))
                        r[0] = i;

                    if (board.NOTEROW[i].equals(mvs[2]))
                        r[2] = i;
                }

                // символы должны быть буквами, подписанными под столбиками
                if (r[0] < 0 || r[2] < 0) {
                    System.out.println(INCORR);
                    continue;
                }

                // числа должны быть от 1 до 8
                try {
                    r[1] = Integer.valueOf(mvs[1]) - 1;
                    r[3] = Integer.valueOf(mvs[3]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println(INCORR);
                    continue;
                }

                if (!((0 <= r[1] && r[1] <= 7) && (0 <= r[3] && r[3] <= 7))) {
                    System.out.println(INCORR);
                    continue;
                }

                if (board.desk[r[1]][r[0]].isEmpty()) { // нечем ходить
                    System.out.println("В позиции " + mvc[0] + mvc[1] + " ничего нет");
                    continue;
                }

                int colorFrom = board.FIGURES.indexOf(board.desk[r[1]][r[0]].trim());
                int colorTo = board.FIGURES.indexOf(board.desk[r[3]][r[2]].trim());

                if (movieNumber % 2 == 0 && colorFrom >= 0) {
                    System.out.println("Сейчас ход черных");
                    continue;
                }

                if (movieNumber % 2 != 0 && colorFrom < 0) {
                    System.out.println("Сейчас ход белых");
                    continue;
                }

                if ((!board.desk[r[3]][r[1]].isEmpty())
                        && ((colorFrom < 0 && colorTo < 0) || (colorFrom >= 0 && colorTo >= 0))) {
                    System.out.println("Фигура не может рубить своих");
                    continue;
                }

                moveCoord = new MoveCoord(r);
                isCheked = true;

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean makeMove() {
        int[] fr = moveCoord.getFrom();
        String fig = board.desk[fr[0]][fr[1]].trim().toUpperCase();
        IFigure Fig = null;

        switch (fig) {
            case "Л":
                Fig = new Rook(moveCoord);
                break;
        }

        Fig.findTemlate(moveCoord);
        boolean result = Fig.checkMove(moveCoord, board);

        if (result) { // делаем ход
            int[] t = moveCoord.getTo();
            board.desk[t[0]][t[1]] = board.desk[fr[0]][fr[1]];
            board.desk[fr[0]][fr[1]] = "";
        }

        return result;

    }
}

interface IMoveCoord {
    public int[] from = new int[2];
    public int[] to = new int[2];

    public int[] getFrom();

    public int[] getTo();
}

class MoveCoord implements IMoveCoord {

    public IMoveCoord moveCoord;
    public int[] from = new int[2];
    public int[] to = new int[2];

    public MoveCoord(int[] coor) {
        from[0] = coor[1];
        from[1] = coor[0];
        to[0] = coor[3];
        to[1] = coor[2];
    }

    public int[] getFrom() {
        return from;
    }

    public int[] getTo() {
        return to;
    }
}