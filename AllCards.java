import java.util.ArrayList;
import java.util.List;

public class AllCards {
	
	List<Character> allCards = new ArrayList<Character>();
	
	public AllCards() {
		allCards.add(new Character("Bob",1,1,1,""));
		allCards.add(new Character("Bob",1,1,1,""));
		allCards.add(new Character("Bob",1,1,1,""));
	}
	
	public List<Character> getCards(){
		return allCards;
	}
	
}
