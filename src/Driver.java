import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Driver {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 - 11 - 29
   * Abstract: This class is part of a document code along for week 5 homework.
   * This is the driver code.
   */

  public static void main(String[] args) {
    HashMap<String, Integer> items = new HashMap<>();
    items.put("gold", 5);
    List<Monster> monsters = new ArrayList<>();
    monsters.add(new Imp(15, 20, items));
    monsters.add(new Kobold(1, 5, items));

    for (Monster m : monsters) {
      System.out.println(m);
    }
  }
}
