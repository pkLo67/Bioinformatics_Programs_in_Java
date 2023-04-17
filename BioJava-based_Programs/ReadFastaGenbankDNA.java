import org.biojava.bio.BioException;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;
import org.biojavax.bio.seq.RichSequence.IOTools;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ReadFastaGenbankDNA {

    //Create a JFileChooser object and parse "." to open the current working directory
    private static JFileChooser ourChooser = new JFileChooser(".");

    /**
     * This method is dervied from readFastaDNA.java
     * @return br: BufferedReader
     * This method functions to open a file through a FileChooser
     */
    public static BufferedReader openFile() {
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


    public static void main(String[] args) throws BioException {

        System.out.println("\nThe Program to Read the Fasta or Genbank File");
        System.out.println("The Program Author: Pang-Kuo Lo");

        //Ask the user to enter the option number into the showInputDialog box.
        String fileType = JOptionPane.showInputDialog("Enter 1 for the Fasta file or 2 for the Genebank file:");
        int type = Integer.parseInt(fileType.strip());
        int count = 0;
        
        //Ask the user to select the file for reading
        BufferedReader br = openFile();

        if (type == 1) {
            //Use the readFastaDNA() method to read the fasta file
            RichSequenceIterator it =  IOTools.readFastaDNA(br, null);       

            while (it.hasNext()){
                count++;
                RichSequence s = it.nextRichSequence();
                System.out.println("Accession: " + s.getAccession() + 
                                "\nLength: " + s.length());
                System.out.println("Sequence: \n" + s.seqString().toUpperCase() + "\n");
            }
        } else if (type == 2) {
            //Use the readGenbankDNA() method to read the Genbank file
            RichSequenceIterator it =  IOTools.readGenbankDNA(br, null);

            while (it.hasNext()) {
                count++;
                RichSequence s = it.nextRichSequence();
                System.out.println("Accession: " + s.getAccession() + " | " +
                                "Length: " + s.length() + " bp" + " | " +
                                "Description: " + s.getDescription().replace("\n", " "));
                System.out.println("Sequence: \n" + s.seqString().toUpperCase() + "\n");
            }
        } else {
            System.out.println("Invalid selection. Please try again.");
            System.exit(0);
        }
        
       System.out.println("# sequences read: " + count + "\n");
   
    }
}


