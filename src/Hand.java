import java.util.ArrayList;
import java.util.List;

public class Hand {

  private List<Card> held;
  private List<Card> down;

  public Hand(List<Card> held, List<Card> down) {
    this.held = new ArrayList<>(held);
    this.down = new ArrayList<>(down);
  }

  public Hand(Deck deck) {
    this(deck.getCardsAt(28, 52), new ArrayList<>());
  }

  public Hand cycle() {
    List<Card> newHeld = new ArrayList<>();
    List<Card> newDown = new ArrayList<>(down);
    if (held.size() >= 3) {
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

  //  plays the card in the last index of the down pile
  public Hand playTopCard() {
    if (hasTopCard()) {
      List<Card> newDown = new ArrayList<>(down);
      newDown.remove(down.size() - 1);
      return new Hand(held, newDown);
    }
    throw new IllegalStateException("bark?");
  }

  public boolean hasTopCard() {
    return down.size() > 0;
  }

  // the top card in the down pile is in the last index
  public Card getTopCard() {
    if (hasTopCard()) {
      return down.get(down.size() - 1);
    }
    throw new IllegalStateException("bark?");
  }

  @Override
  public String toString() {
    return "" + held + down;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Hand hand = (Hand) o;

    if (!held.equals(hand.held)) {
      return false;
    }
    return down.equals(hand.down);
  }

  @Override
  public int hashCode() {
    int result = held.hashCode();
    result = 31 * result + down.hashCode();
    return result;
  }
}
