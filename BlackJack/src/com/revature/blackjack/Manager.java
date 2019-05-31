package com.revature.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

	public static ArrayList<Player> numberPlayers = new ArrayList<>();
	public static int turnCount = 0;

	public void playerCreator() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter Number of Players? " + "(Max of 4)");
		int numPlayers = keyboard.nextInt();
		keyboard.nextLine();

		if (numPlayers < 5) {
			for (int i=1; i < numPlayers + 1; i++ ) {
				numberPlayers.add(new Player(i));
			}
		} else {
			System.out.println("max of 4");
		}
	}

	public static void main(String[] args) {
		int x;
		do {
			System.out.println("***WELCOME TO BLACKJACK!***");
			Deck deck = new Deck();
			Manager bjm = new Manager();
			bjm.playerCreator();
			Dealer dealer = new Dealer();

			//adding cards
			for (int id=0; id<numberPlayers.size();id++) {
				numberPlayers.get(id).addCard(deck.dealCard());
			}

			//adding cards
			for (int id=0; id<numberPlayers.size();id++) {
				numberPlayers.get(id).addCard(deck.dealCard());
			}
			//		
			//display added card
			for (int id=0; id<numberPlayers.size();id++) {
				System.out.println(numberPlayers.get(id));
			}

			//DEALER CARDS
			dealer.addCard(deck.dealCard());
			dealer.addCard(deck.dealCard());
			dealer.viewHand();

			Scanner input = new Scanner(System.in);
			if (dealer.getPlayerCards().get(0).getRank() == 1) {

				for (int id=0; id<numberPlayers.size();id++) {
					System.out.println("Player " + numberPlayers.get(id).getId() + ":" +" Do you want Insurance? Yes(1) or No(2)?");
					int answer = input.nextInt();
					if (answer ==1) {
						numberPlayers.get(id).setInsureFlag(true);
					}
				}
			}

			Scanner scan = new Scanner(System.in);
			for (int id=0; id<numberPlayers.size();id++) {
				if( numberPlayers.get(id).getTotalPoints() > 8 && numberPlayers.get(id).getTotalPoints() < 12){

					System.out.println("Player " + numberPlayers.get(id).getId() + ": Double Down? Yes(1) or No(2)");
					int response = scan.nextInt();
					if (response ==1) {
						numberPlayers.get(id).addCard(deck.dealCard());
						numberPlayers.get(id).setDoubleFlag(true);
					}
				}
			}

			Scanner keyboard = new Scanner(System.in);

			for (int id=0; id<numberPlayers.size();id++) {
				while(numberPlayers.get(id).getTotalPoints() < 21) 
				{
					if (numberPlayers.get(id).isDoubleFlag()) {
						break;
					}

					System.out.println("Player" + numberPlayers.get(id).getId() + ": Do you want to HIT(1) or STAND(2)?");
					int answer = keyboard.nextInt();

					if (answer == 1) {
						//adding card to current player's hand
						numberPlayers.get(id).addCard(deck.dealCard());

					} else {
						break;
					}

					//after a round of hitting or standing: GETS ID
					for (int w=0; w<numberPlayers.size();w++) {
						System.out.println(numberPlayers.get(w));
					}
				}

			}
			for (int w=0; w<numberPlayers.size();w++) {
				System.out.println(numberPlayers.get(w));
			}

			while (dealer.getTotalPoints()<16) {
				dealer.addCard(deck.dealCard());
			}

			for (int w=0; w<numberPlayers.size();w++) {
				//if player busts and doubled down
				if (numberPlayers.get(w).getTotalPoints() >21 && numberPlayers.get(w).isDoubleFlag())
				{	numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() - 20);
				System.out.println("BUST!");
				//if player bust and did NOT double down	
				}else if (numberPlayers.get(w).getTotalPoints() >21 && !numberPlayers.get(w).isDoubleFlag())
				{	numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() - 10);
				System.out.println("BUST!");

				}
				//if dealer wins and player doubled down
				else if(numberPlayers.get(w).getTotalPoints() < dealer.getTotalPoints()&&
						(dealer.getTotalPoints()<21) && numberPlayers.get(w).isDoubleFlag()){
					numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() - 20);
					System.out.println("YOU LOSE!");

					//if dealer wins and player did NOT double down	
				}else if(numberPlayers.get(w).getTotalPoints() < dealer.getTotalPoints()&&
						(dealer.getTotalPoints()<21) && !numberPlayers.get(w).isDoubleFlag()){
					numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() - 10);
					System.out.println("YOU LOSE!");

				} else if(numberPlayers.get(w).getTotalPoints() < dealer.getTotalPoints()&&
						(dealer.getTotalPoints() == 21)) {
					if (numberPlayers.get(w).isInsureFlag()) {
						numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() -5);
					}else {
						numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() -10);
					}

				}

				else if( (numberPlayers.get(w).getTotalPoints()==dealer.getTotalPoints()) ) {
					System.out.println("Tied up!");
				} else {
					if (numberPlayers.get(w).isDoubleFlag()) {
						numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() + 20);
						System.out.println("YOU WIN!");
					} else if (!numberPlayers.get(w).isDoubleFlag()){
						numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() + 10);
						System.out.println("YOU WIN!");
					} else if (numberPlayers.get(w).isInsureFlag()) {
						numberPlayers.get(w).setGamePoints(numberPlayers.get(w).getGamePoints() + 5);
						System.out.println("YOU WIN!");
					} else {
						System.out.println("YOU WIN!");
					}

				}
				System.out.println(dealer.displayCards());
				for (int id=0; id<numberPlayers.size();id++) {
					System.out.println("Player " + numberPlayers.get(id).getId() + ": " + "You have " +
							numberPlayers.get(id).getGamePoints() + " Game Points");
				}
			}

			numberPlayers.clear();

			for (int id=0; id<numberPlayers.size();id++) { 
				System.out.println(numberPlayers.get(id).displayCards());// + dealer.getPlayerCards());
			}

			Scanner playAgain = new Scanner(System.in);
			System.out.println();
			System.out.println("Would you like to play another round? YES(1) or NO(2)?");
			x = playAgain.nextInt(); 
		} while (x == 1); 
		if (x==2) {
			System.out.println("Thanks for playing!");
			System.out.println("Goodbye!");
		}
	}
}
