package com.dimmik.cards.prefplayers;

import com.dimmik.cards.pref.PrefDeal;
import com.dimmik.cards.pref.PrefTradeStepInfo;
import com.dimmik.cards.pref.PrefDeal.TwoCards;
import com.dimmik.cards.pref.trade.Bid;
import com.dimmik.cards.sheets.card.Card;
import com.dimmik.cards.table.Deal;
import com.dimmik.cards.table.IPlayer;
import com.dimmik.cards.table.ITradeStepInfo;
import com.dimmik.cards.table.Move;
import com.dimmik.cards.table.Seat;

public abstract class AbstractPlayer implements IPlayer {

  public abstract Card nextCard(Seat seat, Move move);

  public void tradeStep(Seat seat, Deal d, ITradeStepInfo bc) {
    if (!(d instanceof PrefDeal)) {
      throw new IllegalStateException("deal must be PrefDeal");
    }
    if (!(bc instanceof PrefTradeStepInfo)) {
      throw new IllegalStateException("bid container must be PrefBidContainer");
    }
    prefTradeStep(seat, (PrefDeal) d, (PrefTradeStepInfo) bc);
  }

  private void prefTradeStep(Seat seat, PrefDeal d, PrefTradeStepInfo bc) {
    switch (bc.getStep()) {
    case SET_BID:
      Bid bid = setBid(seat, d);
      bc.setBid(bid);
      break;
    case GIVE_SIDE_CARDS:
      giveSideCards(seat, d, bc);
      break;
    case GET_THROWN_CARDS:
      PrefDeal.TwoCards thrown = getThrownCards(seat, d);
      bc.setFirstThrown(thrown.getFirst());
      bc.setSecondThrown(thrown.getSecond());
      break;
    case SET_GAME:
      Bid game = setGame(seat, d);
      bc.setGame(game);
      break;
    }

  }

  protected abstract Bid setGame(Seat seat, PrefDeal d);

  protected void giveSideCards(Seat seat, PrefDeal d, PrefTradeStepInfo bc) {
  }

  protected abstract TwoCards getThrownCards(Seat seat, PrefDeal d);

  protected abstract Bid setBid(Seat seat, PrefDeal d);

}
