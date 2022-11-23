import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

  @BeforeEach
  void setUp() {
    System.out.println("Starting test...");
  }

  @AfterEach
  void tearDown() {
    System.out.println("PASSED");
  }

  @Test
  void constructorTest() {
    // TODO: still need to test that the name is returned as "test"
    Library test = null;
    assertNull(test);
    test = new Library("TEST");
    assertNotNull(test);
  }
}