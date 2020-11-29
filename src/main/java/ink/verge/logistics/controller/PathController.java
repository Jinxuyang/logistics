package ink.verge.logistics.controller;


import com.fehead.lang.response.CommonReturnType;
import ink.verge.logistics.service.PATHService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation("生成路径")
    @PostMapping("/generate")
    public CommonReturnType generatePath(MultipartFile file){
        if (pathService.generatePaths(file)) return CommonReturnType.create("成功生成");
        else return CommonReturnType.create("生成失败");
    }




}

