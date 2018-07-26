package com.dz;

public abstract class Figure implements IFigure {
    public IMoveCoord moveCoord;
    public int matrix[][];
    public int template = -1;
    public boolean jump;
    public int[] from;
    public int[] to;

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
    public String findTemlate(IMoveCoord moveCoord)  {
        return "";
    }
}

interface IFigure {
    public String checkMove(IMoveCoord moveCoord, Board board);
    public String findTemlate(IMoveCoord moveCoord);
}
