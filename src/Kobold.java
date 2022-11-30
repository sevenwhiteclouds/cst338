import java.util.HashMap;

public class Kobold extends Monster {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 - 11 - 29
   * Abstract: This class is part of a document code along for week 5 homework.
   * This is a simple class "Kobold" that extends the Monster class and displays the current health
   */

  public Kobold(Integer maxHP, Integer xp, HashMap<String, Integer> items) {
    super(maxHP, xp, items);
  }

  @Override
  public String toString() {
    return "Kobold has : " + super.toString();
  }
}
