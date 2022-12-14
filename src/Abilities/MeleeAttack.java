package Abilities;

import Abilities.Attack;
import Monsters.Monster;

public class MeleeAttack implements Attack {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 -12 - 6
   * Abstract: WK06HW00: Strategy Part 01/02
   * This is an implementation of Abilities.Attack.java.
   */

  Monster attacker;

  public MeleeAttack(Monster attacker) {
    this.attacker = attacker;
  }

  @Override
  public Integer attack(Monster target) {
    String message = attacker + " uses a melee attack on " + target;

    System.out.println(message);

    return attacker.getStr() - target.getDef();
  }
}