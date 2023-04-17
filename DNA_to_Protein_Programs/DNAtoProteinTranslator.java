import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DNAtoProteinTranslator {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("\nThe Program for Analyses of Forward DNA Codon Frames and Their Protein Translation");
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

        //Use the for loop statement to print 3-nucleotide codons and translated amino acids
        for(int n=1; n <= 3; n++) {
            System.out.println("Reading frame #" + n + " codons and amino acids are:");
            ArrayList<String> frame = codon(dna, n);    //Evoke codon() and assign the output the ArrayList object
            frame.forEach(codon -> System.out.printf("%s ", codon));   //Use forEach() to print codons
            System.out.println();
            frame.forEach(codon -> System.out.printf("%s   ", codon2aa(codon)));  //Use codon2aa() to convert codon to aa and print aa
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

    /**
     * The method name: codon2aa
     * @param codon: String
     * @return Amino Acid Code: Character
     * This method perform the following tasks:
     *  1. Creat an empty HashMap object "codonToAA".
     *  2. Use put() to add the codon as the key and aa as the value to the HashMap object
     *  3. Return the amino acid code after parsing the codon as the key into the get() method 
     *     for the HashMap object
     */
    public static Character codon2aa(String codon) {
        HashMap<String, Character> codonToAA = new HashMap<>();
        codonToAA.put("TTT", 'F');
        codonToAA.put("TTC", 'F');
        codonToAA.put("TTA", 'L');
        codonToAA.put("TTG", 'L');
        codonToAA.put("TCT", 'S');
        codonToAA.put("TCC", 'S');
        codonToAA.put("TCA", 'S');
        codonToAA.put("TCG", 'S');
        codonToAA.put("TAT", 'Y');
        codonToAA.put("TAC", 'Y');
        codonToAA.put("TAA", '*');
        codonToAA.put("TAG", '*');
        codonToAA.put("TGT", 'C');
        codonToAA.put("TGC", 'C');
        codonToAA.put("TGA", '*');
        codonToAA.put("TGG", 'W');
        codonToAA.put("CTT", 'L');
        codonToAA.put("CTC", 'L');
        codonToAA.put("CTA", 'L');
        codonToAA.put("CTG", 'L');
        codonToAA.put("CCT", 'P');
        codonToAA.put("CCC", 'P');
        codonToAA.put("CCA", 'P');
        codonToAA.put("CCG", 'P');
        codonToAA.put("CAT", 'H');
        codonToAA.put("CAC", 'H');
        codonToAA.put("CAA", 'Q');
        codonToAA.put("CAG", 'Q');
        codonToAA.put("CGT", 'R');
        codonToAA.put("CGC", 'R');
        codonToAA.put("CGA", 'R');
        codonToAA.put("CGG", 'R');
        codonToAA.put("ATT", 'I');
        codonToAA.put("ATC", 'I');
        codonToAA.put("ATA", 'I');
        codonToAA.put("ATG", 'M');
        codonToAA.put("ACT", 'T');
        codonToAA.put("ACC", 'T');
        codonToAA.put("ACA", 'T');
        codonToAA.put("ACG", 'T');
        codonToAA.put("AAT", 'N');
        codonToAA.put("AAC", 'N');
        codonToAA.put("AAA", 'K');
        codonToAA.put("AAG", 'K');
        codonToAA.put("AGT", 'S');
        codonToAA.put("AGC", 'S');
        codonToAA.put("AGA", 'R');
        codonToAA.put("AGG", 'R');
        codonToAA.put("GTT", 'V');
        codonToAA.put("GTC", 'V');
        codonToAA.put("GTA", 'V');
        codonToAA.put("GTG", 'V');
        codonToAA.put("GCT", 'A');
        codonToAA.put("GCC", 'A');
        codonToAA.put("GCA", 'A');
        codonToAA.put("GCG", 'A');
        codonToAA.put("GAT", 'D');
        codonToAA.put("GAC", 'D');
        codonToAA.put("GAA", 'E');
        codonToAA.put("GAG", 'E');
        codonToAA.put("GGT", 'G');
        codonToAA.put("GGC", 'G');
        codonToAA.put("GGA", 'G');
        codonToAA.put("GGG", 'G');

        return codonToAA.get(codon);

    }
}
