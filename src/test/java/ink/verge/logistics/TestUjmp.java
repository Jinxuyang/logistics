package ink.verge.logistics;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
import com.jmatio.types.MLDouble;
import org.ujmp.core.Matrix;
import org.ujmp.jmatio.ImportMatrixMAT;

import java.io.File;
import java.io.IOException;

/**
 * @Author Verge
 * @Date 2020/11/27 17:31
 * @Version 1.0
 */
public class TestUjmp {
    public static void main(String[] args) throws IOException {

        MatFileReader reader = new MatFileReader("data.mat");
        MLArray mlArray = reader.getMLArray("bestVC");
        MLCell cell = (MLCell)mlArray;

        System.out.println(cell.getM());
        System.out.println(cell.getN());

        MLDouble mlDouble = (MLDouble) cell.get(0);
        double[][] doubles = mlDouble.getArray();
        double[] temp = doubles[0];
        for (double v : temp) {
            System.out.println(v);
        }
        /*System.out.println(doubles[0].length);
        System.out.println(doubles[0][1]);
        *///MLDouble mlDouble = (MLDouble) cell.get(0);
        //System.out.println(cell.contentToString());
        //System.out.println(mlDouble.get(0));
        /*double[][] doubleA = mlDouble.getArray();
        System.out.println(doubleA[0][0]);*/
        //System.out.println(cell.get(0));
    }
}
