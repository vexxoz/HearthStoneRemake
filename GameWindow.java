import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

	private static Character selectedAlly;
	
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
				myBoard.nextTurn(x,y);
				selectedAlly = myBoard.cardHighlight(x, y);
				
			}

			@Override
			public void mouseReleased(MouseEvent p) {
				int x = p.getX();
				int y = p.getY();
				if(selectedAlly != null) {
					if(myBoard.getGameArea().contains(x,y)) {
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
