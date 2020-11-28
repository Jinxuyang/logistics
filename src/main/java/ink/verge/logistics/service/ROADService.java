package ink.verge.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.verge.logistics.entity.Road;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Verge
 * @since 2020-11-05
 */
public interface ROADService extends IService<Road> {
    List<Road> getRoadsByKeyword(String keyword);

    boolean generateRoadByMAT(String filepath);

    boolean generateMatByExcel(String filepath);

    String uploadExcel(MultipartFile file);

    boolean generatePath(MultipartFile file);
}
