package com.dz;

public class Queen extends Figure implements IFigure {

    Queen(IMoveCoord moveCoord) {
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

        //ход ферзя -- как ладья или как слон
        if (!((dx == 0 || dy == 0) ||  //как ладья
                (Math.abs(dx) == Math.abs(dy)))) //как слон
            return "Так не ходят";

        //ищем темплейт ладьи или слона
        for (int i = 0; i < 8; i++) {
            if ((((matrix[i][0] == 0 && dx == 0) || (matrix[i][0] * dx > 0)) &&
                    ((matrix[i][1] == 0 && dy == 0) || (matrix[i][1] * dy > 0))) || //ход ладьи
                    (matrix[i][0] * dx > 0 && matrix[i][1] * dy > 0))//ход слона
            {
                template = i;
                break;
            }
        }
        if (template < 0)
            return "Так не ходят";

        return "";
    }

    public String checkMove(Board board) {
        return super.checkMove(board);
    }

}
