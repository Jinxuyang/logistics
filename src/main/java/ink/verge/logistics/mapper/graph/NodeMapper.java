package ink.verge.logistics.mapper.graph;

import ink.verge.logistics.entity.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Verge
 * @Date 2020/11/28 14:52
 * @Version 1.0
 */
@Repository
public interface NodeMapper extends Neo4jRepository<Node,Long> {
    @Query("CREATE (n:Node{city:$city}) RETURN n")
    List<Node> insertNode(@Param("city") String city);

    @Query("MATCH (a) -[b:Next{path:1}]- () RETURN a")
    List<Node> selectAllNodes();

}
