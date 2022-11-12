import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BookTest {

  /*
   * Author: Keldin Maldonado
   * Date: 2022 October 28
   * Description: The first file in the library project.
   * This class is a Plain Old Java Object (POJO) and represents a
   * book in a library.
   */

  private Book testBook = null;

  /*
   * using variable for on the fly switching instead of hard coding
   * 6 variable are used to create the initial state of the book
   * these values will then be changed by the 6 "testing" variables
   * in each set test that is performed
   * also includes full string
   */
  private final String initIsbn = "34-w-34";
  private final String initTitle = "Dune";
  private final String initSubject = "sci-fi";
  private final int initPageCount = 235;
  private final String initAuthor= "Frank Herbert";
  private final LocalDate initDueDate = LocalDate.now();

  private final String testIsbn = "1337";
  private final String testTitle = "Headfirst Java";
  private final String testSubject = "education";
  private final int testPageCount = 1337;
  private final String testAuthor= "Grady Booch";
  private final LocalDate testDueDate = LocalDate.now().plusDays(1);

  private final String fullBook = "Dune by Frank Herbert ISBN: 34-w-34";

  @BeforeEach
  void setUp() {
    System.out.println("testing...");

    testBook = new Book(initIsbn, initTitle, initSubject , initPageCount, initAuthor, initDueDate);
  }

  @AfterEach
  void tearDown() {
    System.out.println("done\n");
  }

  @Test
  void constructorTest() {
    Book book = null;
    assertNull(book);
    book = new Book(testIsbn, testTitle, testSubject, testPageCount, testAuthor, testDueDate);
    assertNotNull(book);

    System.out.println("constructor passed");
  }

  @Test
  void getIsbn() {
    assertNotNull(testBook);
    assertEquals(initIsbn, testBook.getIsbn());
    System.out.println("get isbn passed");
  }

  @Test
  void getTitle() {
    assertNotNull(testBook);
    assertEquals(initTitle, testBook.getTitle());
    System.out.println("get title passed");
  }

  @Test
  void getSubject() {
    assertNotNull(testBook);
    assertEquals(initSubject, testBook.getSubject());
    System.out.println("get subject passed");
  }

  @Test
  void getPageCount() {
    assertNotNull(testBook);
    assertEquals(initPageCount, testBook.getPageCount());
    System.out.println("get page count passed");
  }

  @Test
  void getAuthor() {
    assertNotNull(testBook);
    assertEquals(initAuthor, testBook.getAuthor());
    System.out.println("get author passed");
  }

  @Test
  void getDueDate() {
    assertNotNull(testBook);
    assertEquals(initDueDate, testBook.getDueDate());
    System.out.println("get due date passed");
  }

  @Test
  void setIsbn() {
    assertNotNull(testBook);
    assertEquals(initIsbn, testBook.getIsbn());
    testBook.setIsbn(testIsbn);
    assertEquals(testIsbn, testBook.getIsbn());

    System.out.println("set isbn passed");
  }

  @Test
  void setTitle() {
    assertNotNull(testBook);
    assertEquals(initTitle, testBook.getTitle());
    testBook.setTitle(testTitle);
    assertEquals(testTitle, testBook.getTitle());

    System.out.println("set title passed");
  }

  @Test
  void setSubject() {
    assertNotNull(testBook);
    assertEquals(initSubject, testBook.getSubject());
    testBook.setSubject(testSubject);
    assertEquals(testSubject, testBook.getSubject());

    System.out.println("set subject passed");
  }

  @Test
  void setPageCount() {
    assertNotNull(testBook);
    assertEquals(initPageCount, testBook.getPageCount());
    testBook.setPageCount(testPageCount);
    assertEquals(testPageCount, testBook.getPageCount());

    System.out.println("set page count passed");
  }

  @Test
  void setAuthor() {
    assertNotNull(testBook);
    assertEquals(initAuthor, testBook.getAuthor());
    testBook.setAuthor(testAuthor);
    assertEquals(testAuthor, testBook.getAuthor());

    System.out.println("set author passed");
  }

  @Test
  void setDueDate() {
    assertNotNull(testBook);
    assertEquals(initDueDate, testBook.getDueDate());
    testBook.setDueDate(testDueDate);
    assertEquals(testDueDate, testBook.getDueDate());

    System.out.println("set due date passed");
  }

  @Test
  void testEquals() {
    assertNotNull(testBook);
    Book difBook = new Book(testIsbn, testTitle, testSubject, testPageCount, testAuthor, testDueDate);
    assertFalse(testBook.equals(difBook));
    Book difBook2 = new Book(testIsbn, testTitle, testSubject, testPageCount, testAuthor, testDueDate);
    assertTrue(difBook.equals(difBook2));

    System.out.println("test equality passed");
  }

  @Test
  void testToString() {
    assertNotNull(testBook);
    assertEquals(fullBook, testBook.toString());

    System.out.println("to string passed");
  }
}
