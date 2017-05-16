import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Display Window of a splines-based function creator given n points
 * 
 * Country: Spain
 * University: Universidad de La Laguna
 * Subject: Programación de Aplicaciones Interactivas
 * Repository: https://github.com/AkaiBF/ShooterGamePAI
 * 
 * @author Ernesto Echeverría González
 * @email alu0100881622@ull.edu.es
 * @since 05-03-2017
 * @version 1.0.0
 */

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	protected final int WIDTH = 1200;
	protected final int HEIGHT = 500;
	
	public Window() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		
		setLayout(new BorderLayout());
		add(new GraphicScreen(15), BorderLayout.CENTER);
		add(new Controller(), BorderLayout.SOUTH);
	}
	
	public static void main(String args[]) {
		new Window();
	}
}
