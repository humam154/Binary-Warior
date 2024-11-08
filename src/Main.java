//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char[][] grid = {
                {'s', 'o', 's', 's', 's', 's'},
                {'s', 'o', 'o', ' ', 'o', 's'},
                {'s', 'o', 'g', 'p', 'g', 's'},
                {'s', 'o', 'o', ' ', 'o', 's'},
                {'s', 'o', 'o', 'g', 'o', 's'},
                {'s', 'o', 's', 's', 's', 's'}
        };

        int rows = grid.length;
        int columns = grid[0].length;
        int playerIndexRow = 2;
        int playerIndexColumn = 3;
        int numOfGoals = 3;
        
        State state = new State( rows,  columns,  playerIndexRow,  playerIndexColumn, numOfGoals);
        state.setGrid(grid);

        state.play();
    }

}