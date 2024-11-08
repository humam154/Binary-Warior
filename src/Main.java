//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char [][] grid =
                {
                        {'1', '2', '3','4'},
                        {'5','6','7','8'},
                        {'9','A','B','C'},
                        {'D','E','F','G'}
                };


        printGrid(grid);
        System.out.print(getRight(grid, 3, 3));
    }



    public static void moveRight(char [][] grid, int indexRow, int indexColumn) {
        //if (this.checkArray(grid, indexRow, indexColumn)) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == grid[indexRow][indexColumn]) {

                    }
                }
            }
        //}
    }

    private static char getRight(char [][] grid, int indexRow, int indexColumn){
        //if(this.checkArray(grid, indexRow, indexColumn)) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                    if (i == indexRow && j == indexColumn) {
                        return grid[i][j + 1];
                    }
                }
            }
            return 'x';
        //}
    }

    public static void printGrid(char [] [] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
    }
}