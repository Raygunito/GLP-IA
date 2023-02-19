package gui;

import java.awt.Container;
import java.awt.event.*;
import java.awt.CardLayout;

import javax.swing.Action;
import javax.swing.JFrame;

public class GUI extends JFrame implements Runnable {
    private Main_menu menu;
    private AStarGUI astar;
    private MinMaxGUI minmax;
    private QLearnGUI qlearn;
    private Container c;

    public GUI() {
        super("The Cognitive Crew");
        c = getContentPane();
        c.setLayout(new CardLayout());
        firstLaunch();
        // ActionListener pour le Main Menu
        menu.addActionListenerAStar(new ActionAStar());
        menu.addActionListenerMinMax(new ActionMinMax());
        menu.addActionListenerQLearn(new ActionQLearn());
        menu.addActionListenerQuit(new ActionQuit());

        // ActionListener pour le AStar
        astar.getCp().addActionListenerBack(new ActionBack());
        
        // ActionListener pour le MinMax
        minmax.getCp().addActionListenerBack(new ActionBack());
        
        // ActionListener pour le QLearn
        qlearn.getCp().addActionListenerBack(new ActionBack());

        
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        // c.revalidate();
        c.repaint();
    }

    private void firstLaunch() {
        menu = new Main_menu();
        astar = new AStarGUI();
        minmax = new MinMaxGUI();
        qlearn = new QLearnGUI();
        c.add(menu, "menu");
        c.add(minmax, "minmax");
        c.add(astar, "astar");
        c.add(qlearn, "qlearn");
        minmax.setVisible(false);
        menu.setVisible(true);
        astar.setVisible(false);
        qlearn.setVisible(false);
    }

    class ActionBack implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            minmax.setVisible(false);
            menu.setVisible(true);
            astar.setVisible(false);
            qlearn.setVisible(false);
            c.revalidate();
            c.repaint();
        }

    }

    class ActionAStar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Thread astarThread = new Thread(astar);
            astarThread.start();
            menu.setVisible(false);
            astar.setVisible(true);
            ((CardLayout) c.getLayout()).show(c, "astar");
            c.revalidate();
            c.repaint();
        }

    }

    class ActionQLearn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Thread qlearnThread = new Thread(qlearn);
            qlearnThread.start();
            menu.setVisible(false);
            qlearn.setVisible(true);
            c.revalidate();
            c.repaint();
            ((CardLayout) c.getLayout()).show(c, "qlearn");
        }
    }

    class ActionMinMax implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Thread minmaxThread = new Thread(minmax);
            minmaxThread.start();
            menu.setVisible(false);
            minmax.setVisible(true);
            c.revalidate();
            c.repaint();
            ((CardLayout) c.getLayout()).show(c, "minmax");
        }

    }

    class ActionQuit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GUI.this.dispose();
        }
    }
}
