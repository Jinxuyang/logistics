package ink.verge.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.verge.logistics.entity.Worker;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Verge
 * @since 2020-11-05
 */
public interface WORKERService extends IService<Worker> {
    List<Worker> getWorkersByKeyword(String keyword);
}
