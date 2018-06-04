import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow {

	private static Card selectedAlly;
	private static boolean menuClosed = false;
	
	public static void main(String[] args) {
		
		JFrame menuFrame = new JFrame("MemeStone");
		menuFrame.setSize(200,300);
		menuFrame.setResizable(false);
		
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel menu = new JPanel();
		
		JLabel title = new JLabel("MemeStone");
		JButton start = new JButton("Start Game!");
		JButton end = new JButton("Exit Game!");
		
		menu.add(title);
		menu.add(start);
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
			}
			
		}
		
		start.addActionListener(new menuListener());
		end.addActionListener(new menuListener());
		
		menuFrame.add(menu);
		
		menuFrame.setVisible(true);

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
				if(!myBoard.nextTurn(x,y)) {
					selectedAlly = myBoard.selectedPlayerCard(x, y);
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
							System.out.println("released on enemy");
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
						System.out.println("released on enemy Hero");
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
