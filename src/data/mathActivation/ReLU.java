package data.mathActivation;

public class ReLU implements Activation {

    @Override
    public double activate(double[] tab, int index) {
        return Math.max(0, tab[index]);
    }

    @Override
    public double derivative(double[] tab, int index) {
        return (tab[index] > 0) ? 1 : 0;
    }

    @Override
    public ActivationType getActivationType() {
        return ActivationType.RELU;
    }
    
}
