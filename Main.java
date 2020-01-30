package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        Matrix mat = new Matrix();
        mat.printMatrix();
        while (true) {
            do {
                System.out.println("Enter the coordinates: ");
                line = sc.nextLine();
            } while (!mat.setCoordinates(line));
            mat.printMatrix();
            if (mat.gameIsFinished())
                return;
        }
    }
}

class Matrix {
    private int[][] matrix;
    static boolean type = true;

    {
        matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = ' ';
            }
        }
    }

    public void printMatrix() {
        System.out.println("---------");
        System.out.print("|");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + (char)matrix[0][i]);
        }
        System.out.println(" |");
        System.out.print("|");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + (char)matrix[1][i]);
        }
        System.out.println(" |");
        System.out.print("|");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + (char)matrix[2][i]);
        }
        System.out.println(" |");
        System.out.println("---------");
    }

    public boolean gameIsFinished() {
        if (Math.abs(countX() - countO()) > 1 || (checkSolutionO() && checkSolutionX())) {
            System.out.println("Impossible");
            return true;
        } else if (checkSolutionX()) {
            System.out.println("X wins");
            return true;
        } else if (checkSolutionO()) {
            System.out.println("O wins");
            return true;
        } else if (countO() + countX() == 9) {
            System.out.println("Draw");
            return true;
        } else {
            return false;
        }
    }

    public boolean setCoordinates(String line) {
        int x;
        int y;
        try {
            String[] arr = line.split(" ");
            if (arr.length != 2) {
                System.out.println("You should enter two numbers!");
                return false;
            }
            x = Integer.parseInt(arr[0]);
            y = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (matrix[3 - y][x - 1] == 'X' || matrix[3 - y][x - 1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        if (type) {
            matrix[3 - y][x - 1] = 'X';
            type = false;
        } else {
            matrix[3 - y][x - 1] = 'O';
            type = true;
        }
        return true;
    }

    private boolean checkSolutionX() {
        boolean solutionX = false;
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0] == 'X' && matrix[i][1] == 'X' && matrix[i][2] == 'X') {
                solutionX = true;
                break;
            }
            if (matrix[0][i] == 'X' && matrix[1][i] == 'X' && matrix[2][i] == 'X') {
                solutionX = true;
                break;
            }
        }
        if (matrix[0][0] == 'X' && matrix[1][1] == 'X' && matrix[2][2] == 'X')
            solutionX = true;
        if (matrix[2][0] == 'X' && matrix[1][1] == 'X' && matrix[0][2] == 'X')
            solutionX = true;
        return solutionX;
    }

    private boolean checkSolutionO() {
        boolean solutionO = false;
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0] == 'O' && matrix[i][1] == 'O' && matrix[i][2] == 'O') {
                solutionO = true;
                break;
            }
            if (matrix[0][i] == 'O' && matrix[1][i] == 'O' && matrix[2][i] == 'O') {
                solutionO = true;
                break;
            }
        }
        if (matrix[0][0] == 'O' && matrix[1][1] == 'O' && matrix[2][2] == 'O')
            solutionO = true;
        if (matrix[2][0] == 'O' && matrix[1][1] == 'O' && matrix[0][2] == 'O')
            solutionO = true;
        return solutionO;
    }

    private int countX() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 'X')
                    count++;
            }
        }
        return count;
    }

    private int countO() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 'O')
                    count++;
            }
        }
        return count;
    }
}
