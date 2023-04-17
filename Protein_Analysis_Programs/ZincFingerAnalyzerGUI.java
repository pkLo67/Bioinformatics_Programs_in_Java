import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZincFingerAnalyzerGUI extends JFrame {

    // objects for GUI
	JFileChooser jf = new JFileChooser(".");
	JTextField fileNameTF = new JTextField(55);
	JTextArea txtArea = new JTextArea(40, 60);
	JButton jb = new JButton("Open the file");

    // constructor
	public ZincFingerAnalyzerGUI() {
		fileNameTF.setEditable(true);
		JPanel content = new JPanel();
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);

        Font font = new Font("Courier New", Font.PLAIN, 14);
        txtArea.setFont(font);
		
		content.setLayout(layout);
        content.add(new JLabel("The Program for identifying the zinc finger domain in protein sequences.                     "));
        content.add(jb);
		content.add(new JLabel("File Path:"));
		content.add(fileNameTF);
		content.add(new JScrollPane(txtArea));
				
		// set JPanel to the window
		this.setContentPane(content);
		
		jb.addActionListener(new OpenAction());
				
	}

    // inner class to open file
	private class OpenAction implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			int retval = jf.showOpenDialog(ZincFingerAnalyzerGUI.this);
			File file = null;
			if (retval == JFileChooser.APPROVE_OPTION){
				file = jf.getSelectedFile();
				fileNameTF.setText(file.getAbsolutePath());
			}		
				txtArea.setText(openAnalysisResult(file));
		}
	}


    //Inner protein class
    private class Protein {

        //Initializing two private field variables
        private String id;
        private String seq;
    
        //The constructor with two parameters for this class
        private Protein(String id, String seq) {
            this.id = id;
            this.seq = seq;
        }
    
        //The getter method to retrieve the sequence ID
        private String getID() {
            return this.id;
        }
    
        //The getter method to retrieve the protein sequence
        private String getSeq() {
            return this.seq;
        }
    
        //The setter method (referenced to setSeq) to assign a parsed sequence to 
        //the class field referenced to seq
        private void setSeq(String seq) {
            this.seq = seq;
        }
    
    }
    
    
     /**
     * The Method Name: seqCount
     * @param file: File
     * @return count: int
     * This method performs the following tasks:
     *  1. Scanner is used to read each line of the sequence fasta file.
     *  2. Regex is used to find the title line of each protein sequence and count the number.
     *  3. Return the count number.
     */
    public int seqCount(File file) {

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
            JOptionPane.showMessageDialog(this, "File is not found");
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
    public int ZFproteinCount(int count, Protein[] proteinArray) {
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

        return counter;

    }


    /**
     * The Method Name: spacesStringWithZfdMarks
     * @param seq: String
     * @param start: int
     * @param end: int
     * @return String
     * This method performs the following the tasks:
     *  1. Create a string composed of spaces with the length same as the input seq.
     *  2. Replace the spaces with asterisks based on start and end position numbers.
     *  3. Return this string.
     */
    public String spacesStringWithZfdMarks(String seq, int start, int end) {
        String str = "";

        for(int i=0; i < seq.length(); i++) {
            if (i >= start && i < end) {
                str += "*";
            } else {
                str += " ";
            }
        }
        return str;
    }


    /**
     * The Method Name: formatProteinSeq
     * @param protein
     * @param str
     * @param len
     * @return String: The formatted protein sequence with the marked zinc finger domain
     * This method performs the following tasks:
     *  1. Break two long strings (protein and string from spacesStringWithZfdMarks() ) 
     *     into chunks based on the parsed line length.
     *  2. Add the newline character (\n) at the end of the chunk string.
     *  3. Concatenate these chunk strings together.
     *  4. Return the concatenated string.
     */
    public String formatProteinSeq(String protein, String str, int len) {
		
		String formatSeq = "";
		while (protein.length()> len) {
			formatSeq += protein.substring(0, len) + "\n";
            formatSeq += str.substring(0, len) + "\n";
			protein = protein.substring(len);
            str = str.substring(len);
		}
		// add the remaining part
		if (protein.length()>0) {
			formatSeq += protein + "\n";
            formatSeq += str + "\n";
		}		
		return formatSeq;	
	}


    /**
     * The Method Name: zincFingerDomainAnalyzer
     * @param count: int
     * @param proteinArray: Protein[] array
     * This method performs the following tasks:
     *  1. The regular expression is used to identify the zinc finger domain in each protein sequence.
     *  2. The method stores the following information to a String variable:
     *      a. The protein sequence ID and protein name
     *      b. The identified zinc finger domain in the protein sequence
     *      c. The start and end positions of the identified zinc finger domain
     *      d. The full protein sequence formatted by the formatProteinSeq() method
     *      e. The regions of the zinc finger domain in the protein sequences are indicated by asterisks
     */
    public String zincFingerDomainAnalyzer(int count, Protein[] proteinArray) {
        String result = "";
        for(int i=0; i < count; i++) {
            String aa = "[AC-IK-NP-TVWY]";
            String conSeq = String.format("C%s{2}C%s{17}C%s{2}C", aa, aa, aa);
            Pattern pattern = Pattern.compile(conSeq);
            Matcher matcher = pattern.matcher(proteinArray[i].getSeq());

            while (matcher.find()) {
                String protein = proteinArray[i].getSeq();
                String str = spacesStringWithZfdMarks(proteinArray[i].getSeq(), matcher.start(), matcher.end());
                result += proteinArray[i].getID() + "\n";
                result += "contains the zinc finger site: " + matcher.group(0) + "\n";
                result += "at locations:\nStart at: " + matcher.start() + " " +  "End at: " + matcher.end() + "\n";
                result += formatProteinSeq(protein, str, 50);
            }
        }
        return result;
    }


    /**
     * The method name: openAnalysisResult
     * @param file: File
     * @return outputResult: String
     * This method performs the following tasks:
     *  1. Store all protein IDs and sequences in the fasta file into an array with the protein class type.
     *  2. Parse the protein array and count into the ZFproteinCount() method to count the number of proteins with the zinc finger domain.
     *  3. Parse the protein array and count into the zincFingerDomainAnalyzer() method to identify and map the zinc finger domain in protein sequences.
     *  4. Return a String variable containing all output results.
     */
    public String openAnalysisResult (File file) {
        //Sequentially add sequence ID and protein sequence to each element of the protein array
        Protein[] proteinArray = new Protein[seqCount(file)];
        String protein = "";
        int count = 0;

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
            JOptionPane.showMessageDialog(this, "File is not found");
        }

        //Store the analysis result from the zincFingerDomainAnalyzer2 method to a string variable
        String outputResult = count + " zinc finger proteins are analyzed.\n";
        outputResult += ZFproteinCount(count, proteinArray) + " proteins are found to contain the zinc finger domain.\n\n";       
        outputResult += zincFingerDomainAnalyzer(count, proteinArray);

        return outputResult;
    }


    public static void main(String[] args) {
		// create a ZincFingerAnalyzerGUI window and display it
		JFrame window = new ZincFingerAnalyzerGUI();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(630, 820);
		window.setVisible(true);
	}

}
