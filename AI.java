import java.util.List;

public class AI {

	private Deck cardsInHand;
	private Deck cardsPlayed;
	private Deck playerPlayedCards;
	private int mana;
	
	/**
	 * Constructor for AI
	 */
	public AI() {
		mana = 0;
	}
	
	/**
	 * Helper Method for an AI turn
	 * @param cardsInHandIn Deck of cards in enemy hand
	 * @param cardsPlayedIn Deck of cards the enemy has played
	 * @param playedPlayerCardsIn Deck of cards the player has played
	 * @param manaIn How much mana in a turn 
	 */
	public void enemyTurn(Deck cardsInHandIn, Deck cardsPlayedIn, Deck playedPlayerCardsIn, int manaIn) {
		cardsInHand = cardsInHandIn;
		cardsPlayed = cardsPlayedIn;
		playerPlayedCards = playedPlayerCardsIn;
		mana = manaIn;
		
		move();
		attack();
	}
	
	/**
	 * Method to play cards
	 */
	public void move() {
		
		if(cardsPlayed.size() < 8) {
			
			int value = 0;
			
			while(mana > 0 && cardsInHand.size() > 1) {
				Card tempBestPlay = cardsInHand.get(0);
				int tempValue = 0;
				for(int i = 1; i<cardsInHand.size();i++) {
					Card tempCompare = cardsInHand.get(i);
					if(tempCompare.getCost() <= mana) {
						if(tempCompare.getType().equals("Character") && tempBestPlay.getType().equals("Character")) {
							Character compare = (Character)tempCompare;
							Character bestPlay = (Character)tempBestPlay;
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
						}if(tempBestPlay.getType().equals("Spell") && tempCompare.getType().equals("Character")) {
							Spell spell = (Spell) tempBestPlay;
							if(spell.getSpellType().equals("Heal") && cardsPlayed.getCharacters().size() > 0) {
								tempBestPlay = (Card)spell;
							}else if(spell.getSpellType().equals("Damage") && playerPlayedCards.getCharacters().size() > 0) {
								tempBestPlay = (Card)spell;
							}else {
								tempBestPlay = tempCompare;
							}
						}
					}
				}
				
				//System.out.println(bestPlay.toString());
				if(tempBestPlay.getType().equals("Character")) {
					System.out.println("Enemy played Character");
					Character bestPlay = (Character) tempBestPlay;
					cardsPlayed.add(bestPlay);			
				}else {
					Spell spell = (Spell)tempBestPlay;
					if(spell.getSpellType().equals("Damage")) {
						System.out.println("Enemy Played Damage Spell");
						for(Character c : playerPlayedCards.getCharacters()) {
							if(c.takeDamage(spell.getDamage())) {
								System.out.println("Ally died by Spell");
								playerPlayedCards.remove(c);
							}							
						}					
					}
					if(spell.getSpellType().equals("Heal")) {
						System.out.println("Enemy Played Heal Spell");
						for(Character c : cardsPlayed.getCharacters()) {
							c.heal(spell.getHeal());
							System.out.println("Enemy healed");
						}
					}	
				}

				cardsInHand.remove(tempBestPlay);
				mana -= tempBestPlay.getCost();		
			}
			if(cardsInHand.size() == 1 && playerPlayedCards.size() > 1) {
				Card bestPlay = cardsInHand.get(0);
				if(bestPlay.getType().equals("Character")) {
					Character c = (Character) bestPlay;
					cardsPlayed.add(c);					
				}else {
					System.out.println("Enemy played Spell");
					Spell spell = (Spell)bestPlay;
					if(spell.getSpellType().equals("Damage") && playerPlayedCards.getCharacters().size() > 0) {
						for(Character c : playerPlayedCards.getCharacters()) {
							if(c.takeDamage(spell.getDamage())) {
								System.out.println("Ally died by Spell");
								playerPlayedCards.remove(c);
							}							
						}
						cardsInHand.remove(bestPlay);
						mana -= bestPlay.getCost();						
					}
					if(spell.getSpellType().equals("Heal") && cardsPlayed.getCharacters().size() > 0) {
						for(Character c : cardsPlayed.getCharacters()) {
							c.heal(spell.getHeal());
							System.out.println("Enemy healed");
						}
						cardsInHand.remove(bestPlay);
						mana -= bestPlay.getCost();						
					}	
				}
				cardsInHand.remove(bestPlay);		
				mana -= bestPlay.getCost();
			}
		}
		
	}
	
	/**
	 * Method to attack with available cards played
	 */
	public void attack() {
		//for each enemy
		for(int i = 0; i < cardsPlayed.size(); i++) {
			Character enemy = cardsPlayed.getCharacters().get(i);
			if(!enemy.getHasMoved()) {
				// checks to see if there are any minions to attack
				if(playerPlayedCards.size() > 1) {
					int index = 0;
					int bestValue = 0;
					
					// go through all player characters
					for(int j = 0; j < playerPlayedCards.size(); j++) {	
						Character player = playerPlayedCards.getCharacters().get(j);
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
						
					if(enemy.takeDamage(playerPlayedCards.getCharacters().get(index).getAtk())) {
						System.out.println("Enemy died");
						cardsPlayed.remove(enemy);
					}
					if(playerPlayedCards.getCharacters().get(index).takeDamage(enemy.getAtk())) {
						System.out.println("player died");
						playerPlayedCards.remove(index);
					}				
					
				}else if(playerPlayedCards.size() == 1){
					
					if(enemy.takeDamage(playerPlayedCards.getCharacters().get(0).getAtk())) {
						System.out.println("Enemy died");
						cardsPlayed.remove(enemy);
					}
					if(playerPlayedCards.getCharacters().get(0).takeDamage(enemy.getAtk())) {
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
