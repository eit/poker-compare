//package PokerCompare;
/**
 * 1.count rank/suit occurrences
 * 2.sort hand cards
 * 3.recognize the pattern of hand cards
 * 	3.1.recognize straight (by consecutive 5 value=1)  
 * 	3.2.recognize flush (scan suit)
 * 	3.3.recognize pair, three of a kind, four of a kind, full house and high card (by occurrences)。
 * 4.compare
 * 	4.1 compare pattern
 * 	4.2 compare value
 * 
 * @author Jayson LP Chen
 * @version v1.0
 */
public class PokerCompare {

	private int patternValue(String pattern) {
		int value = -1;
		if (pattern == "HighCard")
			value = 0;
		else if (pattern == "Pair")
			value = 1;
		else if (pattern == "TwoPairs")
			value = 2;
		else if (pattern == "ThreeOfAKind")
			value = 3;
		else if (pattern == "Straight")
			value = 4;
		else if (pattern == "Flush")
			value = 5;
		else if (pattern == "FullHouse")
			value = 6;
		else if (pattern == "FourOfAKind")
			value = 7;
		else if (pattern == "StraightFlush")
			value = 8;

		return value;
	}

	public void ShowPattern(Hand h) {
		if (h.pattern == "HighCard") {
			System.out.println("High card.");
		}
		if (h.pattern == "Pair") {
			System.out.println("Pair.");
		}
		if (h.pattern == "TwoPairs") {
			System.out.println("Two pairs.");
		}
		if (h.pattern == "ThreeOfAKind") {
			System.out.println("Three of a kind.");
		}
		if (h.pattern == "Straight") {
			System.out.println("Straight.");
		}
		if (h.pattern == "Flush") {
			System.out.println("Flush.");
		}
		if (h.pattern == "FullHouse") {
			System.out.println("Full house.");
		}
		if (h.pattern == "FourOfAKind") {
			System.out.println("Four of a kind.");
		}
		if (h.pattern == "StraightFlush") {
			System.out.println("Stragiht flush.");
		}
	}

	public int PatternCheck(Hand h) {
		// check pattern of hand cards
		// function patternValue(String pattern)
		// HighCard = 0, Pair = 1, TwoPairs = 2, ThreeOfAKind = 3,
		// Straight = 4, Flush = 5, FullHouse = 6, FourOfAKind = 7,
		// StraightFlush = 8

		int[] statistics = new int[15];
		int[] suit = new int[4];

		h.pattern = "HighCard";// initialize

		// count occurrences
		for (int i = 0; i < 5; i++)
		{
			statistics[h.cards[i].getRank()]++;
			if (h.cards[i].getRank() == 14)
				statistics[1]++;
			suit[h.cards[i].getSuit()]++;
		}

		// because of max=5 so we sort simply
		// priority: 1.occurrence. 2.rank
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4 - i; j++) {
				boolean needswap = false;

				if (statistics[h.cards[j].getRank()] > statistics[h.cards[j + 1].getRank()])
					needswap = true;
				
				if (statistics[h.cards[j].getRank()] == statistics[h.cards[j + 1].getRank()])
					if (h.cards[j].getRank() > h.cards[j + 1].getRank())
						needswap = true;

				if (needswap)// swap
				{
					Card temp = new Card();
					temp = h.cards[j];
					h.cards[j] = h.cards[j + 1];
					h.cards[j + 1] = temp;
				}
			}
		}

		// straight (if consecutive 5 value=1 )
		boolean end = false;
		for (int i = 1; i < 11 && !end; i++)// A2345~ TJQKA
		{
			int j;
			for (j = 0; j < 5; j++)// scan hand cards from any i=1, then check i, i+1, ..., i+4 
			{
				// check one by one
				if (statistics[i + j] > 1) {
					// if the occurrences > 1, is NOT straight
					end = true;
					break;
				}
				if (statistics[i + j] < 1)// check gap
				{ 
					// if the occurrences < 1, is NOT straight
					i = i + j;
					break;
				}
			}
			if (j == 5)// straight
			{
				h.pattern = "Straight";
				end = true;
			}
		}

		// suit=5 => flush
		// if flush & straight => StraightFlush
		for (int i = 0; i < 4; i++) {
			if (suit[i] == 5)
			{
				if (h.pattern == "Straight")
					h.pattern = "StraightFlush";
				else {
					h.pattern = "Flush";
				}
				return patternValue(h.pattern);
			}
		}

		// if a rank=4 => return 7
		// if a rank=3 & another=2 => return 6
		// if only one rank=3 => return 3
		// if two rank=3 => return 2
		// if only one rank=2 => return 1
		int[] kind = new int[5]; 

		for (int i = 2; i < 15; i++)
		{
			kind[statistics[i]]++;
		}

		if (kind[4] > 0) {
			h.pattern = "FourOfAKind";
		} else if (kind[3] > 0) {
			if (kind[2] > 0)
				h.pattern = "FullHouse";
			else
				h.pattern = "ThreeOfAKind";
		} else if (kind[2] > 0) {
			if (kind[2] == 2)
				h.pattern = "TwoPairs";
			else
				h.pattern = "Pair";
		}
		// if no match
		return patternValue(h.pattern);
	}

	public int compare(Hand B, Hand W) {
		if (patternValue(B.pattern) != patternValue(W.pattern)) {
			return patternValue(B.pattern) - patternValue(W.pattern);
		} else if (B.pattern == "Flush" || B.pattern == "StraightFlush") {
			// A2345 is special case
			// A2345 will be sorted to 2345A
			int B_high = B.cards[4].getRank();
			int W_high = W.cards[4].getRank();

			if (B.cards[4].getRank() == 14 || B.cards[0].getRank() == 2)
				B_high = 5;

			if (W.cards[4].getRank() == 14 || W.cards[0].getRank() == 2)
				W_high = 5;

			return B_high - W_high;
		} else {
			// rank compare
			for (int i = 4; i >= 0; i--) {
				if (B.cards[i].getRank() != W.cards[i].getRank())
					return B.cards[i].getRank() - W.cards[i].getRank();
			}
			return 0;
		}

	}

	public int judge(Hand B, Hand W) {
		// recognize pattern
		PatternCheck(B);
		PatternCheck(W);
		// compare
		return compare(B, W);
	}

}
