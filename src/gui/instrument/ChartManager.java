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
 * @author Tianxiao.Liu@u-cergy.fr
 */
public class ChartManager {
	private HashMap<String, Integer> qLearnTypeCount = new HashMap<String, Integer>();
	private ArrayList<Integer> heuristics = new ArrayList<Integer>();

	/**
	 * Initializes the manager, by creating null value for each node type.
	 */
	public ChartManager() {
		qLearnTypeCount.put("Hole", 0);
		qLearnTypeCount.put("Tile", 0);
	}

	/**
	 * Counts update for node types.
	 * 
	 * @param type the node type to count
	 */
	public void countType(String type) {
		int count = qLearnTypeCount.get(type);
		qLearnTypeCount.put(type, count + 1);
	}

	/**
	 * Adds step by step the evolution of the heuristic.
	 * 
	 * @param cost current heuristic
	 */
	public void registerHeuristicByStep(int cost) {
		heuristics.add(cost);
	}

	/**
	 * Generates the node type pie chart.
	 * 
	 * @return the pie chart
	 */
	public JFreeChart getTypeCountPie() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Hole", qLearnTypeCount.get("Hole"));
		dataset.setValue("Tile", qLearnTypeCount.get("Tile"));
		return ChartFactory.createPieChart("Cases rencontrés", dataset, true, true, false);
	}

	/**
	 * Generates the node type bar chart.
	 * 
	 * @return the bar chart.
	 */
	public JFreeChart getTypeCountBar() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(qLearnTypeCount.get("Coucou"), "series", "Constant");

		return ChartFactory.createBarChart("", "Node type", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
	}

	/**
	 * Generates the curve chart for evolution of heuristic during the visit.
	 * @return the curve chart
	 */
	public JFreeChart getHeuristicEvolutionChart() {
		XYSeries serie = new XYSeries("Heuristic Evolution");
		for (int index = 0; index < heuristics.size(); index++) {
			serie.add(index, heuristics.get(index));
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);

		return ChartFactory.createXYLineChart("", "Tile visited", "Heuristic", dataset, PlotOrientation.VERTICAL, true, true, false);
	}

}
