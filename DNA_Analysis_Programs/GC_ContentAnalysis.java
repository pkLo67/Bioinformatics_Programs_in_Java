import java.util.Scanner;

public class GC_ContentAnalysis {
    public static void main(String[] args) {
        System.out.println("\n****The Program For GC Content Analysis of a DNA Sequence****");
        System.out.println("The Program's Author: Pang-Kuo Lo\n");

        // Create a Scanner object for the user input of a DNA sequence
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
        System.out.println();

        // Initialize an object from the "DNAProgram" class
        DNAProgram DNA = new DNAProgram(dna);

        // Use the if-else statement to check whether the provided DNA sequence is valid
        // Perform GC content analysis if the input DNA sequence is valid
        if (DNA.IsDNAValid()) {
            int dnaLength = DNA.getSize();   // Store the DNA size to a variable
            System.out.println("The DNA Validation Result:");
            System.out.println("The DNA sequence is valid and its size is " + dnaLength + " bp.");
            
            int count_of_G = DNA.baseCount('G');   // Store the "G" count to a variable
            int count_of_C = DNA.baseCount('C');   // Store the "C" count to a variable
            
            // Calculate the GC content percentage of a DNA sequence
            double GC_Content = (double) (count_of_C + count_of_G) * 100 / dnaLength;

            // Print the GC content information to the console
            System.out.println("\nThe GC Content Analysis Result:");
            System.out.printf("The GC content of the DNA sequence is %.2f", GC_Content); 
            System.out.println("%.\n");
        } else {
            // Print the information if the DNA sequence is invalid
            System.out.println("The DNA Validation Result:");
            System.out.println("The DNA sequence is not valid.");
            // Use the invalidDNAChecker method to identify the invalid DNA letter(s)
            invalidDNAChecker(dna);
            System.out.println("The DNA sequence can only contain 'GATC'.");
        }
        System.out.println();
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

