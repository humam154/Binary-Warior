import java.util.*;

public class State {

    public int rows,columns;
    private char [][] grid;
    private static int numOfGoals;

    public State(){
    }
    public State (int rows, int columns, int numOfGoals){
        this.rows = rows;
        this.columns = columns;
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
        this.grid = new char[grid.length][grid[0].length];
            for (int i = 0 ; i < grid.length ; i ++){
                System.arraycopy(grid[i], 0, this.grid[i], 0, grid[0].length);
            }

    }

    public void printGrid(){
        for (char[] chars : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(chars[j] + "\t");
            }
            System.out.println();
        }
    }

    public static int getNumOfGoals() {
        return numOfGoals;
    }

    public static void setNumOfGoals(int numOfGoals) {
        State.numOfGoals = numOfGoals;
    }

    private State deepCopy(){
        State copy = new State();
        copy.setRows(rows);
        copy.setColumns(columns);
        copy.setGrid(grid);
        copy.setNumOfGoals(numOfGoals);
        return copy;
    }

    private char[][] copyArrays(char [][] oldArray, char[][] newArray){
        for(int i = 0 ; i < oldArray.length ; i ++){
            for (int j = 0 ; j < oldArray[0].length ; j ++){
                newArray[i][j] = oldArray[i][j];
            }
        }
        return newArray;
    }

    public State moveRight(){
        State copy = deepCopy();
        int numOfRows =copy.grid.length;
        int numOfColumns = copy.grid[0].length;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (j < numOfColumns - 1 && copy.grid[i][j+1] == ' ') {
                        copy.grid[i][j + 1] = 'p';
                        copy.grid[i][j] = ' ';
                        return copy;
                    } else if (j < numOfColumns - 1 && grid[i][j+1] == 'g') {
                        copy.grid[i][j+1] = 'p';
                        copy.grid[i][j] = ' ';
                        numOfGoals--;
                        return copy;
                    }
                }
            }
        }

        return copy;
    }

    public State moveLeft(){
        State copy = deepCopy();
        int numOfRows =copy.grid.length;
        int numOfColumns = copy.grid[0].length;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (j > 0 && copy.grid[i][j - 1] == ' ') {
                        copy.grid[i][j - 1] = 'p';
                        copy.grid[i][j] = ' ';
                        return copy;
                    } else if (j > 0 && grid[i][j - 1] == 'g') {
                        copy.grid[i][j-1] = 'p';
                        copy.grid[i][j] = ' ';
                        numOfGoals--;
                        return copy;
                    }
                }
            }
        }
        return copy;
    }

    public State moveUp(){
        State copy = deepCopy();
        int numOfRows =copy.grid.length;
        int numOfColumns = copy.grid[0].length;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (i > 0 && copy.grid[i-1][j] == ' ') {
                        copy.grid[i-1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        return copy;
                    } else if (i > 0 && grid[i-1][j] == 'g') {
                        copy.grid[i-1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        numOfGoals--;
                        return copy;
                    }
                }
            }
        }
        return copy;
    }

    public State moveDown(){
        State copy = deepCopy();
        int numOfRows =copy.grid.length;
        int numOfColumns = copy.grid[0].length;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (i < numOfRows - 1 && copy.grid[i+1][j] == ' ') {
                        copy.grid[i+1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        return  copy;
                    } else if (i < numOfRows -1  && grid[i+1][j] == 'g') {
                        copy.grid[i+1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        numOfGoals--;
                        return copy;
                    }
                }
            }
        }
        return copy;
    }

    private boolean gameWon(){
        return (numOfGoals == 0);
    }


    public List<State> getNextStates (){
        LinkedList<State> states = new LinkedList<State>();

        states.add(moveRight());
        states.add(moveLeft());
        states.add(moveUp());
        states.add(moveDown());

        return states;
    }

    public void play(){
        char x;
        Scanner in = new Scanner(System.in);

        System.out.println("enter W to move up");
        System.out.println("enter S to move down");
        System.out.println("enter D to move right");
        System.out.println("enter A to move Left");
        System.out.println("enter Z to exit");

        State current = this;
        var init = copyArrays(current.grid, new char[current.grid.length][current.grid[0].length]);
        this.printGrid();
        while(true){
           x = in.next().charAt(0);
           switch (x){

               case 'w' :
                   current = current.moveUp();
                   break;
               case 's':
                   current = current.moveDown();
                   break;
               case 'd':
                   current = current.moveRight();
                   break;
               case 'a':
                   current = current.moveLeft();
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

           current.printGrid();
        }
    }

    public void DFS(){
        var stack = new Stack<State>();
        HashMap<State, Boolean> visited = new HashMap<>();

        int moves = 0;
        var current = deepCopy();
        stack.push(current);
        visited.put(current, true);

        while(!stack.isEmpty()){
            moves++;
            var state = stack.pop();
            if(state.gameWon()){
                System.out.println("Game Won "  + " moves took=" + moves);
                break;
            }
            var list = state.getNextStates();

            for (State value : list){
                if(!visited.containsKey(value)){
                    visited.put(value, true);
                    stack.push(value);
                }
            }
        }
    }

    public void BFS() {
        Queue<State> queue = new LinkedList<>();
        HashMap<State, Boolean> visited = new HashMap<>();
        int moves = 0;
        var current = deepCopy();
        queue.add(current);
        visited.put(current, true);

        while (!queue.isEmpty()) {
            moves++;
            var state = queue.remove();

            if (state.gameWon()) {
                System.out.println("Game Won "  + " moves took=" + moves);
                break;
            }

            var list = state.getNextStates();

            for (State value : list){
                if(!visited.containsKey(value)){
                    visited.put(value, true);
                    queue.add(value);
                }
            }
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, columns, Arrays.deepHashCode(grid));
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        State other = (State) obj;
        return  rows == other.rows &&
                columns == other.columns &&
                Arrays.deepEquals(grid, other.grid);
    }
}
