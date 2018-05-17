import java.util.ArrayList;
import java.util.List;


public class Deck {
	List<Character> myDeck;
	
	public Deck() {	
		
		myDeck = new ArrayList<Character>();
	}
	
	public void startingDeck(int cardsTotal, List<Character> gameCards) {
		int index = 0;
		while(index < cardsTotal && gameCards.size() > 0) {
			int cardIndex = (int)(Math.random()*(gameCards.size()-1));
			gameCards.remove(cardIndex);
			myDeck.add(gameCards.get(cardIndex));
			index++;
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
