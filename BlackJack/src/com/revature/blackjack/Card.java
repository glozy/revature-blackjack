package com.revature.blackjack;

public class Card {

	private int rank;
	private int suit;
	private int points;

	public static final String[] RANKS = {null,"Ace", "2", "3", "4", "5", "6", "7",
			"8", "9", "10","Jack", "Queen", "King"}; 

	public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};

	public Card (int rank, int suit) {
		this.rank = rank;
		this.suit = suit;

		switch (rank) {
		case 2: points = 2; break;
		case 3: points = 3; break;
		case 4: points = 4; break;
		case 5: points = 5; break;
		case 6: points = 6; break;
		case 7: points = 7; break;
		case 8: points = 8; break;
		case 9: points = 9; break;
		case 10: points = 10; break;
		case 11: points = 10; break;
		case 12: points = 10; break;
		case 13: points = 10; break;
		}
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return RANKS[this.rank] + " of " + SUITS[this.suit];
	}
}
