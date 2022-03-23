package tictactoe;

import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int xWin = 264;
        final int oWin = 237;
        final int draw = 9;
        int xSum = 0;
        int oSum = 0;
        int xCounter = 0;
        int oCounter = 0;
        boolean gameFinished = false;
        boolean error = true;
        char player = 'X';

        String result = "";

        char[][] charArray = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };

        do {
            System.out.println("---------");
            for (int i = 0; i < charArray.length; i++) {
                System.out.print("| ");
                for (int k = 0; k < charArray[i].length; k++) {
                    System.out.print(charArray[i][k] + " ");
                    if (charArray[i][k] == 'X') {
                        xSum += charArray[i][k];
                        xCounter++;
                    } else if (charArray[i][k] == 'O') {
                        oSum += charArray[i][k];
                        oCounter++;
                    }
                }
                System.out.println("|");

                if (i == charArray.length - 1) {
                    System.out.println("---------");
                }
                if (xSum == xWin) {
                    result = "X wins";
                    gameFinished = true;
                } else if (oSum == oWin) {
                    result = "O wins";
                    gameFinished = true;
                }
                xSum = 0;
                oSum = 0;
            }

            if (xCounter + oCounter > 0) {
                char chCentral = charArray[1][1];
                if (chCentral != ' ' && chCentral == charArray[0][0] && chCentral == charArray[2][2] ||
                        chCentral != ' ' && chCentral == charArray[0][2] && chCentral == charArray[2][0]) {
                    result = chCentral == 'X' ? "X wins" : "O wins";
                    gameFinished = true;
                } else if (charArray[0][0] != ' ' &&
                        charArray[0][0] == charArray[1][0] && charArray[0][0] == charArray[2][0]) {
                    result = charArray[0][0] == 'X' ? "X wins" : "O wins";
                    gameFinished = true;
                } else if (charArray[0][1] != ' ' &&
                        charArray[0][1] == charArray[1][1] && charArray[0][1] == charArray[2][1]) {
                    result = charArray[0][1] == 'X' ? "X wins" : "O wins";
                    gameFinished = true;
                } else if (charArray[0][2] != ' ' &&
                        charArray[0][2] == charArray[1][2] && charArray[0][2] == charArray[2][2]) {
                    result = charArray[0][2] == 'X' ? "X wins" : "O wins";
                    gameFinished = true;
                } else if (draw == xCounter + oCounter) {
                    result = "Draw";
                    gameFinished = true;
                } else if (Math.abs(xCounter - oCounter) > 1) {
                    result = "Impossible";
                    gameFinished = true;
                }
            }
            xCounter = 0;
            oCounter = 0;

            if (gameFinished) {
                System.out.println(result);
            } else {
                while (error) {
                    System.out.print("Enter the coordinates: ");
                    String intCoordinate1 = scanner.next();
                    String intCoordinate2 = scanner.next();
                    try {
                        int coordinate1 = Integer.parseInt(intCoordinate1);
                        int coordinate2 = Integer.parseInt(intCoordinate2);
                        if (coordinate1 > 3 || coordinate2 > 3) {
                            System.out.println("Coordinates should be from 1 to 3!");
                        } else if (charArray[coordinate1 - 1][coordinate2 - 1] != ' ') {
                            System.out.println("This cell is occupied! Choose another one!");
                        } else {
                            charArray[coordinate1 - 1][coordinate2 - 1] = player;
                            switch (player) {
                                case 'X':
                                    player = 'O';
                                    break;
                                case 'O':
                                    player = 'X';
                                    break;
                            }
                            error = false;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You should enter numbers!");
                    }
                }
                error = true;
            }
        } while (!gameFinished);
    }
}
