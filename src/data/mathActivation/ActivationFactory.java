package data.mathActivation;

public class ActivationFactory {
    public static Activation createActivation(ActivationType activationType){
        switch (activationType) {
            case SIGMOID:
                return new Sigmoid();
            case RELU:
                return new ReLU();
            case SOFTMAX:
                return new Softmax();
            default:
                //TODO logger => error unhandled
                return new Sigmoid();
        }
    }    
}
