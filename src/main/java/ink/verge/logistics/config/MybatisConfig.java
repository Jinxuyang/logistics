package ink.verge.logistics.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Verge
 * @Date 2020/10/8 23:35
 * @Version 1.0
 */
@Configuration
@MapperScan("ink.verge.logistics.mapper")
public class MybatisConfig {

}
