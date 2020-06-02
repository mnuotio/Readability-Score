package readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {

    private final String word;

    public Word(String word) {
        this.word = word;
    }

    public int getNumberOfSyllables() {

        int numberOfVowels = countInstancesOfPattern("[aeiouyAEIOUY]+");            // counts total number of vowel groups
        int lastCharacterIsE = countInstancesOfPattern("([eE]$)|([eE][^A-Za-z]$)"); // returns 1 if last char is e
        int syllableCount = numberOfVowels - lastCharacterIsE;
        return Math.max(numberOfVowels - lastCharacterIsE, 1);
    }

    public boolean isPolySyllable() {
        return getNumberOfSyllables() > 2;
    }

    private int countInstancesOfPattern(String patternToMatch) {
        Pattern pattern = Pattern.compile(patternToMatch);
        Matcher matcher = pattern.matcher(this.word);
        return (int) matcher.results().count();
    }
}