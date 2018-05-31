import java.util.ArrayList;
import java.util.List;

public class AllCards {
	
	private List<Card> allCards = new ArrayList<Card>();
	
	/**
	 * Constructor for all cards
	 */
	public AllCards() {
		allCards.add(new Character("Bob",1,1,1,"pirateImageBig.png", "pirateImageSmall.png"));
//		allCards.add(new Character("Bob",2,1,2,"", ""));
		allCards.add(new Character("Bob",1,3,3,"pirateImageBig.png", "pirateImageSmall.png"));
	}
	
	/**
	 * Get all game cards
	 * @return all cards in the game
	 */
	public List<Card> getCards(){
		return allCards;
	}

	/**
	 * Get card at an index
	 * @param index of the card
	 * @return the card at the given index
	 */
	public Card get(int index) {
		return allCards.get(index);
	}
}
