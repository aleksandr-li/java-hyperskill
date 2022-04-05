package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {

    static Scanner sc = new Scanner(System.in);
    static int rows   = 0;
    static int seats  = 0;
    static String[][] strArray;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows: ");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seats = sc.nextInt();

        strArray = new String[rows + 1][seats + 1];
        for (int i = 0; i < strArray.length; i++) {
            for (int k = 0; k < strArray[i].length; k++) {
                if (i == 0 && k == 0) {
                        strArray[i][k] = " ";
                    } else if (i == 0 && k != 0) {
                        strArray[i][k] = Integer.toString(k);
                    } else if (k == 0 && i != 0) {
                        strArray[i][k] = Integer.toString(i);
                    } else {
                        strArray[i][k] = "S";
                }
            }
        }

        menu();
    }

    static boolean exit  = false;

    public static void menu() {
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int inputMenu = sc.nextInt();
            switch (inputMenu) {
                case 1: showTheSeats();
                    break;
                case 2: buyTicket();
                    break;
                case 3: statistics();
                    break;
                case 0: exit = true;
                    break;
            }
        } while (!exit);
    }

    public static void showTheSeats() {
        System.out.println("Cinema:");
        for (String[] eachRow : strArray) {
            for (String eachSeat : eachRow) {
                System.out.printf("%s ", eachSeat);
            }
            System.out.println();
        }
        System.out.println();
    }

    static final int smallRoom = 60;
    static final int price10 = 10;
    static final int price8 = 8;
    static int ticketPrice = 0;
    static int ticketCounter = 0;
    static int currentIncome = 0;

    public static void buyTicket() {
        System.out.println("Enter a row number:");
        int numRow = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int numSeat = sc.nextInt();

        if (numRow > 9 || numSeat > 9) {
            System.out.println("Wrong input!");
            System.out.println();
            buyTicket();
            return;
        } else if (strArray[numRow][numSeat].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            buyTicket();
            return;
        }

        if (seats * rows > smallRoom) {
            int half1 = rows / 2;
            ticketPrice = numRow > half1 ? price8 : price10;
        }else {
            ticketPrice = price10;
        }
        System.out.printf("Ticket price: $%d%n", ticketPrice);
        System.out.println();
        strArray[numRow][numSeat] = "B";
        ticketCounter++;
        currentIncome = currentIncome + ticketPrice;
        calculatePercentage();
    }

    static double percentage = 0.00;

    public static void calculatePercentage() {
        double numSeats = seats * rows;
        percentage = (100 / numSeats) * ticketCounter;
    }

    static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static void statistics() {
        System.out.println("Number of purchased tickets: " + ticketCounter);
        System.out.println("Percentage: " + decimalFormat.format(percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome());
        System.out.println();
    }

    public static int totalIncome() {
        int income = 0;
        if (seats * rows > smallRoom) {
            int half1 = rows / 2;
            int half2 = rows % 2 == 0 ? rows / 2 : (rows / 2) + 1;

            income = (half1 * seats * price10) + (half2 * seats * price8);
        }else {
            income = seats * rows * price10;
        }
        return income;
    }
}