import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

//import javafx.animation.Animation;


public class Board extends JComponent {
	
	//Game board photos
	BufferedImage postIcon;
	BufferedImage postIconUsed;
	BufferedImage gameAreaTexture;
	BufferedImage postBackground;
	BufferedImage cardBacks;
	
	//player Decks
	private Deck playerDeck;
	private Deck playedPlayerCards;
	private Deck playerHand;
	
	//enemy decks
	AI enemyAI;
	private Deck enemyDeck;
	private Deck playedEnemyCards;
	private Deck enemyHand;
	
	
	//All variables
	private static int mana;
	private static int usedMana;
	
	// turn button object
	private Rectangle nextTurnButton = new Rectangle(950,510,50,40);
	
	// game area rectangle object
	private Rectangle gameArea = new Rectangle(0, 120, 950, 390);
	
	/**
	 * Constructor for the game board
	 */
	public Board() {
		super();
		//load images
		try {
			postIcon = ImageIO.read(new File("postIcon.png"));
			postIconUsed = ImageIO.read(new File("postIconUsed.png"));
			gameAreaTexture = ImageIO.read(new File("background.jpg"));
			postBackground = ImageIO.read(new File("postBackground.jpg"));
			cardBacks = ImageIO.read(new File("cardBacks.png"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		// 
		// The players deck
		// max hand is 8
		// 
		playerDeck = new Deck();
		playerDeck.startingDeck(5);
		
		playerHand = new Deck();
		playerHand.startingDeck(5);
		
		playedPlayerCards = new Deck();
		
		System.out.println("playerDeck: " + playerDeck.toString());
		System.out.println("playerHand: " + playerHand.toString());
		
		// enemy deck instantiations
		enemyAI = new AI();
		enemyDeck = new Deck();
		enemyDeck.startingDeck(5);
		
		enemyHand = new Deck();
		enemyHand.startingDeck(5);
		
		playedEnemyCards = new Deck();
		
		System.out.println("enemyDeck: " + enemyDeck.toString());
		System.out.println("enemyHand: " + enemyHand.toString());
		
		// set the starting mana for the player
		// max mana is 10
		mana = 1;
		usedMana = 0;
	}
	
	/**
	 * Paints the graphics to the game board
	 */
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		
		// sets the game board
		canvas.drawImage(gameAreaTexture, 0,120,null);
		canvas.setColor(new Color(255,255,255,1));
		canvas.fill(gameArea);
		
		
		// prints a maximum of 8 cards in the enemy's hand
		int padding = 0;
		for(Card o : enemyHand.getCards()) {
			try {
				canvas.drawImage(cardBacks, 20+padding, 5, null);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			canvas.setColor(Color.white);
			canvas.drawString("Name: " + o.getName(), 30+padding, 75);
//			canvas.drawString("ATK: " + a.getAtk(), 58+padding, 95);
//			canvas.setColor(Color.black);
//			canvas.drawString("Cost: " + a.getCost(), 60+padding, 115);
			padding += 500/enemyHand.size()+50;
			
			
		}		
		
		// prints all the cards the player has played
		padding = 0;
		for(Character a : playedEnemyCards.getCharacters()) {
			a.setRect(50+padding, 270, 50,  50);
			try {
				canvas.drawImage(ImageIO.read(new File(a.getSourceLittle())), 50+padding, 270, null);
				canvas.setColor(new Color(255,255,255,1));
				canvas.fill(a.getRect());
			}catch (Exception e) {
				canvas.setColor(Color.red);
				canvas.fill(a.getRect());
//				System.out.println(e.getMessage());
			}
			canvas.setColor(Color.white);
			canvas.drawString("HP: " + a.getHp(), 60+padding, 290);
			canvas.drawString("ATK: " + a.getAtk(), 58+padding, 300);
			padding += 500/playedEnemyCards.size()+50;
		}		

		// creates the outline for the players hand
		canvas.setColor(Color.white);
		canvas.drawString("ENEMY HAND", 15, 140);
		canvas.drawLine(0, 120, 1000, 120);	
		
		// creates the outline for the players hand
		canvas.setColor(Color.white);
		canvas.drawString("YOUR HAND", 15, 495);
		canvas.drawLine(0, 510, 1000, 510);
		
		// prints a maximum of 8 cards in the players hand
		padding = 0;
		for(Card a : playerHand.getCards()) {
			
			a.setRect(20+padding, 515);
			try {
				canvas.drawImage(ImageIO.read(new File(a.getSourceBig())), 20+padding, 515, null);
				canvas.setColor(new Color(255,255,255,1));			
				canvas.fill(a.getRect());
			}catch (Exception e) {
				canvas.setColor(Color.red);
				canvas.fill(a.getRect());
//				System.out.println(e.getMessage());
			}
			
			if(a.getType().equals("Character")) {
				Character c = (Character)a;
				canvas.setColor(Color.white);
				canvas.drawString("HP: " + c.getHp(), 60+padding, 535);
				canvas.drawString("ATK: " + c.getAtk(), 58+padding, 555);
				canvas.setColor(Color.black);
				canvas.drawString("Cost: " + c.getCost(), 60+padding, 575);
				padding += 500/playerHand.size()+50;
			}else if(a.getType().equals("Spell")) {
				Spell c = (Spell)a;
				canvas.setColor(Color.white);
				if(c.getType().equals("heal")) {
					canvas.drawString("Heal: " + c.getHeal(), 60+padding, 535);	
				}if(c.getType().equals("damage")) {
					canvas.drawString("Damage: " + c.getDamage(), 60+padding, 535);	
				}
				canvas.setColor(Color.black);
				canvas.drawString("Cost: " + c.getCost(), 60+padding, 575);
				padding += 500/playerHand.size()+50;
			}else {
				System.out.println("ERROR: UNKNOWN CARD IN DECK");
			}
			
		}
		
		// prints all the cards the player has played
		padding = 0;
		for(Character a : playedPlayerCards.getCharacters()) {
			a.setRect(50+padding, 370, 50,50);
			try {
				canvas.drawImage(ImageIO.read(new File(a.getSourceLittle())), 50+padding, 370, null);
				canvas.setColor(new Color(255,255,255,1));
				canvas.fill(a.getRect());
			}catch (Exception e) {
				canvas.setColor(Color.red);
				canvas.fill(a.getRect());
				System.out.println(e.getMessage());
			}
			canvas.setColor(Color.white);
			canvas.drawString("HP: " + a.getHp(), 60+padding, 390);
			canvas.drawString("ATK: " + a.getAtk(), 58+padding, 400);
			padding += 500/playedPlayerCards.size()+50;
		}
		
		// the mana section background
		canvas.drawImage(postBackground, 950, 120, null);
		
		// mana section titles
		canvas.setColor(Color.white);
		canvas.drawString("Posts:", 950, 130);
		
		// prints the mana gems on the side
		padding = 0;
		for(int i = 1; i<= mana-usedMana;i++) {
			canvas.drawImage(postIcon, 955, 150+padding, null);
			padding += 35;
		}
		//canvas.setColor(Color.black);
		for(int i = mana-usedMana; i< mana;i++) {
			canvas.drawImage(postIconUsed, 955, 150+padding, null);
			padding += 35;
		}		
		
		
		canvas.setColor(Color.BLACK);
		
		canvas.fill(nextTurnButton);
		canvas.setColor(Color.white);
		canvas.drawString("Next", 960, 525);
		canvas.drawString("Turn", 960, 545);
		
		canvas.setColor(Color.black);
		String deckCount = playerDeck.size() + "";
		canvas.drawString("Cards: " + deckCount, 940, 570);
		
		
	}
	/**
	 * Puts the card played from hand to board
	 * @param cardPlayed card to play
	 */
	public void playCard(Card cardPlayed) {
		if(cardPlayed.getCost() <= mana-usedMana && cardPlayed.getType().equals("Character") && playedPlayerCards.size() < 8) {
			System.out.println("Played Character from hand");
			cardPlayed.setPos("Board");
			playedPlayerCards.add(cardPlayed);
			playerHand.remove(cardPlayed);
			usedMana += cardPlayed.getCost();
						
		}
		if(cardPlayed.getCost() <= mana-usedMana && cardPlayed.getType().equals("Spell")) {
			Spell spellPlayed = (Spell)cardPlayed;
			System.out.println("Played Spell from hand");
			if(spellPlayed.getSpellType().equals("Damage")) {
				for(Character c : playedEnemyCards.getCharacters()) {
					if(c.takeDamage(spellPlayed.getDamage())) {
						System.out.println("Enemy died");
						playedEnemyCards.remove(c);
					}
				}
			}
			if(spellPlayed.getSpellType().equals("Heal")) {
				for(Character c : playedPlayerCards.getCharacters()) {
					c.heal(spellPlayed.getHeal());
					System.out.println("Ally healed");
				}
			}			
			
			playerHand.remove(cardPlayed);
			usedMana += cardPlayed.getCost();			
		}
		
		repaint();
	}
	
	/**
	 * Method to check if a player card was selected for dragging
	 * @param x coord of the mouse
	 * @param ycoord of the mouse
	 * @return the card that was selected
	 */
	public Card selectedPlayerCard(int x, int y) {
		for(Card c : playerHand.getCards()) {
			if(c.getRect().contains(x, y)) {
				System.out.println("Selected Card from hand");
				return c;
			}
		}
		for(Character c : playedPlayerCards.getCharacters()) {
			if(c.getRect().contains(x, y)) {
				System.out.println("Selected Card from board");
				if(c.getHasMoved()) {
					return null;
				}
				return c;
			}
		}		
		return null;
		//repaint();
		
	}
	/**
	 * Runs the code for the next turn
	 * @param x coords of the mouse
	 * @param y coord of the mouse
	 * @return boolean if the button was pressed for optimization
	 */
	public boolean nextTurn(int x, int y) {
		if(nextTurnButton.contains(x, y)) {
			if(mana < 10) {		
				mana++;
				usedMana = 0;		
			}else if(mana == 10) {
				usedMana = 0;				
			}			
			if(playerHand.size() < 8 && playerDeck.size() > 0) {
				playerHand.add(playerDeck.get(0));
				playerDeck.remove(playerDeck.get(0));
			}	
			if(enemyHand.size() < 8 && enemyDeck.size() > 0) {
				enemyHand.add(enemyDeck.get(0));
				enemyDeck.remove(enemyDeck.get(0));
//				System.out.println(enemyDeck.getCharacters().get(0).toString());
			}
			
			
			enemyAI.enemyTurn(enemyHand, playedEnemyCards, playedPlayerCards, mana);
			playedPlayerCards.refreshCards();
			playedEnemyCards.refreshCards();
			repaint();
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Method to handle the player attacking
	 * @param attackingCardIn Card thats attacking
	 * @param enemyCardIn Card thats being attacked
	 */
	public void playerAttack(Card attackingCardIn, Card enemyCardIn) {
		if(attackingCardIn.getType().equals("Character") && enemyCardIn.getType().equals("Character")) {
			Character attackingCard = (Character) attackingCardIn;
			Character enemyCard = (Character) enemyCardIn;
			attackingCard.changeHasMovedTrue();
			if(enemyCard.takeDamage(attackingCard.getAtk())) {
				System.out.println("Enemy died");
				playedEnemyCards.remove(enemyCard);
			}
			if(attackingCard.takeDamage(enemyCard.getAtk())) {
				System.out.println("player died");
				System.out.println(attackingCard);
				playedPlayerCards.remove(attackingCard);
			}
			repaint();
		}
	}
	
	/**
	 * Method to get enemy played cards
	 * @return enemy played cards
	 */
	public Deck getEnemyCards(){
		return playedEnemyCards;
	}
	
	/**
	 * Method to return the game area
	 * @return game area rectangle
	 */
	public Rectangle getGameArea() {
		return gameArea;
	}

	
}
