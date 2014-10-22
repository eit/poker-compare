import java.util.HashMap;

//package PokerCaompare;

public class Card {
	private int rank;
	private int suit;
	private HashMap<String, Integer> suitMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> rankMap = new HashMap<String, Integer>();
	
	public Card(){
		// build dictionary for mapping suit/rank to value
		suitMap.put("C", 0);
		suitMap.put("D", 1);
		suitMap.put("H", 2);
		suitMap.put("S", 3);
		
		rankMap.put("2", 2);
		rankMap.put("3", 3);
		rankMap.put("4", 4);
		rankMap.put("5", 5);
		rankMap.put("6", 6);
		rankMap.put("7", 7);
		rankMap.put("8", 8);
		rankMap.put("9", 9);
		rankMap.put("T", 10);
		rankMap.put("J", 11);
		rankMap.put("Q", 12);
		rankMap.put("K", 13);
		rankMap.put("A", 14);
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(String rank) {
		//this.rank = rankValue(rank);
		this.rank = rankMap.get(rank);
	}
	public int getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		//this.suit = suitValue(suit);
		this.suit = suitMap.get(suit);
	}
	/*
	// suit mapping to value 
	private int suitValue(char suit) {
		int value = -1;
		switch (suit) {
		case 'C':
			value = 0;// clubs
			break;
		case 'D':
			value = 1;// diamonds
			break;
		case 'H':
			value = 2;// hearts
			break;
		case 'S':
			value = 3;// spades
			break;
		}
		return value;
	}
	// rank mapping to value 
	private int rankValue(char rank) {
		int value = -1;
		switch (rank) {
		case '2':
			value = 2;
			break;
		case '3':
			value = 3;
			break;
		case '4':
			value = 4;
			break;
		case '5':
			value = 5;
			break;
		case '6':
			value = 6;
			break;
		case '7':
			value = 7;
			break;
		case '8':
			value = 8;
			break;
		case '9':
			value = 9;
			break;
		case 'T':
			value = 10;
			break;
		case 'J':
			value = 11;
			break;
		case 'Q':
			value = 12;
			break;
		case 'K':
			value = 13;
			break;
		case 'A':
			value = 14;
			break;
		}
		return value;
	}
	*/
	
}
