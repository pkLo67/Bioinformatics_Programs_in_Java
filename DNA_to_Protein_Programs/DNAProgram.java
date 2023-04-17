import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNAProgram {
    // Create a field variable for this class under the private scope
    private String DNA;

    // Create a constructor for this class
    public DNAProgram(String DNA) {
        this.DNA = DNA;
    }

    // Create a "getDNA" method for allowing other classes to have access to this field variable
    public String getDNA() {
        return this.DNA;
    }

    // Create a setDNA method for allowing other classes to set the field variable
    public void setDNA(String DNA) {
        this.DNA = DNA;
    }

    // Create a method to check whether the input DNA is valid
    /**
     * The Method Name: IsDNAValid
     * @return : true or false (boolean)
     * This method performs the following tasks:
     *  1. Use the regex pattern to check invalid letters in a DNA sequence.
     *  2. Store the number of invalid letters in a variable called "count".
     *  3. Use the if-else statement to print the validation result.
     */
    public void IsDNAValid() {
        String regex = "[^GATC]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(DNA);

        int counter = 0;

        while(matcher.find()) {
            counter++;
        }

        System.out.println("The DNA Validation Result:");
        if (counter == 0) {
            System.out.println("The input DNA is valid.\n");
        } else {
            System.out.println("The input DNA is not valid.\n");
            System.out.println("The finding for the invalid letter(s):");
            matcher = pattern.matcher(DNA);
            while (matcher.find()) {
                counter++;
                System.out.print("Start index: " + matcher.start());
                System.out.print("  End index: " + matcher.end() + "  ");
                System.out.printf("Letter: %s\n", matcher.group());         
            }
            System.out.println();
        }
        
    }

    // Create a method to return the information of the DNA size
    public int getSize() {
        return DNA.length();
    }

    // Create a method to count the number of a specific nucleotide in a DNA sequence
    /**
     * The Method Name: baseCount
     * @param n : char
     * @return : integer
     * This method performs the following tasks:
     *  1. Use the for-loop to count the number of a specific nucleotide according to the input argument.
     *  2. Return the count value.
     */
    public int baseCount(char n) {
        int countNt = 0;

        for(int i = 0; i < DNA.length(); i++) {
            if (DNA.charAt(i) == n) {
                countNt++;
            }
        }

        return countNt;
    }

}
