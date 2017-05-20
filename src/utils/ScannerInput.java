package utils;

import java.util.Scanner;

/**
 * Created by Patrick on 11/05/2017.
 * Class ScannerInput is a utility class with static methods to read the user's input
 */
public class ScannerInput {

    /**
     * Read the user's input and ensures that it is an integer
     * @param prompt The message to prompt the input in the menu controller
     * @return The user's input, when valid
     */
    @SuppressWarnings("resource")
    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt( input.next() );
            }
            catch (NumberFormatException e) {//catches any non-integer input, loop will run again
                System.err.println("\tEnter a valid number please.");
            }
        }
        while (true);
    }

    @SuppressWarnings("resource")
    public static double validNextDouble(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Double.parseDouble( input.next() );
            }
            catch (NumberFormatException e) {//catches any non-numeric input, loop will run again
                System.err.println("\tEnter a number please.");
            }
        }
        while (true);
    }

    /**
     * Read's the users input as a String
     * @param prompt The message to prompt the input in the menu controller
     * @return The user's input
     */
    @SuppressWarnings("resource")
    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }
}