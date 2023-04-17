import java.util.Scanner;

public class DNA_ValidationProgram {
    
    public static void main(String[] args) {
        System.out.println("\n****The Program For Validation of a DNA Sequence****");
        System.out.println("The Program's Author: Pang-Kuo Lo\n");

        // Create a Scanner object for users to input of a DNA sequence
        Scanner input = new Scanner(System.in);

        System.out.println("Eneter a DNA sequence:");
        String dna = input.nextLine();
        dna = dna.strip();  // remove the leading and trailing spaces present in the string

        // Use the While-loop to make sure that a DNA sequence is entered
        while (dna.length() == 0) {
            System.out.println("No DNA sequence is entered.\nPlease enter a DNA sequence:");
            dna = input.nextLine();
            dna = dna.strip();
        }

        // format the input DNA sequence to upper case in case lower case letters are provided
        dna = dna.toUpperCase();

        // Initialize an object from the "DNAProgram" class
        DNAProgram DNA = new DNAProgram(dna);
        System.out.println();
        System.out.println("The DNA Validation Result:");

        // Use the if-else statement to check whether the provided DNA sequence is valid
        // Display the information of the DNA size if the input DNA sequence is valid
        // Display the input error message and the information of the identified invalid letter(s) if the input DNA sequence is invalid
        if (DNA.IsDNAValid()) {
            int dnaLength = DNA.getSize();   // Store the DNA size to a variable
            System.out.println("The DNA sequence is valid and its size is " + dnaLength + " bp.");
            System.out.println();
        } else {
            // Print the input error message if the DNA sequence is invalid
            System.out.println("The DNA sequence is not valid.");
            // Use the invalidDNAChecker method to identify the invalid letter(s) in the parsed DNA sequence
            invalidDNAChecker(dna);
            System.out.println("The DNA sequence can only contain 'GATC'.");
            System.out.println();
        }

        input.close();
           
    }

    /**
     * The Method Name: invalidDNAChecker
     * @param dna : String
     * This Method can perform the following tasks:
     *  1. Use the for loop statement to check every letter in the DNA sequence
     *  2. Any identified invalid letter(s) and the position(s) in the provided DNA sequence will be printed to the console
     */
    public static void invalidDNAChecker(String dna) {

        for(int i = 0; i < dna.length(); i++) {
            char nt = dna.charAt(i);
            if (nt=='A' || nt=='T' || nt=='G' || nt=='C') {
                continue;
            } else {
                System.out.println("The invalid letter at the position of " + (i+1) + " is " + nt + ".");
            }
        }

    }
}
