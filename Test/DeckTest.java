import org.junit.Test;

public class DeckTest {

  @Test
  public void defaultDeckTest() {
    Deck deck = new Deck();
    System.out.println(deck);
    deck.shuffle();
    System.out.println(deck);
  }
}
