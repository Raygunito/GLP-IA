package test;

import javax.swing.*;

import data.elements.Hole;
import data.qlearn.QCell;
import process.qlearn.QLearnCore;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TestQLearnGUI extends JFrame {
    private static final int CELL_SIZE = 35;
    private QLearnCore qLearn;
    private JPanel mainPanel;
    private JLabel gridSizeLabel, nbIterationLabel, learningRateLabel, explorationRateLabel, discountFactorLabel;
    private JTextField gridSizeField, nbIterationField, learningRateField, explorationRateField, discountFactorField;
    private JButton trainButton, showQTableButton;
    private JTextArea qTableArea;
    private smollPanel sPanel;
    private JScrollPane js;

    public TestQLearnGUI() {
        super("Q-Learning GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 600);
        this.setLocationRelativeTo(null);
        // Initialize components
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));
        gridSizeLabel = new JLabel("Grid Size:");
        nbIterationLabel = new JLabel("Number of Iterations:");
        learningRateLabel = new JLabel("Learning Rate:");
        explorationRateLabel = new JLabel("Exploration Rate:");
        discountFactorLabel = new JLabel("Discount Factor:");
        gridSizeField = new JTextField("15");
        nbIterationField = new JTextField("500");
        learningRateField = new JTextField("0.2");
        explorationRateField = new JTextField("0.5");
        discountFactorField = new JTextField("0.8");
        trainButton = new JButton("Train Q-Learning");
        showQTableButton = new JButton("Show Q-Table");
        qTableArea = new JTextArea();
        sPanel = new smollPanel();
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

        mainPanel.setPreferredSize(new Dimension(300, 500));
        js = new JScrollPane(qTableArea);
        js.setPreferredSize(new Dimension(500, 500));
        trainButton.addActionListener(new ActionStart());
        showQTableButton.addActionListener(new ActionShow());
        // Add main panel to frame
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(sPanel);
        this.add(mainPanel);
        this.add(js);
        this.setVisible(true);
    }

    class ActionStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int gridSize = Integer.parseInt(gridSizeField.getText());
            int nbIteration = Integer.parseInt(nbIterationField.getText());
            float learningRate = Float.parseFloat(learningRateField.getText());
            float explorationRate = Float.parseFloat(explorationRateField.getText());
            float discountFactor = Float.parseFloat(discountFactorField.getText());
            qLearn = new QLearnCore(gridSize, nbIteration, learningRate, explorationRate, discountFactor);

            for (int i = 0; i < nbIteration; i++) {
                qLearn.doOneIteration();
                sPanel.paintComponent(getGraphics());
            }
            sPanel.paintBestPath(getGraphics());
            JOptionPane.showMessageDialog(TestQLearnGUI.this, "Training Complete.");
        }

    }

    class ActionShow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (qLearn == null) {
                JOptionPane.showMessageDialog(TestQLearnGUI.this, "Please train the Q-Learning algorithm first.");
            } else {
                qTableArea.setText(qLearn.getqTable().printTable());
            }
        }

    }

    public static void main(String[] args) {
        new TestQLearnGUI();
    }

    class smollPanel extends JPanel {
        public smollPanel() {
            super();
            setPreferredSize(new Dimension(600, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (qLearn != null) {

                for (int i = 0; i < qLearn.getGrid().getSize(); i++) {
                    for (int j = 0; j < qLearn.getGrid().getSize(); j++) {
                        QCell cell = qLearn.getGrid().getCell(i, j);

                        if (cell.getElement() instanceof Hole) {
                            g.setColor(Color.RED);
                        } else if (cell.equals(qLearn.getGrid().getStartingCell())) {
                            g.setColor(Color.GREEN);
                        } else if (cell.equals(qLearn.getGrid().getEndingCell())) {
                            g.setColor(Color.BLUE);
                        } else {
                            g.setColor(Color.WHITE);
                        }

                        g.fillRect(30 + i * CELL_SIZE, 50 + j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }
                }
                ArrayList<QCell> path = qLearn.path;
                for (QCell cell : path) {
                    g.setColor(Color.cyan);
                    g.drawRect(30 + cell.getCoordinate().coordinateX() * CELL_SIZE,
                            50 + cell.getCoordinate().coordinateY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        public void paintBestPath(Graphics g) {
            super.paintComponent(g);
            if (qLearn != null) {

                for (int i = 0; i < qLearn.getGrid().getSize(); i++) {
                    for (int j = 0; j < qLearn.getGrid().getSize(); j++) {
                        QCell cell = qLearn.getGrid().getCell(i, j);

                        if (cell.getElement() instanceof Hole) {
                            g.setColor(Color.RED);
                        } else if (cell.equals(qLearn.getGrid().getStartingCell())) {
                            g.setColor(Color.GREEN);
                        } else if (cell.equals(qLearn.getGrid().getEndingCell())) {
                            g.setColor(Color.BLUE);
                        } else {
                            g.setColor(Color.WHITE);
                        }

                        g.fillRect(30 + i * CELL_SIZE, 50 + j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }
                }
                ArrayList<QCell> path = qLearn.bestPath();
                for (QCell cell : path) {
                    g.setColor(Color.cyan);
                    g.drawRect(30 + cell.getCoordinate().coordinateX() * CELL_SIZE,
                            50 + cell.getCoordinate().coordinateY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

}
