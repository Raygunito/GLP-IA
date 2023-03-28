package main.java.org.example;

public class State {
    private final double[] QValue;

    public State() {
        QValue = new double[]{0, 0, 0, 0};
    }

    public double[] getQValue() {
        return QValue;
    }

    public int getMax() {
        int direction = 0;
        double attraction = QValue[0];
        for (int i = 1; i < QValue.length; i++) {
            if (QValue[i] > attraction) {
                direction = i;
                attraction = QValue[i];
            }
        }
        return direction;
    }
}