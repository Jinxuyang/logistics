package ink.verge.logistics.controller;


import com.fehead.lang.response.CommonReturnType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ink.verge.logistics.entity.Order;
import ink.verge.logistics.service.ORDERService;
import ink.verge.logistics.service.PATHService;
import ink.verge.logistics.service.ROADService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Verge
 * @since 2020-11-29
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ORDERService orderService;

    @Autowired
    private PATHService pathService;

    @PostMapping("/insert")
    @ApiOperation("新增订单")
    public CommonReturnType insertOrder(@RequestBody Order order){
        if (orderService.save(order)){
            pathService.updatePathStatus(order.getPathId(),true);
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除订单")
    public CommonReturnType deleteOrder(@PathVariable("id") int id){
        if (orderService.removeById(id)){
            //pathService.updatePathStatus(id,false);
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @PutMapping("/update/{id}")
    @ApiOperation("修改订单信息")
    public CommonReturnType updateOrder(@PathVariable("id") int id, @RequestBody Order order){
        order.setId(id);
        if (orderService.updateById(order)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @GetMapping("/get")
    @ApiOperation("获取所有订单")
    public CommonReturnType getAllOrders( @RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> list = orderService.list();
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }

    /*@GetMapping("/get/{keyword}")
    @ApiOperation("获取所有订单")
    public CommonReturnType getAllOrders(@RequestParam(value = "pageNum") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                        @PathVariable String keyword){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> list = orderService.getOrdersByKeyword(keyword);
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }*/
}

