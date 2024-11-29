import java.util.*;

public class State {

    public int rows,columns;
    private char [][] grid;
    private int numOfGoals;
    private int cost = 0;
    private State parent;

    public State(){
    }
    public State (int rows, int columns, int numOfGoals){
        this.rows = rows;
        this.columns = columns;
        this.numOfGoals = numOfGoals;
        this.grid = new char[rows][columns];
        this.parent = new State();
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

    public int getNumOfGoals() {
        return this.numOfGoals;
    }

    public void setNumOfGoals(int numOfGoals) {
        this.numOfGoals = numOfGoals;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

        outerLoop:
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (j < numOfColumns - 1 && copy.grid[i][j+1] == 'g') {
                        copy.grid[i][j+1] = 'p';
                        copy.grid[i][j] = ' ';
                        copy.setNumOfGoals(copy.getNumOfGoals() - 1);
                        break outerLoop;
                    } else if (j < numOfColumns - 1 && grid[i][j+1] == ' ') {
                        copy.grid[i][j + 1] = 'p';
                        copy.grid[i][j] = ' ';
                        break outerLoop;
                    }
                }
            }
        }
        copy.setCost(copy.getCost() + 3);
        return copy;
    }
    public State moveLeft(){
        State copy = deepCopy();
        int numOfRows =copy.grid.length;
        int numOfColumns = copy.grid[0].length;

        outerLoop:
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (j > 0 && copy.grid[i][j - 1] == 'g') {
                        copy.grid[i][j-1] = 'p';
                        copy.grid[i][j] = ' ';
                        copy.setNumOfGoals(copy.getNumOfGoals() - 1);
                        break outerLoop;
                    } else if (j > 0 && grid[i][j - 1] == ' ') {
                        copy.grid[i][j - 1] = 'p';
                        copy.grid[i][j] = ' ';
                        break outerLoop;
                    }
                }
            }
        }
        copy.setCost(copy.getCost() + 5);
        return copy;
    }
    public State moveUp(){
        State copy = deepCopy();
        int numOfRows =copy.grid.length;
        int numOfColumns = copy.grid[0].length;

        outerLoop:
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (i > 0 && copy.grid[i-1][j] == 'g') {
                        copy.grid[i-1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        copy.setNumOfGoals(copy.getNumOfGoals() - 1);
                        break outerLoop;
                    } else if (i > 0 && grid[i-1][j] == ' ') {
                        copy.grid[i-1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        break outerLoop;
                    }
                }
            }
        }
        copy.setCost(copy.getCost() + 1);
        return copy;
    }
    public State moveDown(){
        State copy = deepCopy();

        int numOfRows =copy.grid.length;
        int numOfColumns = copy.grid[0].length;

        outerLoop:
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (copy.grid[i][j] == 'p') {
                    if (i < numOfRows - 1 && copy.grid[i+1][j] == 'g') {
                        copy.grid[i+1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        copy.setNumOfGoals(copy.getNumOfGoals() - 1);
                        break outerLoop;
                    } else if (i < numOfRows - 1  && grid[i+1][j] == ' ') {
                        copy.grid[i+1][j] = 'p';
                        copy.grid[i][j] = ' ';
                        break outerLoop;
                    }
                }
            }
        }
        copy.setCost(copy.getCost() + 2);
        return copy;
    }

    private boolean gameWon(){
        return (numOfGoals == 0);
    }

    public char getRight(char ch){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j + 1 < columns && grid[i][j] == ch) {
                    return grid[i][j+1];
                }
            }
        }
        return 'o';
    }
    public char getLeft(char ch){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j -1 >=0 && grid[i][j] == ch) {
                    return grid[i][j-1];
                }
            }
        }
        return 'o';
    }
    public char getUp(char ch){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i - 1 >= 0 && grid[i][j] == ch) {
                    return grid[i-1][j];
                }
            }
        }
        return 'o';
    }
    public char getDown(char ch){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == ch && i + 1 < rows) {
                    return grid[i+1][j];
                }
            }
        }
        return 'o';
    }

    public List<State> getNextStates (){
        LinkedList<State> states = new LinkedList<>();

        if(this.getRight('p') != 'o' && this.getRight('p') != 's') {
            var nextState = moveRight();
            nextState.parent = this;
            nextState.setCost(this.getCost() + 3);
            states.add(nextState);
        }
        if(this.getLeft('p') != 'o' && this.getLeft('p') != 's') {
            var nextState = moveLeft();
            nextState.parent = this;
            nextState.setCost(this.getCost() + 5);
            states.add(nextState);
        }
        if(this.getUp('p') != 'o' && this.getUp('p') != 's') {
            var nextState = moveUp();
            nextState.parent = this;
            nextState.setCost(this.getCost() + 1);
            states.add(nextState);
        }
        if(this.getDown('p') != 'o' && this.getDown('p') != 's') {
            var nextState = moveDown();
            nextState.parent = this;
            nextState.setCost(this.getCost() + 2);
            states.add(nextState);
        }

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
        var visitedGrids = new LinkedList<State>();
        HashMap<State, Integer> depthMap = new HashMap<>();


        int moves = 0;
        var current = deepCopy();
        stack.push(current);
        visited.put(current, true);
        depthMap.put(current, 0);
        visitedGrids.add(current);


        while(!stack.isEmpty()){
            current = stack.pop();
            moves = depthMap.get(current);

            if(current.gameWon()){
                System.out.printf("Game Won moves took=%d%n", moves);
                System.out.println();
                System.out.println("visited grids:");
                for (State s : visitedGrids){
                    s.printGrid();
                    System.out.println("----------------");
                }
                System.out.println();
                System.out.println();
                current.printPath();
                break;
            }
            var list = current.getNextStates();

            for (State value : list){
                if(!visited.containsKey(value)){
                    visited.put(value, true);
                    visitedGrids.add(value);
                    stack.push(value);
                    depthMap.put(value, moves + 1);
                }
            }
        }
        System.out.println("can't be solved");
    }
    public void BFS() {
        Queue<State> queue = new LinkedList<>();
        HashMap<State, Boolean> visited = new HashMap<>();
        var visitedGrids = new LinkedList<State>();
        HashMap<State, Integer> depthMap = new HashMap<>();

        int moves = 0;
        var current = deepCopy();
        queue.add(current);
        visited.put(current, true);
        depthMap.put(current, 0);
        visitedGrids.add(current);

        while (!queue.isEmpty()) {
            current = queue.remove();
            moves = depthMap.get(current);

            if (current.gameWon()) {
                System.out.printf("Game Won moves took=%d%n", moves);
                System.out.println();
                System.out.println("Visited grids:");
                for (State s : visitedGrids) {
                    s.printGrid();
                    System.out.println("----------------");
                }
                System.out.println();
                System.out.println();
                current.printPath();
                break;
            }

            var list = current.getNextStates();

            for (State value : list) {
                if (!visited.containsKey(value)) {
                    visited.put(value, true);
                    visitedGrids.add(value);
                    queue.add(value);
                    depthMap.put(value, moves + 1);
                }
            }
        }
        System.out.println("can't be solved");
    }
    public void UCS(){
        PriorityQueue<State> queue = new PriorityQueue<>(new Comparator<State>(){
            @Override
            public int compare(State o1, State o2) {
                return Integer.min(o1.getCost(), o2.getCost());
            }
        });
        HashMap<State, Boolean> visited = new HashMap<>();
        var visitedGrids = new LinkedList<State>();

        var current = deepCopy();
        current.setCost(0);
        queue.add(current);
        visited.put(current, true);
        visitedGrids.add(current);

        while (!queue.isEmpty()) {
            current = queue.remove();

            if(current.gameWon()){
                System.out.println("Game won, the cost is:" + current.getCost());
                System.out.println();
                System.out.println("visited grids:");
                for (State s : visitedGrids){
                    s.printGrid();
                    System.out.println("----------------");
                }
                System.out.println();
                System.out.println();
                current.printPath();
                break;
            }

            var list = current.getNextStates();

            for(State value : list){
                if(!visited.containsKey(value)){
                    visited.put(value, true);
                    visitedGrids.add(value);
                    queue.add(value);
                }
            }
        }
        System.out.println("can't be solved");
    }
    public void hillClimbing(){
        PriorityQueue<State> queue = new PriorityQueue<>(
                new Comparator<State>() {
                    @Override
                    public int compare(State o1, State o2) {
                        return Integer.min(o1.mDist('p', 'g'), o2.mDist('p', 'g'));
                    }
                }
        );
        HashMap<State, Boolean> visited = new HashMap<>();
        var visitedGrids = new LinkedList<State>();


        var current = deepCopy();
        queue.add(current);
        visited.put(current, true);
        visitedGrids.add(current);

        while (!queue.isEmpty()){
            current = queue.remove();

            if(current.gameWon()){
                System.out.println("distance=" + mDist('p', 'g'));
                System.out.println();
                System.out.println("Visited grids:");
                for (State s : visitedGrids) {
                    s.printGrid();
                    System.out.println("----------------");
                }
                System.out.println();
                System.out.println();
                current.printPath();
                break;
            }

            var list = current.getNextStates();

            for (State value : list){
                if(!visited.containsKey(value)){
                    visited.put(value, true);
                    queue.add(value);
                    visitedGrids.add(value);
                }
            }
        }
        System.out.println("can't be solved");
    }

    private int mDist(char x, char y){
        int Xx = 0, Xy = 0, Yx = 0, Yy = 0;
        outerLoop1:
        for (int i = 0 ; i < this.grid.length ; i++){
            for (int j = 0 ; j < this.grid[0].length ; j++){
                if (this.grid[i][j] == x){
                    Xx = i;
                    Yx = j;
                    break outerLoop1;
                }
            }
        }
        outerLoop2:
        for (int i = 0 ; i < this.grid.length ; i++){
            for (int j = 0 ; j < this.grid[0].length ; j++){
                if (this.grid[i][j] == y){
                    Xy = i;
                    Yy = j;
                    break outerLoop2;
                }
            }
        }
        return Math.abs(Xx - Xy) + Math.abs(Yx - Yy);
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

    public void printPath(){
        var path = new LinkedList<State>();

        var current = this;

        while (current != null){
            path.addFirst(current);
            current = current.parent;
        }

        System.out.println("path to goal:");
        for (State state : path){
            state.printGrid();
            System.out.println("----------------");
        }
    }

}