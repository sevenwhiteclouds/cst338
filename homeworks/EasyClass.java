// this is only needed if it's inside the homework folder
// turned in the assignment without this
package homeworks;

public class EasyClass {

  /*
   * Name: Keldin Maldonado
   * Date: 2022 - November - 1
   * Explanation: Practice reading and translating UML to java
   * and to practice writing and running Unit Tests.
   */

  private String name;
  private Integer count;
  private Double score;

  public EasyClass(String name, Integer count, Double score) {
    this.name = name;
    this.count = count;
    this.score = score;
  }

  public EasyClass(String name, Double score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }
}