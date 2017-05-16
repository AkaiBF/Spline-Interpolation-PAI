import java.util.ArrayList;

/**
 * Calculator of a Monotone Cubic Interpolation. It firsts calculates the tangents and then it creates the function.
 * For more information, see https://en.wikipedia.org/wiki/Monotone_cubic_interpolation 
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

public class SplineInterpolator {
	private ArrayList<Float> xAxis;											// Array of X axis values of the points
	private ArrayList<Float> yAxis;											// Array of Y axis values of the points
	private float[] tangents;														// Array of tangents' increment between two points. The tangents are set as the average of two secants
	
	/**
	 * Three-attributes constructor
	 * @param xAxis Array of X axis of points
	 * @param yAxis Array of Y axis of points
	 * @param tangents Array of tangents' increment between two points. The tangents are set as the average of two secants
	 */
	public SplineInterpolator(ArrayList<Float> xAxis, ArrayList<Float> yAxis, float[] tangents) {
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.setTangents(tangents);
	}
	/**
	 * Two-attributes constructor
	 * @param xAxis Array of X axis of points
	 * @param yAxis Array of Y axis of points
	 */
	public SplineInterpolator(ArrayList<Float> xAxis, ArrayList<Float> yAxis) {
		SplineInterpolator interpolator = new SplineInterpolator(xAxis, yAxis, null).createMonotoneCubicSpline(xAxis, yAxis);
		this.xAxis = interpolator.getxAxis();
		this.yAxis = interpolator.getyAxis();
		this.tangents = interpolator.getTangents();
	}
	/**
	 * Calculator of tangents using the Monotone Cubic Interpolation method
	 * @param xAxis Array of X axis of points
	 * @param yAxis Array of Y axis of points
	 * @return New SplineInterpolator containing all the necessary attributes to create the function
	 */
	public SplineInterpolator createMonotoneCubicSpline(ArrayList<Float> xAxis, ArrayList<Float> yAxis) {
		int numPoints = getxAxis().size();
		float[] secants = new float[numPoints - 1];
		float[] tangents = new float[numPoints];
		
		for(int i = 0; i < numPoints - 1; i++) {
			float interval = getxAxis().get(i + 1) - getxAxis().get(i);
			secants[i] = (getyAxis().get(i + 1) - getyAxis().get(i)) / interval;
		}
		
		tangents[0] = secants[0];
		for(int i = 1; i < numPoints - 1; i++) {
			tangents[i] = (secants[i - 1] + secants[i]) / 2;
		}
		tangents[numPoints - 1] = secants[numPoints - 2];
		for(int i = 0; i < numPoints - 1; i++) {
			if(secants[i] == 0f) {
				tangents[i] = 0f;
				tangents[i + 1] = 0f;
			} else {
				float alpha = tangents[i] / secants[i];
				float beta = tangents[i + 1] / secants[i];
				float hypothenusa = (float) Math.hypot(alpha, beta);
				
				if(hypothenusa > 9f) {
					float theta = 3f / hypothenusa;
					tangents[i] = theta * alpha * secants[i];
					tangents[i + 1] = theta * beta * secants[i];
				}
			}
		}
		return new SplineInterpolator(xAxis, yAxis, tangents);
	}
	/**
	 * Function of the Interpolation using a Hermite interpolation
	 * @param xValue X axis value
	 * @return Y axis value
	 */
	public float function(float xValue) {
		int numPoint = getxAxis().size();
		if(Float.isNaN(xValue)) {
			return xValue;
		}
		if(xValue <= getxAxis().get(0)) {
			return getyAxis().get(0);
		}
		if(xValue >= getxAxis().get(numPoint - 1)) {
			return getyAxis().get(numPoint - 1); 
		}
		int counter = 0;
		while(xValue >= getxAxis().get(counter + 1)) {
			counter++;
			if(xValue == getxAxis().get(counter)) {
				return getyAxis().get(counter);
			}
		}
		float interval = getxAxis().get(counter + 1) - getxAxis().get(counter);
		float theta = (xValue - getxAxis().get(counter)) / interval;
		return (getyAxis().get(counter) * (1 + 2 * theta) + interval * getTangents()[counter] * theta) * (1 - theta) * (1 - theta) +
				(getyAxis().get(counter + 1) * (3 - 2 * theta) + interval * getTangents()[counter + 1] * (theta - 1)) * theta * theta;
	}


	// Getters & Settes
	public ArrayList<Float> getxAxis() {
		return xAxis;
	}

	public void setxAxis(ArrayList<Float> xAxis) {
		this.xAxis = xAxis;
	}

	public ArrayList<Float> getyAxis() {
		return yAxis;
	}

	public void setyAxis(ArrayList<Float> yAxis) {
		this.yAxis = yAxis;
	}

	public float[] getTangents() {
		return tangents;
	}

	public void setTangents(float[] tangents) {
		this.tangents = tangents;
	}
}
