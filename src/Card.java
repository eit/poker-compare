//package PokerCaompare;

public class Card {
	private int rank;
	private int suit;
	
	public int getRank() {
		return rank;
	}
	public void setRank(char rank) {
		this.rank = rankValue(rank);
	}
	public int getSuit() {
		return suit;
	}
	public void setSuit(char suit) {
		this.suit = suitValue(suit);
	}
	
	// 使用字典將花色轉為數值，以方便後面比較大小
	private int suitValue(char suit) {
		int value = -1;
		switch (suit) {
		case 'C':
			value = 0;// 梅花
			break;
		case 'D':
			value = 1;// 方塊
			break;
		case 'H':
			value = 2;// 愛心
			break;
		case 'S':
			value = 3;// 黑桃
			break;
		}
		return value;
	}
	// 使用字典將 rank 轉為數值，以方便後面比較大小
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

}
