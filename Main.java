package tictactoe;
import java.util.Scanner;

public class Main {

    static void drawField(char[][] mass) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(mass[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';
        boolean win = false;
        char winLetter = 0;
        int emptyCells = 9;
        char[][] mass = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mass[i][j] = ' ';
            }
        }
        drawField(mass);
        while(!win) {
            System.out.println("Enter the coordinates:");
            char first = scanner.next().charAt(0);
            char second = scanner.next().charAt(0);
            if (!Character.isDigit(first) || !Character.isDigit(second)) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int x = Character.getNumericValue(first);
            int y = Character.getNumericValue(second);
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else {
                if (mass[3 - y][x - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                switch (currentPlayer) {
                    case 'X':
                        mass[3 - y][x - 1] = currentPlayer;
                        currentPlayer = 'O';
                        break;
                    case 'O':
                        mass[3 - y][x - 1] = currentPlayer;
                        currentPlayer = 'X';
                        break;
                }
                emptyCells--;
            }
            drawField(mass);
            //checking of row
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 1; j++) {
                    if (mass[i][j] == mass[i][j + 1] && mass[i][j] == mass[i][j + 2] && mass[i][j] != ' ') {
                        win = true;
                        winLetter = mass[i][j] == 'X' ? 'X' : 'O';
                    }
                }
            }
            //checking of column
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 3; j++) {
                    if (mass[i][j] == mass[i + 1][j] && mass[i][j] == mass[i + 2][j] && mass[i][j] != ' ') {
                        win = true;
                        winLetter = mass[i][j] == 'X' ? 'X' : 'O';
                    }
                }
            }
            //checking of diagonals
            if (mass[1][1] == mass[0][0] && mass[1][1] == mass[2][2] && mass[1][1] != ' '
                    || mass[1][1] == mass[0][2] && mass[1][1] == mass[2][0] && mass[1][1] != ' ') {
                win = true;
                winLetter = mass[1][1] == 'X' ? 'X' : 'O';
            }
            if (emptyCells == 0 && !win) {
                break;
            }
        }
        System.out.println(emptyCells == 0 && !win ? "Draw" :
                winLetter == 'X' ? "X wins" : "O wins");
    }
}