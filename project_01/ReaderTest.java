// only required when inside folder with this name
// turned in without this
package project_01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

  /*
   * Author: Keldin Maldonado
   * Date: 2022 November 1
   * Description: The second file in the library project.
   * This class is a Plain Old Java Object (POJO) and represents a
   * reader in a library.
   */

  // initial reader is created with these values
  private final int initCardNumber = 2187;
  private final String initName = "Bob Barker";
  private final String initPhone = "831-582-4007";


  // change to these test values in the setter tests
  private final int testCardNumber = 9000;
  private final String testName = "Laurence Fishburn";
  private final String testPhone = "213-532-5827";

  // test book to add to the list
  private final Book testBook = new Book("1337", "Headfirst Java", "education",
    1337, "Grady Booch,", LocalDate.now());

  // test book 2
  private final Book testBookTwo = new Book("34-w-34", "Dune", "sci-fi",
    235, "Frank Herbert", LocalDate.now());

  // new container space to hold a Reader
  private Reader testReader = null;

  @BeforeEach
  void setUp() {
    System.out.println("testing...");

    // container is finally initialized here
    testReader = new Reader(initCardNumber, initName, initPhone);
  }

  @AfterEach
  void tearDown() {

    System.out.println("done\n");
  }

  @Test
  void constructorTest() {

    Reader testReaderTwo = new Reader(testCardNumber, testName, testPhone);
    assertNotNull(testReaderTwo);

    System.out.println("constructor passed");
  }

  @Test
  void addBook() {

    assertEquals(0, testReader.getBookCount());
    assertEquals(Code.SUCCESS, testReader.addBook(testBook));
    assertEquals(Code.BOOK_ALREADY_CHECKED_OUT_ERROR, testReader.addBook(testBook));
    assertEquals(Code.SUCCESS, testReader.addBook(testBookTwo));
    assertEquals(2, testReader.getBookCount());

    System.out.println("add book passed");
  }

  @Test
  void hasBook() {

    assertFalse(testReader.hasBook(testBook));
    testReader.addBook(testBook);
    assertTrue(testReader.hasBook(testBook));

    System.out.println("has book passed");
  }

  @Test
  void removeBook() {

    assertFalse(testReader.hasBook(testBook));
    testReader.addBook(testBook);

    assertTrue(testReader.hasBook(testBook));

    testReader.removeBook(testBook);
    assertFalse(testReader.hasBook(testBook));

    assertEquals(Code.READER_DOESNT_HAVE_BOOK_ERROR, testReader.removeBook(testBook));

    System.out.println("remove book passed");
  }

  @Test
  void getBooks() {

    assertEquals(0,testReader.getBooks().size());
    testReader.addBook(testBook);
    assertEquals(1,testReader.getBooks().size());
    assertEquals(testBook, testReader.getBooks().get(0));

    System.out.println("get books passed");
  }

  @Test
  void getBookCount() {

    assertEquals(0, testReader.getBookCount());
    testReader.addBook(testBook);
    assertEquals(1, testReader.getBookCount());
    System.out.println("get book count passed");
  }

  @Test
  void getName() {

    assertNotNull(testReader);
    assertEquals(initName, testReader.getName());
    System.out.println("get name passed");
  }

  @Test
  void getPhone() {

    assertNotNull(testReader);
    assertEquals(initPhone, testReader.getPhone());
    System.out.println("get phone passed");
  }

  @Test
  void getCardNumber() {

    assertNotNull(testReader);
    assertEquals(initCardNumber, testReader.getCardNumber());
    System.out.println("get card number passed");
  }

  @Test
  void setCardNumber() {

    assertNotNull(testReader);
    assertEquals(initCardNumber, testReader.getCardNumber());
    testReader.setCardNumber(testCardNumber);
    assertEquals(testCardNumber, testReader.getCardNumber());

    System.out.println("set card number passed");
  }

  @Test
  void setName() {

    assertNotNull(testReader);
    assertEquals(initName, testReader.getName());
    testReader.setName(testName);
    assertEquals(testName, testReader.getName());

    System.out.println("set card name passed");
  }

  @Test
  void setPhone() {

    assertNotNull(testReader);
    assertEquals(initPhone, testReader.getPhone());
    testReader.setPhone(testPhone);
    assertEquals(testPhone, testReader.getPhone());

    System.out.println("set phone number passed");
  }

  @Test
  void setBooks() {

    Reader testReaderTwo  = new Reader(testCardNumber, testName, testPhone);
    assertEquals(0, testReaderTwo.getBookCount());
    testReaderTwo.addBook(testBookTwo);
    assertEquals(1, testReaderTwo.getBookCount());

    assertEquals(0, testReader.getBookCount());
    testReader.setBooks(testReaderTwo.getBooks());
    assertEquals(1, testReader.getBookCount());

    System.out.println("set books passed");
  }

  @Test
  void testToString() {

    testReader.addBook(testBook);
    testReader.addBook(testBookTwo);

    assertEquals("Bob Barker (#2187) has checked out " +
      "[Headfirst Java by Grady Booch, ISBN: 1337, " +
      "Dune by Frank Herbert ISBN: 34-w-34]", testReader.toString());

    System.out.println("to string test passed");
  }

  @Test
  void testEquals() {

    Reader testReaderTwo = new Reader(initCardNumber, initName, initPhone);

    assertTrue(testReader.equals(testReaderTwo));

    System.out.println("equals test passed");
  }

  @Test
  void testHashCode() {

    Reader testReaderTwo = new Reader(testCardNumber, initName, initPhone);
    assertNotEquals(testReaderTwo.hashCode(), testReader.hashCode());

    testReaderTwo.setCardNumber(initCardNumber);
    assertEquals(testReaderTwo.hashCode(), testReader.hashCode());

    System.out.println("hash code passed");
  }
}