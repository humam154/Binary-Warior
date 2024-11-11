import java.util.*;

public class State {

    private int rows,columns;
    private char [][] grid;
    private int playerIndexRow, playerIndexColumn;
    private static int numOfGoals;
    private char [][] initialGrid;

    public State(){

    }
    public State (int rows, int columns, int playerIndexRow, int playerIndexColumn, int numOfGoals){
        this.rows = rows;
        this.columns = columns;
        this.playerIndexRow = playerIndexRow;
        this.playerIndexColumn = playerIndexColumn;
        State.numOfGoals = numOfGoals;
        this.grid = new char[rows][columns];
        this.initialGrid = new char[rows][columns];
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

            for (int i = 0 ; i < this.rows ; i ++){
                System.arraycopy(grid[i], 0, this.initialGrid[i], 0, this.columns);
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

    public void printInitGrid(){
        for (int i = 0 ; i < rows ; i ++) {
            for (int j = 0 ; j < columns ; j ++){
                System.out.print(this.initialGrid[i][j] + "\t");
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

        return newState;
    }

    private boolean moveRight(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
                if(getRight(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexColumn += 1;
                    grid[indexRow][indexColumn] = 'p';
                    this.playerIndexRow = indexRow;
                    this.playerIndexColumn = indexColumn;
                    numOfGoals--;
                    return false;
                }
                if (getRight(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(initialGrid);
                    return true;
                }
                if(!moveRightIsValid(grid, indexRow, indexColumn)){
                    return false;
                }
                grid[indexRow][indexColumn] = ' ';
                indexColumn += 1;
                grid[indexRow][indexColumn] = 'p';
                this.playerIndexRow = indexRow;
                this.playerIndexColumn = indexColumn;
        }
        return false;
    }

    private boolean moveLeft(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
                if(getLeft(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexColumn -= 1;
                    grid[indexRow][indexColumn] = 'p';
                    this.playerIndexRow = indexRow;
                    this.playerIndexColumn = indexColumn;
                    numOfGoals--;
                    return false;
                }
                if (getLeft(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(initialGrid);
                    return true;
                }
                if (!moveLeftIsValid(grid, indexRow, indexColumn)){
                    return false;
                }
                grid[indexRow][indexColumn] = ' ';
                indexColumn -= 1;
                grid[indexRow][indexColumn] = 'p';
                this.playerIndexRow = indexRow;
                this.playerIndexColumn = indexColumn;
        }
        return false;
    }

    private boolean moveUp(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
                if(getUp(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexRow -= 1;
                    grid[indexRow][indexColumn] = 'p';
                    this.playerIndexRow = indexRow;
                    this.playerIndexColumn = indexColumn;
                    numOfGoals--;
                    return false;
                }
                if (getUp(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(initialGrid);
                    return true;
                }
                grid[indexRow][indexColumn] = ' ';
                if (!moveUpIsValid(grid, indexRow, indexColumn)){
                    return false;
                }
                if (indexRow > 0) {
                    indexRow -= 1;
                    grid[indexRow][indexColumn] = 'p';
                    this.playerIndexRow = indexRow;
                    this.playerIndexColumn = indexColumn;
                }
        }
        return false;
    }

    private boolean moveDown(char [][] grid, int indexRow, int indexColumn) {
        if (this.checkIndexIsValid(grid, indexRow, indexColumn)) {
                if(getDown(grid, indexRow, indexColumn) == 'g'){
                    grid[indexRow][indexColumn] = ' ';
                    indexRow += 1;
                    grid[indexRow][indexColumn] = 'p';
                    this.playerIndexRow = indexRow;
                    this.playerIndexColumn = indexColumn;
                    numOfGoals--;
                    return false;
                }
                if (getDown(grid, indexRow, indexColumn) == 's'){
                    this.setGrid(initialGrid);
                    return true;
                }
                if (!moveDownIsValid(grid, indexRow, indexColumn)){
                    return false;
                }
                grid[indexRow][indexColumn] = ' ';
                indexRow += 1;
                grid[indexRow][indexColumn] = 'p';
                this.playerIndexRow = indexRow;
                this.playerIndexColumn = indexColumn;
            }
        return false;
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

    public List<String> getValidMoves (char [][] grid, int indexRow, int indexColumn){
        LinkedList<String> list = new LinkedList<>();
        if(moveRightIsValid(grid, indexRow, indexColumn) && getRight(grid, indexRow, indexColumn) != 's'){
            list.add("right");
        }
        if(moveLeftIsValid(grid, indexRow, indexColumn) && getLeft(grid, indexRow, indexColumn) != 's'){
            list.add("left");
        }
        if(moveUpIsValid(grid, indexRow, indexColumn) && getUp(grid, indexRow, indexColumn) != 's'){
            list.add("up");
        }
        if(moveDownIsValid(grid, indexRow, indexColumn) && getDown(grid, indexRow, indexColumn) != 's'){
            list.add("down");
        }
        return list;
    }

    private boolean gameWon(){
        return (numOfGoals == 0);
    }

    public List<State> getNextStates(char[][] grid, int indexRow, int indexColumn) {

        var list = this.getValidMoves(grid, indexRow, indexColumn);
        var finalList = new LinkedList<State>();

        for (int i = 0 ; i <list.size() ; i ++) {
            char[][] newGrid = new char[grid.length][grid[0].length];
            for (int k = 0; k < grid.length; k++) {
                System.arraycopy(grid[k], 0, newGrid[k], 0, grid[0].length);  // Deep copy the grid
            }
            int newRow = indexRow;
            int newColumn = indexColumn;


            if (list.get(i).equals("right")) {
                this.moveRight(newGrid, newRow, newColumn);
            } else if (list.get(i).equals("left")) {
                this.moveLeft(newGrid, newRow, newColumn);
            } else if (list.get(i).equals("up")){
                this.moveUp(newGrid, newRow, newColumn);
            } else if (list.get(i).equals("down")) {
                this.moveDown(newGrid, newRow, newColumn);
            }

            var newState = new State(newGrid.length, newGrid[0].length, newRow, newColumn, numOfGoals);
            newState.setGrid(newGrid);
            finalList.add(newState);
        }

        return finalList;
    }

    public void DFS(char [][] grid, int indexRow, int indexColumn){
        var stack = new Stack<State>();
        int moves = 0;
        var stateDFS = new State(grid.length, grid[0].length, indexRow, indexColumn, numOfGoals);
        stateDFS.setGrid(initialGrid);
        stateDFS.printGrid();
        stack.push(stateDFS);


        HashMap<State, Boolean> visited = new HashMap<State, Boolean>();

        while(!stack.isEmpty()){
            moves++;
            var state = stack.pop();
            if(state.gameWon()){
                System.out.println("Game Won"  + "moves took=" + moves);
                break;
            }
            var list = state.getNextStates(grid, indexRow, indexColumn);

            for (State value : list){
                if(!visited.containsKey(value)){
                    visited.put(value, true);
                    stack.push(value);
                }
            }

        }
    }


    public void play(){
        char x;
        Scanner in = new Scanner(System.in);

        System.out.println("enter W to move up");
        System.out.println("enter S to move down");
        System.out.println("enter D to move right");
        System.out.println("enter A to move Left");
        System.out.println("enter Z to exit");

        this.printGrid();

        while(true){
           x = in.next().charAt(0);
           boolean restart = false;
           switch (x){

               case 'w' :
                   restart = moveUp(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 's':
                   restart = moveDown(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 'd':
                    restart = moveRight(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 'a':
                   restart = moveLeft(this.grid, this.playerIndexRow, this.playerIndexColumn);
                   break;
               case 'z':
                   System.out.println("Exiting game.");
                   return;
               default:
                   System.out.println("invalid input, please check the letters again!");
           }

           if (gameWon()){
               System.out.println("congrats, you won");
               break;
           }
           if (restart){
               System.out.println("game over, restarting");
               this.printInitGrid();
               continue;
           }
           printGrid();
        }
    }
}
