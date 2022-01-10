import java.util.List;

public class SolitaireState {
  Stacks stacks;
  Middle middle;
  Hand hand;

  public SolitaireState(Deck deck) {
    this.stacks = new Stacks(deck.getCardsAt(0, 28));
    this.middle = new Middle();
    this.hand = new Hand(28, 52);
  }

  public SolitaireState(Stacks stacks, Middle middle, Hand hand) {
    this.stacks = stacks;
    this.middle = middle;
    this.hand = hand;
  }

  public List<SolitaireState> getNextStates() {
    return null;
  }

  public boolean isWon() {
    return false;
  }
}
