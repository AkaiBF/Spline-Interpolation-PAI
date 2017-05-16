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
 * Repository: https://github.com/AkaiBF/ShooterGamePAI
 * 
 * @author Ernesto Echeverría González
 * @email alu0100881622@ull.edu.es
 * @since 05-03-2017
 * @version 1.0.0
 */


public class GraphicScreen extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	protected final int BORDERS = 20;
	
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
		if(event.getKeyCode() == 40)
			getSetPoints().selectedUp();
		if(event.getKeyCode() == 37)
			getSetPoints().selectToLeft();
		if(event.getKeyCode() == 39)
			getSetPoints().selectToRight();
		if(event.getKeyCode() == 38)
			getSetPoints().selectedDown();
		repaint();
			
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
