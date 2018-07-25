package com.dz;

public class Rook extends Figure implements IFigure{
    Rook(IMoveCoord moveCoord) {
        //super.Figure(moveCoord);
        int matrix[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[] from = moveCoord.getFrom();
        int[] to = moveCoord.getTo();
        super.template = -1;
        super.matrix = matrix;
        super.jump = false;
        super.from = from;
        super.to = to;
    }

    public void findTemlate(IMoveCoord moveCoord) {
        int dx = to[1] - from[1];
        int dy = to[0] - from[0];

        dx = dx == 0 ? dx : dx / Math.abs(dx);
        dy = dy == 0 ? dy : dy / Math.abs(dy);

        for (int i = 0; i < 4; i++) {
            if (matrix[i][0] == dx && matrix[i][1] == dy) {
                template = i;
                break;
            }
        }
    }

    public boolean checkMove(IMoveCoord moveCoord, Board board) {
        boolean result = true;
        boolean isTo = false;
        int x = from[1];
        int y = from[0];

        while (!isTo) {
            x = x + matrix[template][0];
            y = y + matrix[template][1];

            if (x == to[1] && y == to[0])
                isTo = true;

            if (!isTo && !jump && !board.desk[y][x].isEmpty()) {
                result = false;
                break;
            }

        }
        return result;
    }

}
