package com.dz;

public class Bishop extends Figure implements IFigure {
    Bishop(IMoveCoord moveCoord) {
        int matrix[][] = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        int[] from = moveCoord.getFrom();
        int[] to = moveCoord.getTo();
        super.template = -1;
        super.matrix = matrix;
        super.jump = false;
        super.from = from;
        super.to = to;
    }

    public String findTemlate(IMoveCoord moveCoord) {
        int dx = to[1] - from[1];
        int dy = to[0] - from[0];

        //при ходе слона перемещения должны совпадать по модулю
        if (Math.abs(dx) != Math.abs(dy))
            return "Так не ходят";

        //ищем темплейт хода слона.
        // В темплейте перемещения должны быть того же знака
        for (int i = 0; i < 4; i++) {
            if (matrix[i][0] * dx > 0 && matrix[i][1] * dy > 0) {
                template = i;
                break;
            }
        }
        if (template < 0)
            return "Так не ходят";

        return "";
    }

    public String checkMove(IMoveCoord moveCoord, Board board) {
        return super.checkMove(moveCoord, board);
    }

}
