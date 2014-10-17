
public class Hand {

	public Card[] cards;
	public String pattern;
	Hand(Card a,Card b, Card c, Card d, Card e) {
		cards = new Card[5];
		cards[0]=a;
		cards[1]=b;
		cards[2]=c;
		cards[3]=d;
		cards[4]=e;
	}
}
