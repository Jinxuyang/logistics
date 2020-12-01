package ink.verge.logistics.service;

import ink.verge.logistics.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Verge
 * @since 2020-11-29
 */
public interface ORDERService extends IService<Order> {
    //boolean generateOrderByPath();

    Order getOrderByWorkId(int id);

    List<Order> getOrderWhichStatusEqWaiting();

}
