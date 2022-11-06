
import java.io.Serializable;
import java.util.List;

public class NeuralNetwork implements Serializable {

    Matrix[] layer_weights;
    Matrix[] layer_bias;
    double l_rate=0.01;

    public NeuralNetwork(int[] layer_size) {
        layer_weights = new Matrix[layer_size.length-1];
        for(int i = 1; i<layer_size.length; i++){
            layer_weights[i-1]=new Matrix(layer_size[i], layer_size[i-1]);
        }
        layer_bias = new Matrix[layer_size.length-1];
        for(int i = 1; i < layer_size.length; i++){
            layer_bias[i-1] = new Matrix(layer_size[i], 1);
        }
    }
    public NeuralNetwork(int[] layer_size, double l_rate) {
        this.l_rate = l_rate;
        layer_weights = new Matrix[layer_size.length-1];
        for(int i = 1; i<layer_size.length; i++){
            layer_weights[i-1]=new Matrix(layer_size[i], layer_size[i-1]);
        }
        layer_bias = new Matrix[layer_size.length-1];
        for(int i = 1; i < layer_size.length; i++){
            layer_bias[i-1] = new Matrix(layer_size[i], 1);
        }
    }

    public List<Double> predict(double[] x)
    {
        Matrix input = Matrix.fromArray(x);
        Matrix[] layer_activation = new Matrix[layer_bias.length +1];
        layer_activation[0] = input;
        for(int i = 1; i< layer_activation.length;i++){
            layer_activation[i] = Matrix.multiply(layer_weights[i-1], layer_activation[i-1]);
            layer_activation[i].add(layer_bias[i-1]);
            layer_activation[i].sigmoid();
        }
        return layer_activation[layer_activation.length-1].toArray();
    }


    public void fit(double[][]x,double[][]y,int epochs)
    {
        for(int i=0;i<epochs;i++)
        {
            int sampleN =  (int)(Math.random() * x.length );
            this.train(x[sampleN], y[sampleN]);
        }
    }

    public void train(double [] x,double [] y)
    {
        Matrix input = Matrix.fromArray(x);
        Matrix[] layer_activation = new Matrix[layer_bias.length +1];
        layer_activation[0] = input;
        for(int i = 1; i< layer_activation.length;i++){
            layer_activation[i] = Matrix.multiply(layer_weights[i-1], layer_activation[i-1]);
            layer_activation[i].add(layer_bias[i-1]);
            layer_activation[i].sigmoid();
        }
        Matrix target = Matrix.fromArray(y);
        Matrix error = Matrix.subtract(target, layer_activation[layer_activation.length-1]);
        for(int i = layer_activation.length-1; i > 0; i--){
            layer_activation[i].dsigmoid();
            layer_activation[i].multiply(error);
            layer_activation[i].multiply(l_rate);
            Matrix transpose = Matrix.transpose(layer_activation[i-1]);
            Matrix delta = Matrix.multiply(layer_activation[i], transpose);
            layer_weights[i-1].add(delta);
            layer_bias[i-1].add(layer_activation[i]);
            Matrix weight_transpose= Matrix.transpose(layer_weights[i-1]);
            error = Matrix.multiply(weight_transpose, error);
        }

    }


}