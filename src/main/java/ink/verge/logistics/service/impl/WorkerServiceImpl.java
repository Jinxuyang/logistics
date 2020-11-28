package ink.verge.logistics.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.verge.logistics.entity.Worker;
import ink.verge.logistics.mapper.WorkerMapper;
import ink.verge.logistics.service.WORKERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Verge
 * @since 2020-11-04
 */
@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WORKERService {
    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public List<Worker> getWorkersByKeyword(String keyword) {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        wrapper.like("username",keyword)
                .or().like("introduction",keyword);
        return workerMapper.selectList(wrapper);
    }

}
