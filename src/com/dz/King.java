package com.dz;

public class King extends Figure implements IFigure {

    King(IMoveCoord moveCoord) {
        int matrix[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},
                {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        int[] from = moveCoord.getFrom();
        int[] to = moveCoord.getTo();
        super.template = -1;
        super.matrix = matrix;
        super.jump = false;
        super.from = from;
        super.to = to;
    }

    @Override
    public String findTemlate() {
        int dx = to[1] - from[1];
        int dy = to[0] - from[0];

        //ход короля -- на соседнюю клеточку (пока без рокировки)
        if (!(Math.abs(dx) <= 1 && Math.abs(dy) <= 1))
            return "Так не ходят";

        //ищем темплейт
        for (int i = 0; i < 8; i++) {
            if (matrix[i][0] == dx && matrix[i][1] == dy) {
                template = i;
                break;
            }
        }
        if (template < 0)
            return "Так не ходят";

        return "";
    }

    @Override
    public String checkMove(Board board) {
        //TODO: проверить, что поле to не битое
        return "";
    }
}
