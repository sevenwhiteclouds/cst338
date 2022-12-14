package Monsters;

import Abilities.MeleeAttack;

import java.util.HashMap;

public class Imp extends Monster {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 - 11 - 29
   * Abstract: This class is part of a document code along for week 5 homework.
   * This is a simple class "Monsters.Imp" that extends the Monsters.Monster class and displays the current health
   */

  public Imp(Integer maxHP, Integer xp, HashMap<String, Integer> items) {
    super(maxHP, xp, items);

    Integer maxSrt = 15;
    Integer maxDef = 6;
    Integer maxAgi = 3;

    attack = new MeleeAttack(this);

    str = super.getAttribute(str, maxSrt);
    def = super.getAttribute(def, maxDef);
    agi = super.getAttribute(agi, maxAgi);
  }

  @Override
  public String toString() {
    return "Monsters.Imp has : " + super.toString();
  }
}
