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
    return card.getValue().equals(CardValue.ACE) || played.contains(card.nextCardDown());
  }

  public int numPlayed() {
    return played.size();
  }

  @Override
  public String toString() {
//    if (played.size() == 0) {
//      return "[  ] [  ] [  ] [  ]";
//    }
//    return "AS AH AC AD";
    return played.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Middle middle = (Middle) o;

    return played.equals(middle.played);
  }

  @Override
  public int hashCode() {
    return played.hashCode();
  }
}
