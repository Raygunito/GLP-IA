package gui.utilsPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import gui.GUIConstant;
import log.LoggerUtility;

public class InformationPanel extends JPanel{
    private static final int WIDTH = GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR*45 ;
    private JPanel info1Panel,info2Panel;
    private JLabel info1,info2,infoValue1,infoValue2;
    private JTextArea instruction;
    private static Logger logger = LoggerUtility.getLogger(InformationPanel.class, "text");
    private JPanel wrapper;
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
                logger.error(algoName + " isn't found for initializing the InformationPanel");
                throw new IllegalArgumentException("Wrong name used to construct InformationPanel, use constants from GUIConstant.");
        }

        wrapper.add(info1Panel);
        wrapper.add(info2Panel);
        add(wrapper);
        add(instruction);
    }

    private void initPanel(){
        info1Panel = new JPanel();
        info2Panel = new JPanel();
        info1Panel.add(info1);
        info1Panel.add(infoValue1);
        info1Panel.add(info2);
        info1Panel.add(infoValue2);
        wrapper = new JPanel(new FlowLayout(FlowLayout.LEADING));
        wrapper.setPreferredSize(new Dimension((2*WIDTH/3) -10, HEIGHT));
        instruction.setPreferredSize(new Dimension((WIDTH/3)-10, HEIGHT));
        instruction.setWrapStyleWord(true);
        instruction.setLineWrap(true);
        instruction.setEditable(false);
    }

    private void initLabel(){
        info1 = new JLabel();
        info2 = new JLabel();
        instruction = new JTextArea();
        infoValue1 = new JLabel("0");
        infoValue2 = new JLabel("0");
        Font fontSize = new Font("Segoe UI", Font.PLAIN, GUIConstant.SCALING_FACTOR*4);
        info1.setFont(fontSize);
        info2.setFont(fontSize);
        infoValue1.setFont(fontSize);
        infoValue2.setFont(fontSize);
    }

    private void initAStar(){
        info1.setText("Tiles visited :");
        info2.setText("Current path size :");
        instruction.setText("Cas d'utilisation : GPS itinéaire/ Labyrinthe \nPermet de trouver le chemin le plus court. ");
    }
    
    private void initMinMax(){
        info1.setText("Coin taken by bot:");
        info2.setText("Total node visited :");
        instruction.setText("Cas d'utilisation : Jeu à somme nulle comme Morpion, Puissance 4, Echecs \nPermet de choisir le meilleur coup à jouer.\nPour gagner, il faut retirer la dernière pièce du jeu.");
    }
    private void initQLearn(){
        info1.setText("Iteration number :");
        info2.setText("Total tiles visited :");
        instruction.setText("Cas d'utilisation : Apprentissage basique comme la gestion des feux d'un traffic\nPermet d'apprendre de ses expériences passés pour mieux réussir son épisode actuel.\nC'est l'algo le plus primitif des Reinforcement Learning.");
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


    public String getInfoValue2() {
        return infoValue2.getText();
    }

    public JPanel getWrapper() {
        return wrapper;
    }
}
