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
			myDeck.add(new Character(addCard.getName(), addCard.getHp(), addCard.getAtk(), addCard.getCost(), addCard.getSource()));
		}
	}
	
	public void remove(Character c) {
		myDeck.remove(c);
	}
	
	public void add(Character c) {
		myDeck.add(c);
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
