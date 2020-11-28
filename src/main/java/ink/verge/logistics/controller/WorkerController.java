package ink.verge.logistics.controller;


import com.fehead.lang.response.CommonReturnType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ink.verge.logistics.entity.Worker;
import ink.verge.logistics.service.WORKERService;
import io.swagger.annotations.Api;
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
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/worker")
@Api("Worker-Controller")
public class WorkerController {
    @Autowired
    private WORKERService workerService;

    @PostMapping("/insert")
    @ApiOperation("新增员工")
    public CommonReturnType insertWorker(@RequestBody Worker worker){
        if (workerService.save(worker)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除员工")
    public CommonReturnType deleteWorker(@PathVariable("id") int id){
        if (workerService.removeById(id)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @PutMapping("/update/{id}")
    @ApiOperation("修改员工信息")
    public CommonReturnType updateWorker(@PathVariable("id") int id, @RequestBody Worker worker){
        worker.setId(id);
        if (workerService.updateById(worker)){
            return CommonReturnType.create(null,"success");
        } else {
            return CommonReturnType.create(null,"fail");
        }
    }
    @GetMapping("/get")
    @ApiOperation("获取所有员工")
    public CommonReturnType getAllWorkers( @RequestParam(value = "pageNum") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Worker> list = workerService.list();
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }

    @GetMapping("/get/{keyword}")
    @ApiOperation("获取所有员工")
    public CommonReturnType getAllWorkers(@RequestParam(value = "pageNum") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                       @PathVariable String keyword){
        PageHelper.startPage(pageNum,pageSize);
        List<Worker> list = workerService.getWorkersByKeyword(keyword);
        int pageCnt = PageInfo.of(list).getPages();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("pageCnt",pageCnt);
        return CommonReturnType.create(resultMap);
    }
}

