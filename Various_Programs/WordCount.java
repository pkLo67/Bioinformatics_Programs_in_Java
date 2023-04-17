import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class WordCount {
    
    public static void main(String[] args) {
        // Assign the file name passed from the command line to a String variable
        String filename = args[0];
        // Create a File object by passing the file name
        File file = new File(filename);
        // Create an ArrayList object to store words
        ArrayList<String> wordList = new ArrayList<>();

        // Use the try-catch blocks to handle FileNotFoundException
        try {
            // Create a Scanner object to read the file contents
            Scanner sc = new Scanner(file);
            // Use a while loop to read the Scanner object line-by-line
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                // Use a for loop to loop through each word after the test line is split by spaces
                for (String word:line.split("\s+")) {
                    // Use the if statement to remove the empty string
                    if (!word.equals("")) {
                        wordList.add(word); // Add each word to the ArrayList object
                    }                   
                }
            }
            // Print the total word count based on the size of the Array list
            System.out.println("The word count is " + wordList.size());
            sc.close();  //close the Scanner object 
        } catch (FileNotFoundException e) {
            System.err.println("An error to open the file occurred.");
            e.printStackTrace();
        }

        // Use Collections.sort() method to sort the Array list
        Collections.sort(wordList);

        // Create a TreeMap object for storing words and word frequency values
        TreeMap<String, Integer> sortedWordFreq = new TreeMap<>();

        // Use the for loop to add words as keys to the TreeMap object and 
        // count its frequency that is added to the TreeMap object as values
        for(int i=0; i < wordList.size(); i++) {
            if (sortedWordFreq.containsKey(wordList.get(i))) {
                sortedWordFreq.put(wordList.get(i), sortedWordFreq.get(wordList.get(i)) + 1);
            } else {
                sortedWordFreq.put(wordList.get(i), 1);
            }        
        }

        // Print words and their frequencies in a pair
        for(String word:sortedWordFreq.keySet()) {
            System.out.println(word + ": " + sortedWordFreq.get(word));
        }
        
        System.out.println("The Program Author: Pang-Kuo Lo");
    }
}
