package ink.verge.logistics;

import ink.verge.logistics.mapper.graph.NodeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Verge
 * @Date 2020/11/28 14:55
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestNodeMapper {
    @Autowired
    private NodeMapper nodeMapper;
    @Test
    public void test(){
        //System.out.println(nodeMapper.findAll());
        System.out.println(nodeMapper.selectAllNodes());
    }
}
