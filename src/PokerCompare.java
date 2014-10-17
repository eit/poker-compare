//package PokerCompare;
/**
 * 1.先統計每種點數出現的次數、每種花色出現的次數。 2.依照先比出現次數在比點數大小排序五張牌 3.根據4,5,6取得牌型。
 * 4.連續5種點數出現一次為順子。 5.根據每種花色出現的次數偵測同花，又順又同花則是同花順。 6.根據單張牌出現的次數，偵測對子、三條、四條、葫蘆、雜牌。
 * 7.取得牌型後，先比牌型，若牌型相同則比較單張牌的點數
 * 因為已將每張牌排序，除了順子類要特別處理以外，對子、三條、四條、葫蘆、雜牌可以用字典順序的方式比較大小。
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
			System.out.println("Full house");
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

		h.pattern = "HighCard";// 初始化 雜牌

		// 統計花色跟牌點出現次數，這樣檢查牌型的迴圈比較簡單
		for (int i = 0; i < 5; i++)// 統計
		{
			statistics[h.cards[i].getRank()]++;
			if (h.cards[i].getRank() == 14)
				statistics[1]++;
			suit[h.cards[i].getSuit()]++;
		}

		// 因為長度短 直接作簡單排序即可
		// 先排出次數再排點數大小
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4 - i; j++) {
				boolean needswap = false;

				if (statistics[h.cards[j].getRank()] > statistics[h.cards[j + 1]
						.getRank()])
					needswap = true;
				// 出現次數相同 然後比點數,點數相同也不用比花色 因為沒差
				if (statistics[h.cards[j].getRank()] == statistics[h.cards[j + 1]
						.getRank()])
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

		// 連續五個洞都有值且為1則為順子
		// straight
		boolean end = false;
		for (int i = 1; i < 11 && !end; i++)// A2345~ TJQKA
		{
			int j;
			for (j = 0; j < 5; j++)// i為開頭 j為檢查5張是否存在
			{
				if (statistics[i + j] > 1) {
					// 偵測到大於1直接跳出 不可能有順子
					end = true;
					break;
				}
				if (statistics[i + j] < 1)// has gap
				{ // 偵測到一個0可以跳過好幾組 不連續
					i = i + j;// 迴圈會自動+1 成 i+j+1
					break;
				}
				// else 1 沒事繼續檢查
			}
			if (j == 5)// 偵測到是順子
			{
				h.pattern = "Straight";
				end = true;
			}
		}

		// suit出現 5 五張牌花色一樣為同花 5
		// 又順又同花為同花順 8
		for (int i = 0; i < 4; i++) {
			if (suit[i] == 5)// 偵測到同花
			{
				if (h.pattern == "Straight")
					h.pattern = "StraightFlush";// 同花順
				else {
					h.pattern = "Flush";// 同花
				}
				return patternValue(h.pattern);
			}
		}

		// 某個洞出現4則為四條 可直接 return 7
		// 某個洞出現3另外一個洞出現2是為葫蘆 6
		// 一個洞出現3是 三條 3
		// 兩個洞出現2是兩對 2
		// 一個洞出現2是一對 1
		int[] kind = new int[5]; // 0 1 2 3 4對應到 無 單張 對 三條 四條

		for (int i = 2; i < 15; i++)// 2~A
		{
			kind[statistics[i]]++;
		}

		if (kind[4] > 0) {
			h.pattern = "FourOfAKind";
		} else if (kind[3] > 0) {
			if (kind[2] > 0)
				h.pattern = "FullHouse";// 葫蘆
			else
				h.pattern = "ThreeOfAKind";// 三條
		} else if (kind[2] > 0) {
			if (kind[2] == 2)
				h.pattern = "TwoPairs";
			else
				// ==1
				h.pattern = "Pair";
		}
		// 都沒有就雜牌 初始值
		return patternValue(h.pattern);
	}

	public int compare(Hand B, Hand W) {
		if (patternValue(B.pattern) != patternValue(W.pattern)) {
			return patternValue(B.pattern) - patternValue(W.pattern);
		} else if (B.pattern == "Flush" || B.pattern == "StraightFlush") {
			// 順子
			// A2345特別處理
			// A2345會被排序成
			// 2345A
			// 特徵為:順子類、開頭是2結尾是A
			int B_high = B.cards[4].getRank();
			int W_high = W.cards[4].getRank();

			if (B.cards[4].getRank() == 14 || B.cards[0].getRank() == 2)
				B_high = 5;

			if (W.cards[4].getRank() == 14 || W.cards[0].getRank() == 2)
				W_high = 5;

			return B_high - W_high;
		} else {
			// 其他牌型 純看點數比字典順序
			for (int i = 4; i >= 0; i--) {
				if (B.cards[i].getRank() != W.cards[i].getRank())
					return B.cards[i].getRank() - W.cards[i].getRank();
			}
			return 0;// 點數皆相等
		}

	}

	public int judge(Hand B, Hand W) {
		// 先確認排型
		PatternCheck(B);
		PatternCheck(W);
		// 比大小
		return compare(B, W);
	}

}
