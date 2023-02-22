package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe qui permet de créer le menu principal de notre application.
 */
public class Main_menu extends JPanel {
    //TODO ActionListener link with data class
    //TODO make this panel scalable with the scale factor
    private JLabel title;
    private JButton astar,minmax,qlearn,quit;
    public Main_menu() {
        super();
        setAlignmentX(Component.CENTER_ALIGNMENT);
        init();
        add(Box.createVerticalStrut(50));
        add(title);
        add(Box.createVerticalStrut(50));
        add(astar);
        add(Box.createVerticalStrut(10));
        add(minmax);
        add(Box.createVerticalStrut(10));
        add(qlearn);
        add(Box.createVerticalStrut(10));
        add(quit);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
    }

    private void init(){
        title= new JLabel("The Cognitive Crew");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font("Arial",Font.BOLD,36));

        astar= new JButton("A Star");
        astar.setAlignmentX(CENTER_ALIGNMENT);
        astar.setFont(new Font("Arial",Font.BOLD,14));
        astar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1),BorderFactory.createEmptyBorder(15, 48, 15, 48)));


        minmax = new JButton("MinMax");
        minmax.setAlignmentX(CENTER_ALIGNMENT);
        minmax.setFont(new Font("Arial",Font.BOLD,14));
        minmax.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1),BorderFactory.createEmptyBorder(15, 42, 15, 42)));
        
        qlearn = new JButton("QLearning");
        qlearn.setAlignmentX(CENTER_ALIGNMENT);
        qlearn.setFont(new Font("Arial",Font.BOLD,14));
        qlearn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1),BorderFactory.createEmptyBorder(15, 33, 15, 33)));
        
        quit = new JButton("Quit");
        quit.setAlignmentX(CENTER_ALIGNMENT);
        quit.setFont(new Font("Arial",Font.BOLD,14));
        quit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1),BorderFactory.createEmptyBorder(15, 55, 15, 55)));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        Point2D center = new Point2D.Float(w / 2, h/2);
        float radius = w / 2;
        float[] dist = { 0.01f, 0.9f, 1.0f };
        Point2D focus = new Point2D.Float(w / 2, h / 16);
        Color[] colors = { Color.WHITE, Color.decode("#90B1F1"), Color.decode("#90B1F1") };
        RadialGradientPaint rgp = new RadialGradientPaint(center, radius,
                focus, dist, colors, CycleMethod.NO_CYCLE);
        g2d.setPaint(rgp);
        g2d.fillRect(0, 0, w, h);
    }

    /** 
     * Ajoute un actionlistener au bouton AStar.
     * @param action
     */
    public void addActionListenerAStar(ActionListener action){
        astar.addActionListener(action);
    }
    /** 
     * Ajoute un actionlistener au bouton Minmax.
     * @param action
     */
    public void addActionListenerMinMax(ActionListener action){
        minmax.addActionListener(action);
    }
    /** 
     * Ajoute un actionlistener au bouton QLearning.
     * @param action
     */
    public void addActionListenerQLearn(ActionListener action){
        qlearn.addActionListener(action);
    }
    /** 
     * Ajoute un actionlistener au bouton Quit.
     * @param action
     */
    public void addActionListenerQuit(ActionListener action){
        quit.addActionListener(action);
    }
}
