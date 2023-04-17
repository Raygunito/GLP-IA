package gui.instrument;

import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This class manages all instrument charts of the graphical tree, including : 1) pie chart for node type count 2) bar chart for node type count 3) curve
 * chart for tree height evolution during the visit.
 * 
 * This class works with {@link InstrumentVisitor} which feeds the necessary information asked by the charts.
 * 
 * @see InstrumentVisitor
 * @author Tianxiao.Liu@u-cergy.fr
 */
public class ChartManager {
	private HashMap<Character, Integer> nodeTypeCount = new HashMap<Character, Integer>();
	private ArrayList<Integer> heights = new ArrayList<Integer>();

	/**
	 * Initializes the manager, by creating null value for each node type.
	 */
	public ChartManager() {
		nodeTypeCount.put('c', 0);
		nodeTypeCount.put('v', 0);
		nodeTypeCount.put('a', 0);
		nodeTypeCount.put('s', 0);
		nodeTypeCount.put('m', 0);
	}

	/**
	 * Counts update for node types.
	 * 
	 * @param type the node type to count
	 */
	public void countType(char type) {
		int count = nodeTypeCount.get(type);
		nodeTypeCount.put(type, count + 1);
	}

	/**
	 * Adds step by step the evolution of the tree height.
	 * 
	 * @param height current tree height
	 */
	public void registerHeightByStep(int height) {
		heights.add(height);
	}

	/**
	 * Generates the node type pie chart.
	 * 
	 * @return the pie chart
	 */
	public JFreeChart getTypeCountPie() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Constant", nodeTypeCount.get('c'));
		dataset.setValue("Variable", nodeTypeCount.get('v'));
		dataset.setValue("Addition", nodeTypeCount.get('a'));
		dataset.setValue("Subtraction", nodeTypeCount.get('s'));
		dataset.setValue("Multiplication", nodeTypeCount.get('m'));

		return ChartFactory.createPieChart("", dataset, true, true, false);
	}

	/**
	 * Generates the node type bar chart.
	 * 
	 * @return the bar chart.
	 */
	public JFreeChart getTypeCountBar() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(nodeTypeCount.get('c'), "series", "Constant");
		dataset.setValue(nodeTypeCount.get('v'), "series", "Variable");
		dataset.setValue(nodeTypeCount.get('a'), "series", "Addition");
		dataset.setValue(nodeTypeCount.get('s'), "series", "Subtraction");
		dataset.setValue(nodeTypeCount.get('m'), "series", "Multiplication");

		return ChartFactory.createBarChart("", "Node type", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
	}

	/**
	 * Generates the curve chart for evolution of tree height during the visit.
	 * 
	 * @return the curve chart
	 */
	public JFreeChart getHeightEvolutionChart() {
		XYSeries serie = new XYSeries("Heuristic Evolution");
		for (int index = 0; index < heights.size(); index++) {
			serie.add(index, heights.get(index));
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);

		return ChartFactory.createXYLineChart("", "Tile visited", "Heuristic", dataset, PlotOrientation.VERTICAL, true, true, false);
	}


}
