package data.mathActivation;

public class Softmax implements Activation {

    @Override
    public double activate(double[] tab, int index) {
        double expSum = 0;
        for (int i = 0; i < tab.length; i++) {
            expSum += Math.exp(tab[i]);
        }

        double res = Math.exp(tab[index]) / expSum;

        return res;
    }

    @Override
    public double derivative(double[] tab, int index) {
        double expSum = 0;
			for (int i = 0; i < tab.length; i++)
			{
				expSum += Math.exp(tab[i]);
			}

			double ex = Math.exp(tab[index]);

			return (ex * expSum - ex * ex) / (expSum * expSum);
    }

    @Override
    public ActivationType getActivationType() {
        return ActivationType.SOFTMAX;
    }

}
