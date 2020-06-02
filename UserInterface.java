package readability;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;
    private final ProgramLogic core;

    public UserInterface(ProgramLogic core, Scanner scanner) {
        this.scanner = scanner;
        this.core = core;
    }

    public void start() {

        // Output the file text and main metrics
        System.out.println("The text is:\n" + core.getText());
        System.out.print("Words: " + core.getNumberOfWords() + "\n");
        System.out.print("Sentences: " + core.getNumberOfSentences() + "\n");
        System.out.print("Characters: " + core.getNumberOfCharacters() + "\n");
        System.out.print("Syllables: " + core.getNumberOfSyllables() + "\n");
        System.out.print("Polysyllables: " + core.getNumberOfPolySyllables() + "\n");

        // Get input from the user and print the desired readability score(s)
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String userInput = scanner.nextLine();
        printReadabilityIndex(userInput);
    }

    public void printReadabilityIndex(String userInput) {
        switch (userInput) {
            case "ARI":     // Automated Readability Index
                printARI();
                break;
            case "FK":      // Flesch-Kincaid
                printFK();
                break;
            case "SMOG":    // Simple Measure of Gobbledygook
                printSMOG();
                break;
            case "CL":      // Coleman-Liau
                printCL();
                break;
            case "all":     // All Scores
                printAll();
                break;
            default:
                System.out.println("Invalid Input.");
        }
    }

    public void printARI() { // Automated Readability Index
        System.out.print("Automated Readability Index: " + core.getARI());
        System.out.println(" (about " + core.getAge(core.getARI()) + " year olds).");
    }

    public void printFK() { // Flesch-Kincaid
        System.out.print("Flesch–Kincaid readability tests: " + core.getARI());
        System.out.println(" (about " + core.getAge(core.getFK()) + " year olds).");
    }

    public void printSMOG() { // Simple Measure of Gobbledygook
        System.out.print("Simple Measure of Gobbledygook: " + core.getSMOG());
        System.out.println(" (about " + core.getAge(core.getSMOG()) + " year olds).");
    }

    public void printCL() { // Coleman-Liau
        System.out.print("Coleman–Liau index: " + core.getCL());
        System.out.println(" (about " + core.getAge(core.getCL()) + " year olds).");
    }

    public void printAll() { // Prints all of the readability scores (followed by their average)
        printARI();
        printFK();
        printSMOG();
        printCL();
        double averageAge = core.getAverageAge(core.getARI(), core.getFK(), core.getSMOG(), core.getCL());
        System.out.println("\nThis text should be understood in average by " + averageAge + " year olds.");
    }
}