import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZincFingerDM_Identifier {
    public static void main(String[] args) {
        System.out.println("\nThe Program for identifying the zinc finger domain in protein sequences");
        System.out.println("The Program Author: Pang-Kuo Lo\n");

        //Prompt the user to enter the protein fasta file name
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the protein sequence fasta file name:");
        String filename = "";
        File file;
        int count = 0;     

        //Use the do-while loop to ensure the entered file name is valid
        //Use the seqCount method to get the number of sequences in the fasta file
        //Assign the output to the "count" variable
        boolean loopContinue = true;

        do {
            filename = input.nextLine().strip();
            file = new File(filename);
            count = seqCount(file);
            if (count == -1) {    //seqCount returns -1 if the file is not found
                continue;
            } else {
                loopContinue = false;
                System.out.printf("\nThe %s contains %d zinc finger protein sequences for analysis.\n", filename, count);
            }            
        } while (loopContinue);

        //Sequentially add sequence ID and sequence to each element of the protein array
        Protein[] proteinArray = new Protein[count];
        String protein = "";
        count = 0;

        try {
            //As the contents of the Scanner object are emptied after each iteration,
            //the Scanner object is recreated again for iterating each line contents
            Scanner sc = new Scanner(file);     
            while (sc.hasNextLine()) {
                String line = sc.nextLine().strip();
                Pattern pattern = Pattern.compile("^>.+");
                Matcher matcher = pattern.matcher(line);
                
                if (matcher.matches()) {      //Use the regex patter to identify the sequence title line     
                    protein = "";
                    count++;
                    proteinArray[count-1] = new Protein(line, "");

                } else {
                    //Use this statement to add all sequence lines together for making a full sequence
                    protein += line;
                    proteinArray[count-1].setSeq(protein);
                }
            }
            sc.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error in opening file due to the file not found.\n");
        }
        
        //Invoke the ZFproteinCount method to output the number of protein sequences containing the zinc finger domain
        ZFproteinCount(count, proteinArray);

        //Invoke the zincFingerDomainAnalyzer method to display the locations of
        //the zinc finger domain in protein sequences on the screen
        zincFingerDomainAnalyzer(count, proteinArray);

        //Store the analysis result from the zincFingerDomainAnalyzer2 method to a string variable
        String outputResult = zincFingerDomainAnalyzer2(count, proteinArray);

        //Prompt the user to provide the output file name        
        System.out.print("Enter the output file name: ");
        String outputName = input.nextLine();                    

        try {
            //passing file instance in filewriter
            FileWriter wr = new FileWriter(outputName);
            //calling writer.write() method with the string and write it to the file with the designated file name
            wr.write(outputResult);
            wr.flush();
            System.out.printf("The zinc finger analysis result has been saved in %s.\n\n", outputName);
            wr.close();
        } catch (IOException x) {
            System.err.println("Error Information: " + x);
        }

        input.close();
    }


    /**
     * The Method Name: seqCount
     * @param file: File
     * @return count: int
     * This method performs the following tasks:
     *  1. Scanner is used to read each line of the sequence fasta file.
     *  2. Regex is used to find the title line of each protein sequence and count the number.
     *  3. Return the count number.
     *  4. If FileNotFoundException occurs, return -1.
     */
    public static int seqCount(File file) {

        int count = 0;
        //Use try-catch statement block for handling exception when the file is read by Scanner
        try {
            //Scanner reads the file by parsing the File object
            Scanner sc = new Scanner(file);

            //Use the while statment to read the contents in the Scanner object line by line
            while (sc.hasNextLine()) {
                String line = sc.nextLine().strip();
                
                if (line.matches("^>.+")) {
                    count++;   //Add one every time when the regular expression pattern matches
                } 
            }

            sc.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error in opening file due to the file not found.\n");
            System.out.println("Enter the protein sequence fasta file name:");
            return -1;
        } 

        return count;
    }


    /**
     * The Method Name: ZFproteinCount
     * @param count: int
     * @param proteinArray: Protein[] array
     * This method performs the following tasks:
     *  1. The regular expression is used to identify the zinc finger domain in each protein sequence.
     *  2. The number of protein sequences containing the zinc finger domain is counted during iteration.
     *  3. The final number is assigned to the "counter" variable.
     */
    public static void ZFproteinCount(int count, Protein[] proteinArray) {
        int counter = 0;
        for(int i=0; i < count; i++) {
            String aa = "[AC-IK-NP-TVWY]";
            String conSeq = String.format("C%s{2}C%s{17}C%s{2}C", aa, aa, aa);
            Pattern pattern = Pattern.compile(conSeq);
            Matcher matcher = pattern.matcher(proteinArray[i].getSeq());

            while (matcher.find()) {
                counter++;
            }
        }

        System.out.println(counter + " protein sequences are identified to contain the zinc finger domain pattern.\n");
    }


    /**
     * The Method Name: zincFingerDomainAnalyzer
     * @param count: int
     * @param proteinArray: Protein[] array
     * This method performs the following tasks:
     *  1. The regular expression is used to identify the zinc finger domain in each protein sequence.
     *  2. The method prints the following information:
     *      a. The protein sequence ID and protein name
     *      b. The identified zinc finger domain in the protein sequence
     *      c. The start and end positions of the identified zinc finger domain
     *      d. The full protein sequence in a format.
     */
    public static void zincFingerDomainAnalyzer(int count, Protein[] proteinArray) {
        for(int i=0; i < count; i++) {
            String aa = "[AC-IK-NP-TVWY]";
            String conSeq = String.format("C%s{2}C%s{17}C%s{2}C", aa, aa, aa);
            Pattern pattern = Pattern.compile(conSeq);
            Matcher matcher = pattern.matcher(proteinArray[i].getSeq());

            while (matcher.find()) {
                System.out.println(proteinArray[i].getID());
                System.out.printf("contains the zinc finger site: %s\n", matcher.group(0));
                System.out.printf("at locations:\n%d %d\n", matcher.start(), matcher.end());
                System.out.println(proteinArray[i].formatSeq(50));
                System.out.println();
            }
        }
    }

    /**
     * The Method Name: zincFingerDomainAnalyzer2
     * @param count: int
     * @param proteinArray: Protein[] array
     * This method performs the following tasks:
     *  1. The regular expression is used to identify the zinc finger domain in each protein sequence.
     *  2. The method stores the following information to a String variable:
     *      a. The protein sequence ID and protein name
     *      b. The identified zinc finger domain in the protein sequence
     *      c. The start and end positions of the identified zinc finger domain
     *      d. The full protein sequence in a format.
     */
    public static String zincFingerDomainAnalyzer2(int count, Protein[] proteinArray) {
        String result = "";
        for(int i=0; i < count; i++) {
            String aa = "[AC-IK-NP-TVWY]";
            String conSeq = String.format("C%s{2}C%s{17}C%s{2}C", aa, aa, aa);
            Pattern pattern = Pattern.compile(conSeq);
            Matcher matcher = pattern.matcher(proteinArray[i].getSeq());

            while (matcher.find()) {
                result += proteinArray[i].getID() + "\n";
                result += "contains the zinc finger site: " + matcher.group(0) + "\n";
                result += "at locations:\nStart at: " + matcher.start() + " " +  "End at: " + matcher.end() + "\n";
                result += proteinArray[i].formatSeq(50);
            }
        }
        return result;
    }

}
