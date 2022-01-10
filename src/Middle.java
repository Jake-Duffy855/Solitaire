import java.util.HashSet;
import java.util.Set;

public class Middle {
  private Set<Card> played;

  public Middle(Set<Card> played) {
    this.played = played;
  }

  public Middle() {
    this(new HashSet<>());
  }

  public Middle play(Card card) {
    Set<Card> newPlayed = new HashSet<>(played);
    newPlayed.add(card);
    return new Middle(newPlayed);
  }

  public boolean canPlay(Card card) {
    return played.contains(card.nextCardDown());
  }

  public int numPlayed() {
    return played.size();
  }
}
