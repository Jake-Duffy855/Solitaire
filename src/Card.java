public class Card {

  Suit suit;
  CardValue value;

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

  @Override
  public String toString() {
    return "" + value + suit;
  }
}
