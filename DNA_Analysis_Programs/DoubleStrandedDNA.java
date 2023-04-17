import java.util.Scanner;

public class DoubleStrandedDNA {
    public static void main(String[] args) {
        System.out.println("\n****The Program For Displaying Double Stranded DNA****");
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
        // Use the "doubleStrandedDNA" method to display the double stranded DNA if the input DNA is valid
        if (DNA.IsDNAValid()) {
            // Evoke the doubleStrandedDNA method to print double-stranded DNA
            doubleStrandedDNA(dna);
        } else {
            // Print the information if the DNA sequence is invalid
            System.out.println("The DNA sequence is not valid.");
            // Use the invalidDNAChecker method to identify the invalid DNA letter(s)
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
     *  2. The positions of any invalid letters in the provided DNA sequence will be printed to the console
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

    /**
     * The Method Name: doubleStrandedDNA
     * @param dna : String
     * This method can perform the following tasks:
     *  1. Convert the DNA to its complementary DNA strand
     *  2. Print the double-stranded DNA to the console
     */
    public static void doubleStrandedDNA(String dna) {
        String rcDNA = "";
        for(int i=0; i < dna.length(); i++) {
            rcDNA += complementaryNt(dna.charAt(i));
        }
        System.out.println("Here is the Starting DNA:\n" + dna);
        System.out.println("\nHere is the Double-Stranded DNA:");
        System.out.println(dna + "\n" + rcDNA + "\n");
    }

    /**
     * The Method Name: complementaryNt
     * @param n : char
     * @return char
     * This method can perform the following task:
     *  1. Convert the nucleotide to its complementary necleotide
     *  2. Return the complementary nucleotide
     */
    public static char complementaryNt(char n) {
        switch(n) {
            case 'A': return 'T';
            case 'C': return 'G';
            case 'G': return 'C';
            case 'T': return 'A';
            default: return ' ';
        }
    }
}

