import java.io.*;
import java.util.List;

public class Main {
    static double [][] X= {
            {7},
            {8},
            {9},
            {10},
            {11},
            {12},
            {13},
            {14},
            {15},
            {16},
            {17},
            {18},
            {19},
            {20},
            {21}
    };
    static double [][] Y= {
            {0,1},//7
            {0,1},//8
            {0,1},//9
            {0,1},//10
            {0,1},//11
            {0,1},//12
            {0,1},//13
            {0,1},//14
            {1,0},//15
            {1,0},//16
            {1,0},//17
            {1,0},//18
            {1,0},//19
            {1,0},//20
            {1,0}//21
    };
    static double [][] X2= {
            {14},
            {15},
            {16},
            {17}
    };
    static double [][] Y2= {
            {0,1},//14
            {0,1},//15
            {0,1},//16
            {1,0}//17
    };
    public static void main(String[] Args) throws IOException, ClassNotFoundException {
        NeuralNetwork net = NeuralNetwork.loadFromFileNeuralNetwork("test.ser");
        List<Double> output;
        double [][] input = {
                {7},
                {8},
                {9},
                {10},
                {11},
                {12},
                {13},
                {14},
                {15},
                {16},
                {17},
                {18},
                {19},
                {20},
                {21},
                {50}
        };
        for(double d[]:input)
        {
            output = net.predict(d);
            System.out.println( d[0]+" "+ output.toString());
        }
        System.out.println();
        net.fit(X2, Y2, 300);
        for(double d[]:input)
        {
            output = net.predict(d);
            System.out.println( d[0]+" "+ output.toString());
        }
    }
}
