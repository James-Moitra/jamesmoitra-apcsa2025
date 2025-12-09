package gol;

import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y) {
        board = new int[x][y];
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (x + i >= 0 && x + i < board.length && y + j >= 0 && y + j < board[0].length) {
                    board[x + i][y + j] = data[i][j];
                }
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    // Step the simulation forward one turn.
    public void step() {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int neighbors = countNeighbors(x, y);
                if (board[x][y] == 1 && (neighbors < 2 || neighbors > 3)) {
                    newBoard[x][y] = 0;
                } else if (board[x][y] == 0 && neighbors == 3) {
                    newBoard[x][y] = 1;
                } else {
                    newBoard[x][y] = board[x][y];
                }
            }
        }
        board = newBoard;
        print();
    }

    public int countNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                count += get(x + i, y + j);
            }j7
        }
        return count;
    }

    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.

    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit = board[0].length;
        return board[(x + xLimit) % xLimit][(y + yLimit) % yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get() {
        return board;
    }

    // Test helper to print the current state
    public void print() {
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == 1) {
                    System.out.print("⬛");
                } else {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}