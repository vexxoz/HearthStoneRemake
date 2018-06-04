import java.util.ArrayList;
import java.util.List;

public class AllCards {
	
	private List<Card> allCards = new ArrayList<Card>();
	
	/**
	 * Constructor for all cards
	 */
	public AllCards() {
		allCards.add(new Character("Bob",1,1,1,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",1,3,3,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",3,8,5,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",4,6,5,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",7,4,3,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",9,5,5,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",7,5,4,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",9,9,8,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",7,2,2,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",1,2,1,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",8,4,4,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",4,3,3,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",1,3,2,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",9,8,7,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",9,2,3,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",5,9,7,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",8,1,2,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",4,9,7,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",5,7,5,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",4,2,2,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",1,4,2,"pirateImageBig.png", "pirateImageSmall.png"));
		allCards.add(new Character("Bob",4,4,3,"pirateImageBig.png", "pirateImageSmall.png"));		

		allCards.add(new Spell("Blast","Damage", 5, 5, 0, "cometBig.jpg"));
		allCards.add(new Spell("Heal","Heal", 2, 0, 1, "healingHands.jpg"));
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
