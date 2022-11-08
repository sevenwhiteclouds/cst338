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
    shelfOne = new Shelf(shelfNum1, shelfSub1);
  }

  @AfterEach
  void tearDown() {

  }

  // TODO: this test is not implemented right
  @Test
  void listBooks() {

    book2.setSubject(sub1);
    shelfOne.addBook(book2);
    shelfOne.addBook(book1);

    System.out.println("list books: PASSED");
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
    System.out.println("get book count: PASSED");
  }

  @Test
  void getSelfNumber() {
  }

  @Test
  void getSubject() {
  }
}