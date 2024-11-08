import java.util.Scanner;
import java.util.LinkedList;

public class State {

    private int rows,columns;
    private char [][] grid;
    private int playerIndexRow, playerIndexColumn;
    private LinkedList<State> states;
    private static int numOfGoals;

    public State(){

    }
    public State (int rows, int columns, int playerIndexRow, int playerIndexColumn, int numOfGoals){
        this.rows = rows;
        this.columns = columns;
        this.playerIndexRow = playerIndexRow;
        this.playerIndexColumn = playerIndexColumn;
        State.numOfGoals = numOfGoals;
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
            for(int i = 0 ; i < this.rows ; i ++){
                System.arraycopy(grid[i], 0, this.grid[i], 0, columns);
            }
            State initial = new State();
            initial.rows = grid.length;
            initial.columns = grid[0].length;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 'p') {
                        i = playerIndexRow;
                        j = playerIndexColumn;
                        break;
                    }
                }
            }
            this.states.add(initial);
    }

    public void printGrid(){
        for (int i = 0 ; i < rows ; i ++) {
            for (int j = 0 ; j < columns ; j ++){
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setPlayerIndexRow(int playerIndexRow) {
        this.playerIndexRow = playerIndexRow;
    }

    public int getPlayerIndexRow() {
        return playerIndexRow;
    }

    public void setPlayerIndexColumn(int playerIndexColumn) {
        this.playerIndexColumn = playerIndexColumn;
    }

    public int getPlayerIndexColumn() {
        return playerIndexColumn;
    }

    public static int getNumOfGoals() {
        return numOfGoals;
    }

    public static void setNumOfGoals(int numOfGoals) {
        State.numOfGoals = numOfGoals;
    }

    private State deepCopy(State oldState, State newState){
        newState.rows = oldState.rows;
        newState.columns = oldState.columns;
        newState.grid = oldState.grid;
        newState.states = new LinkedList<State>();
        newState.states.addAll(oldState.states);

        return newState;
    }

    private void moveRight(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            while (moveRightIsValid(grid, indexRow, indexColumn)){
                if(getRight(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexColumn += 1;
                    grid[indexRow][indexColumn] = 'p';
                    numOfGoals--;
                    break;
                }
                if (getRight(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(this.states.getFirst().grid);
                    System.out.println("Game Over");
                    return;
                }
                grid[indexRow][indexColumn] = ' ';
                indexColumn += 1;
                grid[indexRow][indexColumn] = 'p';
            }
        }
    }

    private void moveLeft(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            while (moveLeftIsValid(grid, indexRow, indexColumn)){
                if(getLeft(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexColumn -= 1;
                    grid[indexRow][indexColumn] = 'p';
                    numOfGoals--;
                    break;
                }
                if (getLeft(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(this.states.getFirst().grid);
                    System.out.println("Game Over");
                    return;
                }
                grid[indexRow][indexColumn] = ' ';
                indexColumn -= 1;
                grid[indexRow][indexColumn] = 'p';
            }
        }
    }

    private void moveUp(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            while (moveUpIsValid(grid, indexRow, indexColumn)){
                if(getUp(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexRow -= 1;
                    grid[indexRow][indexColumn] = 'p';
                    numOfGoals--;
                    break;
                }
                if (getUp(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(this.states.getFirst().grid);
                    System.out.println("Game Over");
                    return;
                }
                grid[indexRow][indexColumn] = ' ';
                indexRow -= 1;
                grid[indexRow][indexColumn] = 'p';
            }
        }
    }

    private void moveDown(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            while (moveDownIsValid(grid, indexRow, indexColumn)){
                if(getDown(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexRow += 1;
                    grid[indexRow][indexColumn] = 'p';
                    numOfGoals--;
                    break;
                }
                if (getDown(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(this.states.getFirst().grid);
                    System.out.println("Game Over");
                    return;
                }
                grid[indexRow][indexColumn] = ' ';
                indexRow += 1;
                grid[indexRow][indexColumn] = 'p';
            }
        }
    }

    private char getRight (char [][] grid, int indexRow, int indexColumn){
        if(this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == indexRow && j == indexColumn) {
                        return grid[i][j + 1];
                    }
                }
            }
            return 'x';
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private char getLeft (char [][] grid, int indexRow, int indexColumn){
        if(this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == indexRow && j == indexColumn) {
                        return grid[i][j-1];
                    }
                }
            }
            return 'x';
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private char getUp (char [][] grid, int indexRow, int indexColumn){
        if(this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == indexRow && j == indexColumn) {
                        return grid[i-1][j];
                    }
                }
            }
            return 'x';
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private char getDown (char [][] grid, int indexRow, int indexColumn){
        if(this.checkIndexIsValid(grid, indexRow, indexColumn)) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == indexRow && j == indexColumn) {
                        return grid[i+1][j];
                    }
                }
            }
            return 'x';
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private boolean moveRightIsValid (char [][] grid, int indexRow, int indexColumn){
        return getRight(grid, indexRow, indexColumn) != 'o';
    }

    private boolean moveLeftIsValid (char [][] grid, int indexRow, int indexColumn){
        return getLeft(grid, indexRow, indexColumn) != 'o';
    }

    private boolean moveUpIsValid (char [][] grid, int indexRow, int indexColumn){
        return getUp(grid, indexRow, indexColumn) != 'o';
    }

    private boolean moveDownIsValid (char [][] grid, int indexRow, int indexColumn){
        return getDown(grid, indexRow, indexColumn) != 'o';
    }

    private boolean checkIndexIsValid(char [][]grid, int row, int column){
        return row <= grid.length && column <= grid[0].length;
    }

    private boolean gameWon(){
        return (numOfGoals == 0);
    }


    public void play(){
        char x;
        Scanner in = new Scanner(System.in);

        System.out.println("enter W to move up");
        System.out.println("enter S to move down");
        System.out.println("enter D to move right");
        System.out.println("enter A to move Left");
        System.out.println("enter Z to exit");

        while(true){
           x = in.next().charAt(0);

           switch (x){

               case 'W' :
                   moveUp(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 'S':
                   moveDown(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 'D':
                   moveRight(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 'A':
                   moveLeft(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 'Z':
                   System.out.println("Exiting game.");
                   return;
               default:
                   System.out.println("invalid input, please check the letters again!");
           }

           if (gameWon()){
               System.out.println("congrats, you won");
               break;
           }
           else if()
        }
    }
}
