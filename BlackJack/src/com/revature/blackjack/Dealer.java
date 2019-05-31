package com.revature.blackjack;

import java.util.ArrayList;

public class Dealer extends Player {

	public Dealer() {}

	@Override
	public ArrayList<Card> getPlayerCards() {
		return super.getPlayerCards();
	}

	@Override
	public int getTotalPoints() {
		return super.getTotalPoints();
	}
	@Override
	public void viewHand() {
		System.out.println("Dealer: " + playerCards.get(0).toString() + " , [card hidden]");
	}

	@Override
	public String displayCards() {
		return "Dealer: " + playerCards + " Total points!: " + getTotalPoints();
	}
}
