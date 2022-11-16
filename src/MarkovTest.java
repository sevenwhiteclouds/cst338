import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MarkovTest {

  /*
   * Title: MarkovTest.java
   * Abstract: This java class fully tests that Markov.java works as intended.
   * Author: Keldin Maldonado
   * Date 2022 - 11 - 15
   */

  @BeforeEach
  void setUp() {
    System.out.print("testing...");
  }

  @AfterEach
  void tearDown() {
    File done = new File("testFile.txt");
    done.delete();

    System.out.println("PASSED.");
  }

  @Test
  void constructorTest() {
    Markov test = null;
    assertNull(test);
    test = new Markov();
    assertNotNull(test);
  }

  @Test
  void getWordsTest() {
    Markov testMarkov = new Markov();

    File file = new File("testFile.txt");
    BufferedWriter writer;
    try{
      file.createNewFile();
      writer = new BufferedWriter(new FileWriter("testFile.txt"));
      writer.write("hi, hello, sup, yes, no.");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    testMarkov.addFromFile("testFile.txt");

    HashMap<String, ArrayList<String>> tester = testMarkov.getWords();

    assertEquals(tester.size(), testMarkov.getWords().size());
    assertTrue(tester.containsKey("hi,"));
    assertTrue(tester.containsKey("__$"));

    assertEquals("hello,", tester.get("hi,").get(0));

  }

  @Test
  void getSentence() {
    Markov testMarkov = new Markov();

    File file = new File("testFile.txt");
    BufferedWriter writer;
    try{
      file.createNewFile();
      writer = new BufferedWriter(new FileWriter("testFile.txt"));
      writer.write("Hello there.");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    testMarkov.addFromFile("testFile.txt");
    assertEquals("Hello there.", testMarkov.getSentence());
  }

  @Test
  void randomWordTest() {
    Markov testMarkov = new Markov();

    File file = new File("testFile.txt");
    BufferedWriter writer;
    try{
      file.createNewFile();
      writer = new BufferedWriter(new FileWriter("testFile.txt"));
      writer.write("Sup sus.");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    testMarkov.addFromFile("testFile.txt");
    assertEquals("Sup", testMarkov.randomWord("__$"));
    assertEquals("sus.", testMarkov.randomWord("Sup"));

  }

  @Test
  void endsInPuncTest () {
    Markov testMarkov = new Markov();

    assertFalse(testMarkov.endsWithPunctuation("No"));
    assertTrue(testMarkov.endsWithPunctuation("DONE!"));
  }

  @Test
  void toStringTest() {
    Markov testMarkov = new Markov();
    assertEquals("{__$=[]}", testMarkov.toString());

    File file = new File("testFile.txt");
    BufferedWriter writer;
    try{
      file.createNewFile();
      writer = new BufferedWriter(new FileWriter("testFile.txt"));
      writer.write("Hello there.");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    testMarkov.addFromFile("testFile.txt");

    assertEquals("{Hello=[there.], __$=[Hello]}", testMarkov.toString());

  }
}