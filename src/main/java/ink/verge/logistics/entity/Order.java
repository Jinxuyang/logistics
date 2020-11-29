package ink.verge.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Verge
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_order")
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "货物体积(立方厘米)")
    private Integer volume;

    @ApiModelProperty(value = "货物质量(克)")
    private Integer weight;

    private Date earliestPickTime;

    private Date latestPickTime;

    private Integer carId;

    private Integer pathId;

    private Integer workerId;

    private String status;


}
