package com.revature.blackjack;

import java.util.ArrayList;

public class Player {

	private boolean doubleFlag;
	private boolean insureFlag;
	private int gamePoints;
	private int id;

	ArrayList<Card> playerCards = new ArrayList<>();

	public boolean isDoubleFlag() {
		return doubleFlag;
	}

	public void setDoubleFlag(boolean doubleFlag) {
		this.doubleFlag = doubleFlag;
	}

	public boolean isInsureFlag() {
		return insureFlag;
	}

	public void setInsureFlag(boolean insureFlag) {
		this.insureFlag = insureFlag;
	}

	public Player() {}

	public Player(int id) {
		doubleFlag = false;
		insureFlag = false;
		this.id = id;
		this.gamePoints = 50;
	}

	public int getGamePoints() {
		return gamePoints;
	}

	public void setGamePoints(int gamePoints) {
		this.gamePoints = gamePoints;
	}

	public ArrayList<Card> getPlayerCards() {
		return playerCards;
	}

	public void viewHand() {
		System.out.println(playerCards.get(0).toString());
	}

	public void addCard (Card c) {
		playerCards.add(c);
	}

	public int getTotalPoints() {
		int totalP=0;
		for(Card c : playerCards) {
			if (c.getRank() == 1) {
				if (totalP < 11) {
					totalP +=11;
				} else {
					totalP +=1;
				}
			}
			totalP += c.getPoints();
		}
		return totalP;
	}

	public String displayCards() {
		return "Player" + id + ": " + getPlayerCards() + " ***Total points!: " + getTotalPoints() + "***";
	}

	@Override
	public String toString() {
		return displayCards();
	}

	public int getId() {
		return id;
	}
}