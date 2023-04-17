import org.biojava.bio.BioException;
import org.biojava.bio.seq.DNATools;
import org.biojava.bio.symbol.SymbolList;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;
import org.biojavax.bio.seq.RichSequence.IOTools;
import java.io.*;
import javax.swing.JFileChooser;

public class DNAtoProteinTranslation {

    //Create a JFileChooser object and parse "." to open the current working directory
    private static JFileChooser ourChooser = new JFileChooser(".");

    /**
     * This method is dervied from readFastaDNA.java
     * @return br: BufferedReader
     * This method functions to open a file through a FileChooser
     */
    public static BufferedReader openFile(){
        int retval = ourChooser.showOpenDialog(null);
        BufferedReader br = null;

        if (retval == JFileChooser.APPROVE_OPTION){
            File file = ourChooser.getSelectedFile();
            try {
              br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
              System.out.println("trouble reading "+file.getName());
              e.printStackTrace();
            }
        }   
        return br;
    }

    /**
     * The DNAtoProtein() is my created method
     * @param seq: String
     * @return protein: String
     * This method translates a DNA sequence to a protein sequence
     */
    public static String DNAtoProtein(String seq) {
        String protein = "";
        try {
            SymbolList seqList = DNATools.createDNA(seq);
            SymbolList proteinList = DNATools.toProtein(seqList);
            protein = proteinList.seqString();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return protein;
        
    }

    public static void main(String[] args) throws BioException {

        //The main() method is modified from the main() in readFastaDNA.java
        System.out.println("\nThe Program to Translate DNA Sequences in the Fasta File");
        System.out.println("The Program Author: Pang-Kuo Lo");

        BufferedReader br = openFile();

        RichSequenceIterator it =  IOTools.readFastaDNA(br, null);

        int count = 0;

        while (it.hasNext()) {
            count++;
            RichSequence s = it.nextRichSequence();
            System.out.println("\nAccession: " + s.getAccession() + 
                              "\nDNA Sequence Length: " + s.length());
            System.out.println("DNA Sequence: \n" + s.seqString().toUpperCase());
            //Print the protein sequences translated from three forward open reading frames.
            System.out.println("\nProtein Tranlation of the Forward Reading Frame #1: \n" + DNAtoProtein(s.seqString()));
            System.out.println("\nProtein Tranlation of the Forward Reading Frame #2: \n" + DNAtoProtein(s.seqString().substring(1)));
            System.out.println("\nProtein Tranlation of the Forward Reading Frame #3: \n" + DNAtoProtein(s.seqString().substring(2)));
            System.out.println();
        }
        System.out.println("\n# sequences read: " + count + "\n"); 
    }

}
