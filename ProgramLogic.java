package readability;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ProgramLogic {

    private final String fileAsString;
    private final Paragraph paragraph;
    private final int charCount, wordCount, sentenceCount, syllableCount, polySyllableCount;

    public ProgramLogic(String path) {
        this.fileAsString = getFileAsString(path);
        this.paragraph = new Paragraph(this.fileAsString);
        this.charCount = paragraph.getNumberOfCharacters();
        this.wordCount = paragraph.getNumberOfWords();
        this.sentenceCount = paragraph.getNumberOfSentences();
        this.syllableCount = paragraph.getNumberOfSyllables();
        this.polySyllableCount = paragraph.getNumberOfPolySyllables();
    }

    public int getNumberOfWords() {
        return wordCount;
    }

    public int getNumberOfSentences() {
        return sentenceCount;
    }

    public int getNumberOfCharacters() {
        return charCount;
    }

    public int getNumberOfSyllables() {
        return syllableCount;
    }

    public int getNumberOfPolySyllables() {
        return polySyllableCount;
    }

    public double getARI() { // Returns the automated readability index
        double score = 4.71 * charCount / (double) wordCount + .5 * (double) wordCount / sentenceCount - 21.43;
        return Math.floor(score * 100) / 100;
    }

    public double getFK() { // Returns the Flesch-Kincaid readability score
        double score = 0.39 * (wordCount / (double) sentenceCount) + 11.8 * (syllableCount / (double) wordCount) - 15.59;
        return Math.floor(score * 100) / 100;
    }

    public double getSMOG() { // Returns the Simple Measure of Gobbledygook score
        double score = 1.043 * Math.sqrt(polySyllableCount * 30 / (double) sentenceCount) + 3.1291;
        return Math.floor(score * 100) / 100;
    }

    public double getCL() { // Coleman-Liau
        double score = 0.0588 * (charCount / (double) wordCount * 100) - 0.296 * (sentenceCount / (double) wordCount * 100) - 15.8;
        return Math.floor(score * 100) / 100;
    }

    public String getText() {
        return this.fileAsString;
    }

    public String getFileAsString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            System.out.println("Error reading file! Terminating Program.");
            System.exit(0);
            return "";
        }
    }

    public double getAverageAge(double a, double b, double c, double d) {
        double age = (getAge(getCL()) + getAge(getARI()) + getAge(getSMOG()) + getAge(getFK())) / 4.0;
        return Math.floor(age * 100) / 100;
    }

    public int getAge(double readabilityScore) {
        int score = Math.min((int) Math.round(readabilityScore), 14); // Keeps index within array bounds
        int[] scoreArray = {6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 24};
        return scoreArray[score - 1];
    }
}
