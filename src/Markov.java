import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Markov {
  /*
   * Title: Markov.java
   * Abstract: This program builds new sentences from existing ones.
   * Author: Keldin Maldonado
   * Date 2022 - 11 - 15
   */

  private static final String PUNCTUATION = "__$";
  private static final String PUNCTUATION_MARKS = ".!?$";

  private HashMap<String, ArrayList<String>> words = new HashMap<>();
  private String prevWord = "";

  void addLine(String line) {
    if (!line.isEmpty()) {
      // cleaning the read in string and only leaving a space to then split
      // the string at that point and easily store it in an array of strings
      line = line.replaceAll("\n", "");
      line = line.replaceAll("\t", "");
      line = line.stripLeading();
      line = line.stripTrailing();
      line = line.replaceAll("\\s+", " ");

      String[] wordsSplit = line.split(" ");

      for (int i = 0; i < wordsSplit.length; i++) {
        addWord(wordsSplit[i]);
        prevWord = wordsSplit[i];
      }
    }
  }

  void addWord(String word) {
    if (endsWithPunctuation(prevWord)) {
      words.get(PUNCTUATION).add(word);
    } else if (!endsWithPunctuation(prevWord)) {
      if (words.containsKey(prevWord)) {
        words.get(prevWord).add(word);
      } else {
        words.put(prevWord, new ArrayList<>());
        words.get(prevWord).add(word);
      }
    }

    prevWord = word;
  }

  public static boolean endsWithPunctuation(String word) {
    char lastChar = word.charAt(word.length() - 1);

    try {
      for (int i = 0; i < PUNCTUATION_MARKS.length(); i++) {
        if (PUNCTUATION_MARKS.charAt(i) == lastChar) {
          return true;
        }
      }
    } catch (Exception e) {
      System.out.println("Error occurred while checking for punctuations!");
    }

    return false;
  }

  public String getSentence() {
    Random random = new Random();

    int ranNum = random.nextInt(0, words.get(PUNCTUATION).size());

    String currentWord = words.get(PUNCTUATION).get(ranNum);

    String finalString = "";

    do {
      if (!endsWithPunctuation(currentWord)) {
        finalString += currentWord + " ";
        currentWord = randomWord(currentWord);
      }

      // this last if before the loop is checked for another go around
      // makes sure that the newly selected word in the previous if
      // statement does not end in a period
      if (endsWithPunctuation(currentWord)) {
        finalString += currentWord;
      }

    } while (!endsWithPunctuation(currentWord));

    return finalString;
  }

  String randomWord(String word) {
    Random random = new Random();

    // the random object gets the next int within 0 and the size
    // of the word being passed in. all done in one single line
    return words.get(word).get(random.nextInt(0, words.get(word).size()));
  }

  public Markov() {
    prevWord = PUNCTUATION;
    words.put(PUNCTUATION, new ArrayList<>());
  }

  public void addFromFile(String filename) {
    File file = new File(filename);
    Scanner scanner = null;

    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist or was not able to open");
    }

    do {
      addLine(scanner.nextLine());
    } while (scanner.hasNextLine());
  }

  HashMap<String, ArrayList<String>> getWords() {
    return words;
  }

  @Override
  public String toString() {
    return "" + words;
  }
}