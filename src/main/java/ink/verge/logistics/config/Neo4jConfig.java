package ink.verge.logistics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author Verge
 * @Date 2020/11/28 15:10
 * @Version 1.0
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "ink.verge.logistics.mapper.graph")
@EnableTransactionManagement
public class Neo4jConfig {
}
