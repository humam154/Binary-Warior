//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char[][] grid = {
                {'s', 's', 's', 's', 's'},
                {'s', 'o', ' ', 'o', 's'},
                {'s', 'g', 'p', 'g', 's'},
                {'s', 'o', 'o', 'o', 's'},
                {'s', 's', 's', 's', 's'}
        };

        int rows = grid.length;
        int columns = grid[0].length;
        int playerIndexRow = 2;
        int playerIndexColumn = 2;
        int numOfGoals = 2;

        State state = new State( rows,  columns,  playerIndexRow,  playerIndexColumn, numOfGoals);
        state.setGrid(grid);

        state.play();
    }

}