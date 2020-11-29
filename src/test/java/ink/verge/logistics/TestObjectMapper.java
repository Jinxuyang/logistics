package ink.verge.logistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.verge.logistics.entity.trans.PathNoId;
import org.junit.Test;

/**
 * @Author Verge
 * @Date 2020/11/28 18:19
 * @Version 1.0
 */
public class TestObjectMapper {
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void test(){
        //PathNoId pathNoId = new PathNoId();
        double[] doubles = {1,2,3};
        //pathNoId.setPath(doubles);
        try {
            String str = objectMapper.writeValueAsString(doubles);
            double[] doubles1 = objectMapper.readValue(str,double[].class);
            System.out.println(doubles1.length);
            System.out.println(objectMapper.writeValueAsString(doubles));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
