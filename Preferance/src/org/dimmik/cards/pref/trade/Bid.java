package org.dimmik.cards.pref.trade;

import org.dimmik.cards.sheets.card.Rank;
import org.dimmik.cards.sheets.card.Suit;

public class Bid {
  private final Suit suit;
  private final Rank rank;
  private final String name;
  private Bid(Suit s, Rank r){
    if (r == null || s == null) {
      throw new IllegalArgumentException("neither rank nor suit can be null");
    }
    suit = s;
    rank = r;
    name = r + " of " + s;
  }
  private Bid(String name) {
    suit = Suit.NO_SUIT;
    rank = null;
    this.name = name;
  }
  
  public static Bid valueOf(Suit suit, Rank rank) {
    return new Bid(suit, rank);
  }
  
  public static final Bid PASS = new Bid("PASS");
  public static final Bid MISER = new Bid("MISER");
  // for visters
  public static final Bid VIST = new Bid("VIST");
  public static final Bid HALF = new Bid("HALF");
  
  public Suit getSuit() {
    return suit;
  }
  public Rank getRank() {
    return rank;
  }
  public boolean isNoTrump(){
    return suit == Suit.NO_SUIT;
  }
  public String getName() {
    return name;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((rank == null) ? 0 : rank.hashCode());
    result = prime * result + ((suit == null) ? 0 : suit.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Bid other = (Bid) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (rank == null) {
      if (other.rank != null)
        return false;
    } else if (!rank.equals(other.rank))
      return false;
    if (suit == null) {
      if (other.suit != null)
        return false;
    } else if (!suit.equals(other.suit))
      return false;
    return true;
  }
  @Override
  public String toString(){
    return name;
  }
}
