import java.util.Objects;

public class Card {

  private final Suit suit;
  private final CardValue value;
  private final SuitColor color;

  public Card(Suit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
    if (suit.equals(Suit.SPADES) || suit.equals(Suit.CLUBS)) {
      this.color = SuitColor.BLACK;
    } else {
      this.color = SuitColor.RED;
    }
  }

  public Suit getSuit() {
    return suit;
  }

  public CardValue getValue() {
    return value;
  }

  public Card nextCardUp() {
    if (value.equals(CardValue.KING)) {
      throw new IllegalStateException("King has no next card up");
    }
    return new Card(suit, CardValue.fromValue(value.getValue() + 1));
  }

  public Card nextCardDown() {
    if (value.equals(CardValue.ACE)) {
      throw new IllegalStateException("Ace has no next card down");
    }
    return new Card(suit, CardValue.fromValue(value.getValue() - 1));
  }

  public boolean isAbove(Card card) {
    boolean oppositeColors = !color.equals(card.color);
    boolean oneHigher = this.value.getValue() - 1 == card.value.getValue();
    return oppositeColors && oneHigher;
  }

  @Override
  public String toString() {
    return "" + value + suit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return suit == card.suit && value == card.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(suit, value);
  }
}
