import java.io.*;
import java.util.List;

public class Main {
    static double [][] X= {
            {0,0},
            {1,0},
            {0,1},
            {1,1}
    };
    static double [][] Y= {
            {1, 0},{0, 1},{0, 1},{1,0}
    };
    public static void main(String[] Args) throws IOException, ClassNotFoundException {
        NeuralNetwork net = new NeuralNetwork(new int[]{2, 10, 2},0.01);
        List<Double> output;
        net.fit(X, Y, 100000);
        double [][] input = {
                {0,0},{0,1},{1,0},{1,1}
        };
        for(double d[]:input)
        {
            output = net.predict(d);
            System.out.println(output.toString());
        }
        FileOutputStream fileOutputStream= new FileOutputStream("test.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(net);
        objectOutputStream.flush();
        objectOutputStream.close();
        FileInputStream fileInputStream = new FileInputStream("test.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        NeuralNetwork p2 = (NeuralNetwork) objectInputStream.readObject();
        System.out.println();
    }
}
