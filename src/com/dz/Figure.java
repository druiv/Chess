package com.dz;

public abstract class Figure implements IFigure {
    public IMoveCoord moveCoord;
    public int matrix[][];
    public int template = -1;
    public boolean jump;
    public int[] from;
    public int[] to;

    public boolean checkMove(IMoveCoord moveCoord, Board board) {
        return false;
    }
    public void findTemlate(IMoveCoord moveCoord) {
    }
}

interface IFigure {
    public boolean checkMove(IMoveCoord moveCoord, Board board);
    public void findTemlate(IMoveCoord moveCoord);
}
