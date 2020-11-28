package ink.verge.logistics.mapper;

import ink.verge.logistics.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Verge
 * @since 2020-11-18
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
