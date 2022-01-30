import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

public class DeckTest {

  @Test
  public void defaultDeckTest() {
    Deck deck = new Deck();
    System.out.println(deck);
    deck.shuffle();
    System.out.println(deck);
  }

  @Test
  public void testDefaultStacks() {
    Deck deck = new Deck(new Random(2));
    deck.shuffle();
    SolitaireState s = new SolitaireState(deck);
    System.out.println(s);
    System.out.println(s.getNextStates());
    s = s.getNextStates().get(0);
//    s = s.getNextStates().get(0);
//    System.out.println(s);
    System.out.println(s.getNextStates());
  }


  @Test
  public void testCycle() {
    Deck deck = new Deck(new Random(2));
    deck.shuffle();
    Hand h = new Hand(deck);
    h = h.cycle();
    h = h.playTopCard();
    h = h.playTopCard();
    for (int i = 0; i < 20; i++) {
      System.out.println(h);
      h = h.cycle();
    }
  }

  @Test
  public void testReal() {
    Deck deck = new Deck(new Random());
    deck.shuffle();
    SolitaireState s = new SolitaireState(deck);
    List<SolitaireState> states = new ArrayList<>();
    states.add(s);
    Set<SolitaireState> previousStates = new HashSet<>();
    int iterations = 0;
    int maxPlayed = 0;
    while (states.size() > 0) {
      List<SolitaireState> newStates = new ArrayList<>();
      for (SolitaireState state : states) {
        previousStates.add(state);
        for (SolitaireState newState : state.getNextStates()) {
          if (newState.isWon()) {
            System.out.println("winner!");
            System.out.println(previousStates.size());
            System.out.println(iterations);
            System.out.println(maxPlayed);
            return;
          }
          if (!previousStates.contains(newState) && !newStates.contains(newState)) {
            if (newState.numPlayed() > maxPlayed) {
              maxPlayed = newState.numPlayed();
//              System.out.println(newState.numPlayed());
              newStates.add(0, newState);
            } else {
              newStates.add(newState);
            }
          }
        }
      }
      states = newStates.subList(0,Math.min(100, newStates.size()));
      iterations++;
    }
    System.out.println(previousStates.size());
    System.out.println(iterations);
    System.out.println(maxPlayed);
  }

  /// MOVING KING TO NEW EMPTY STACK FROM STACK should work????
}
