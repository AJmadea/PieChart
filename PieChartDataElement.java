package piechart;

public class PieChartDataElement {

	private String title;
	private int discreteAmount;
	private int angle;
	
	public PieChartDataElement(String title, int discreteAmount) {
		this.title = title;
		this.discreteAmount = discreteAmount;
		angle = 0;
	}
	
	public void addAmount(int a) {
		discreteAmount+=a;
	}
	
	public String getTitle() {return title;}
	public int getDiscreteAmount() {return discreteAmount;}
	
	public void calculateAngle(int sum) {
		angle = 360*discreteAmount / sum;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public static PieChartDataElement createElement(PieChartDataConverter d) {
		return d.convertToElement();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title);
		sb.append("\t");
		sb.append(discreteAmount);
		sb.append("\t");
		sb.append(angle/360.0);
		return sb.toString();
	}
}