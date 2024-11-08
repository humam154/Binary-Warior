import java.util.LinkedList;

public class State {

    private int rows,columns;
    private char [][] grid;
    private int playerIndexRow, playerIndexColumn;
    private LinkedList<State> states;

    public State(){

    }
    public State (int rows, int columns, int playerIndexRow, int playerIndexColumn){
        this.rows = rows;
        this.columns = columns;
        this.playerIndexRow = playerIndexRow;
        this.playerIndexColumn = playerIndexColumn;
        this.grid = new char[rows][columns];
    }

    public void setRows(int rows){
        this.rows = rows;
    }

    public int getRows(){
        return this.rows;
    }

    public void setColumns(int columns){
        this.columns = columns;
    }

    public int getColumns(){
        return this.columns;
    }

    public void setGrid(char [][] grid){
        if (grid.length == this.rows && grid[0].length == this.columns){
            for(int i = 0 ; i < this.rows ; i ++){
                System.arraycopy(grid[i], 0, this.grid[i], 0, columns);
            }
        }
    }

    public void printGrid(){
        for (int i = 0 ; i < rows ; i ++) {
            for (int j = 0 ; j < columns ; j ++){
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int getPlayerIndexRow() {
        return playerIndexRow;
    }

    public void setPlayerIndexRow(int playerIndexRow) {
        this.playerIndexRow = playerIndexRow;
    }

    public int getPlayerIndexColumn() {
        return playerIndexColumn;
    }

    public void setPlayerIndexColumn(int playerIndexColumn) {
        this.playerIndexColumn = playerIndexColumn;
    }

    public State deepCopy(State oldState, State newState){
        newState.rows = oldState.rows;
        newState.columns = oldState.columns;
        newState.grid = oldState.grid;
        newState.states = new LinkedList<State>();
        newState.states.addAll(oldState.states);

        return newState;
    }


    public void moveRight(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkArray(grid, indexRow, indexColumn)) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (grid[i][j] == grid[indexRow][indexColumn]) {

                    }
                }
            }
        }
    }

    private char getRight(char [][] grid, int indexRow, int indexColumn){
        if(this.checkArray(grid, indexRow, indexColumn)) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i == indexRow && j == indexColumn) {
                        return grid[i][j + 1];
                    }
                }
            }
            return 'x';
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private boolean checkArray(char [][]grid, int row, int column){
        return row <= grid.length && column <= grid[0].length;
    }
}
