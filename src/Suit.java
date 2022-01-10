public enum Suit {
  SPADES(0), HEARTS(1), CLUBS(2), DIAMONDS(3);

  private int value;

  private Suit(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    String[] results = new String[]{"S", "H", "C", "D"};
    return results[value];
  }
}
