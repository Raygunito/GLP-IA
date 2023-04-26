package data.mathActivation;

public class Sigmoid implements Activation {

    @Override
    public double activate(double[] tab, int index) {
        return 1.0 / (1 + Math.exp(-tab[index]));
    }

    @Override
    public double derivative(double[] tab, int index) {
        double a = activate(tab, index);
        return a * (1 - a);
    }

    @Override
    public ActivationType getActivationType() {
        return ActivationType.SIGMOID;
    }

}
