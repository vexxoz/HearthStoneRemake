
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
		
		if(cardsPlayed.size() < 8) {
			
			int value = 0;
			
			while(mana > 0 && cardsInHand.size() > 1) {
				Character bestPlay = cardsInHand.get(0);
				int tempValue = 0;
				for(int i = 1; i<cardsInHand.size();i++) {
					Character compare = cardsInHand.get(i);
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
			if(cardsInHand.size() == 1 && playerPlayedCards.size() > 1) {
				Character bestPlay = cardsInHand.get(0);
				cardsPlayed.add(bestPlay);
				cardsInHand.remove(bestPlay);		
				mana -= bestPlay.getCost();
			}
		}
		
	}
	
	public void attack() {
		//for each enemy
		for(int i = 0; i < cardsPlayed.size(); i++) {
			Character enemy = cardsPlayed.get(i);
			if(!enemy.getHasMoved()) {
				// checks to see if there are any minions to attack
				if(playerPlayedCards.size() > 1) {
					int index = 0;
					int bestValue = 0;
					
					// go through all player characters
					for(int j = 0; j < playerPlayedCards.size(); j++) {	
						Character player = playerPlayedCards.get(j);
						int value = 0;
						
						if(enemy.getAtk() >= player.getHp()) {
							value += 5;
						}
						if(enemy.getHp() > player.getAtk()) {
							value += 5;
						}
						
						if(value>bestValue) {
							index = j;
							bestValue = value;
						}
						
					}
						
					if(enemy.takeDamage(playerPlayedCards.get(index).getAtk())) {
						System.out.println("Enemy died");
						cardsPlayed.remove(enemy);
					}
					if(playerPlayedCards.get(index).takeDamage(enemy.getAtk())) {
						System.out.println("player died");
						playerPlayedCards.remove(index);
					}				
					
				}else if(playerPlayedCards.size() == 1){
					
					if(enemy.takeDamage(playerPlayedCards.get(0).getAtk())) {
						System.out.println("Enemy died");
						cardsPlayed.remove(enemy);
					}
					if(playerPlayedCards.get(0).takeDamage(enemy.getAtk())) {
						System.out.println("player died");
						playerPlayedCards.remove(0);
					}
					
				}else{
					//System.out.println("No minion to attack");
					//attack face
				}
	
			}
		}

	}
	
	
}
