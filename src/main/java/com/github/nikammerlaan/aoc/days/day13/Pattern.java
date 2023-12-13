package com.github.nikammerlaan.aoc.days.day13;

public record Pattern(
    char[][] rows,
    char[][] cols
) {

    public int getScore(int targetMistakes) {
        var result = 0;

        for(int i = 1; i < cols.length; i++) {
            if(isVerticalReflection(i, targetMistakes)) {
                result += i;
                break;
            }
        }

        for(int i = 1; i < rows.length; i++) {
            if(isHorizontalReflection(i, targetMistakes)) {
                result += 100 * i;
                break;
            }
        }

        return result;
    }

    private boolean isVerticalReflection(int pivot, int targetMistakes) {
        return isReflection(cols, pivot, targetMistakes);

    }

    private boolean isHorizontalReflection(int pivot, int targetMistakes) {
        return isReflection(rows, pivot, targetMistakes);
    }

    private boolean isReflection(char[][] grid, int pivot, int targetMistakes) {
        int mistakes = 0;
        for(int i = 0; pivot + i < grid.length && pivot - i - 1 >= 0 && mistakes <= targetMistakes; i++) {
            var up = grid[pivot - i - 1];
            var down = grid[pivot + i];
            mistakes += getMistakes(up, down);
        }
        return mistakes == targetMistakes;
    }

    private int getMistakes(char[] a, char[] b) {
        int differences = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) {
                differences++;
            }
        }
        return differences;
    }

    public static Pattern fromGrid(char[][] grid) {
        var cols = new char[grid[0].length][grid.length];
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[x].length; y++) {
                cols[y][x] = grid[x][y];
            }
        }

        return new Pattern(grid, cols);
    }

}
