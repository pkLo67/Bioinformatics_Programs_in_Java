## Bioinformatics_Programs_in_Java

* BioJava-based_Programs
	* FetchGenPeptBank.java: This Java program is used to fetch gene nucleotides and protein sequences using their accession numbers.
	* ReadFastaGenbankDNA.java: This Java program is used to read either a fasta or genebank file based on the selected option.
	* DNAtoProteinTranslation.java: This Java program is used to translate DNA sequences into their respective protein sequences.
	* sequence.fasta: A collection of DNA sequences in a fasta format.
	* sequence.gb: A collection of DNA sequences in a genbank format.
* DNA_Analysis_Program
	* DNAProgram.java: The Java Class is used in other DNA analysis programs.
	* DNA_ValidationProgram.java: Validate whether the input DNA sequence only contains valid bases G, A, T, C.
	* DoubleStrandedDNA.java: Generate a double-stranded DNA according to the input DNA sequence.
	* GC_ContentAnalysis.java: Calculate the GC content of the input DNA sequence.
* DNA_to_Protein_Programs
	* DNAProgram.java: The Java Class contains a method to validate a DNA sequence.
	* DNA_ValidateProgram.java: A Java program can validate a DNA sequence.
	* ForwardCodonFrame.java: A Java program formats a DNA sequence to three forward codon frames.
	* DNAtoProteinTranslator.java: A Java program translate a DNA sequence to protein sequences according to three forward codon frames.
	* GeneticCodeForHashMap.class: Read the gene_code.txt file to generate Java code used in DNAtoProteinTranslator.java.
* FastaSeq_Analysis_Programs
	* FastaGenerator.java: Generate a fasta file and print it to the screen according to user-entered IDs and sequences.
	* FastaSeqAnalysis.java: Identify and map a restriction enzyme site in fasta DNA sequences. Pass sequence.fasta to test this Java program. RE_analysis_result.txt is an example of output data.
	* sequence.fasta: A collection of DNA sequences in a fasta format for Java program testing.
	* RE_analysis_result.txt: The output result from the FastaSeqAnalysis Java program.
* Protein_Analysis_Programs
	* Protein.java: The Java Class contains methods to validate and format protein sequences.
	* ZincFingerDM_Identifier.java: The Java program is used to identify the zinc finger domain in protein sequences.
	* ZincFingerDM_Analyzer.java: The Java program is used to identify and display the zinc finger domain in protein sequences.
	* ZincFingerAnalyzerGUI.java: The Java GUI is used to identify and display the zinc finger domain in protein sequences.
	* protein_sequence.fasta: A collection of zinc finger protein sequences (3448 proteins) in a fasta format.
	* zinc_finger_analysis_result.txt: The output result from the ZincFingerDM_Analyzer program.
* Various_Programs
	* Average.java: Use Array to store integers passed through the command line and calculate the average.
	* LargestNumberFinder.java: Identify the largest number from 10 integers entered by the user.
	* NumberGuess.java: Use the do-while loop to ask the player to guess a number generated from Math.random().
	* PrintPairedBases.java: Use the for loop and charAt() to print paired DNA bases in a vertical order.
	* SortNumbers.java: Use ArrayList to store integers passed through the command line and sort numbers.
	* WordCount.java: Count the number of words in a text and frequency of each word. The text file is passed to the command line as an argument.
