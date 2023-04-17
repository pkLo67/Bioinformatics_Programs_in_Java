import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortNumbers {
    public static void main(String[] args) {

		System.out.println("\nThe Program to Sort Integer Numbers");
		System.out.println("The program's author: Pang-Kuo Lo\n");

		if (args.length == 0) {
			System.out.println("No numbers are entered. Try Again.\n");
			System.exit(0);
		} else if (args.length == 1) {
            System.out.println("Enter at least two integers for sorting.\n");
            System.exit(0);
        } else {
			for(String arg: args) {
				try {
					Integer.parseInt(arg);
				} catch (NumberFormatException e) {
					System.err.println("Argument '" + arg + "'' is not an integer. Try Again.\n");
					System.exit(0);
				}
			}
		} 


		// Use the ArrayList method:
		List <Integer> numberArray = new ArrayList<Integer>(args.length);

		for (String arg: args) {
			numberArray.add(Integer.parseInt(arg));
		}

        System.out.printf("The %d input integers before sorting:\n", numberArray.size());
        numberArray.forEach(n-> System.out.print(n + " "));
        System.out.println();

        // Sort the integer numbers
        Collections.sort(numberArray);
		
        System.out.printf("The %d input integers after sorting:\n", numberArray.size());
        numberArray.forEach(n-> System.out.print(n + " "));

		System.out.println("\n");

		
	}
}
