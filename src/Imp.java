import java.util.HashMap;

public class Imp extends Monster {
  public Imp(Integer maxHP, Integer xp, HashMap<String, Integer> items) {
    super(maxHP, xp, items);
  }

  @Override
  public String toString() {
    return "Imp has : " + super.toString();
  }
}
