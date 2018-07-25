package com.dz;

public abstract class Figure implements IFigure {
    public IMoveCoord moveCoord;
    public int matrix[][];
    public int template = -1;
    public boolean jump;
    public int[] from;
    public int[] to;

    public String checkMove(IMoveCoord moveCoord, Board board) {
        return "";
    }
    public String findTemlate(IMoveCoord moveCoord)  {
        return "";
    }
}

interface IFigure {
    public String checkMove(IMoveCoord moveCoord, Board board);
    public String findTemlate(IMoveCoord moveCoord);
}
