package com.dz;

public class Knight extends Figure implements IFigure {

    Knight(IMoveCoord moveCoord) {
        int matrix[][] = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        int[] from = moveCoord.getFrom();
        int[] to = moveCoord.getTo();
        super.template = -1;
        super.matrix = matrix;
        super.jump = true;
        super.from = from;
        super.to = to;
    }

    @Override
    public String findTemlate() {
        int dx = to[1] - from[1];
        int dy = to[0] - from[0];
        String result = "";

        for (int i = 0; i < 8; i++) {
            if (matrix[i][0] == dx && matrix[i][1] == dy) {
                template = i;
                break;
            }
        }
        if (template < 0)
            result = "Так не ходят";

        return result;
    }

    @Override
    public String checkMove(Board board) {
        return ""; //конь умеет прыгать
    }

}
