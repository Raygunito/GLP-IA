package gui.algorithmPanel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import process.minmax.MinMaxCore;
import process.minmax.TreeFactory;

public class MinMaxPanel extends JPanel{
    private MinMaxCore minMaxCore;
    private JButton one,two,three;
    private JPanel playPanel;
    public MinMaxPanel(int coin, int difficulty) {
        super();
        minMaxCore = new MinMaxCore(coin, difficulty);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initPlayPanel();
    }
    private void initPlayPanel() {
        playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");


        playPanel.add(one);
        playPanel.add(two);
        playPanel.add(three);
    }

}
