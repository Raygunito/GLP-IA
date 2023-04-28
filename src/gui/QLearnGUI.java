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
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;

public class QLearnGUI extends JPanel {
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
        // TODO Remplacer le qlearnPanel avec sa version fonctionnelle
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
            qlearnPanel = new QLearnPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                    Float.valueOf(cp.getOpt2Field().getText()) / 100, QLearnGUI.this.ip);
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

    }

    class ActionStop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            qlearnPanel.togglePaused();
            if (qlearnPanel.isPaused()) {
                cp.getStop().setText("Resume");
                cp.getOpt2Field().setEditable(true);
            } else {
                cp.getStop().setText("Stop");
                cp.getOpt2Field().setEditable(false);
            }
        }
    }
}
