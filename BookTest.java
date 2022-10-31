import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BookTest {
  @BeforeEach
  void setUp() {

  }

  @AfterEach
  void tearDown() {

  }

  @Test
  void constructorTest() {
    Book book = null;
    assertNull(book);
    book = new Book(
      "1337",
        "Headfirst Java",
        "education",
      1337,
      "Grady Booc",
      LocalDate.now()
      );
    assertNotNull(book);
  }

  @Test
  void getIsbn() {

  }

  @Test
  void setIsbn() {

  }

  @Test
  void getTitle() {

  }

  @Test
  void setTitle() {

  }

  @Test
  void getSubject() {

  }

  @Test
  void setSubject() {

  }

  @Test
  void getPageCount() {

  }

  @Test
  void setPageCount() {

  }

  @Test
  void getAuthor() {

  }

  @Test
  void setAuthor() {

  }

  @Test
  void getDueDate() {

  }

  @Test
  void setDueDate() {

  }

  @Test
  void testEquals() {

  }

  @Test
  void testToString() {

  }
}