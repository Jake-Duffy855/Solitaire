import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
  List<Card> cards;
  Random rand;

  public Deck(List<Card> cards, Random rand) {
    this.cards = new ArrayList<>(cards);
    this.rand = rand;
  }

  public Deck(List<Card> cards) {
    this(cards, new Random());
  }

  public Deck(Random rand) {
    this(getDefaultOrder(), rand);
  }

  public Deck() {
    this(getDefaultOrder(), new Random());
  }

  public void shuffle() {
    Collections.shuffle(cards, rand);
  }

  private static List<Card> getDefaultOrder() {
    ArrayList<Card> defaultOrder = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (CardValue value : CardValue.values()) {
        defaultOrder.add(new Card(suit, value));
      }
    }
    return defaultOrder;
  }

  @Override
  public String toString() {
    return cards.toString();
  }

  /**
   * Get cards in deck from start index to end indecx
   * @param start start index (inclusive)
   * @param end end index (exclusive)
   * @return list of cards
   */
  public List<Card> getCardsAt(int start, int end) {
    return cards.subList(start, end);
  }
}
