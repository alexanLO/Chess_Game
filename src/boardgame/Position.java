package boardgame;

//Posição no tabuleiro
public class Position {
    
    private int row;
    private int column;

    public Position(int row, int column){
        this.setRow(row);
        this.setColumn(column);
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override 
    public String toString(){
        return row + ", " + column;
    }
}
