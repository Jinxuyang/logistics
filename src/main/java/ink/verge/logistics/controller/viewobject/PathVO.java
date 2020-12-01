package ink.verge.logistics.controller.viewobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author Verge
 * @Date 2020/11/29 13:39
 * @Version 1.0
 */
@Data
@ApiModel(value="PathVO对象", description="")
public class PathVO {
    private Integer id;

    private double[] path;

    private boolean status;
}
