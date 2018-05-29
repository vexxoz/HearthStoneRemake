import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 18caldwellblake
 * Deck class for decks of cards
 *
 */
public class Deck {
	List<Character> myDeck;
	static AllCards cards = new AllCards();
	
	
	/**
	 * Constructor for the Deck class 
	 */
	public Deck() {	
		
		myDeck = new ArrayList<Character>();
	}
	
	/**
	 * @param cardsTotal - how many cards to put into this deck
	 * 
	 */
	public void startingDeck(int cardsTotal) {

		for(int i=0; i<cardsTotal;i++) {
			
			int index = (int)(Math.random()*(cards.getCards().size()-1));
			Character addCard = cards.get(index);
			myDeck.add(new Character(addCard.getName(), addCard.getHp(), addCard.getAtk(), addCard.getCost(), addCard.getSourceBig(), addCard.getSourceLittle()));
		}
	}
	
	/**
	 * Sets every card hasMoved to false meaning it can attack
	 */
	public void refreshCards() {
		for(Character c : myDeck) {
			c.changeHasMovedFalse();
		}
	}
	
	/**
	 * 
	 * @param value index of Deck array
	 * @return character from the Deck
	 */
	public Character get(int value) {
		return myDeck.get(value);
	}
	
	public void remove(Character c) {
		myDeck.remove(c);
	}
	
	public void remove(int value) {
		myDeck.remove(value);
	}	
	
	public void add(Character c) {
		myDeck.add(c);
	}
	
	public int size() {
		return myDeck.size();
	}
	
	public List<Character> getCharacters(){
		return myDeck;
	}
	
	public String toString() {
		String value = "";
		for(Character i : myDeck) {
			value += i.toString() + "\n";
		}
		return value;
	}
	
	
}
