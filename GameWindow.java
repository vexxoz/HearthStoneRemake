import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameWindow {

	private static Card selectedAlly;
	private static boolean menuClosed = false;
	
	public static void main(String[] args) {
	
		menuWindow();

	}
	
	public static void menuWindow() {
		JFrame menuFrame = new JFrame("MemeStone");
		menuFrame.setSize(200,300);
		menuFrame.setResizable(false);
		
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel menu = new JPanel();
		
		menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("MemeStone");
		JButton start = new JButton("START GAME");
		JButton howToPlay = new JButton("HOW TO PLAY");
		JButton end = new JButton("EXIT GAME");
		
		title.setFont(new Font("TimesRoman", Font.PLAIN, 35));
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		howToPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		end.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		menu.add(title);
		menu.add(start);
		menu.add(howToPlay);
		menu.add(end);
		
		
		class menuListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(start)) {
					startGame();
					menuFrame.dispose();
				}
				if(e.getSource().equals(end)) {
					menuFrame.dispose();
				}
				if(e.getSource().equals(howToPlay)) {
					howToPlay();
				}
			}
			
		}
		
		start.addActionListener(new menuListener());
		howToPlay.addActionListener(new menuListener());
		end.addActionListener(new menuListener());
		
		menuFrame.add(menu);
		
		menuFrame.setVisible(true);
	}
	
	public static void howToPlay() {
		JFrame howToPlay = new JFrame("MemeStone");
		howToPlay.setSize(1050,850);
		howToPlay.setResizable(false);
		
		howToPlay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JPanel menu = new JPanel();
		
		menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("How To Play");
		JTextArea dec = new JTextArea("This game is just like HeathStone, you have to destroy your enemy's hero before they destroy yours. You can play cards that can attack and spells that heal and do damage. Cards that are played can not attack on the same turn. A card can not attack more than once per turn. If a card runs out of health then it dies. H stands for health, D stands for damage, A for attack, and C for cost. You can not play cards that cost more than the posts (mana) you have.");
		dec.setWrapStyleWord(true);
		dec.setLineWrap(true);
		JLabel image = new JLabel(new ImageIcon("howToPlayImage.jpg")); 
		
		JButton end = new JButton("Close");
		
		title.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		dec.setAlignmentX(Component.CENTER_ALIGNMENT);
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		end.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		menu.add(title);
		menu.add(dec);
		menu.add(image);
		menu.add(end);
		
		
		class menuListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(end)) {
					howToPlay.dispose();
				}
			}
			
		}
		
		end.addActionListener(new menuListener());
		
		howToPlay.add(menu);
		
		howToPlay.setVisible(true);
	}
	
	public static void startGame() {
		JFrame gameFrame = new JFrame("MemeStone");
		gameFrame.setSize(1000, 650);
		gameFrame.setResizable(false);
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		Board myBoard = new Board();
		myBoard.setPreferredSize(new Dimension(1000, 650));
		
		//mouse clicks
		class CanvasListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent p) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent p) {
				int x = p.getX();
				int y = p.getY();
				if(myBoard.getGameOverText().equals("")) {
					if(!myBoard.nextTurn(x,y)) {
						selectedAlly = myBoard.selectedPlayerCard(x, y);
					}
				}else {
					if(myBoard.selectMainMenu(x,y)) {
						gameFrame.dispose();
						menuWindow();
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent p) {
				int x = p.getX();
				int y = p.getY();
				if(selectedAlly != null) {
					boolean isEnemy = false;
					for(Character c : myBoard.getEnemyCards().getCharacters()) {
						if(c.getRect().contains(x,y) && selectedAlly.getPos().equals("Board")) {
							//System.out.println("released on enemy");
							myBoard.playerAttack(selectedAlly, c);
							isEnemy = true;
							break;
						}
					}
					if(myBoard.getEnemyHero().getRect().contains(x, y) && selectedAlly.getPos().equals("Board")) {
						if(myBoard.attackHero(selectedAlly)) {
							myBoard.setGameOverText("YOU WON");
						}
						myBoard.moved(selectedAlly);
//						System.out.println("released on enemy Hero");
						isEnemy = true;
					}
					if(myBoard.getGameArea().contains(x,y) && !isEnemy && selectedAlly.getPos().equals("Hand")) {
						myBoard.playCard(selectedAlly);
					}
				}
				selectedAlly = null;
			}

			
		}
		
		myBoard.addMouseListener(new CanvasListener());		
		
		panel.add(myBoard);
		
		gameFrame.add(panel);
		gameFrame.setVisible(true);
	}

}
