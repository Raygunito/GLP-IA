package gui;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    private void init() {
        Font mainTitleFont = null;
        Font menuFont = null;
        try {
            mainTitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/gui/fonts/poppins/Poppins-Bold.ttf")).deriveFont(36f);
            menuFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/gui/fonts/poppins/Poppins-Bold.ttf")).deriveFont(22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(mainTitleFont);
            ge.registerFont(menuFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        title = new JLabel("The Cognitive Crew");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(mainTitleFont);

        astar = new JButton("A Star");
        astar.setAlignmentX(CENTER_ALIGNMENT);
        astar.setFont(menuFont);
        astar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 0), BorderFactory.createEmptyBorder(10, 57, 10, 57)));

        minmax = new JButton("MinMax");
        minmax.setAlignmentX(CENTER_ALIGNMENT);
        minmax.setFont(menuFont);
        minmax.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 0), BorderFactory.createEmptyBorder(10, 47, 10, 47)));

        qlearn = new JButton("QLearning");
        qlearn.setAlignmentX(CENTER_ALIGNMENT);
        qlearn.setFont(menuFont);
        qlearn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 0), BorderFactory.createEmptyBorder(10, 33, 10, 33)));

        quit = new JButton("Quit");
        quit.setAlignmentX(CENTER_ALIGNMENT);
        quit.setFont(menuFont);
        quit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 0), BorderFactory.createEmptyBorder(10, 68, 10, 68)));
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
