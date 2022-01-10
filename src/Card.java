import java.util.Objects;

public class Card {

  private final Suit suit;
  private final CardValue value;

  public Card(Suit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
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
