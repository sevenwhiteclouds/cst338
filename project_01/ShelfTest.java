package project_01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

  // info to populate shelf one
  private final int shelfNum1 = 1;
  private final String shelfSub1 = "sci-fi";

  // info to populate test shelf 2
  private final int shelfNum2 = 2;
  private final String shelfSub2  = "drugs";

  // constants to populate book 1 and book 2
  private final String isbn1 = "34-w-34";
  private final String title1 = "Dune";
  private final String sub1 = "sci-fi";
  private final int pages1 = 235;
  private final String author1 = "Frank Herbert";
  private final LocalDate due1 = LocalDate.now();

  private final String isbn2 = "1337";
  private final String title2 = "Headfirst Java";
  private final String sub2 = "education";
  private final int pages2 = 1337;
  private final String author2= "Grady Booch";
  private final LocalDate due2 = LocalDate.now().plusDays(1);

  // 2 books to test
  Book book1 = new Book(isbn1, title1, sub1, pages1, author1, due1);
  Book book2 = new Book(isbn2, title2, sub2, pages2, author2, due2);

  // scoping shelf so every test can see it
  Shelf shelfOne = null;

  @BeforeEach
  void setUp() {
    System.out.println("Starting test...");
    shelfOne = new Shelf(shelfNum1, shelfSub1);
  }

  @AfterEach
  void tearDown() {
    System.out.println("done.\n");
  }

  @Test
  void listBooks() {
    // setting all books to the same subject
    book2.setSubject(sub1);

    // adding all the books to the shelf
    shelfOne.addBook(book2);
    shelfOne.addBook(book2);
    shelfOne.addBook(book2);
    shelfOne.addBook(book1);
    shelfOne.addBook(book1);

    assertEquals("5 books on shelf: 1 : sci-fi\n" +
      "Dune by Frank Herbert ISBN: 34-w-34 2\n" +
      "Headfirst Java by Grady Booch ISBN: 1337 3", shelfOne.listBooks());

    System.out.println("list books: PASSED");
  }

  @Test
  void constructorTest() {
    Shelf constructorTest = new Shelf(shelfNum2, shelfSub2);
    assertNotEquals(null, constructorTest);
    assertEquals(shelfSub2, constructorTest.getSubject());
    assertEquals(shelfNum2, constructorTest.getShelfNumber());

    System.out.println("constructor test: PASSED");
  }

  @Test
  void equalsTest() {
    // shelf two has the same values as shelf one
    Shelf shelfTwo = new Shelf(shelfNum1, shelfSub1);
    // shelf three has different values than shelf one
    Shelf shelfThree = new Shelf(shelfNum2, shelfSub2);

    assertTrue(shelfOne.equals(shelfTwo));
    assertFalse(shelfOne.equals(shelfThree));
    System.out.println("equals: PASSED");
  }

  @Test
  void hashCodeTest() {
    // shelf two has the same values as shelf one
    Shelf shelfTwo = new Shelf(shelfNum1, shelfSub1);
    // shelf three has different values than shelf one
    Shelf shelfThree = new Shelf(shelfNum2, shelfSub2);

    assertEquals(shelfOne.hashCode(), shelfTwo.hashCode());
    assertNotEquals(shelfOne.hashCode(), shelfThree.hashCode());
    System.out.println("hash code: PASSED");
  }

  @Test
  void toStringTest() {
   assertEquals("1 : sci-fi", shelfOne.toString());
   System.out.println("to string: PASSED");
  }

  @Test
  void removeBook() {
    assertEquals(Code.BOOK_NOT_IN_INVENTORY_ERROR, shelfOne.removeBook(book1));

    assertEquals(Code.SUCCESS, shelfOne.addBook(book1));
    assertEquals(Code.SUCCESS, shelfOne.addBook(book1));
    assertEquals(Code.SUCCESS, shelfOne.addBook(book1));
    assertEquals(3, shelfOne.getBookCount(book1));

    assertEquals(Code.SUCCESS, shelfOne.removeBook(book1));
    assertEquals(2, shelfOne.getBookCount(book1));

    assertEquals(Code.SUCCESS, shelfOne.removeBook(book1));
    assertEquals(Code.SUCCESS, shelfOne.removeBook(book1));
    assertEquals(0, shelfOne.getBookCount(book1));

    System.out.println("remove book: PASSED");
  }

  @Test
  void addBook() {
    assertEquals(Code.SUCCESS, shelfOne.addBook(book1));
    assertEquals(1, shelfOne.getBookCount(book1));
    assertEquals(Code.SUCCESS, shelfOne.addBook(book1));
    assertEquals(2, shelfOne.getBookCount(book1));

    assertEquals(Code.SHELF_SUBJECT_MISMATCH_ERROR, shelfOne.addBook(book2));

    System.out.println("add book: PASSED");
  }
  @Test
  void getBookCount() {
    shelfOne.addBook(book1);
    assertEquals(1, shelfOne.getBookCount(book1));
    System.out.println("get book count: PASSED");
  }

  @Test
  void getShelfNumber() {
    assertEquals(shelfNum1, shelfOne.getShelfNumber());

    System.out.println("get shelf number: PASSED");
  }

  @Test
  void getSubject() {
    assertEquals(shelfSub1, shelfOne.getSubject());

    System.out.println("get subject number: PASSED");
  }

  @Test
  void getBooks() {
    Shelf shelfTwo = new Shelf(shelfNum1, sub1);
    assertEquals(shelfTwo.getBooks(), shelfOne.getBooks());

    shelfOne.addBook(book1);

    assertNotEquals(shelfTwo.getBooks(), shelfOne.getBooks());

    System.out.println("get books: PASSED");
  }

  @Test
  void setBooksTest() {
    Shelf shelfTwo = new Shelf(shelfNum1, sub1);
    shelfOne.addBook(book1);

    assertNotEquals(shelfOne.getBooks(), shelfTwo.getBooks());

    shelfTwo.setBooks(shelfOne.getBooks());
    assertEquals(shelfOne.getBooks(), shelfTwo.getBooks());

    System.out.println("set books: PASSED");
  }

  @Test
  void setSubject () {
    assertEquals(shelfSub1, shelfOne.getSubject());
    shelfOne.setSubject(shelfSub2);

    assertEquals(shelfSub2, shelfOne.getSubject());

    System.out.println("set subject: PASSED");
  }

  @Test
  void setShelfNum () {
    assertEquals(shelfNum1, shelfOne.getShelfNumber());
    shelfOne.setShelfNumber(shelfNum2);

    assertEquals(shelfNum2, shelfOne.getShelfNumber());

    System.out.println("set shelf num: PASSED");
  }
}