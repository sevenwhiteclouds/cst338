// this is only needed if it's inside the homework folder
// turned in the assignment without this
package homeworks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LearningTest {

  /*
   * Name: Keldin Maldonado
   * Date: 2022 - November - 1
   * Explanation: Practice reading and translating UML to java
   * and to practice writing and running Unit Tests.
   */

  Learning learningTest = null;

  @BeforeEach
  void setUp() {
    System.out.println("Before each");
    learningTest = new Learning();
    /*
     Default values
     name: "Keldin";
     count = 1;
     friends = new ArrayList<>();
     */
  }

  @AfterEach
  void tearDown() {
    System.out.println("After Each");
  }

  @Test
  void getCount() {
    System.out.println("Get Count test");
    assertEquals(1, learningTest.getCount());
    assertNotEquals(2, learningTest.getCount());

  }

  @Test
  void setCount() {
    System.out.println("Set Count Test");
    assertEquals(1, learningTest.getCount());
    learningTest.setCount(42);
    assertNotEquals(1, learningTest);
    assertEquals(42, learningTest.getCount());
  }

  @Test
  void getName() {
    System.out.println("Get name test");
    assertEquals("Keldin", learningTest.getName());
  }
}
