import java.util.List;

public class SolitaireState {
  private final Board board;
  private final Middle middle;
  private final Hand hand;

  public SolitaireState(Deck deck) {
    this.board = new Board(deck.getCardsAt(0, 28));
    this.middle = new Middle();
    this.hand = new Hand(deck.getCardsAt(28, 52));
  }

  public SolitaireState(Board board, Middle middle, Hand hand) {
    this.board = board;
    this.middle = middle;
    this.hand = hand;
  }

  public List<SolitaireState> getNextStates() {
    return null;
  }

  public boolean isWon() {
    return middle.numPlayed() == 52;
  }
}
