import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaSeqAnalysis {
    public static void main(String[] args) {
        System.out.println("\nThe Program for Analysis of Restriction Enzyme Sites in Fasta DNA Sequences");
        System.out.println("The Program Author: Pang-Kuo Lo");
        System.out.print("\nEnter the name of the sequence file: ");

        //Create a Scanner object to prompt users to enter information
        Scanner input = new Scanner(System.in);

        //Assign the user-provided file name to a String variable/object
        String fileName = input.nextLine();
        //Create an instance object of the File class by parsing the file name
        File file = new File(fileName);

        //Create an integer object for storing the sequence count
        int count = 0;

        //Use try-catch statement block for handling exception when the file is read by Scanner
        try {
            //Scanner reads the file by parsing the File object
            Scanner sc = new Scanner(file);

            //Use the while statment to output the contents in the Scanner object line by line
            while (sc.hasNextLine()) {
                String line = sc.nextLine().strip();
                
                if (line.matches("^>.+")) {
                    count++;   //Add one every time when the regular expression pattern matches
                } 
            }

            sc.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error in opening file due to the file not found.\n");
            System.exit(0);
        } 

        //Print the number of sequences in the file
        System.out.printf("File seq.fasta contains %d sequences\n\n", count);

        //Create three String arrays for storing the sequence title lines, sequences 
        //and blank spaces with same lengths as sequences (used for restriction enzyme analysis)
        //Only indexes of 1 to count in arrays are used in following tasks. Index 0 is not used.
        String[] seqIdArray = new String[count+1];
        String[] seqArray = new String[count+1];
        String[] reArray = new String[count+1];

        //Reset count to 0 for its reuse
        count = 0;

        try {
            //As the contents of the Scanner object are emptied after iteration,
            //the Scanner object is recreated again for iterating each line contents
            Scanner sc2 = new Scanner(file);        
            while (sc2.hasNextLine()) {
                String line = sc2.nextLine().strip();
                
                if (line.matches("^>.+")) {      //Use the regex patter to identify the sequence title line
                    count++;
                    seqIdArray[count] = line;    //Store the sequence title line to the array
                    seqArray[count] = "";        //Reset the array at this index to the empty string when the title is identified
                    reArray[count] = "";         //Reset the array at this index to the empty string when the title is identified
                    if (count > 1) {
                        //Use the for loop to add blank spaces to the array at the prior iterated index
                        //This code is used to create blank spaces with the same length as the sequence stored in the sequence array
                        for(int n=0; n<seqArray[count-1].length(); n++) {
                            reArray[count-1] += " ";
                        }
                    } 

                } else {
                    //Use this statement to add all sequence lines together for making a full sequence
                    seqArray[count] += line;   
                }
            }

            sc2.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error in opening file due to the file not found.\n");

        } 
        
        //Add the blank spaces to the reArray at the last index
        if (count == seqIdArray.length-1) {
            for(int n=0; n<seqArray[count].length(); n++) {
                reArray[count] += " ";
            }
        }

        //Use Scanner to read the user-entered sequence of a restriction site
        System.out.print("Enter the sequence of a restriction site: ");
        String reSite = input.nextLine().strip().toUpperCase();
        
        //Create a Pattern object for regex analysis of a restriction site
        Pattern pattern = Pattern.compile(reSite);

        for (int i=1; i <= count; i++) {
            //Use matcher() method to perform regex searching analysis
            Matcher matcher = pattern.matcher(seqArray[i]);
            //Use toCharArray() to convert the space string into a charArray for storing individual space as an array item
            char[] charArray = reArray[i].toCharArray();
            while (matcher.find()) {
                for(int j=matcher.start(); j < matcher.end(); j++) {
                    charArray[j] = '*';     //Replace the space with an asterisk at the RE-site-matched positions
                }
            }
            //Use copyValueOf() method to convert charArray to a string and assign it back to reArray at the iterated index
            reArray[i] = String.copyValueOf(charArray);

        }

        //Use the following nested for loop statement to print out the RE analysis result to the console
        for (int i=1; i <= count; i++) {
            System.out.println(seqIdArray[i]);
            for (int j=0; j < seqArray[i].length(); j+=50) {     //Print 50 characters per line
                if (j+50 > seqArray[i].length()) {       //Print the remaining sequence characters with the number < 50
                    int res = seqArray[i].length()%50;
                    System.out.println(seqArray[i].substring(j, j+res));
                    System.out.println(reArray[i].substring(j, j+res));
                } else {
                    System.out.println(seqArray[i].substring(j, j+50));
                    System.out.println(reArray[i].substring(j, j+50));
                }          
            }
        }

        //Create an empty string to store the entire RE analysis result
        String outputResult = "";

        //Use the nested for loop statement to assemble all line items from sequence title, sequence,
        //and space-asterisk arrays together to become an entire string.
        //Each line item ends with a newline character
        for (int i=1; i <= count; i++) {
            outputResult += seqIdArray[i] + "\n";

            for (int j=0; j < seqArray[i].length(); j+=50) {
                if (j+50 > seqArray[i].length()) {
                    int res = seqArray[i].length()%50;
                    outputResult += seqArray[i].substring(j, j+res) + "\n";
                    outputResult += reArray[i].substring(j, j+res) + "\n";
                } else {
                    outputResult += seqArray[i].substring(j, j+50) + "\n";
                    outputResult += reArray[i].substring(j, j+50) + "\n";
                }          
            }
        }

        //Prompt the user to provide the output file name        
        System.out.print("Enter the output file name: ");
        String outputName = input.nextLine();                    
        input.close();

        try {
            //passing file instance in filewriter
            FileWriter wr = new FileWriter(outputName);
            //calling writer.write() method with the string and write it to the file with the designated file name
            wr.write(outputResult);
            wr.flush();
            System.out.printf("The RE analysis result has been saved in %s.\n\n", outputName);
            wr.close();
        } catch (IOException x) {
            System.err.println("Error Information: " + x);
        }
        
    }

}
