package gui;


public interface GUIConstant {
    /**
     * <p>Big screen = 4</p>
     * <p>Default value = 3</p>
     * <p>Small screen = 2</p>
     */
    int SCALING_FACTOR=2;

    //--------------------------------------------------------//
    int DIM_X=SCALING_FACTOR*400;
    int DIM_Y=SCALING_FACTOR*225;
    String ASTAR="astar";
    String QLEARN="qlearning";
    String MINMAX= "minmax";
}
