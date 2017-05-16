import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Display Window of a splines-based function creator given n points
 * 
 * Country: Spain
 * University: Universidad de La Laguna
 * Subject: Programación de Aplicaciones Interactivas
 * Repository: https://github.com/AkaiBF/Spline-Interpolation-PAI
 * 
 * @author Ernesto Echeverría González
 * @email alu0100881622@ull.edu.es
 * @since 05-03-2017
 * @version 1.0.0
 */

public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	protected final int WIDTH = 1200;
	protected final int HEIGHT = 500;

	private Controller controller;
	private GraphicScreen graphicScreen;
	
	public Window() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		
		controller = new Controller();
		graphicScreen = new GraphicScreen(15);
		
		setLayout(new BorderLayout());
		add(new GraphicScreen(15), BorderLayout.CENTER);
		add(controller, BorderLayout.SOUTH);
	}
	
	public static void main(String args[]) {
		new Window();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == getController().getRefreshButton()) {
			getGraphicScreen().clear();
		}
	}
	
	public Controller getController() {
		return controller;
	}
	public GraphicScreen getGraphicScreen() {
		return graphicScreen;
	}
}
