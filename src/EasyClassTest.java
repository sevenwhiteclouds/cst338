import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EasyClassTest {

  /*
   * Name: Keldin Maldonado
   * Date: 2022 - November - 1
   * Explanation: Practice reading and translating UML to java
   * and to practice writing and running Unit Tests.
   */

  // to test overloaded constructor with only two arguments
  private EasyClass twoArgs = null;

  // to test overloaded constructor with three arguments
  private EasyClass threeArgs = null;

  Random randomNum;

  @BeforeEach
  void setUp() {
    System.out.println("Starting test...");
    twoArgs = new EasyClass("2 args", 99.99);
    /*
     * Values being set with the constructor with 2 arguments
     * name: "2 args"
     * count: null
     * score: 99.99
     */

    threeArgs = new EasyClass("3 args", 3, 100.00);
    /*
     * Values being set with the constructor with 3 arguments
     * name: "3 args"
     * count: 3
     * score: 100.00
     */

    randomNum = new Random();
  }

  @AfterEach
  void tearDown() {
    System.out.println("Test completed.\n");

    randomNum = null;
  }

  @Test
  void getName() {
    assertEquals("2 args", twoArgs.getName());
    assertEquals("3 args", threeArgs.getName());
    System.out.println("Get name test: passed for two constructors.");
  }

  @Test
  void getCount() {
    assertEquals(null, twoArgs.getCount());
    assertEquals(3, threeArgs.getCount());
    System.out.println("Get count test: passed for two constructors.");
  }

  @Test
  void getScore() {
    assertEquals(99.99, twoArgs.getScore());
    assertEquals(100.00, threeArgs.getScore());
    System.out.println("Get score test: passed for two constructors.");
  }

  @Test
  void setName() {
    assertEquals("2 args", twoArgs.getName());
    twoArgs.setName("2 args -- modified");
    assertNotEquals("2 args", twoArgs.getName());
    assertEquals("2 args -- modified", twoArgs.getName());

    assertEquals("3 args", threeArgs.getName());
    threeArgs.setName("3 args -- modified");
    assertNotEquals("3 args", threeArgs.getName());
    assertEquals("3 args -- modified", threeArgs.getName());

    System.out.println("Set name test: passed for two constructors.");
  }

  @Test
  void setCount() {
    Integer newInt = randomNum.nextInt();
    assertEquals(null, twoArgs.getCount());
    twoArgs.setCount(newInt);
    assertNotEquals(null, twoArgs.getCount());
    assertEquals(newInt, twoArgs.getCount());

    newInt = randomNum.nextInt();
    assertEquals(3, threeArgs.getCount());
    threeArgs.setCount(newInt);
    assertNotEquals(3, threeArgs.getCount());
    assertEquals(newInt, threeArgs.getCount());

    System.out.println("Set count test: passed for two constructors.");
  }

  @Test
  void setScore() {
    Double newDouble = randomNum.nextDouble();
    assertEquals(99.99, twoArgs.getScore());
    twoArgs.setScore(newDouble);
    assertNotEquals(99.99, twoArgs.getScore());
    assertEquals(newDouble, twoArgs.getScore());

    newDouble = randomNum.nextDouble();
    assertEquals(100.00, threeArgs.getScore());
    threeArgs.setScore(newDouble);
    assertNotEquals(100.00, threeArgs.getScore());
    assertEquals(newDouble, threeArgs.getScore());

    System.out.println("Set score test: passed for two constructors.");
  }
}