import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 18caldwellblake
 * Deck class for decks of cards
 *
 */
public class Deck {
	List<Card> myDeck;
	static AllCards cards = new AllCards();
	
	
	/**
	 * Constructor for the Deck class 
	 */
	public Deck() {	
		
		myDeck = new ArrayList<Card>();
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
		for(Card o : myDeck) {
			if(o.getClass().equals("Character")) {
				Character c = (Character) o;
				c.changeHasMovedFalse();
			}
		}
	}
	
	/**
	 * 
	 * @param value index of Deck array
	 * @return character from the Deck
	 */
	public Card get(int value) {
		return myDeck.get(value);
	}
	
	public void remove(Card c) {
		myDeck.remove(c);
	}
	
	public void remove(int value) {
		myDeck.remove(value);
	}	
	
	public void add(Card c) {
		myDeck.add(c);
	}
	
	public int size() {
		return myDeck.size();
	}
	
	public List<Card> getCards(){
		return myDeck;
	}
	
	public List<Spell> getSpells(){
		List<Spell> temp = new ArrayList<Spell>();
		
		for(Card o : myDeck) {
			if(o.getClass().equals("Spell")) {
				Spell s = (Spell)o;
				temp.add(s);
			}
		}
		return temp;
	}
	
	public List<Character> getCharacters(){
		List<Character> temp = new ArrayList<Character>();
		
		for(Card o : myDeck) {
			if(o.getClass().equals("Character")) {
				Character c = (Character)o;
				temp.add(c);
			}
		}
		return temp;
	}
	
	public String toString() {
		String value = "";
		for(Card o : myDeck) {
			value += o.toString() + "\n";
		}
		return value;
	}
	
	
}
