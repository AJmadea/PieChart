package piechart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Andrew Madea
 */
public class PieChartData {

	private ArrayList<PieChartDataElement> data;
	private int sum;
	
	public PieChartData() {
		data = new ArrayList<>();
		sum = 0;
	}
	
	/**
	 * @param e PieChartDataElement to be added
	 * @see It updates the angles to make sure the new angle is considered
	 */
	public void add(PieChartDataElement e) {
		if(data.add(e)) {
			sum += e.getDiscreteAmount();
			updateAngles();
		}
	}
	
	public void remove(PieChartDataElement e) {
		if(data.remove(e)) {
			sum-=e.getDiscreteAmount();
			updateAngles();
		}
	}
	
	private void updateAngles() {
		for(PieChartDataElement e : data) {
			e.calculateAngle(sum);
		}
	}
	
	public int[] getAngles() {
		int[] arr = new int[data.size()];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = data.get(i).getAngle();
		}
		return arr;
	}
	
	public int getSize() {return data.size();}
	
	/** 
	 * Creates PieChartData from an array of objects that implement the 
	 * PieChartDataConverter interface
	 * @param d the array of objects with PieChartDataConverter interface 
	 * @return PieChartData object that contains the data
	 */
	public static PieChartData createData(PieChartDataConverter[] d) {
		PieChartData pcd = new PieChartData();
		for(PieChartDataConverter pcdc : d) {
			pcd.add(pcdc.convertToElement());
		}
		return pcd;
	}
	
	/**
	 * 
	 * @param d Wildcard upperbound list for the PieChartDataConverter interface
	 * @return 
	 */
	public static PieChartData createData(List<? extends PieChartDataConverter> d) {
		PieChartDataConverter[] c = new PieChartDataConverter[d.size()];
		for(int i = 0; i < d.size(); i++) {
			c[i] = d.get(i);
		}
		return createData(c);
	}
	
	/**
	 * Factory that creates PieChartData from a Map.  The keys are some type of object and the values
	 * are an Integer.  The the objects toString() method is called for the String param in PieChartDataElement
	 * @param map Frequency Map
	 * @return PieChartData that represents the given map
	 */
	public static PieChartData createData(Map<? extends Object, Integer> map) {
		PieChartData pcd = new PieChartData();
		for(Object o : map.keySet()) 
			pcd.add(new PieChartDataElement(o.toString(),map.get(o)));
		return pcd;
	}
	
	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(PieChartDataElement e : data) {
			sb.append(e.toString());
			sb.append("\n");
		}

		return sb.toString();
	}
}