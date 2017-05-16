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
 * Repository: https://github.com/AkaiBF/Spline-Interpolation-PAI
 * 
 * @author Ernesto Echeverría González
 * @email alu0100881622@ull.edu.es
 * @since 05-16-2017
 * @version 1.0.0
 */


public class SetPoints {
	protected final int MAXIMUM = 100;																		// Subdivision of the Y Axis
	protected final int RADIUS = 5;																				// Radius of the points
	protected final Color COLOR = Color.RED;															// Color of the points
	protected final Color SELECTEDCOLOR = Color.YELLOW;										// Color of the selected point
	protected final Color COLORLINE = Color.GREEN;												// Color of the Interpolation function
	protected final int SPEED = 5;																				// Speed at which the points can be moved
	
	private ArrayList<Point> points;																			// Implementation of a Set of Points
	private int selectedPoint;																						// Selected point to move
	
	/**
	 * Constructor
	 * @param size Number of points inserted in the set
	 */
	public SetPoints(int size) {
		setSelectedPoint(0);
		points = new ArrayList<Point>();
		for(int i = 0; i < size; i++) {
			points.add(new Point(i, (int) (Math.random() * MAXIMUM)));
		}
	}

	/**
	 * Select a as a new point the closest point at the right of the previous one.
	 */
	public void selectToRight() {
		if(getSelectedPoint() < size() - 1)
			setSelectedPoint(getSelectedPoint() + 1);
	}
	/**
	 * Select a as a new point the closest point at the left of the previous one.
	 */
	public void selectToLeft() {
		if(getSelectedPoint() > 0)
			setSelectedPoint(getSelectedPoint() - 1);
	}
	/**
	 * Move the selected point upwards
	 */
	public void selectedUp() {
		if(getPoints().get(getSelectedPoint()).getyAxis() < (MAXIMUM - SPEED))
		getPoints().get(getSelectedPoint()).setyAxis(getPoints().get(getSelectedPoint()).getyAxis() + SPEED);
	}
	/**
	 * Move the selected point downwards
	 */
	public void selectedDown() {
		if(getPoints().get(getSelectedPoint()).getyAxis() > SPEED)
		getPoints().get(getSelectedPoint()).setyAxis(getPoints().get(getSelectedPoint()).getyAxis() - SPEED);
	}
	/**
	 * Move the selected point to the left
	 */
	public void selectedLeft() {
		if(getSelectedPoint() != 0 && getPoints().get(getSelectedPoint()).getxAxis() > getPoints().get(getSelectedPoint() - 1).getxAxis() + SPEED)
		getPoints().get(getSelectedPoint()).setxAxis(getPoints().get(getSelectedPoint()).getxAxis() - SPEED);
	}
	/**
	 * Move the selected point to the right
	 */
	public void selectedRight() {
		if(getSelectedPoint() != getPoints().size() - 1 && getPoints().get(getSelectedPoint()).getxAxis() > getPoints().get(getSelectedPoint() + 1).getxAxis() - SPEED)
		getPoints().get(getSelectedPoint()).setxAxis(getPoints().get(getSelectedPoint()).getxAxis() + SPEED);
	}
	
	/**
	 * Removes all points from the set
	 */
	public void clear() {
		getPoints().clear();
	}
	/**
	 * @return The number of points in the set
	 */
	public int size() {
		return getPoints().size();
	}
	
	public void drawPoints(Graphics graphicObject, int width, int height, int borders) {
		if(!getPoints().isEmpty()) {
			int factorX = width / size();																							// Conversion factor for xAxis
			int factorY = height / MAXIMUM;																						// Conversion factor for yAxis
		
			ArrayList<Float> xAxis = new ArrayList<Float>();
			ArrayList<Float> yAxis = new ArrayList<Float>();
			for(int i = 0; i < getPoints().size(); i++) {
				xAxis.add((float) getPoints().get(i).getxAxis());
				yAxis.add((float) getPoints().get(i).getyAxis());
			}
			
			if(getPoints().size() > 1) {
				graphicObject.setColor(COLORLINE);
				SplineInterpolator interpolation = new SplineInterpolator(xAxis, yAxis);
				for(int i = 0; i < width; i++) {
					float interpolationYAxis = interpolation.function((float) i / ((float) width / (float) size()));
					float interpolationYAxist1 = interpolation.function((float) (i + 1) / ((float) width / (float) size()));
					graphicObject.drawLine(i + borders, (int) (interpolationYAxis * factorY + borders), i + 1 + borders, (int) (interpolationYAxist1 * factorY + borders));
				}
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
