package com.dz;

import java.util.Scanner;

public class Board {
    public String[][] board = new String[8][8];
    public final String[] NOTEROW = "A,B,C,D,E,F,G,H".split(",");
    final String[] INITROW = "Л ,К ,С ,Ф ,Кр,С ,К ,Л ".split(",");
    final String FIGURES = "П,Л,К,С,Ф,Кр";

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
                    sb = sb.append(". ");
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

    public boolean makeMovie(int movieNumber) {
        final String INCORR = "Некорректный ввод. Пример: e2e4";
        try (Scanner in = new Scanner(System.in)) {
            boolean isMoved = false;

            while(!isMoved) {
                String mv = in.nextLine().trim().toUpperCase();

                if ("СТОП".equals(mv) || "STOP".equals(mv)) { // ввели стоп или stop -- конец игре
                    System.out.println("Игра окончена");
                    System.exit(0);
                }

                // проверка на корректность ввода хода
                // длина введенной строки должна быть 4 символа
                if(mv.length()!=4){
                    System.out.println(INCORR);
                    continue;
                }

                char [] mvc = mv.toCharArray();

                int r0 = -1, r2 = -1;

                for(int i = 0; i<NOTEROW.length; i++){
                    if(NOTEROW[i].equals(mvc[0]))
                        r0 = i;

                    if(NOTEROW[i].equals(mvc[2]))
                        r2 = i;
                }

                // символы должны быть буквами, подписанными под столбиками
                if(r0<0 || r2<0){
                    System.out.println(INCORR);
                    continue;
                }

                // числа должны быть от 1 до 8
                int r1, r3;
                try {
                    r1 = Integer.valueOf(mvc[1]);
                    r3 = Integer.valueOf(mvc[3]);
                } catch (NumberFormatException e) {
                    System.out.println(INCORR);
                    continue;
                }

                if(!((1<=r1 && r1 <= 8) && (1<=r3 && r3 <= 8))){
                    System.out.println(INCORR);
                    continue;
                }

                if(board[r1][r0].isEmpty()){ // нечем ходить
                    System.out.println("В позиции " + mvc[0]+mvc[1]+" ничего нет" );
                    continue;
                }

                if(movieNumber % 2 == 0 && FIGURES.indexOf(board[r1][r0])<0){
                    System.out.println("Сейчас ход черных");
                    continue;
                }


            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return true;

    }

}
