import java.util.ArrayList;
import java.util.List;

public class Hand {

  List<Card> held;
  List<Card> down;

  public Hand(List<Card> held, List<Card> down) {
    this.held = new ArrayList<>(held);
    this.down = new ArrayList<>(down);
  }

  public Hand(List<Card> cards) {
    this(cards, new ArrayList<>());
  }

  public Hand Cycle() {
    List<Card> newHeld = new ArrayList<>();
    List<Card> newDown = new ArrayList<>(down);
    if (held.size() > 3) {
      newHeld = held.subList(3, held.size());
      newDown.addAll(held.subList(0, 3));
    } else if (held.size() < 3 && held.size() > 0) {
      newHeld = new ArrayList<>();
      newDown.addAll(held);
    } else {
      newHeld = new ArrayList<>(down);
      newDown = new ArrayList<>();
    }
    return new Hand(newHeld, newDown);
  }

  public boolean hasTopCard() {
    return down.size() > 0;
  }

  public Card getTopCard() {
    if (down.size() > 0) {
      return down.get(down.size() - 1);
    }
    throw new IllegalStateException("bark?");
  }
}
