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
    Deck deck = new Deck(new Random(2));
    deck.shuffle();
    SolitaireState s = new SolitaireState(deck);
    List<SolitaireState> states = new ArrayList<>();
    states.add(s);
    Set<SolitaireState> previousStates = new HashSet<>();
    while (states.size() > 0) {
      System.out.println(states.get(0));
      List<SolitaireState> newStates = new ArrayList<>();
      for (SolitaireState state : states) {
        previousStates.add(state);
        for (SolitaireState newState : state.getNextStates()) {
          if (newState.isWon())
            return;
          if (!previousStates.contains(newState)) {
            newStates.add(newState);
          }
        }
        break;
      }
      states = newStates;
    }
  }
}
