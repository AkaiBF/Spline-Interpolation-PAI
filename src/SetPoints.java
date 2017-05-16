import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Set of Points with different xAxis value to be used as a function
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


public class SetPoints {
	protected final int MAXIMUM = 100;																		// Subdivision of the Y Axis
	protected final int RADIUS = 5;																				// Radius of the points
	protected final Color COLOR = Color.RED;															// Color of the points
	protected final Color SELECTEDCOLOR = Color.YELLOW;										// Color of the selected point
	protected final Color COLORLINE = Color.GREEN;												// Color of the Interpolation function
	protected final int SPEED = 5;
	
	private ArrayList<Point> points;
	private int selectedPoint;
	
	public SetPoints(int size) {
		setSelectedPoint(0);
		points = new ArrayList<Point>();
		for(int i = 0; i < size; i++) {
			points.add(new Point(i, (int) (Math.random() * MAXIMUM)));
		}
	}

	public void selectToRight() {
		if(getSelectedPoint() < size() - 1)
			setSelectedPoint(getSelectedPoint() + 1);
	}
	
	public void selectToLeft() {
		if(getSelectedPoint() > 0)
			setSelectedPoint(getSelectedPoint() - 1);
	}
	
	public void selectedUp() {
		if(getPoints().get(getSelectedPoint()).getyAxis() < (MAXIMUM - SPEED))
		getPoints().get(getSelectedPoint()).setyAxis(getPoints().get(getSelectedPoint()).getyAxis() + SPEED);
	}
	
	public void selectedDown() {
		if(getPoints().get(getSelectedPoint()).getyAxis() > SPEED)
		getPoints().get(getSelectedPoint()).setyAxis(getPoints().get(getSelectedPoint()).getyAxis() - SPEED);
	}
	
	public int size() {
		return getPoints().size();
	}
	
	public void drawPoints(Graphics graphicObject, int width, int height, int borders) {
		int factorX = width / size();
		int factorY = height / MAXIMUM;
		
		ArrayList<Float> xAxis = new ArrayList<Float>();
		ArrayList<Float> yAxis = new ArrayList<Float>();
		for(int i = 0; i < getPoints().size(); i++) {
			xAxis.add((float) getPoints().get(i).getxAxis());
			yAxis.add((float) getPoints().get(i).getyAxis());
		}
		
		graphicObject.setColor(COLORLINE);
		SplineInterpolator interpolation = new SplineInterpolator(xAxis, yAxis, null).createMonotoneCubicSpline(xAxis, yAxis);
		for(int i = 0; i < width; i++) {
			float interpolationYAxis = interpolation.function((float) i / ((float) width / (float) size()));
			float interpolationYAxist1 = interpolation.function((float) (i + 1) / ((float) width / (float) size()));
			graphicObject.drawLine(i + borders, (int) (interpolationYAxis * factorY + borders), i + 1 + borders, (int) (interpolationYAxist1 * factorY + borders));
		}
		
		graphicObject.setColor(COLOR);
		int counter = 0;
		Iterator<Point> iterator = getPoints().iterator();
		while(iterator.hasNext()) {
			if(counter == getSelectedPoint()) 
				graphicObject.setColor(SELECTEDCOLOR);
			Point point = iterator.next();
			graphicObject.drawOval(point.getxAxis() * factorX - RADIUS + borders, point.getyAxis() * factorY - RADIUS + borders, RADIUS * 2, RADIUS * 2);
			if(counter == getSelectedPoint()) 
				graphicObject.setColor(COLOR);
			counter++;
		}
	}
	
	// Getters & Setters
	public String toString() {
		String output = new String("[");
		Iterator<Point> iterator = getPoints().iterator();
		while(iterator.hasNext()) {
			output += iterator.next() + " ";
		}
		output += "]";
		ArrayList<Float> xAxis = new ArrayList<Float>();
		ArrayList<Float> yAxis = new ArrayList<Float>();
		for(int i = 0; i < getPoints().size(); i++) {
			xAxis.add((float) getPoints().get(i).getxAxis());
			yAxis.add((float) getPoints().get(i).getyAxis());
		}
		return output;
	}
	public ArrayList<Point> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	public int getSelectedPoint() {
		return selectedPoint;
	}
	public void setSelectedPoint(int selectedPoint) {
		this.selectedPoint = selectedPoint;
	}
	
}
