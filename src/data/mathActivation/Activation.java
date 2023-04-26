package data.mathActivation;

public interface Activation {
    public double activate(double[] tab, int index);
    public double derivative(double[] tab, int index);
    public ActivationType getActivationType();
}
