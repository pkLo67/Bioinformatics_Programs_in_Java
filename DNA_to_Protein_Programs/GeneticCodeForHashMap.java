import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GeneticCodeForHashMap {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("gene_code.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> items = new ArrayList<>();

        while(sc.hasNextLine()) {
            items.add(sc.nextLine());
        }

        for(int n=0; n <=180; n+=12) {
            for(int i=0; i <=3; i++) {
                System.out.printf("codonToAA.put(\"%s\", '%s');", items.get(n+i), items.get(n+i+4));
                System.out.println();
            }
            
        }

        sc.close();
    }
}
