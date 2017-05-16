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
 * @since 05-16-2017
 * @version 1.0.0
 */

public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	protected final int WIDTH = 1200;																						// By default width of the frame
	protected final int HEIGHT = 500;																						// By default height of the frame

	private Controller controller;																							// Controller of the application
	private GraphicScreen graphicScreen;																				// Visualization panel of the application
	
	public Window() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		
		controller = new Controller();
		graphicScreen = new GraphicScreen(15);
		
		// Set listeners
		controller.getRefreshButton().addActionListener(this);
		controller.getTextField().addActionListener(this);
		
		// Positioning into layout
		setLayout(new BorderLayout());
		add(graphicScreen, BorderLayout.CENTER);
		add(controller, BorderLayout.SOUTH);
		
	}
	
	public static void main(String args[]) {
		new Window();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == getController().getRefreshButton()) {
			getGraphicScreen().clear();
			getGraphicScreen().setSetPoints(new SetPoints(0));
			getGraphicScreen().repaint();
		}
		if(event.getSource() == getController().getTextField()) {
			getGraphicScreen().setSetPoints(new SetPoints(Integer.parseInt(getController().getTextField().getText())));
			getGraphicScreen().repaint();
		}
	}
	
	// Gettes & Setters
	public Controller getController() {
		return controller;
	}
	public GraphicScreen getGraphicScreen() {
		return graphicScreen;
	}
}
