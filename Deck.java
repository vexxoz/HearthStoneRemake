import java.util.ArrayList;
import java.util.List;


public class Deck {
	List<Character> myDeck;
	static AllCards cards = new AllCards();
	
	public Deck() {	
		
		myDeck = new ArrayList<Character>();
	}
	
	public void startingDeck(int cardsTotal) {

		for(int i=0; i<cardsTotal;i++) {
			
			int index = (int)(Math.random()*(cards.getCards().size()-1));
			Character addCard = cards.get(index);
			myDeck.add(new Character(addCard.getName(), addCard.getHp(), addCard.getAtk(), addCard.getCost(), addCard.getSourceBig(), addCard.getSourceLittle()));
		}
	}
	
	public void refreshCards() {
		for(Character c : myDeck) {
			c.changeHasMovedFalse();
		}
	}
	
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
