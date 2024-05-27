
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class Application extends JFrame{
	private JPanel arrowsPlate;
	public Slot[][] map;
	
	public Application() {
		map = new Slot[100][100];
		initUI();
	}
	private void initUI() {
		
		
		arrowsPlate = new Board(map.length,map[0].length,map,this);
		
		
		add(arrowsPlate,BorderLayout.CENTER);
		pack();
		
		setSize(1000,800);
		setTitle("Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			Application ex = new Application();
			ex.setVisible(true);
		});
	}
}
