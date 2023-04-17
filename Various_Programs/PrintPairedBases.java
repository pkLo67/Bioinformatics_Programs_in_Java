public class PrintPairedBases {
    public static void main(String[] args) {
        System.out.println("The program's author: Pang-Kuo Lo");
        System.out.println();

        // Assign forward and reverse DNA strand sequences to two String variables
        String plusStrand = "ATGCTTGACC";
        String minusStrand = "TACGAACTGG";

        System.out.println("The double-stranded DNA:\n" + plusStrand + "\n" + minusStrand);
        System.out.println();

        // Use the for loop to print paired DNA bases in a vertical order
        System.out.println("In a vertical order:");
        for(int i=0; i < plusStrand.length(); i++) {
            System.out.printf("%s%s\n", plusStrand.charAt(i), minusStrand.charAt(i));
        }
    }
}
