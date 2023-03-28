package gui.utilsPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIConstant;

public class InformationPanel extends JPanel{
    private static final int WIDTH = GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR*45 ;
    private JPanel info1Panel,info2Panel;
    private JLabel info1,info2,infoValue1,infoValue2;
    public InformationPanel(String algoName) throws IllegalArgumentException{
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));

        initLabel();
        initPanel();
        switch (algoName) {
            case GUIConstant.ASTAR:
                initAStar();
                break;
            case GUIConstant.MINMAX:
                initMinMax();
                break;
            case GUIConstant.QLEARN:
                initQLearn();
                break;
        
            default:
                throw new IllegalArgumentException("Wrong name used to construct InformationPanel, use constants from GUIConstant.");
        }

        add(info1Panel);
        add(info2Panel);
    }

    private void initPanel(){
        info1Panel = new JPanel();
        info2Panel = new JPanel();
        info1Panel.add(info1);
        info1Panel.add(infoValue1);
        info1Panel.add(info2);
        info1Panel.add(infoValue2);

    }

    private void initLabel(){
        info1 = new JLabel();
        info2 = new JLabel();
        infoValue1 = new JLabel("0");
        infoValue2 = new JLabel("0");
    }

    private void initAStar(){
        info1.setText("Case visité :");
        info2.setText("Taille du chemin :");
    }
    
    private void initMinMax(){
        info1.setText("Pièce(s) prise par le bot :");
        info2.setText("Noeud visité :");
        
    }
    private void initQLearn(){
        info1.setText("Numéro itération :");
        info2.setText("Death Count :");
    }

    public void setInfoValue1(String value) {
        infoValue1.setText(value);
    }
    
    public void setInfoValue2(String value) {
        infoValue2.setText(value);
    }
    
    public void resetValue(){
        infoValue1.setText("0");
        infoValue2.setText("0");
    }
}
