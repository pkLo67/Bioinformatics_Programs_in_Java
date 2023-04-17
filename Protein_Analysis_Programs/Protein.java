import java.util.regex.Pattern;

public class Protein {

    //Initializing two private field variables
    private String id;
    private String seq;

    //The constructor with two parameters for this class
    public Protein(String id, String seq) {
        this.id = id;
        this.seq = seq;
    }

    //The getter method to retrieve the sequence ID
    public String getID() {
        return this.id;
    }

    //The setter method (referenced to setID) to assign a parsed  
    //sequence ID to the class field referenced to id
    public void setID(String id) {
        this.id = id;
    }

    //The getter method to retrieve the protein sequence
    public String getSeq() {
        return this.seq;
    }

    //The setter method (referenced to setSeq) to assign a parsed sequence to 
    //the class field referenced to seq
    public void setSeq(String seq) {
        this.seq = seq;
    }

    //The getter method to retrieve the protein sequence size
    public int getSize() {
        return seq.length();
    }

    // break a long sequence into chunks
    public String formatSeq(int len) {
		
        String formatedSeq = "";
        String seqCopy = new String(seq);
        while (seqCopy.length()> len) {
            formatedSeq += seqCopy.substring(0, len) + "\n";
            seqCopy = seqCopy.substring(len);
        }
        // add the remaining part
        if (seqCopy.length()>0) {
            formatedSeq += seqCopy;
        }		
      return formatedSeq;	
    }

    // method for protein sequence validation
    public boolean validSeq() {
        return Pattern.matches("[^AC-IK-NP-TVWY]+", seq);
    }

    //The revised toString method to override the default method for the Object class
    @Override
    public String toString() {
        return String.format(">%s\n%s\n", id, seq);
    }
}
