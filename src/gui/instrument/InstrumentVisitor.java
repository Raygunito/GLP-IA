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

	// @Override
	// public Void visit(Constant node) {
	// 	chartManager.countType('c');
	// 	chartManager.registerHeightByStep(currentHeight);
	// 	return null;
	// }

	// @Override
	// public Void visit(Variable node) {
	// 	chartManager.countType('v');
	// 	chartManager.registerHeightByStep(currentHeight);
	// 	return null;
	// }

	// @Override
	// public Void visit(Addition node) {
	// 	processOperationNode(node);
	// 	chartManager.countType('a');
	// 	return null;
	// }

	// @Override
	// public Void visit(Subtraction node) {
	// 	processOperationNode(node);
	// 	chartManager.countType('s');
	// 	return null;
	// }

	// @Override
	// public Void visit(Multiplication node) {
	// 	processOperationNode(node);
	// 	chartManager.countType('m');
	// 	return null;
	// }

	// private void processOperationNode(Tree node) {
	// 	chartManager.registerHeightByStep(currentHeight);
	// 	currentHeight++;
	// 	node.getLeftOperand().accept(this);
	// 	node.getRightOperand().accept(this);
	// 	currentHeight--;
	// }
}
