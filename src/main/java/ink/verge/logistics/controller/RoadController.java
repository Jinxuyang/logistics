package ink.verge.logistics.controller;


import com.fehead.lang.controller.BaseController;
import com.fehead.lang.response.CommonReturnType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ink.verge.logistics.entity.Road;
import ink.verge.logistics.service.ROADService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Verge
 * @since 2020-11-03
 */
@RestController
@RequestMapping("/road")
@Api("Road-Controller")
public class RoadController extends BaseController {

    @Autowired
    private ROADService roadService;

    @PostMapping("/insert")
    @ApiOperation("新增路线")
    public CommonReturnType insertRoad(@RequestBody Road road){
        if (roadService.save(road)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除路线")
    public CommonReturnType deleteRoad(@PathVariable("id") int id){
        if (roadService.removeById(id)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @PutMapping("/update/{id}")
    @ApiOperation("修改路线信息")
    public CommonReturnType updateRoad(@PathVariable("id") int id, @RequestBody Road road){
        road.setId(id);
        if (roadService.updateById(road)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @GetMapping("/get")
    @ApiOperation("获取所有路线")
    public CommonReturnType getAllRoads( @RequestParam(value = "pageNum") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Road> list = roadService.list();
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }

    @GetMapping("/get/{keyword}")
    @ApiOperation("获取所有路线")
    public CommonReturnType getAllRoads(@RequestParam(value = "pageNum") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                       @PathVariable String keyword){
        PageHelper.startPage(pageNum,pageSize);
        List<Road> list = roadService.getRoadsByKeyword(keyword);
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }

    @PostMapping("/upload")
    @ApiOperation("上传excel")
    public CommonReturnType generatePath(MultipartFile file) {
        boolean url = roadService.generatePath(file);
        return CommonReturnType.create(url);
    }
}

