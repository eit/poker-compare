public class UnitTest {

	public static void main(String[] args) {

		Card a = new Card();
		a.setRank('A');
		a.setSuit('C');
		Card b = new Card();
		b.setRank('K');
		b.setSuit('C');
		Card c = new Card();
		c.setRank('Q');
		c.setSuit('C');
		Card d = new Card();
		d.setRank('J');
		d.setSuit('C');
		Card e = new Card();
		e.setRank('T');
		e.setSuit('C');

		Card f = new Card();
		f.setRank('T');
		f.setSuit('C');
		Card g = new Card();
		g.setRank('T');
		g.setSuit('S');
		Card h = new Card();
		h.setRank('T');
		h.setSuit('H');
		Card i = new Card();
		i.setRank('T');
		i.setSuit('D');
		Card j = new Card();
		j.setRank('3');
		j.setSuit('C');

		Hand Black = new Hand(a, b, c, d, e);
		Hand White = new Hand(f, g, h, i, j);

		PokerCompare deck = new PokerCompare();
		deck.compare(Black, White);

		int result = deck.judge(Black, White);

		deck.ShowPattern(Black);
		deck.ShowPattern(White);

		if (result == 0)// =
			System.out.println("Tie.");
		else if (result > 0)// 1,>
			System.out.println("Black wins.");
		else
			// <0,-1,<
			System.out.println("White wins.");

	}
}