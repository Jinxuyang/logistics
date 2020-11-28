package ink.verge.logistics.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.verge.logistics.entity.Car;
import ink.verge.logistics.mapper.CarMapper;
import ink.verge.logistics.service.CARService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Verge
 * @since 2020-11-05
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CARService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> getCarsByKeyword(String keyword) {
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.like("model",keyword)
                .or().like("max_weight",keyword)
                .or().like("max_volume",keyword);
        return carMapper.selectList(wrapper);
    }
}
