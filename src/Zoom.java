import javax.swing.*;
import java.awt.event.*;


public class Zoom implements MouseWheelListener{
	public static JPanel board;
	
	public Zoom(JPanel panel) {
		board = panel;
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int nothes = e.getWheelRotation();
		
		board.setSize(board.getWidth()-100*nothes,board.getHeight()-100*nothes);
	
	}
}
