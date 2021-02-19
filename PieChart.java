package piechart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class PieChart extends JComponent {

	private PieChartData data;
	private static List<Color> colors;
	private static Random rand;
	private static final int INCREMENT = 255/2;

	static {
		rand = new Random();
		colors = new ArrayList<>();
		for(int i = 0; i < 256; i+=INCREMENT) {
			colors.add(new Color(i,i,i));
		}
	}
	
	private static Color getRandomColor() {
		int i = rand.nextInt(colors.size());
		return colors.get(i);
	}
	
	public PieChart(PieChartData data) {
		this.data = data;
	}

	/**
	 * This will paint the PieChart piecewise by the PieChartData
	 */
	@Override public void paintComponent(Graphics g) {
		int currentAngle = 0;
		int[] angles = data.getAngles();
		Graphics2D g2d = (Graphics2D)g;
		int w = getWidth();
		int h = getHeight();
		int r = calculateRadius(w,h,(int)0.4*h);

		for(int x : angles) {
			g.setColor(getRandomColor());
			g2d.fillArc((w-r)/2, (h-r)/2, r, r, currentAngle, x);
			currentAngle += x;
		}	
		System.out.println("Current Angle: " + currentAngle);
	}
	
	private int calculateRadius(int width, int height, int offset) {
		int smallest = width < height ? width : height;
		offset = offset > smallest ? offset : 50;
		return (int) (smallest/2 - offset);
	}	
}