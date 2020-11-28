package ink.verge.logistics;

import com.mathworks.toolbox.javabuilder.MWException;
import logistic.LogisticTool;

/**
 * @Author Verge
 * @Date 2020/11/27 17:07
 * @Version 1.0
 */
public class TestMatlab {


    public static void main(String[] args) throws MWException {
        LogisticTool tool = new LogisticTool();
        String filename = "https://lab-oss.oss-cn-beijing.aliyuncs.com/files/20201127/074357UFE10.xlsx";
        tool.GA_VRPTW(filename);
    }

}
