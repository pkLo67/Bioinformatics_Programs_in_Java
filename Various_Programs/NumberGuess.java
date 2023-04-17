// need the Scanner class to get user input
import java.util.Scanner;

public class NumberGuess {

    public static void main(String[] args) {

        // a. declare a final int, and assign a random number as the guessed number
        int numberForGuess = randomNumber();
        int guessNumber;
        int counter = 0;

        // b. create a Scanner to get user input
		Scanner input = new Scanner(System.in);

        System.out.println("The program randomly chose a number between 1 and 10.");
        
        // c. use a do {} while loop to prompt the user to enter an integer between 1 and 10,
        //    assign the user input to an int, and compare to the guessing number
        do {          
            System.out.println("Try to guess this number:");
            guessNumber = input.nextInt(); 
            counter ++;
        
            // d. use a if-else statement to provide hints for the guessed number
            if (guessNumber > numberForGuess) {
                System.out.println("Your entered number is larger than the guessed number.");
            } else if (numberForGuess == guessNumber) {
                if (counter == 1) {
                    System.out.println("You got it after " + counter + " attempt. The guessed number is " + numberForGuess);
                } else {
                    System.out.println("You got it after " + counter + " attempts. The guessed number is " + numberForGuess);
                }                
                break;
            } else {
                System.out.println("Your entered number is smaller than the guessed number.");
            } 
        } while (numberForGuess != guessNumber);

        
        input.close();
       
        // Here is to print your name
        System.out.println("\nThe Program's Author: Pang-Kuo Lo\n");  
    
    }

    /**
     * The method name: randomNumber
     * @return
     * 1. Use Math.random() * 10 + 1 to generate random number from 0 to 9.99999----
     * 2. Use (int) to converted the randomly generated double to the integer
     */
    public static int randomNumber() {
        return (int) (Math.random() * 10 + 1);
    }

}