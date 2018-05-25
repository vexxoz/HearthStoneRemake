
public class AI {

	private Deck cardsInHand;
	private Deck cardsPlayed;
	private Deck playerPlayedCards;
	private int mana;
	
	
	public AI() {
		mana = 0;
	}
	
	public void enemyTurn(Deck cardsInHandIn, Deck cardsPlayedIn, Deck playedPlayerCardsIn, int manaIn) {
		cardsInHand = cardsInHandIn;
		cardsPlayed = cardsPlayedIn;
		playerPlayedCards = playedPlayerCardsIn;
		mana = manaIn;
		
		move();
		attack();
	}
	
	public void move() {
		
		if(cardsPlayed.getCharacters().size() < 8) {
			
			int value = 0;
			
			while(mana > 0 && cardsInHand.getCharacters().size() > 1) {
				Character bestPlay = cardsInHand.getCharacters().get(0);
				int tempValue = 0;
				for(int i = 1; i<cardsInHand.getCharacters().size();i++) {
					Character compare = cardsInHand.getCharacters().get(i);
					if(compare.getCost() <= mana) {
						if(compare.getAtk() > bestPlay.getAtk()) {
							if(compare.getAtk()-bestPlay.getAtk() <= 2) {
								tempValue += 2;
							}
							else if(compare.getAtk()-bestPlay.getAtk() <= 5) {
								tempValue += 3;
							}
							else if(compare.getAtk()-bestPlay.getAtk() <= 10) {
								tempValue += 5;
							}else {
								tempValue += 20;
							}
						}
						if(compare.getHp() > bestPlay.getHp()) {
							if(compare.getHp()-bestPlay.getHp() <= 2) {
								tempValue += 3;
							}
							else if(compare.getHp()-bestPlay.getHp() <= 5) {
								tempValue += 5;
							}
							else if(compare.getHp()-bestPlay.getHp() <= 10) {
								tempValue += 10;
							}else {
								tempValue += 30;
							}
						}
						
						if(compare.getCost() < bestPlay.getCost()) {
							if(bestPlay.getCost()-compare.getCost() <= 2) {
								tempValue += 10;
							}
							else if(bestPlay.getCost()-compare.getCost() <= 3) {
								tempValue += 15;
							}
							else if(bestPlay.getCost()-compare.getCost() <= 5) {
								tempValue += 20;
							}else {
								tempValue += 35;
							}
						}
						
						if(tempValue > value) {
							value = tempValue;
							bestPlay = compare;
						}
					}
				}
				
				//System.out.println(bestPlay.toString());
				
				cardsPlayed.add(bestPlay);
				cardsInHand.remove(bestPlay);
				mana -= bestPlay.getCost();
				
			}
			if(cardsInHand.getCharacters().size() == 1 && playerPlayedCards.getCharacters().size() > 1) {
				Character bestPlay = cardsInHand.getCharacters().get(0);
				cardsPlayed.add(bestPlay);
				cardsInHand.remove(bestPlay);		
				mana -= bestPlay.getCost();
			}
		}
		
	}
	
	public void attack() {
		//for each enemy
		for(int i = 0; i < cardsPlayed.getCharacters().size(); i++) {
			Character enemy = cardsPlayed.getCharacters().get(i);
			
			// checks to see if there are any minions to attack
			if(playerPlayedCards.getCharacters().size() > 1) {
				int index = 0;
				
				
				// go through all player characters
				for(int j = 0; j < playerPlayedCards.getCharacters().size(); j++) {			
					Character player = playerPlayedCards.getCharacters().get(j);
					
				}
					
				
			}else if(playerPlayedCards.getCharacters().size() == 1){
				
				if(enemy.takeDamage(playerPlayedCards.getCharacters().get(0).getAtk())) {
					System.out.println("Enemy died");
					cardsPlayed.remove(enemy);
				}
				if(playerPlayedCards.getCharacters().get(0).takeDamage(enemy.getAtk())) {
					System.out.println("player died");
					playerPlayedCards.getCharacters().remove(0);
				}
				
			}else{
				System.out.println("No minion to attack");
				//attack face
			}

			
		}

	}
	
	
	
}
