package readability;

import java.util.ArrayList;

public class Sentence {

    private String sentence;
    private ArrayList<Word> words;

    public Sentence (String sentence) {
        this.sentence = sentence;
        this.words = splitToWords();
    }

    private ArrayList<Word> splitToWords() {
        String[] wordArray = this.sentence.split(" ");
        ArrayList<Word> wList = new ArrayList<>();
        for (String w : wordArray) {
            wList.add(new Word(w));
        }
        return wList;
    }

    public int getNumberOfWords() {
        return words.size();
    }

    public int getNumberOfSyllables() {
        return words.stream().mapToInt(Word::getNumberOfSyllables).sum();
    }

    public int getNumberOfPolySyllables() {
        return (int)words.stream().filter(Word::isPolySyllable).count();
    }
}