package ink.verge.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
import com.jmatio.types.MLDouble;
import com.mathworks.toolbox.javabuilder.MWException;
import ink.verge.logistics.entity.Path;
import ink.verge.logistics.entity.trans.PathNoId;
import ink.verge.logistics.mapper.PathMapper;
import ink.verge.logistics.service.PATHService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import logistic.LogisticTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Verge
 * @since 2020-11-28
 */
@Service
public class PathServiceImpl extends ServiceImpl<PathMapper, Path> implements PATHService {
    @Autowired
    private PathMapper pathMapper;


    @Override
    public boolean generatePaths(MultipartFile file) {
        File tempFile = new File("D:/root/logistics"+file.getOriginalFilename());

        LogisticTool tool = null;
        MatFileReader reader = null;
        try {
            file.transferTo(tempFile);
            tool = new LogisticTool();
            tool.GA_VRPTW(tempFile.getAbsolutePath());
            reader = new MatFileReader("data.mat");
        } catch (MWException | IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        MLArray mlArray = reader.getMLArray("bestVC");
        MLCell cell = (MLCell)mlArray;

        int rowNum = cell.getM();
        for (int i = 0;i < rowNum;i++){
            MLDouble mlDouble = (MLDouble) cell.get(i);
            double[][] doubles = mlDouble.getArray();
            double[] tempDouble = doubles[0];
            // PathNoId pathNoId = new PathNoId();
            // pathNoId.setPath(tempDouble);
            ObjectMapper objectMapper = new ObjectMapper();
            Path path = new Path();
            try {
                String pathStr = objectMapper.writeValueAsString(tempDouble);
                path.setPath(pathStr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false;
            }
            pathMapper.insert(path);
        }

        return true;
    }

    @Override
    public List<Path> getPathWhichNotGenerateOrder() {
        QueryWrapper<Path> wrapper = new QueryWrapper<>();
        wrapper.eq("status",false);
        return pathMapper.selectList(wrapper);
    }

    @Override
    public boolean updatePathStatus(int pathId,boolean status) {
        Path path = new Path();
        path.setId(pathId);
        path.setStatus(status);
        if (pathMapper.updateById(path) == 1){
            return true;
        } else {
            return false;
        }
    }
}
