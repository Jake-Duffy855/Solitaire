import java.util.ArrayList;
import java.util.List;

public class Stack {
  List<Card> down;
  List<Card> up;

  public Stack(List<Card> down, List<Card> up) {
    this.down = new ArrayList<>(down);
    this.up = new ArrayList<>(up);
  }

  public Stack(List<Card> cards) {
    this(cards.subList(0, cards.size() - 1), cards.subList(cards.size() - 1, cards.size()));
  }

  public Card getTop() {
    return up.get(0);
  }

  public Card getBottom() {
    return up.get(up.size() - 1);
  }

  public boolean canPlay(Card card) {
    return getBottom().isAbove(card);
  }

  public Stack play(Card card) {
    if (canPlay(card)) {
      List<Card> newUp = new ArrayList<>(up);
      newUp.add(card);
      return new Stack(new ArrayList<>(down), newUp);
    }
    throw new IllegalArgumentException("noooooooooo!");
  }

  @Override
  public String toString() {
    return down + "|" + up;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Stack stack = (Stack) o;

    if (!down.equals(stack.down)) {
      return false;
    }
    return up.equals(stack.up);
  }

  @Override
  public int hashCode() {
    int result = down.hashCode();
    result = 31 * result + up.hashCode();
    return result;
  }
}
