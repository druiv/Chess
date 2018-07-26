package com.dz;

public class Rook extends Figure implements IFigure {
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

    public String findTemlate(IMoveCoord moveCoord) {
        int dx = to[1] - from[1];
        int dy = to[0] - from[0];

        //при ходе ладьи одно из перемещений должно равняться нулю
        if (dx != 0 && dy != 0)
            return "Так не ходят";

        //ищем темплейт хода ладьи.
        // В темплейте также одно из перемещений должно равняться нулю,
        // а другое должно быть того же знака
        for (int i = 0; i < 4; i++) {
            if (((matrix[i][0] == 0 && dx == 0) || (matrix[i][0] * dx > 0)) &&
                    (matrix[i][1] == 0 && dy == 0) || (matrix[i][1] * dy > 0)) {
                template = i;
                break;
            }
        }
        if (template < 0)
            return "Так не ходят";

        return "";
    }

    public String checkMove(IMoveCoord moveCoord, Board board) {
        String result = "";
        boolean isTo = false;
        int x = from[1];
        int y = from[0];

        while (!isTo) {
            x = x + matrix[template][0];
            y = y + matrix[template][1];

            if (x == to[1] && y == to[0])
                isTo = true;

            if (!isTo && !jump && !board.desk[y][x].isEmpty()) {
                result = "Фигура не может перепрыгнуть";
                break;
            }

        }
        return result;
    }

}
