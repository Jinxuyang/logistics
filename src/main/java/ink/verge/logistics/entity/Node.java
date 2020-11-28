package ink.verge.logistics.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @Author Verge
 * @Date 2020/11/28 14:08
 * @Version 1.0
 */
@NodeEntity(label = "Place")
@Data
public class Node {
    @Id
    private Long nodeId;

    @Property(name = "province")
    private String province;

    @Property(name = "province")
    private String city;

    @Property(name = "province")
    private String district;

    @Property(name = "province")
    private String street;

}
