package ink.verge.logistics.controller;

import com.fehead.lang.response.CommonReturnType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ink.verge.logistics.entity.Car;
import ink.verge.logistics.service.CARService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Verge
 * @Date 2020/12/1 15:32
 * @Version 1.0
 */
public class CarController {
    @Autowired
    private CARService carService;

    @PostMapping("/insert")
    @ApiOperation("新增车辆")
    public CommonReturnType insertCar(@RequestBody Car car){
        if (carService.save(car)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除车辆")
    public CommonReturnType deleteCar(@PathVariable("id") int id){
        if (carService.removeById(id)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @PutMapping("/update/{id}")
    @ApiOperation("修改车辆信息")
    public CommonReturnType updateCar(@PathVariable("id") int id, @RequestBody Car car){
        car.setId(id);
        if (carService.updateById(car)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @GetMapping("/get")
    @ApiOperation("获取所有车辆")
    public CommonReturnType getAllCars(@RequestParam(value = "pageNum") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Car> list = carService.list();
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }

    @GetMapping("/get/{keyword}")
    @ApiOperation("获取所有车辆")
    public CommonReturnType getAllCars(@RequestParam(value = "pageNum") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                        @PathVariable String keyword){
        PageHelper.startPage(pageNum,pageSize);
        List<Car> list = carService.getCarsByKeyword(keyword);
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }
}
