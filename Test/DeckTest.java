import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.Test;
import me.tongfei.progressbar.ProgressBar;
import cn.weiyinfu.tqdm.Tqdm;

public class DeckTest {

  public static void main(String[] args) {
    System.out.println(playGame(1000000));
  }

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
  public void testOne() {
    System.out.println(playGame(100000));
  }


  public static void testReal() {
    int games = 100;
    for (int j = 0; j < 12; j++) {
      ProgressBar pb = new ProgressBar("Test", games);
      int won = 0;
      int keep = (int) Math.pow(2, j);
      for (int i = 0; i < games; i++) {
        pb.step();
        if (playGame(keep)) {
          won++;
        }
//        System.out.println();
      }
      System.out.println(keep + ": " + won + "/" + games);
    }
  }

  public static boolean playGame(int keep) {
    Deck deck = new Deck(new Random(0));
    deck.shuffle();
    SolitaireState s = new SolitaireState(deck);
    List<SolitaireState> states = new ArrayList<>();
    states.add(s);
//    Set<SolitaireState> previousStates = new HashSet<>();
    int iterations = 0;
    while (states.size() > 0) {
      Set<SolitaireState> newStates = new HashSet<>();
      for (SolitaireState state : ProgressBar.wrap(states, "Iteration " + iterations + ":")) {
//        previousStates.add(state);
        for (SolitaireState newState : state.getNextStates()) {
          if (newState.isWon()) {
            return true;
          }
          if (!newStates.contains(newState)) {// && !previousStates.contains(newState)) {
            newStates.add(newState);
          }
        }
      }
      states = new ArrayList<>(newStates);
      states.sort(Comparator.comparing(SolitaireState::numPlayed, Comparator.reverseOrder()));
      states = states.subList(0, Math.min(keep, newStates.size()));
      iterations++;
    }
    return false;
  }
}


