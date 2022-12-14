package Abilities;

import Monsters.Monster;

public interface Attack extends Ability {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 -12 - 6
   * Abstract: WK06HW00: Strategy Part 01/02
   * This is an interface that is organized under the Abilities.Ability interface.
   */

  public Integer attack(Monster target);
}