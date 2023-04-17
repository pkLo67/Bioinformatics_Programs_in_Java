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

        DNA.IsDNAValid();

        input.close();
           
    }
}
