package gui.instrument;


/**
 * This class works with {@link ChartManager}. It registers necessary information for creating different charts.
 * 
 * @see ChartManager
 * @author Tianxiao.Liu@u-cergy.fr
 */
public class InstrumentVisitor {
	private ChartManager chartManager;
	private int currentHeight = 0;

	public InstrumentVisitor(ChartManager chartManager) {
		this.chartManager = chartManager;
	}

	// private void processOperationNode(Tree node) {
	// 	chartManager.registerHeightByStep(currentHeight);
	// 	currentHeight++;
	// 	node.getLeftOperand().accept(this);
	// 	node.getRightOperand().accept(this);
	// 	currentHeight--;
	// }
}
