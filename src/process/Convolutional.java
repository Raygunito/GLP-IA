package process;

import java.util.Random;

public class Convolutional {
  
    // Hyperparameters
    private int numFilters = 16; // number of convolutional filters
    private int filterSize = 3; // size of each filter
    private int poolSize = 2; // size of the pooling window
    private int hiddenSize = 100; // number of neurons in the fully connected layer
    private double learningRate = 0.01; // learning rate for gradient descent
    private int epochs = 10; // number of epochs to train
    
    // Model parameters
    private double[][][] filters;
    private double[] biases;
    private double[][][] poolingMask;
    private double[] weights;
    
    public Convolutional() {
      // Initialize model parameters randomly
      Random rand = new Random();
      filters = new double[numFilters][filterSize][filterSize];
      for (int i = 0; i < numFilters; i++) {
        for (int j = 0; j < filterSize; j++) {
          for (int k = 0; k < filterSize; k++) {
            filters[i][j][k] = rand.nextDouble() * 2 - 1;
          }
        }
      }
      biases = new double[numFilters];
      for (int i = 0; i < numFilters; i++) {
        biases[i] = rand.nextDouble() * 2 - 1;
      }
      poolingMask = new double[numFilters][poolSize][poolSize];
      for (int i = 0; i < numFilters; i++) {
        for (int j = 0; j < poolSize; j++) {
          for (int k = 0; k < poolSize; k++) {
            poolingMask[i][j][k] = 1.0 / (poolSize * poolSize);
          }
        }
      }
      weights = new double[numFilters * ((28 - filterSize + 1) / poolSize) * ((28 - filterSize + 1) / poolSize) * hiddenSize];
      for (int i = 0; i < weights.length; i++) {
        weights[i] = rand.nextDouble() * 2 - 1;
      }
    }
    
    public void train(double[][][] X, int[] Y) {
      for (int epoch = 0; epoch < epochs; epoch++) {
        for (int i = 0; i < X.length; i++) {
          // Forward pass
          double[][][] conv = convolve(X[i], filters, biases);
          double[][][] pool = pool(conv, poolingMask);
          double[] flat = flatten(pool);
          double[] hidden = fullyConnected(flat, weights);
          double[] output = softmax(hidden);
          
          // Backward pass
          double[] dOutput = new double[10];
          dOutput[Y[i]] = 1.0;
          double[] dHidden = fullyConnectedBackward(dOutput, hidden, weights);
          double[][][] dFlat = flattenBackward(dHidden, pool);
          double[][][] dPool = poolBackward(dFlat, conv, poolingMask);
          double[][][] dConv = convolveBackward(X[i], dPool, filters);
          
          // Update model parameters
          filters = updateFilters(filters, dConv, learningRate);
          biases = updateBiases(biases, dConv, learningRate);
          weights = updateWeights(weights, flat, dHidden, learningRate);
        }
      }
    }
    public int predict(double[][][] X) {
        double[][][] conv = convolve(X, filters, biases);
        double[][][] pool = pool(conv, poolingMask);
        double[] flat = flatten(pool);
        double[] hidden = fullyConnected(flat, weights);
        double[] output = softmax(hidden);
        int maxIndex = 0;
        double maxProb = output[0];
        for (int i = 1; i < output.length; i++) {
            if (output[i] > maxProb) {
                maxIndex = i;
                maxProb = output[i];
                }
            }
        return maxIndex;
    }
        
    private double[][][] convolve(double[][][] X, double[][][] filters, double[] biases) {
        int numRows = X.length;
        int numCols = X[0].length;
        int numFilters = filters.length;
        int outRows = numRows - filters[0].length + 1;
        int outCols = numCols - filters[0][0].length + 1;
        double[][][] out = new double[numFilters][outRows][outCols];
            for (int f = 0; f < numFilters; f++) {
                for (int i = 0; i < outRows; i++) {
                    for (int j = 0; j < outCols; j++) {
                    double sum = 0.0;
                        for (int k = 0; k < filters[0].length; k++) {
                            for (int l = 0; l < filters[0][0].length; l++) {
                                sum += X[i+k][j+l][0] * filters[f][k][l];
                            }
                        }
                    out[f][i][j] = sum + biases[f];
                    }
                }
            }
        return out;
    }
    
    private double[][][] pool(double[][][] X, double[][][] mask) {
        int numRows = X.length;
        int numCols = X[0].length;
        int numFilters = X.length;
        int poolRows = mask[0].length;
        int poolCols = mask[0][0].length;
        int outRows = numRows / poolRows;
        int outCols = numCols / poolCols;
        double[][][] out = new double[numFilters][outRows][outCols];
            for (int f = 0; f < numFilters; f++) {
                for (int i = 0; i < outRows; i++) {
                    for (int j = 0; j < outCols; j++) {
                        double sum = 0.0;
                        for (int k = 0; k < poolRows; k++) {
                            for (int l = 0; l < poolCols; l++) {
                                sum += X[ipoolRows+k][jpoolCols+l][f] * mask[f][k][l];
                            }
                        }
                        out[f][i][j] = sum;
                    }
                }
            }
        return out;
    }
    
    private double[] flatten(double[][][] X) {
        int numRows = X.length;
        int numCols = X[0].length;
        int numFilters = X[0][0].length;
        double[] out = new double[numRows * numCols * numFilters];
        int index = 0;
        for (int k = 0; k < numFilters; k++) {
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    out[index++] = X[i][j][k];
                }
            }
        }
        return out;
    }
    private double[] fullyConnected(double[] X, double[][] weights) {
        double[] out = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < X.length; j++) {
                sum += X[j] * weights[i][j];
            }
            out[i] = sum;
        }
        return out;
    }
    
    private double[] softmax(double[] X) {
        double[] out = new double[X.length];
        double sum = 0.0;
        for (int i = 0; i < X.length; i++) {
            out[i] = Math.exp(X[i]);
            sum += out[i];
        }
        for (int i = 0; i < X.length; i++) {
            out[i] /= sum;
        }
        return out;
    }
}