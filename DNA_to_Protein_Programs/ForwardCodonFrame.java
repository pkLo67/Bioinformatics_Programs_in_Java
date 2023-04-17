import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ForwardCodonFrame {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("\nThe Program for Forward DNA Codon Frame Analysis");
        System.out.println("The Program's Author: Pang-Kuo Lo\n");

        //Prompt the user to enter the file name or file path for the DNA sequence
        System.out.println("Enter the file name or file path for DNA sequence:");
        Scanner input = new Scanner(System.in);
        String filePath = input.nextLine().strip();

        //Use Scanner to read the content of the file 
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        //The string of each line is concaternated together to be a full DNA sequence
        String dna = "";
        while(sc.hasNextLine()) {
            dna += sc.nextLine().strip().toUpperCase();
        }

        System.out.println("\nThe DNA sequence is:\n" + dna + "\n");

        //Use the for loop statement to print 3-nucleotide codons
        for(int n=1; n <= 3; n++) {
            System.out.println("Reading frame #" + n + " codons are:");
            ArrayList<String> frame = codon(dna, n);   //Evoke codon() and assign the output the ArrayList object
            frame.forEach(codon -> System.out.printf("%s ", codon));    //Use forEach() to print codons
            System.out.println("\n");
        }
        
        input.close();
        sc.close();

    }

    /**
     * The method name: codon
     * @param dna: String
     * @param frameNo: int
     * @return frame: ArrayList<String>
     * This method performs the following tasks:
     *  1. Create an empty ArrayList.
     *  2. Use the for loop statement to add 3-nt codons to the ArrayList.
     *  3. Return the ArrayList contains 3-nt codons as array items
     */
    public static ArrayList<String> codon(String dna, int frameNo) {

        ArrayList<String> frame = new ArrayList<>();
        int res = (dna.length()-frameNo+1)%3;
        for(int i = frameNo - 1; i < dna.length()-res; i+=3) {
            frame.add(dna.substring(i, i+3));
        }
        return frame;
    }

}
