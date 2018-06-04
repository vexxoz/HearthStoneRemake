import java.util.List;

public class AI {

	private Deck cardsInHand;
	private Deck cardsPlayed;
	private Deck playerPlayedCards;
	private int mana;
	private Hero playerHero;
	
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
	public boolean enemyTurn(Deck cardsInHandIn, Deck cardsPlayedIn, Deck playedPlayerCardsIn, Hero playerHeroIn, int manaIn) {
		cardsInHand = cardsInHandIn;
		cardsPlayed = cardsPlayedIn;
		playerPlayedCards = playedPlayerCardsIn;
		mana = manaIn;
		playerHero = playerHeroIn;
		
		move();
		if(attack()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method to play cards
	 */
	private void move() {

//		System.out.println("ENEMY AI MOVE THOUGHTS START ");
//		System.out.println();
//		System.out.println();
		
		Card bestCard = null;
		int bestValue = 0;
		boolean spellPlayable = true;
		
		for(Card card : cardsInHand.getCards()) {
			int value = 0;
			if(card.getType().equals("Character")){
//				System.out.println("Checking enemy Character");
				Character character = (Character)card;
				if(cardsInHand.getCards().size() == 1 && character.getCost() <= mana) {
					bestCard = character;
				}else{
					if(character.getCost() <= mana) {
						if(character.getAtk() >= 0) {
							 value += 1;
						 }
						 if(character.getAtk() > 5) {
							 value += 10;
						 }
						 if(character.getHp() > 0) {
							 value += 1;
						 }
						 if(character.getHp() > 4) {
							 value += 5;
						 }
						 if(character.getHp() > 6) {
							 value += 10;
						 }
						 if(character.getCost() < 10) {
							 value += 1;
						 }
						 if(character.getCost() < 4) {
							 value += 2;
						 }
						 if(character.getCost() < 2) {
							 value += 3;
						 }
					}
				}
				if(value > bestValue) {
					bestCard = character;
				}
			}else if(card.getType().equals("Spell")) {
				Spell spell = (Spell)card;
				if(spell.getSpellType().equals("Damage")) {
					// if the player has more than one character played and the enemy has less than or equal to 2 cards
					if(playerPlayedCards.getCharacters().size() > 1 && cardsInHand.getCards().size() <=2 && spell.getCost() <= mana) {
						System.out.println("Enemy played a damage spell");
						bestCard = spell;
						
					// if this spell is your only card and the player has more than one card played
					}else if (playerPlayedCards.getCharacters().size() > 0 && cardsInHand.getCards().size() == 1 && spell.getCost() <= mana) {
						System.out.println("Enemy played a damage spell");
						bestCard = spell;
					// if the player has more than or equal to 4 cards played then play a damage spell
					}else if (playerPlayedCards.getCharacters().size() > 4 && spell.getCost() <= mana) {
						System.out.println("Enemy played a damage spell");
						bestCard = spell;						
					}else {
						spellPlayable = false;
					}
				}else if(spell.getSpellType().equals("Heal")) {
					// if the enemy has 1 or more cards then play the heal spell
					if(cardsPlayed.getCharacters().size() >= 1 && spell.getCost() <= mana) {
						System.out.println("Enemy played a heal spell");
						bestCard = spell;
					}else {
						spellPlayable = false;
					}
				}
			}else {
				System.out.println("Unknown Card type");
			}
		}
		
		if(bestCard != null) {
			cardsInHand.remove(bestCard);
			mana -= bestCard.getCost();
			if(bestCard.getType().equals("Character")) {
				System.out.println("Enemy played a character");
				cardsPlayed.add(bestCard);
			}
			if(bestCard.getType().equals("Spell")) {
				Spell spell = (Spell)bestCard;
				if(spell.getSpellType().equals("Damage")) {
					for(Character c : playerPlayedCards.getCharacters()) {
						if(c.takeDamage(spell.getDamage())) {
							System.out.println("Ally died from enemy damage spell");
							playerPlayedCards.remove(c);
						}
					}					
				}else if(spell.getSpellType().equals("Heal")) {
					for(Character c : cardsPlayed.getCharacters()) {
						c.heal(spell.getHeal());
						System.out.println("Enemy minion healed healed");
					}
				}
			}
		}
		
//		System.out.println("ENEMY AI MOVE THOUGHTS END ");
		
		if(cardsInHand.getIndexOf(mana) != null && spellPlayable) {
//			System.out.println("Playing another card");
			move();
		}
		
	}
	
	/**
	 * Method to attack with available cards played
	 */
	private boolean attack() {
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
					
				}else {
					System.out.println("Enemy attacked your hero");
					if(playerHero.takeDamage(enemy.getAtk())) {
						return true;
					}
				}
	
			}
		}
		return false;
	}
	
	
}
