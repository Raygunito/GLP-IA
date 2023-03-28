package main.java.org.example;

import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GUI extends JFrame {
    private QLearningCore qLearningCore;
    private JPanel table;
    private JPanel map;
    private JLabel[][][] tableLabel;
    private JLabel[][] mapLabel;

    public GUI() {
        super();
        init();
    }

    private void init() {
        qLearningCore = new QLearningCore();
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0, 2));
        table = new JPanel(new GridLayout(9, 4));
        tableLabel = new JLabel[QLearningConstant.DIMENSION][QLearningConstant.DIMENSION][4];
        map = new JPanel(new GridLayout(QLearningConstant.DIMENSION, QLearningConstant.DIMENSION));
        mapLabel = new JLabel[QLearningConstant.DIMENSION][QLearningConstant.DIMENSION];
        for (int i = 0; i < QLearningConstant.DIMENSION; i++) {
            for (int j = 0; j < QLearningConstant.DIMENSION; j++) {
                mapLabel[i][j] = new JLabel("■");
                if (qLearningCore.getGrid().getGrid()[i][j].reward() == 1) {
                    mapLabel[i][j].setForeground(Color.green);
                } else if (qLearningCore.getGrid().getGrid()[i][j].reward() == -1) {
                    mapLabel[i][j].setForeground(Color.red);
                } else if (i == 0 && j == 0) {
                    mapLabel[i][j].setForeground(Color.blue);
                } else {
                    mapLabel[i][j].setForeground(Color.white);
                }
                map.add(mapLabel[i][j]);
                for (int k = 0; k < 4; k++) {
                    tableLabel[i][j][k] = new JLabel(Double.toString(qLearningCore.getqTable().getQTable()[i][j].getQValue()[k]));
                    table.add(tableLabel[i][j][k]);
                }
            }
        }
        contentPane.add(table);
        contentPane.add(map);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void run() {
        ArrayList<Integer>nombreEssai=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int a=0;
            Cell cell = qLearningCore.getGrid().getGrid()[0][0];
            update(cell);
            while (cell != qLearningCore.getGrid().getEndingCell()){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cell = qLearningCore.run(cell);
                    update(cell);
                    a++;
                }catch (IndexOutOfBoundsException ignored){};
            }
            nombreEssai.add(a);
        }
        int i=0;
    }
    private void update(Cell cell) {
        for (int i = 0; i < QLearningConstant.DIMENSION; i++) {
            for (int j = 0; j < QLearningConstant.DIMENSION; j++) {
                 if (qLearningCore.getGrid().getGrid()[i][j] == cell) {
                    mapLabel[i][j].setForeground(Color.blue);
                }
                else if (qLearningCore.getGrid().getGrid()[i][j].reward() == 1) {
                    mapLabel[i][j].setForeground(Color.green);
                } else if (qLearningCore.getGrid().getGrid()[i][j].reward() == -1) {
                    mapLabel[i][j].setForeground(Color.red);
                } else {
                    mapLabel[i][j].setForeground(Color.white);
                }
                for (int k = 0; k < 4; k++) {
                    double num=qLearningCore.getqTable().getQTable()[i][j].getQValue()[k];
                    DecimalFormat df = new DecimalFormat("0.0");
                    tableLabel[i][j][k].setText(df.format(num));
                }
            }
        }
    }
}