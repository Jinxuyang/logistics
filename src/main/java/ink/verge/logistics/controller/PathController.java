package ink.verge.logistics.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.lang.response.CommonReturnType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ink.verge.logistics.controller.viewobject.PathVO;
import ink.verge.logistics.entity.Path;
import ink.verge.logistics.service.PATHService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Verge
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/path")
@Api("Path-Controller")
public class PathController {
    @Autowired
    private PATHService pathService;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation("生成路径")
    @PostMapping("/generate")
    public CommonReturnType generatePath(MultipartFile file){
        if (pathService.generatePaths(file)) return CommonReturnType.create("成功生成");
        else return CommonReturnType.create("生成失败");
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除路线")
    public CommonReturnType deletePath(@PathVariable("id") int id){
        if (pathService.removeById(id)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @PutMapping("/update/{id}")
    @ApiOperation("修改路线信息")
    public CommonReturnType updatePathStatus(@PathVariable("id") int id, @RequestParam("status") boolean status){
        Path path = new Path();
        path.setStatus(status);
        path.setId(id);
        if (pathService.updateById(path)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @GetMapping("/get")
    @ApiOperation("获取所有路线")
    public CommonReturnType getAllPaths( @RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "8") int pageSize) throws JsonProcessingException {
        PageHelper.startPage(pageNum,pageSize);
        List<Path> list = pathService.list();
        List<PathVO> pathVOList  = new ArrayList<>();
        for (Path path : list) {
            PathVO pathVO = new PathVO();
            double[] tempDouble = objectMapper.readValue(path.getPath(),double[].class);
            BeanUtils.copyProperties(path,pathVO);
            pathVO.setPath(tempDouble);
            pathVOList.add(pathVO);
        }
        int pageCnt = PageInfo.of(pathVOList).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",pathVOList);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据id获取路径")
    public CommonReturnType getPathById(@PathVariable("id") int id) throws JsonProcessingException {
        Path path = pathService.getById(id);
        PathVO pathVO = new PathVO();
        double[] tempDouble = objectMapper.readValue(path.getPath(),double[].class);
        BeanUtils.copyProperties(path,pathVO);
        pathVO.setPath(tempDouble);
        return CommonReturnType.create(pathVO);
    }

    @GetMapping("/get/notgenerate")
    @ApiOperation("获取所有未生成订单的路径")
    public CommonReturnType getNotGeneratePath(){
        return CommonReturnType.create(pathService.getPathWhichNotGenerateOrder());
    }
}

