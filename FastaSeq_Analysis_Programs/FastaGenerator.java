import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FastaGenerator {
    
    public static void main(String[] args) {
      
        System.out.println("The Program Author: Pang-Kuo Lo");

        //Create a Scanner object
        Scanner input = new Scanner(System.in);
        //Create a String variable to store the clone ID and sequence
        String fastaSeqs = "";    

        //Use a while loop to obtain the clone ID and sequence from the user
        while(true) {
            //Obtain the entered clone ID from the user
            System.out.println("Enter the clone ID of the DNA sequence:");
            String seqID = input.nextLine().strip();
            fastaSeqs += ">" + seqID +"\n";

            //Obtain the entered sequence from the user
            System.out.println("Eneter the DNA sequence:");
            String DNA_Seq = input.nextLine().strip().toUpperCase();
            fastaSeqs += DNA_Seq + "\n\n";

            //Use the if statement to exit the while loop 
            System.out.println("Please enter 'exit' to close the program or press Enter to continue:");
            String ans = input.nextLine().strip().toUpperCase();
            
            if (ans.equals("EXIT")) {break;}
            
        }

        input.close();     //Close the Scanner object
        //Print the fasta sequences to the screen
        System.out.println(fastaSeqs);      

        //Save the fasta sequences to the file
        try {
            FileWriter writer = new FileWriter("seqs.fasta");
            writer.write(fastaSeqs);
            writer.close();
            System.out.println("Successfully wrote to the file seqs.fasta.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
