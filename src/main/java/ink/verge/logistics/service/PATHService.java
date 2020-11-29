package ink.verge.logistics.service;

import ink.verge.logistics.entity.Path;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Verge
 * @since 2020-11-28
 */
public interface PATHService extends IService<Path> {
    boolean generatePaths(MultipartFile file);
}
