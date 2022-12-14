package Monsters;

import Abilities.Attack;

import java.util.HashMap;
import java.util.Random;

public class Monster {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 - 11 - 29
   * Abstract: This class is part of a document code along for week 5 homework.
   * This is a parent class "Monsters.Monster" from which the other classes in this project inherit from.
   * When called, the toString prints out the current health
   */

  Integer agi = 10;
  Integer def = 10;
  Integer str = 10;
  Attack attack;
  private Integer hp;
  private Integer xp = 10;
  private Integer maxHP;
  private HashMap<String, Integer> items = new HashMap<>();

  public Monster(Integer maxHP, Integer xp, HashMap<String, Integer> items) {
    this.maxHP = maxHP;
    hp = this.maxHP;
    this.xp = xp;
    this.items = items;
  }

  Integer getAttribute(Integer min, Integer max) {
    Random rand = new Random();

    if (min > max) {
      Integer temp = min;
      min = max;
      max = temp;
    }

    return rand.nextInt(max-min) + min;
  }

  boolean takeDamage(Integer damage) {
    if (damage > 0) {
      if (getHp() > 0) {
        setHp(getHp() - damage);
      }
    }

    if (getHp() > 0) {
      System.out.println("The creature was hit for " + damage + " damage");
    }
    else {
      System.out.println("Oh no! the creator has perished");
    }

    System.out.println(this.toString());

    if (getHp() > 0) {
      return true;
    }

    return false;
  }

  public Integer attackTarget(Monster monster) {
    Integer returnedValue;

    monster.takeDamage(returnedValue = attack.attack(monster));

    return returnedValue;
  }

  public Integer getAgi() {
    return agi;
  }


  public Integer getDef() {
    return def;
  }

  public Integer getStr() {
    return str;
  }

  public Attack getAttack() {
    return attack;
  }

  public Integer getHp() {
    return hp;
  }

  public void setHp(Integer hp) {
    this.hp = hp;
  }

  public Integer getXp() {
    return xp;
  }

  public HashMap<String, Integer> getItems() {
    return items;
  }

  public void setItems(HashMap<String, Integer> items) {
    this.items = items;
  }

  public Integer getMaxHP() {
    return maxHP;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Monster monster = (Monster) o;

    if (getHp() != null ? !getHp().equals(monster.getHp()) : monster.getHp() != null) return false;
    if (getXp() != null ? !getXp().equals(monster.getXp()) : monster.getXp() != null) return false;
    if (getMaxHP() != null ? !getMaxHP().equals(monster.getMaxHP()) : monster.getMaxHP() != null) return false;
    return getItems() != null ? getItems().equals(monster.getItems()) : monster.getItems() == null;
  }

  @Override
  public int hashCode() {
    int result = getHp() != null ? getHp().hashCode() : 0;
    result = 31 * result + (getXp() != null ? getXp().hashCode() : 0);
    result = 31 * result + (getMaxHP() != null ? getMaxHP().hashCode() : 0);
    result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
    return result;
  }

  // TODO: still need to do the to string handcoded method here

  @Override
  public String toString() {
    return "hp=" + hp + "/" + maxHP;
  }
}