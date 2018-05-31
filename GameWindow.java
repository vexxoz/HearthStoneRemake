import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

	private static Card selectedAlly;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("HearthStone");
		frame.setSize(1000, 650);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
						if(c.getRect().contains(x,y)) {
							System.out.println("released on enemy");
							myBoard.playerAttack(selectedAlly, c);
							isEnemy = true;
							break;
						}
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
		
		frame.add(panel);
		frame.setVisible(true);
		

	}

}
