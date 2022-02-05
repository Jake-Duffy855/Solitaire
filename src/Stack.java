import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stack {

  private List<Card> down;
  private List<Card> up;

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

  public boolean notEmpty() {
    return up.size() > 0;
  }

  public boolean hasDown() {
    return down.size() > 0;
  }

  public Card getBottom() {
    if (notEmpty()) {
      return up.get(up.size() - 1);
    } else {
      throw new IllegalStateException("No bottom???");
    }
  }

  public boolean canPlay(Card card) {
    if (notEmpty()) {
      return getBottom().isAbove(card);
    }
    return card.getValue().equals(CardValue.KING);
  }

  public boolean canPlay(Stack stack) {
    if (notEmpty()) {
      return getBottom().isAbove(stack.getTop());
    }
    return stack.getTop().getValue().equals(CardValue.KING) && stack.hasDown();
  }

  public Stack play(Card card) {
    if (canPlay(card)) {
      List<Card> newUp = new ArrayList<>(up);
      newUp.add(card);
      return new Stack(new ArrayList<>(down), newUp);
    }
    throw new IllegalArgumentException("noooooooooo!");
  }

  public Stack play(Stack other) {
    List<Card> newUp = new ArrayList<>(up);
    newUp.addAll(other.up);
    return new Stack(new ArrayList<>(down), newUp);
  }

  public Stack playBottom() {
    if (notEmpty()) {
      if (up.size() > 1) {
        return new Stack(down, up.subList(0, up.size() - 1));
      } else if (down.size() > 0) {
        return new Stack(down.subList(0, down.size() - 1),
            down.subList(down.size() - 1, down.size()));
      } else {
        return new Stack(new ArrayList<>(), new ArrayList<>());
      }
    } else {
      throw new IllegalStateException("No bottom???");
    }
  }

  public Stack moveUp() {
    if (notEmpty()) {
      if (down.size() > 0) {
        return new Stack(down.subList(0, down.size() - 1),
            down.subList(down.size() - 1, down.size()));
      } else {
        return new Stack(new ArrayList<>(), new ArrayList<>());
      }
    } else {
      throw new IllegalStateException("No bottom???");
    }
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
//    int result = down.hashCode();
//    result = 31 * result + up.hashCode();
//    return result;
    return Objects.hash(down, up);
  }
}
