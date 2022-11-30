import java.util.HashMap;

public class Imp extends Monster {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 - 11 - 29
   * Abstract: This class is part of a document code along for week 5 homework.
   * This is a simple class "Imp" that extends the Monster class and displays the current health
   */

  public Imp(Integer maxHP, Integer xp, HashMap<String, Integer> items) {
    super(maxHP, xp, items);
  }

  @Override
  public String toString() {
    return "Imp has : " + super.toString();
  }
}
