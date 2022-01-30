import java.util.ArrayList;
import java.util.List;

public class SolitaireState {

  private final Board board;
  private final Middle middle;
  private final Hand hand;

  public SolitaireState(Deck deck) {
    this.board = new Board(deck);
    this.middle = new Middle();
    this.hand = new Hand(deck);
  }

  public SolitaireState(Board board, Middle middle, Hand hand) {
    this.board = board;
    this.middle = middle;
    this.hand = hand;
  }

  public List<SolitaireState> getNextStates() {
    List<SolitaireState> newStates = new ArrayList<>();
    newStates.addAll(playFromStacks());
    newStates.addAll(playFromHand());

    return newStates;
  }

  public List<SolitaireState> playFromHand() {
    List<SolitaireState> newStates = new ArrayList<>();

    if (hand.hasTopCard()) {

      // play from hand to middle
      if (middle.canPlay(hand.getTopCard())) {
        newStates.add(
            new SolitaireState(board, middle.play(hand.getTopCard()), hand.playTopCard()));
      }
      // play from hand to stacks
      List<Board> newBoards = board.play(hand.getTopCard());
      if (newBoards.size() > 0) {
        for (Board newBoard : newBoards) {
          newStates.add(new SolitaireState(newBoard, middle, hand.playTopCard()));
        }
      }
    }
    // cycle hand
    newStates.add(new SolitaireState(board, middle, hand.cycle()));

    return newStates;
  }

  public List<SolitaireState> playFromStacks() {
    List<SolitaireState> newStates = new ArrayList<>();
    // play from piles to middle
    for (int stackNumber = 0; stackNumber < board.getStacks().size(); stackNumber++) {
      Stack stack = board.getStacks().get(stackNumber);
      if (stack.notEmpty() && middle.canPlay(stack.getBottom())) {
        Middle newMiddle = middle.play(stack.getBottom());
        Stack newStack = stack.playBottom();
        List<Stack> newStacks = new ArrayList<>(board.getStacks());
        newStacks.set(stackNumber, newStack);
        Board newBoard = new Board(newStacks);
        newStates.add(new SolitaireState(newBoard, newMiddle, hand));
      }
    }
    // move piles
    List<Board> newBoards = board.moveStacks();
    for (Board newBoard : newBoards) {
      newStates.add(new SolitaireState(newBoard, middle, hand));
    }

    return newStates;
  }

  public boolean isWon() {
    return middle.numPlayed() == 52;
  }

  @Override
  public String toString() {
    String result = middle.toString() + "\n";
    result += board.toString();
    result += hand.toString() + "\n";
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SolitaireState that = (SolitaireState) o;

    if (!board.equals(that.board)) {
      return false;
    }
    if (!middle.equals(that.middle)) {
      return false;
    }
    return hand.equals(that.hand);
  }

  @Override
  public int hashCode() {
    int result = board.hashCode();
    result = 13 * result + middle.hashCode();
    result = 31 * result + hand.hashCode();
    return result;
  }
}
