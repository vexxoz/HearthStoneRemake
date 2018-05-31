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
			
			int index = (int)(Math.random()*(cards.getCards().size()));
			
			Card addCard = cards.get(index);
			
			if(addCard.getType().equals("Character")) {
				Character c = (Character) addCard;
				myDeck.add(new Character(c.getName(), c.getHp(), c.getAtk(), c.getCost(), c.getSourceBig(), c.getSourceLittle()));
			}if(addCard.getType().equals("Spell")) {
				Spell c = (Spell)addCard;
				myDeck.add(new Spell(c.getName(), c.getSpellType(), c.getCost(), c.getDamage(), c.getHeal(), c.getSourceBig(), c.getSourceLittle()));
			}
			
		}
	}
	
	/**
	 * Sets every card hasMoved to false meaning it can attack
	 */
	public void refreshCards() {
		for(Card o : myDeck) {
			if(o.getType().equals("Character")) {
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
	
	/**
	 * Remove a card from the deck
	 * @param c Card to remove
	 */
	public void remove(Card c) {
		myDeck.remove(c);
	}
	
	/**
	 * Remove a card from the deck
	 * @param value index of Card
	 */
	public void remove(int value) {
		myDeck.remove(value);
	}	
	
	/**
	 * Add a card to the deck
	 * @param c Card to add to deck
	 */
	public void add(Card c) {
		myDeck.add(c);
	}
	
	/**
	 * Get the size of the Deck
	 * @return the size of the deck
	 */
	public int size() {
		return myDeck.size();
	}
	
	/**
	 * Gets all cards in a deck
	 * @return Cards in the deck
	 */
	public List<Card> getCards(){
		return myDeck;
	}
	
	/**
	 * Gets all the spells from the deck
	 * @return spells from the deck
	 */
	public List<Spell> getSpells(){
		List<Spell> temp = new ArrayList<Spell>();
		
		for(Card o : myDeck) {
			if(o.getType().equals("Spell")) {
				Spell s = (Spell)o;
				temp.add(s);
			}
		}
		return temp;
	}
	
	/**
	 * Gets all the Characters from the deck
	 * @return all the characters from the deck
	 */
	public List<Character> getCharacters(){
		List<Character> temp = new ArrayList<Character>();
		
		for(Card o : myDeck) {
			if(o.getType().equals("Character")) {
				Character c = (Character)o;
				temp.add(c);
			}
		}
		return temp;
	}
	
	/**
	 * To string method
	 */
	public String toString() {
		String value = "";
		for(Card o : myDeck) {
			value += o.toString() + "\n";
		}
		return value;
	}
	
	
}
