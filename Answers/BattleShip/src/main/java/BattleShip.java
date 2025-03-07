import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class BattleShip {


    static final int GRID_SIZE = 10;


    static char[][] player1Grid = new char[GRID_SIZE][GRID_SIZE];


    static char[][] player2Grid = new char[GRID_SIZE][GRID_SIZE];


    static char[][] player1TrackingGrid = new char[GRID_SIZE][GRID_SIZE];


    static char[][] player2TrackingGrid = new char[GRID_SIZE][GRID_SIZE];


    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        initializeGrid(player1Grid);
        initializeGrid(player2Grid);
        initializeGrid(player1TrackingGrid);
        initializeGrid(player2TrackingGrid);


        placeShips(player1Grid);
        placeShips(player2Grid);


        boolean player1Turn = true;


        while (!isGameOver()) {
            if (player1Turn) {
                System.out.println("Player 1's turn:");
                printGrid(player1TrackingGrid);
                playerTurn(player2Grid, player1TrackingGrid);
            } else {
                System.out.println("Player 2's turn:");
                printGrid(player2TrackingGrid);
                playerTurn(player1Grid, player2TrackingGrid);
            }
            player1Turn = !player1Turn;
        }

        System.out.println("Game Over!");
    }


    static void initializeGrid(char[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '~';
            }
        }


    }


    static void placeShips(char[][] grid) {
        Random rand = new Random();
        int i = 2;
        while (i < 6) {
            int row = rand.nextInt(GRID_SIZE);
            int col = rand.nextInt(GRID_SIZE);
            int size = i;
            boolean horizontal = rand.nextBoolean();
            boolean check = canPlaceShip(grid, row, col, size, horizontal);
            if (check == true && horizontal == true) {
                for (int k = 0; k < size; k++) {
                    grid[row + k][col] = '*';

                }
                i++;
            } else if (check == true && horizontal == false) {
                for (int k = 0; k < size; k++) {
                    grid[row][col + k] = '*';
                }
                i++;
            } else {
                i = size;

            }
        }

    }


    static boolean canPlaceShip(char[][] grid, int row, int col, int size, boolean horizontal) {
        if (horizontal == true) {
            if (row + size <= GRID_SIZE && col < GRID_SIZE) {
                for (int k = 0; k < size; k++) {
                    if (grid[row + k][col] == '*') {
                        return false;
                    }

                }
                return true;
            } else {
                return false;
            }

        } else {
            if (row < GRID_SIZE && col + size <= GRID_SIZE) {
                for (int k = 0; k < size; k++) {
                    if (grid[row][col + k] == '*') {
                        return false;
                    }

                }
                return true;
            } else {
                return false;
            }
        }

    }

    static void playerTurn(char[][] opponentGrid, char[][] trackingGrid) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean check = isValidInput(input);
        if (check == true) {
            char colchar = input.charAt(0);
            int row = input.charAt(1) - '0';
            int col = 0;
            switch (colchar) {
                case 'A':
                    col = 0;
                    break;
                case 'B':
                    col = 1;
                    break;
                case 'C':
                    col = 2;
                    break;
                case 'D':
                    col = 3;
                    break;
                case 'E':
                    col = 4;
                    break;
                case 'F':
                    col = 5;
                    break;
                case 'G':
                    col = 6;
                    break;
                case 'H':
                    col = 7;
                    break;
                case 'I':
                    col = 8;
                    break;
                case 'J':
                    col = 9;
                    break;
            }
            if (opponentGrid[row][col] == '*') {
                System.out.println("Hit!");
                opponentGrid[row][col] = 'X';
                trackingGrid[row][col] = 'X';
            } else if (opponentGrid[row][col] == 'X' || opponentGrid[row][col] == '0' ) {
                System.out.println("Repeated input!");
            } else {
                System.out.println("Miss!");
                opponentGrid[row][col] = '0';
                trackingGrid[row][col] = '0';
            }
        } else System.out.println("Invalid input!");
    }


    static boolean isGameOver() {
        boolean check = ( allShipsSunk(player1Grid) || allShipsSunk(player2Grid) ) ;
        return check;
    }


    static boolean allShipsSunk(char[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == '*') {
                    return false;
                }
            }
        }

        return true;
    }


    static boolean isValidInput(String input) {
        if (input.length() == 2) {
            if ((input.charAt(0) >= 'A' && input.charAt(0) <= 'J') && (input.charAt(1) >= '0' && input.charAt(1) <= '9')) {
                return true;
            } else return false;
        } else return false;
    }


    static void printGrid(char[][] grid) {
        System.out.println("$ " + "A" + " " + "B" + " " + "C" + " " + "D" + " " + "E" + " " + "F" + " " + "G" + " " + "H" + " " + "I" + " " + "J");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print(0 + i + " ");
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 'X') {
                    System.out.print("\u001B[31m" + grid[i][j] + " \u001B[0m");
                }else if (grid[i][j] == '0') {
                    System.out.print("\033[0;97m" + grid[i][j] + " \u001B[0m");}
                else{
                System.out.print(grid[i][j] + " ");}
            }

            System.out.println();
        }

    }
}
