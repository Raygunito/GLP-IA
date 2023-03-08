package gui;

import java.awt.Container;
import java.awt.event.*;
import java.awt.CardLayout;

import javax.swing.JFrame;

public class GUI extends JFrame implements Runnable {
    static final double TARGET_FPS = 30.0;
    static final double FRAME_TIME = 1000000000.0 / TARGET_FPS;

    private Main_menu menu;
    private AStarGUI astar;
    private MinMaxGUI minmax;
    private QLearnGUI qlearn;
    private Container c;
    private Thread astarThread,qlearnThread,minmaxThread;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / FRAME_TIME;
            lastTime = now;
            if (delta >= 1) {
                c.revalidate();
                c.repaint();
                delta--;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        qlearnThread = new Thread(qlearn);
        astarThread = new Thread(astar);
        minmaxThread = new Thread(minmax);
        astarThread.start();
        qlearnThread.start();
        minmaxThread.start();
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
            System.exit(0);
        }
    }
}
