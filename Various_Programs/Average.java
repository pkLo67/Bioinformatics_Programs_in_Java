public class Average {

	public static void main(String[] args) {

		System.out.println("\nThe Program to Compute the Average");
		System.out.println("The program's author: Pang-Kuo Lo\n");

		if (args.length == 0) {
			System.out.println("No numbers are entered. Try Again.\n");
			System.exit(0);
		} else {
			for(String arg: args) {
				try {
					Integer.parseInt(arg);
				} catch (NumberFormatException e) {
					System.err.println("Argument '" + arg + "'' is not an integer. Try Again.\n");
					System.exit(1);
				}
			}
		}

		int total = 0;

		System.out.printf("The list of %d integer numbers you entered:\n", args.length);
		// Use the Array method:
		int[] numberArray = new int[args.length];
		
		for(int i=0; i < numberArray.length; i++) {
			numberArray[i] = Integer.parseInt(args[i]);
			System.out.print(numberArray[i] + " ");
			total += numberArray[i];
		}

		System.out.println();

		double average = (double) total / numberArray.length;

		System.out.printf("The avarage of input numbers is %.2f.",  average);
		System.out.println("\n");
		
	}
}
