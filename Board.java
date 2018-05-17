import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Board extends JComponent {

	//All Cards Class
	AllCards allCards = new AllCards();
	
	//photos
	BufferedImage postIcon;
	BufferedImage postIconUsed;
	BufferedImage gameAreaTexture;
	BufferedImage postBackground;
	
	
	//variables
	private Deck playerDeck;
	private Deck playedPlayerCards;
	private Deck playerHand;
	private int mana;
	private int usedMana;
	
	// turn button object
	private Rectangle nextTurnButton = new Rectangle(950,390,50,40);
	
	// game area rectangle object
	private Rectangle gameArea = new Rectangle(0, 0, 950, 390);
	
	public Board() {
		super();
		//load images
		try {
			postIcon = ImageIO.read(new File("postIcon.png"));
			postIconUsed = ImageIO.read(new File("postIconUsed.png"));
			gameAreaTexture = ImageIO.read(new File("background.jpg"));
			postBackground = ImageIO.read(new File("postBackground.jpg"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		// 
		// The players deck
		// max hand is 8
		// 
		
		playerDeck = new Deck();
		playerDeck.startingDeck(0, allCards.getCards());
		
		playerHand = new Deck();
		playerHand.startingDeck(8, allCards.getCards());
		
		playedPlayerCards = new Deck();
		
		// array of cards played on the board
		
		
		// print the decks stats to console
		System.out.println(playerDeck);
		
		// set the starting mana for the player
		// max mana is 10
		mana = 1;
		usedMana = 0;
		System.out.println("Mana: " + mana);
		System.out.println("Used Mana: " + usedMana);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		
		// sets the game board
		canvas.drawImage(gameAreaTexture, 0,0,null);
		canvas.setColor(new Color(255,255,255,1));
		canvas.fill(gameArea);
		
		// creates the outline for the players hand
		canvas.setColor(Color.blue);
		int padding = 0;
		canvas.drawString("YOUR HAND", 15, 385);
		canvas.drawLine(0, 390, 1000, 390);
		
		// prints a maximum of 8 cards in the players hand
		for(Character a : playerHand.getCharacters()) {
//			try {
//				canvas.drawImage(ImageIO.read(new File(a.getSource())), 50+padding, 400, null);
//			}catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
			a.setRect(50+padding, 400);
//			canvas.setColor(new Color(255,255,255,1));			
			canvas.fill(a.getRect());
			//System.out.println(a.getRect());
			canvas.setColor(Color.white);
			canvas.drawString("HP: " + a.getHp(), 60+padding, 420);
			canvas.drawString("ATK: " + a.getAtk(), 58+padding, 440);
			canvas.setColor(Color.black);
			canvas.drawString("Cost: " + a.getCost(), 60+padding, 460);
			padding += 500/playerHand.getCharacters().size()+50;
			
			
		}
		
		// prints all the cards the player has played
		padding = 0;
		for(Character a : playedPlayerCards.getCharacters()) {
//			try {
//				canvas.drawImage(ImageIO.read(new File(a.getSource())), 50+padding, 250, null);
//			}catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
			a.setRect(50+padding, 250);
//			canvas.setColor(new Color(255,255,255,1));
			canvas.fill(a.getRect());
			canvas.setColor(Color.white);
			canvas.drawString("HP: " + a.getHp(), 60+padding, 270);
			canvas.drawString("ATK: " + a.getAtk(), 58+padding, 290);
			padding += 500/playedPlayerCards.getCharacters().size()+50;
		}
		
		// the mana section background
		//canvas.setColor(new Color(117, 117, 117));
		//canvas.fillRect(950, 0, 50, 390);
		canvas.drawImage(postBackground, 950, 0, null);
		
		// mana section titles
		canvas.setColor(Color.white);
		canvas.drawString("Posts:", 950, 10);
		
		// prints the mana gems on the side
		padding = 0;
		canvas.setColor(Color.red);
		for(int i = 1; i<= mana-usedMana;i++) {
			//canvas.drawImage(postIcon, 955, 30+padding, null);
			canvas.fillOval(955,30+padding, 30, 30);
			padding += 35;
		}
		//canvas.setColor(Color.black);
		for(int i = mana-usedMana; i< mana;i++) {
			canvas.drawImage(postIconUsed, 955, 30+padding, null);
			//canvas.fillOval(955,30+padding, 30, 30);
			padding += 35;
		}		
		
		
		canvas.setColor(Color.BLACK);
		
		canvas.fill(nextTurnButton);
		canvas.setColor(Color.white);
		canvas.drawString("Next", 960, 405);
		canvas.drawString("Turn", 960, 425);
		
		canvas.setColor(Color.black);
		String deckCount = playerDeck.getCharacters().size() + "";
		canvas.drawString("Cards: " + deckCount, 940, 450);
		
		
	}
	
	public void playCard(Character a) {
		if(a.getCost() <= mana-usedMana && playedPlayerCards.getCharacters().size() < 8) {
			playedPlayerCards.add(a);
			playerHand.remove(a);
			usedMana += a.getCost();
			repaint();			
		}
		System.out.println("Mana: " + mana);
		System.out.println("Used Mana: " + usedMana);
	}
	
	public Character cardHighlight(int x, int y) {
		for(Character a: playerHand.getCharacters()) {
			if(a.getRect().contains(x, y)) {
				System.out.println(a.toString());
				return a;
			}
		}
		return null;
		//repaint();
		
	}
	
	public void nextTurn(int x, int y) {
		if(nextTurnButton.contains(x, y)) {
			if(mana < 10) {		
				mana++;
				usedMana = 0;
				if(playerHand.getCharacters().size() < 8 && playerDeck.getCharacters().size() > 0) {
					playerHand.add(playerDeck.getCharacters().get(0));
					playerDeck.remove(playerDeck.getCharacters().get(0));
				}				
			}			
			if(mana == 10) {
				usedMana = 0;
				if(playerHand.getCharacters().size() < 8 && playerDeck.getCharacters().size() > 0) {
					playerHand.add(playerDeck.getCharacters().get(0));
					playerDeck.remove(playerDeck.getCharacters().get(0));
				}					
			}			
		}
		repaint();
	}
	
	public Rectangle getGameArea() {
		return gameArea;
	}

	
}
