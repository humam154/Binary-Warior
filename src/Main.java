//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char[][] grid = {
                {'s', 'o', 'o', 'o', 'o', 's'},
                {'s', 'g', ' ', ' ', 'g', 's'},
                {'s', ' ', 'g', ' ', ' ', 's'},
                {'s', ' ', ' ', ' ', ' ', 's'},
                {'s', ' ', 'p', ' ', 'g', 's'},
                {'s', 'o', 'o', 'o', 'o', 's'}
        };

        int rows = grid.length;
        int columns = grid[0].length;
        int numOfGoals = 0;
        for (char[] chars : grid) {
            for (int j = 0; j < columns; j++) {
                if (chars[j] == 'g')
                    numOfGoals++;
            }
        }


        State state = new State( rows,  columns, numOfGoals);
        state.setGrid(grid);

        //state.play();
        state.BFS();

    }

}