package test;

import javax.swing.*;

import data.elements.Hole;
import data.qlearn.Cell;
import process.qlearn.QLearnCore;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TestQLearnGUI extends JFrame implements ActionListener {
    private static final int CELL_SIZE = 35;
    private QLearnCore qLearn;
    private JPanel mainPanel;
    private JLabel gridSizeLabel, nbIterationLabel, learningRateLabel, explorationRateLabel, discountFactorLabel;
    private JTextField gridSizeField, nbIterationField, learningRateField, explorationRateField, discountFactorField;
    private JButton trainButton, showQTableButton;
    private JTextArea qTableArea;

    public TestQLearnGUI() {
        super("Q-Learning GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        // Initialize components
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));
        gridSizeLabel = new JLabel("Grid Size:");
        nbIterationLabel = new JLabel("Number of Iterations:");
        learningRateLabel = new JLabel("Learning Rate:");
        explorationRateLabel = new JLabel("Exploration Rate:");
        discountFactorLabel = new JLabel("Discount Factor:");
        gridSizeField = new JTextField("10");
        nbIterationField = new JTextField("500");
        learningRateField = new JTextField("0.2");
        explorationRateField = new JTextField("0.5");
        discountFactorField = new JTextField("0.8");
        trainButton = new JButton("Train Q-Learning");
        trainButton.addActionListener(this);
        showQTableButton = new JButton("Show Q-Table");
        showQTableButton.addActionListener(this);
        qTableArea = new JTextArea();

        // Add components to main panel
        mainPanel.add(gridSizeLabel);
        mainPanel.add(gridSizeField);
        mainPanel.add(nbIterationLabel);
        mainPanel.add(nbIterationField);
        mainPanel.add(learningRateLabel);
        mainPanel.add(learningRateField);
        mainPanel.add(explorationRateLabel);
        mainPanel.add(explorationRateField);
        mainPanel.add(discountFactorLabel);
        mainPanel.add(discountFactorField);
        mainPanel.add(trainButton);
        mainPanel.add(showQTableButton);

        // Add main panel to frame
        this.add(mainPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(qTableArea), BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == trainButton) {
            int gridSize = Integer.parseInt(gridSizeField.getText());
            int nbIteration = Integer.parseInt(nbIterationField.getText());
            float learningRate = Float.parseFloat(learningRateField.getText());
            float explorationRate = Float.parseFloat(explorationRateField.getText());
            float discountFactor = Float.parseFloat(discountFactorField.getText());
            qLearn = new QLearnCore(gridSize, nbIteration, learningRate, explorationRate, discountFactor);

            for (int i = 0; i < nbIteration; i++) {
                qLearn.doOneIteration();
            }
            JOptionPane.showMessageDialog(this, "Training Complete.");
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    for (int i = 0; i < qLearn.getGrid().getSize(); i++) {
                        for (int j = 0; j < qLearn.getGrid().getSize(); j++) {
                            Cell cell = qLearn.getGrid().getCell(i, j);

                            if (cell.getElement() instanceof Hole) {
                                g.setColor(Color.RED);
                            } else if (cell.equals(qLearn.getGrid().getStartingCell())) {
                                g.setColor(Color.GREEN);
                            } else if (cell.equals(qLearn.getGrid().getEndingCell())) {
                                g.setColor(Color.BLUE);
                            } else {
                                g.setColor(Color.WHITE);
                            }

                            g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        }
                    }
                    ArrayList<Cell> path = qLearn.bestPath();
                    for (Cell cell : path) {
                        g.setColor(Color.cyan);
                        g.drawRect(cell.getCoordinate().coordinateX() * CELL_SIZE,
                                cell.getCoordinate().coordinateY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }
                }
            };
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            JFrame jf = new JFrame("Map");
            jf.add(panel);
            jf.pack();
            jf.setSize(new Dimension(500, 500));
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        } else if (e.getSource() == showQTableButton) {
            if (qLearn == null) {
                JOptionPane.showMessageDialog(this, "Please train the Q-Learning algorithm first.");
            } else {
                qTableArea.setText(qLearn.getqTable().printTable());
            }
        }
    }

    public static void main(String[] args) {
        new TestQLearnGUI();
    }
}
