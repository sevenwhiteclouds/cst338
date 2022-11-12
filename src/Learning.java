import java.util.ArrayList;
import java.util.List;

public class Learning {

  /*
   * Name: Keldin Maldonado
   * Date: 2022 - November - 1
   * Explanation: Practice reading and translating UML to java
   * and to practice writing and running Unit Tests.
   */

  private String name;
  private Integer count;
  private List<String> friends;

  public Learning() {
    name = "Keldin";
    count = 1;
    friends = new ArrayList<>();
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getName() {
    return name;
  }
}
