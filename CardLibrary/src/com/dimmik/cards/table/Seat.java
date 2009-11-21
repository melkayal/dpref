package com.dimmik.cards.table;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.dimmik.cards.sheets.card.Card;

public class Seat {

  private final String name;
  private IPlayer player;

  private final List<Move> tricks = new ArrayList<Move>();

  private final List<Card> cards = new LinkedList<Card>();

  public Seat(String name) {
    this.name = name;
  }

  public Card nextCard(Move move) {
    Card card = player.nextCard(this, move);
    cards.remove(card);
    return card;
  }

  public void addCard(Card c) {
    getCards().add(c);
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setPlayer(IPlayer player) {
    this.player = player;
  }

  public IPlayer getPlayer() {
    return player;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  public List<Move> getTricks() {
    return tricks;
  }

  public void addTrick(Move m) {
    tricks.add(m);
  }

  public void resetTricks() {
    tricks.clear();
  }

}
