public class UnitTest {

	public static void main(String[] args) {
		
		Card CA = new Card();
		CA.setRank("A");
		CA.setSuit("C");
		Card CK = new Card();
		CK.setRank("K");
		CK.setSuit("C");
		Card CQ = new Card();
		CQ.setRank("Q");
		CQ.setSuit("C");
		Card CJ = new Card();
		CJ.setRank("J");
		CJ.setSuit("C");
		Card CT = new Card();
		CT.setRank("T");
		CT.setSuit("C");

		Card ST = new Card();
		ST.setRank("T");
		ST.setSuit("S");
		Card HT = new Card();
		HT.setRank("T");
		HT.setSuit("H");
		Card DT = new Card();
		DT.setRank("T");
		DT.setSuit("D");
		Card C3 = new Card();
		C3.setRank("3");
		C3.setSuit("C");
		Card D3 = new Card();
		D3.setRank("3");
		D3.setSuit("D");
		Card C2 = new Card();
		C2.setRank("2");
		C2.setSuit("C");

		
		Hand StraightFlush = new Hand(CA, CK, CQ, CT, CJ);  
		Hand FourOfAKind = new Hand(CT, ST, HT, DT, C3);
		Hand FullHouse = new Hand(CT, ST, HT, C3, D3);
		Hand Flush = new Hand(CA, CK, CQ, CJ, C3);
		Hand Straight = new Hand(CA, CK, CQ, HT, CJ);
		Hand ThreeOfAKind = new Hand(CT, ST, HT, C3 ,C2);
		Hand TwoPairs = new Hand(C3, D3, C2, CT, HT);
		Hand Pair = new Hand(C3, D3, CA, CJ, HT);
		Hand HighCard = new Hand(CA, CQ, CJ, HT, D3);
		

		Hand Black = new Hand(CA, CK, CQ, CT, CJ);
		Hand White = new Hand(CA, CQ, CJ, HT, D3);
		PokerCompare deck = new PokerCompare();

		System.out.print("Test StraightFlush: ");
		deck.PatternCheck(StraightFlush);
		deck.ShowPattern(StraightFlush);
		
		System.out.print("Test FourOfAKind: ");
		deck.PatternCheck(FourOfAKind);
		deck.ShowPattern(FourOfAKind);
		
		System.out.print("Test FullHouse: ");
		deck.PatternCheck(FullHouse);
		deck.ShowPattern(FullHouse);
		
		System.out.print("Test Flush: ");
		deck.PatternCheck(Flush);
		deck.ShowPattern(Flush);
		
		System.out.print("Test Straight: ");
		deck.PatternCheck(Straight);
		deck.ShowPattern(Straight);
		
		System.out.print("Test ThreeOfAKind: ");
		deck.PatternCheck(ThreeOfAKind);
		deck.ShowPattern(ThreeOfAKind);
		
		System.out.print("Test TwoPairs: ");
		deck.PatternCheck(TwoPairs);
		deck.ShowPattern(TwoPairs);
		
		System.out.print("Test Pair: ");
		deck.PatternCheck(Pair);
		deck.ShowPattern(Pair);
		
		System.out.print("Test HightCard: ");
		deck.PatternCheck(HighCard);
		deck.ShowPattern(HighCard);
		
		deck.compare(Black, White);

		
		int result = deck.judge(Black, White);

		
		
		System.out.print("\nBlack is ");
		deck.ShowPattern(Black);
		System.out.print("White is ");
		deck.ShowPattern(White);

		if (result == 0)
			System.out.println("Tie.");
		else if (result > 0)
			System.out.println("Black wins.");
		else
			System.out.println("White wins.");

	}
}