import org.biojava.bio.BioException;
import org.biojavax.bio.db.ncbi.GenbankRichSequenceDB;
import org.biojavax.bio.db.ncbi.GenpeptRichSequenceDB;
import org.biojavax.bio.seq.RichSequence;
import java.util.Scanner;

public class FetchGenPeptBank {
    public static void main(String[] args) {
        System.out.println("\nThe Program for Fetching Gene DNA and Protein Sequences");
        System.out.println("The Program Author: Pang-Kuo Lo\n");

        Scanner input = new Scanner(System.in);
        boolean continueLoop;

        do {
            System.out.println("Please enter the sequence type (DNA or Protein):");
            String seqType = input.nextLine().strip().toUpperCase();
    
            if (seqType.equals("DNA")) {
                fetchGenBank(input);
            } else if (seqType.equals("PROTEIN")) {
                fetchPeptBank(input);
            }

            System.out.println("Would you like to continue to fetch a sequence? (Yes or No)");
            String opt = input.nextLine().strip().toUpperCase();
            System.out.println();
            if (opt.equals("YES")) {
                continueLoop = true;
            } else {
                continueLoop = false;
            } 
        } while (continueLoop);

        input.close();
        
    }

    public static void fetchGenBank(Scanner input) {
        RichSequence rs = null;
        GenbankRichSequenceDB grsdb = new GenbankRichSequenceDB();

        try{
        // get data via GenBank accession number or gi number
            System.out.println("\nEnter a GenBank accession number or gi number: ");
            String id = input.nextLine();
            System.out.println();
            rs = grsdb.getRichSequence(id);
            System.out.println(rs.getName()+" | "+rs.getDescription());
            System.out.println(rs.seqString().toUpperCase());
            System.out.println();
        }
        catch(BioException be){
            be.printStackTrace();
            System.exit(-1);
        }
    }

    public static void fetchPeptBank(Scanner input) {
        RichSequence rs = null;
        GenpeptRichSequenceDB gprdb = new GenpeptRichSequenceDB();

        try{
        // get data via GenBank accession number or gi number
            System.out.println("\nEnter a GenBank accession number or gi number: ");
            String id = input.nextLine();
            System.out.println();
            rs = gprdb.getRichSequence(id);
            System.out.println(rs.getName()+" | "+rs.getDescription());
            System.out.println(rs.seqString());
            System.out.println();
        }
        catch(BioException be){
            be.printStackTrace();
            System.exit(-1);
        }

    }
}
