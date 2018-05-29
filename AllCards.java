import java.util.ArrayList;
import java.util.List;

public class AllCards {
	
	private List<Character> allCards = new ArrayList<Character>();
	
	public AllCards() {
		allCards.add(new Character("Bob",1,1,1,"pirateImageBig.png", "pirateImageSmall.png"));
//		allCards.add(new Character("Bob",2,1,2,"", ""));
		allCards.add(new Character("Bob",1,3,3,"pirateImageBig.png", "pirateImageSmall.png"));
	}
	
	public List<Character> getCards(){
		return allCards;
	}

	public Character get(int index) {
		return allCards.get(index);
	}
}
