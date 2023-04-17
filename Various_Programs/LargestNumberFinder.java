import java.util.Scanner;

public class LargestNumberFinder {

    public static void main(String[] args) {

        // Create required variables for the program
        int counter = 0;
        int number = 0;
        String inputNumbers = "";
        int largest = 0;

        System.out.println("The program's author: Pang-Kuo Lo\n");

        // Create a Scanner object for User input
        Scanner input = new Scanner(System.in);
        System.out.println("Please input 10 integer numbers:");

        // Use the while loop to prompt the user to enter 10 integers
        while(true) {
            counter++;    // Count how many integers are provided by the user
            System.out.println("Number " + counter + ":");
            number = input.nextInt();    //prompt the user to enter an integer
            inputNumbers = inputNumbers + "  " + number;      // Include the entered number in a string variable
            largest = Math.max(largest, number);       // Use Math.max() to select the largest integer so far

            // Use the if statement to terminate the while loop after the user provides 10 integers
            if (counter == 10) {
                System.out.println("Ten integers you entered are:" + inputNumbers);
                System.out.println("The largest number in a series of 10 integers: " + largest);
                break;
            }

        }

        // Close the Scanner object to avoid the memory leak
        input.close();
    }
}