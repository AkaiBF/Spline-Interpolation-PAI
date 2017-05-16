import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/**
 * Display graphic of a splines-based function creator given n points
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


public class GraphicScreen extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	protected final int BORDERS = 20;																		// Borders with the Axis
	protected final int LEFTARROW = 37;																	// Code of the Left Arrow Key
	protected final int RIGHTARROW = 39;																// Code of the Right Arrow Key
	protected final int UPARROW = 40;																		// Code of the Up Arrow Key
	protected final int DOWNARROW = 38;																	// Code of the Down Arrow Key
	protected final int AKEY = 65;																			// Code of the A Key
	protected final int DKEY = 68;																			// Code of the D Key
	
	private Point axisZero;
	private SetPoints setPoints;
	private int number;
	
	public GraphicScreen(int number) {
		super();
		setFocusable(true);
		addKeyListener(this);
		
		this.number = number;
		setSetPoints(new SetPoints(number));
	}
	
	public void clear() {
		getSetPoints().clear();
		repaint();
	}
	
	public void paintComponent(Graphics graphicObject) {
		setAxisZero(new Point(BORDERS, (int) getSize().getHeight() - BORDERS));
		graphicObject.setColor(Color.WHITE);
		graphicObject.fillRect(0, 0, (int) getSize().getWidth(), (int) getSize().getHeight());
		
		graphicObject.setColor(Color.BLACK);
		graphicObject.drawLine(BORDERS, BORDERS, getAxisZero().getxAxis(), getAxisZero().getyAxis());
		graphicObject.drawLine((int) getSize().getWidth() - BORDERS, getAxisZero().getyAxis(), getAxisZero().getxAxis(), getAxisZero().getyAxis());
		
		getSetPoints().drawPoints(graphicObject, (int) getSize().getWidth() - BORDERS, (int) getSize().getHeight() - BORDERS, BORDERS);
	}

	public Point getAxisZero() {
		return axisZero;
	}

	public void setAxisZero(Point axisZero) {
		this.axisZero = axisZero;
	}

	public SetPoints getSetPoints() {
		return setPoints;
	}

	public void setSetPoints(SetPoints setPoints) {
		this.setPoints = setPoints;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == UPARROW)
			getSetPoints().selectedUp();
		if(event.getKeyCode() == LEFTARROW)
			getSetPoints().selectedLeft();
		if(event.getKeyCode() == RIGHTARROW)
			getSetPoints().selectedRight();
		if(event.getKeyCode() == DOWNARROW)
			getSetPoints().selectedDown();
		if(event.getKeyCode() == AKEY)
			getSetPoints().selectToLeft();
		if(event.getKeyCode() == DKEY)
			getSetPoints().selectToRight();
		repaint();
			
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
