
/**
 * Abstraction of a 2D Point
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


public class Point {
	private int xAxis;
	private int yAxis;
	
	public Point() {
		setxAxis(0);
		setyAxis(0);
	}
	
	public Point(int xAxis, int yAxis) {
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
	}
	
	public String toString() {
		return new String("{" + getxAxis() + ", " + getyAxis() + "}");
	}
	
	// Getters & Setters
	public int getxAxis() {
		return xAxis;
	}
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
}
