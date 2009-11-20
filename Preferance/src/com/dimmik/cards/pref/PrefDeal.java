package com.dimmik.cards.pref;

import java.util.ArrayList;
import java.util.List;

import com.dimmik.cards.sheets.card.Card;
import com.dimmik.cards.sheets.card.Suit;
import com.dimmik.cards.sheets.deck.CardDeck;
import com.dimmik.cards.sheets.deck.ICardDeck;
import com.dimmik.cards.sheets.deck.PrefCardInitStrategy;
import com.dimmik.cards.table.Deal;
import com.dimmik.cards.table.Move;
import com.dimmik.cards.table.Seat;

public class PrefDeal extends Deal {
    private ICardDeck deck = new CardDeck(new PrefCardInitStrategy());
    private final List<Seat> seats;
    private final int firstMoveSeatIdx;
    private int currentMove;
    private List<Card> sideCards = new ArrayList<Card>();
    private List<Card> thrownCards = new ArrayList<Card>();

    public PrefDeal(List<Seat> seats, int firstMove) {
	this.seats = seats;
	firstMoveSeatIdx = firstMove;
	currentMove = firstMoveSeatIdx;
	for (Seat s: seats) {
	    s.resetTricks();
	}
    }

    @Override
    protected Move createMove() {
	// TODO get trump. from trade
	// XXX Pay attention - right now no trump
	Suit trump = Suit.SPADES;
	PrefMove move = new PrefMove(seats, currentMove, trump);
	return move;
    }

    @Override
    protected void serveCards() {
	add10Cards(seats.get(0));
	add10Cards(seats.get(1));
	add10Cards(seats.get(2));
	sideCards.add(getDeck().getNextCard());
	sideCards.add(getDeck().getNextCard());
    }

    private void add10Cards(Seat seat) {
	for (int i = 0; i < 10; i++) {
	    seat.addCard(getDeck().getNextCard());
	}
    }

    @Override
    protected void movePostProcess(Move move) {
	Seat winner = move.whoWon();
	winner.addTrick(move);
	currentMove = seats.indexOf(winner);
    }

    public void setDeck(ICardDeck deck) {
	this.deck = deck;
    }

    public ICardDeck getDeck() {
	return deck;
    }
    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    protected boolean movesRemain() {
	return (seats.get(0).getCards().size() > 0);
    }
}