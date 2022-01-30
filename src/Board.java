import java.util.ArrayList;
import java.util.List;

public class Board {

  private List<Stack> stacks;

  public Board(List<Stack> stacks) {
    this.stacks = stacks;
  }

  public Board(Deck deck) {
    this(generateStacks(deck.getCardsAt(0, 28)));
  }

  private static List<Stack> generateStacks(List<Card> cards) {
    List<Stack> newStacks = new ArrayList<>();
    for (int stackNumber = 1; stackNumber <= 7; stackNumber++) {
      int startIndex = stackNumber * (stackNumber - 1) / 2;
      List<Card> inStack = cards.subList(startIndex, startIndex + stackNumber);
      newStacks.add(new Stack(inStack));
    }
    return newStacks;
  }

  public List<Stack> getStacks() {
    return new ArrayList<>(stacks);
  }

  public List<Board> play(Card card) {
    List<Board> newBoards = new ArrayList<>();
    for (int stackNumber = 0; stackNumber < stacks.size(); stackNumber++) {
      if (stacks.get(stackNumber).canPlay(card)) {
        Stack newStack = stacks.get(stackNumber).play(card);
        List<Stack> newStacks = new ArrayList<>(stacks);
        newStacks.remove(stackNumber);
        newStacks.add(stackNumber, newStack);
        newBoards.add(new Board(newStacks));
      }
    }
    return newBoards;
  }

  public List<Board> moveStacks() {
    List<Board> newBoards = new ArrayList<>();
    for (int fromStackNumber = 0; fromStackNumber < stacks.size(); fromStackNumber++) {
      Stack fromStack = stacks.get(fromStackNumber);
      if (fromStack.notEmpty()) {
        for (int toStackNumber = 0; toStackNumber < stacks.size(); toStackNumber++) {
          Stack toStack = stacks.get(toStackNumber);
          if (fromStackNumber != toStackNumber && toStack.canPlay(fromStack)) {
              Stack newToStack = toStack.play(fromStack);
              Stack newFromStack = fromStack.moveUp();
              List<Stack> newStacks = new ArrayList<>(stacks);
              newStacks.set(fromStackNumber, newFromStack);
              newStacks.set(toStackNumber, newToStack);
              newBoards.add(new Board(newStacks));
          }
        }
      }
    }
    return newBoards;
  }

  @Override
  public String toString() {
    String result = "";
    for (Stack s : stacks) {
      result += s.toString() + "\n";
    }
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

    Board board = (Board) o;

    return stacks.equals(board.stacks);
  }

  @Override
  public int hashCode() {
    return stacks.hashCode();
  }
}
