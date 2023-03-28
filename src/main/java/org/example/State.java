package main.java.org.example;

import java.util.Random;

public class State {
    private final double[] QValue;

    public State() {
        QValue = new double[]{0, 0, 0, 0};
    }

    public double[] getQValue() {
        return QValue;
    }

    public int getMax() {
        Random random=new Random();
        int direction = random.nextInt(4);
        double attraction = QValue[direction];
        for (int i = 1; i < QValue.length; i++) {
            if (QValue[i] > attraction) {
                direction = i;
                attraction = QValue[i];
            }
        }
        return direction;
    }
}
