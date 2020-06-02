package readability;

import java.util.ArrayList;
import java.util.Arrays;

public class Paragraph {

    private final String paragraph;
    private final ArrayList<Sentence> sentences;

    public Paragraph(String paragraph) {
        this.paragraph = paragraph;
        this.sentences = createSentenceList();
    }

    public int getNumberOfCharacters() {
        return (int) this.paragraph.chars()
                .filter(ch -> ch != ' ')
                .count();
    }

    // Converts array of sentences created with String.split() to an ArrayList
    private ArrayList<Sentence> createSentenceList() {
        String[] sentenceArray = this.paragraph.split("[?!.]\\s");
        ArrayList<Sentence> sList = new ArrayList<>();
        Arrays.stream(sentenceArray).forEach(s -> sList.add(new Sentence(s)));
        return sList;
    }

    public int getNumberOfSentences() {
        return sentences.size();
    }

    public int getNumberOfWords() {
        return sentences.stream().mapToInt(Sentence::getNumberOfWords).sum();
    }

    public int getNumberOfSyllables() {
        return sentences.stream().mapToInt(Sentence::getNumberOfSyllables).sum();
    }

    public int getNumberOfPolySyllables() {
        return sentences.stream().mapToInt(Sentence::getNumberOfPolySyllables).sum();
    }

}
