package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import gui.algorithmPanel.QLearnPanel;
import gui.utilsPanel.ControlPanel;
import gui.utilsPanel.ForcedPause;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;

public class QLearnGUI extends JPanel implements ForcedPause {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 400;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private QLearnPanel qlearnPanel;
    private Thread qlearnThread;
    private static Logger logger = LoggerUtility.getLogger(QLearnGUI.class, "text");

    public QLearnGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X, GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.QLEARN);
        cp.setOpt1Value(500);
        cp.setOpt2Value(20);
        ip = new InformationPanel(GUIConstant.QLEARN);
        qlearnPanel = new QLearnPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                Float.valueOf(cp.getOpt2Field().getText()) / 100, this.ip);
        qlearnThread = new Thread(qlearnPanel);
        initUpperPanel();

        cp.addActionListenerStart(new ActionStart());
        cp.addActionListenerRestart(new ActionRestart());
        cp.addActionListenerStop(new ActionStop());

        cp.getStop().setEnabled(false);

        add(upperPanel);
        add(ip);
        logger.info("QLearnGUI ready to run.");
    }

    private void initUpperPanel() {
        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upperPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        upperPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        upperPanel.add(cp);
        upperPanel.add(qlearnPanel);

    }

    public ControlPanel getCp() {
        return cp;
    }

    public InformationPanel getIp() {
        return ip;
    }

    public void resetButton() {
        cp.getStart().setEnabled(true);
        cp.getRestart().setEnabled(true);
        cp.getStop().setEnabled(false);
        cp.getStop().setText("Stop");
    }

    public void resetTextfield() {
        cp.getOpt1Field().setEditable(true);
        cp.getOpt2Field().setEditable(true);
    }

    class ActionStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!qlearnPanel.isPaused()) {
                qlearnThread.start();
                cp.getStart().setEnabled(false);
                cp.getStop().setEnabled(true);
                cp.getOpt1Field().setEditable(false);
                cp.getOpt2Field().setEditable(false);
            }
        }

    }

    class ActionRestart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            qlearnPanel.togglePaused();
            qlearnThread.interrupt();
            upperPanel.remove(1);
            qlearnPanel = new QLearnPanel(verifyOpt1(),verifyOpt2() / 100, QLearnGUI.this.ip);
            qlearnThread = new Thread(qlearnPanel);
            upperPanel.add(qlearnPanel);
            upperPanel.revalidate();
            upperPanel.repaint();
            if (ip.getComponentCount() == 3) {
                ip.remove(2);
            }
            ip.resetValue();
            resetButton();
            resetTextfield();
        }

        public int verifyOpt1() {
            String inputCoins = cp.getOpt1Field().getText();
            int iteration = qlearnPanel.getIteration();
            if (inputCoins.matches("\\d+")) {
                iteration = Integer.valueOf(inputCoins);
                if (iteration > 1000) {
                    logger.warn("Maximum iteration exceeded : " + iteration);
                    iteration = 1000;
                }
                if (iteration < 1) {
                    logger.warn("Minimum iteration exceeded : " + iteration);
                    iteration = 1;
                }
                cp.setOpt1Value(iteration);
            } else {
                logger.warn("User input iteration invalid : " + inputCoins);
                cp.setOpt1Value(qlearnPanel.getIteration());
            }
            return iteration;
        }

        public float verifyOpt2() {
            String inputDepth = cp.getOpt2Field().getText();
            float learningRate = qlearnPanel.getLearningRate()*100;
            if (inputDepth.matches("\\d+")) {
                learningRate = Integer.valueOf(inputDepth);
                if (learningRate > 100) {
                    logger.warn("Maximum learning rate exceeded : " + learningRate);
                    learningRate = 100;
                }
                if (learningRate < 0) {
                    logger.warn("Minimum learning rate exceeded : " + learningRate);
                    learningRate = 0;
                }
                cp.setOpt2Value((int) learningRate);
            } else {
                logger.warn("User input learning rate invalid : " + inputDepth);
                cp.setOpt2Value((int) (qlearnPanel.getLearningRate()*100));
            }
            return learningRate;
        }
    }

    class ActionStop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            qlearnPanel.togglePaused();
            stopAndResume();
        }
    }
    
    private void stopAndResume(){
        if (qlearnPanel.isPaused()) {
            cp.getStop().setText("Resume");
        } else {
            cp.getStop().setText("Stop");
        }
    }
    @Override
    public void togglePaused() {
        if (!qlearnPanel.isPaused() && qlearnThread.isAlive()){
            qlearnPanel.togglePaused(); 
            stopAndResume();       
        }
    }
    
}
