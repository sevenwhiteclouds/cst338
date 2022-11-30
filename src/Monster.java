import java.util.HashMap;

public class Monster {
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