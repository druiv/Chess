package com.dz;

public class Pawn extends Figure implements IFigure {

    Pawn(IMoveCoord moveCoord) {
        int[] from = moveCoord.getFrom();
        int[] to = moveCoord.getTo();
        super.from = from;
        super.to = to;
    }

    @Override
    public String checkMove(Board board) {
        int sign = board.desk[from[0]][from[1]].trim().equals("П") ? 1 : -1;
        int dx = to[1] - from[1];
        int dy = to[0] - from[0];
        String result = "";
        int colorFrom = board.FIGURES.indexOf(board.desk[from[0]][from[1]].trim());
        int colorTo = board.FIGURES.indexOf(board.desk[to[0]][to[1]].trim());
        boolean isToEmpty = board.desk[to[0]][to[1]].isEmpty();
        boolean isToEmpty2 = true;

        if (from[0] == (7 - 5 * sign) / 2) //если пешка стоит в стартовой позиции, вычисляем, пустая ли перед ней клетка
            isToEmpty2 = board.desk[from[0] + sign][from[1]].isEmpty();

        boolean isEnemy = false;
        if ((!isToEmpty)
                && ((colorFrom < 0 && colorTo >= 0) || (colorFrom >= 0 && colorTo < 0)))//пешка и фигура разного цвета
            isEnemy = true;

        if (!((dx == 0 && dy == sign && isToEmpty) ||//простой ход пешки вперед
                (dx == 0 && dy == sign * 2 && from[0] == (7 - 5 * sign) / 2) && isToEmpty && isToEmpty2 ||//ход через клеточку со стартовой позиции
                (Math.abs(dx) == 1 && dy == sign && isEnemy)))//ход со взятием
            result = "Так не ходят";
        //TODO: битое поле

        return result;
    }

}
