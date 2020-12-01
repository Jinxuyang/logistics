package ink.verge.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.verge.logistics.entity.Order;
import ink.verge.logistics.entity.Path;
import ink.verge.logistics.mapper.OrderMapper;
import ink.verge.logistics.mapper.PathMapper;
import ink.verge.logistics.service.ORDERService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Verge
 * @since 2020-11-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements ORDERService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<Order> getOrderWhichStatusEqWaiting() {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("status","-1");
        return orderMapper.selectList(wrapper);

    }


    @Override
    public Order getOrderByWorkId(int id) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("worker_id",id)
                .eq("status","0");
        return orderMapper.selectOne(wrapper);
    }
}
