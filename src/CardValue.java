import javax.swing.text.AbstractDocument.ElementEdit;

public enum CardValue {
  ACE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10),
  JACK(11),
  QUEEN(12),
  KING(13);

  private int value;

  private CardValue(int value) {
    if (value > 13 || value < 1) {
      throw new IllegalArgumentException("Card Value is 1 to 13");
    }
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static CardValue fromValue(int value) {
    switch (value) {
      case 1:
        return ACE;
      case 2:
        return TWO;
      case 3:
        return THREE;
      case 4:
        return FOUR;
      case 5:
        return FIVE;
      case 6:
        return SIX;
      case 7:
        return SEVEN;
      case 8:
        return EIGHT;
      case 9:
        return NINE;
      case 10:
        return TEN;
      case 11:
        return JACK;
      case 12:
        return QUEEN;
      case 13:
        return KING;
      default:
        throw new IllegalArgumentException("Not a valid card value");
    }
  }

  @Override
  public String toString() {
    if (value == 1) {
      return "A";
    } else if (value < 11) {
      return "" + value;
    } else {
      switch (value) {
        case 11:
          return "J";
        case 12:
          return "Q";
        case 13:
          return "K";
        default:
          throw new IllegalArgumentException("Invalid value");
      }
    }
  }
}
