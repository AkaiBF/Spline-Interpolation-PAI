import java.util.ArrayList;

public class SplineInterpolator {
	private ArrayList<Float> xAxis;
	private ArrayList<Float> yAxis;
	private float[] tangents;
	
	public SplineInterpolator(ArrayList<Float> xAxis, ArrayList<Float> yAxis, float[] tangents) {
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.setTangents(tangents);
	}
	
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
	
	public String toString() {
		String output = new String("[");
		int numPoints = getxAxis().size();
		for(int i = 0; i < numPoints; i++) {
			if(i != 0) {
				output += ", ";
			}
			output += "(" + getxAxis().get(i);
			output += ", " + getyAxis().get(i);
			output += ": " + getTangents()[i];
		}
		output += "]";
		return output;
	}

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
