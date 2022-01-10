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
}
