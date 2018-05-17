import java.util.ArrayList;
import java.util.List;

public class AllCards {
	
	private List<Character> allCards = new ArrayList<Character>();
	
	public AllCards() {
		allCards.add(new Character("Bob",1,1,1,"pirateImage.png"));
		allCards.add(new Character("Bob",1,1,1,""));
		allCards.add(new Character("Bob",1,1,1,""));
	}
	
	public List<Character> getCards(){
		return allCards;
	}

	public Character get(int index) {
		return allCards.get(index);
	}
}
