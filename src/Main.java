//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char[][] grid = {
                {'s', 's', 'o', 's', 's', 'o', 'o', 's'},
                {'s', 'g', ' ', 'o', ' ', 'o', 'o', 's'},
                {'o', ' ', ' ', 'g', ' ', 'p', ' ', 's'},
                {'s', 'o', ' ', 'o', ' ', 'o', 'o', 's'},
                {'s', 'o', 'g', 'o', ' ', 'o', 'o', 's'},
                {'s', 's', 'o', 's', ' ', 'o', 'o', 's'}
        };

        int rows = grid.length;
        int columns = grid[0].length;
        int playerIndexRow = 2;
        int playerIndexColumn = 6;
        int numOfGoals = 0;
        for (char[] chars : grid) {
            for (int j = 0; j < columns; j++) {
                if (chars[j] == 'g')
                    numOfGoals++;
            }
        }

        State state = new State( rows,  columns,  playerIndexRow,  playerIndexColumn, numOfGoals);
        state.setGrid(grid);


        var list = state.getNextStates(grid, playerIndexRow, playerIndexColumn);

        for (int i = 0 ; i < list.size() ; i ++){
            list.get(i).printGrid();
            System.out.println();
        }


    }

}