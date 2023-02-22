package main.java.org.example;

import javax.swing.*;
import java.awt.*;
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
        tableLabel = new JLabel[3][3][4];
        map = new JPanel(new GridLayout(3, 3));
        mapLabel = new JLabel[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mapLabel[i][j] = new JLabel("■");
                if (qLearningCore.getGrid().getGrid()[i][j].getReward() == 1) {
                    mapLabel[i][j].setForeground(Color.green);
                } else if (qLearningCore.getGrid().getGrid()[i][j].getReward() == -1) {
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int a=0;
            Cell cell = qLearningCore.getGrid().getGrid()[0][0];
            while (cell != qLearningCore.getGrid().getGrid()[2][2]) {
                try {
                    Thread.sleep(200);
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 if (qLearningCore.getGrid().getGrid()[i][j] == cell) {
                    mapLabel[i][j].setForeground(Color.blue);
                }
                else if (qLearningCore.getGrid().getGrid()[i][j].getReward() == 1) {
                    mapLabel[i][j].setForeground(Color.green);
                } else if (qLearningCore.getGrid().getGrid()[i][j].getReward() == -1) {
                    mapLabel[i][j].setForeground(Color.red);
                } else {
                    mapLabel[i][j].setForeground(Color.white);
                }
                for (int k = 0; k < 4; k++) {
                    double num=qLearningCore.getqTable().getQTable()[i][j].getQValue()[k];
                    double numnum=num%0.1;
                    double nuan=num-numnum;
                    tableLabel[i][j][k].setText(Double.toString(num-num%0.1));
                }
            }
        }
    }
}