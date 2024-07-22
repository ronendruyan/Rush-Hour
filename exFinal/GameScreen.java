package exFinal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;

public class GameScreen extends Screen {
	private static final int GRID_SIZE = 8;
	private static final int CELL_SIZE = 80;

	public GameScreen(ScreenManager SM) {
		super();
		JButton car = new JButton("This is a car");
		car.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Mouse Pressed");
            }
            
            public void mouseReleased(MouseEvent me) {
                System.out.println("Mouse Released");
            }
        });
		car.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                System.out.println("my x is " + me.getX() + "my y is " + me.getY());
                car.setBounds(me.getX(), 100, 100, 50);
                
            }
        });
		car.setBounds(100, 100, 100, 50);
		_components.add(car);
		
        
		
	}
}
