package ink.verge.logistics.mapper;

import ink.verge.logistics.entity.Path;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Verge
 * @since 2020-11-28
 */
@Repository
public interface PathMapper extends BaseMapper<Path> {
    @Select("select * from tb_path where status = 0")
    List<Path> getPathNotGenerateOrder();
}
