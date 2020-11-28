package ink.verge.logistics.service.impl;


import com.aliyun.oss.internal.OSSUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.lang.response.CommonReturnType;
import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
import com.jmatio.types.MLDouble;
import com.mathworks.toolbox.javabuilder.MWException;
import ink.verge.logistics.entity.Road;
import ink.verge.logistics.mapper.RoadMapper;
import ink.verge.logistics.service.ROADService;
import ink.verge.logistics.utils.OssUtils;
import logistic.LogisticTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Verge
 * @since 2020-11-03
 */
@Service
public class RoadServiceImpl extends ServiceImpl<RoadMapper, Road> implements ROADService {
    @Autowired
    private OssUtils ossUtils;

    @Override
    public List<Road> getRoadsByKeyword(String keyword) {
        return null;
    }

    @Override
    public boolean generateRoadByMAT(String filepath) {
        MatFileReader reader = null;
        try {
            reader = new MatFileReader(filepath);
        } catch (IOException e) {
            return false;
        }
        MLArray mlArray = reader.getMLArray("bestVC");
        
        MLCell cell = (MLCell)mlArray;
        MLDouble mlDouble = (MLDouble) cell.get(0);
        return false;
    }

    @Override
    public boolean generateMatByExcel(String filepath) {

        LogisticTool tool = null;
        try {
            tool = new LogisticTool();
            tool.GA_VRPTW(filepath);
            return true;
        } catch (MWException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public String uploadExcel(MultipartFile file){
        File file1 = new File("D:\\root\\logistics\\"+file.getOriginalFilename());
        try {
            file.transferTo(file1);
            return file1.getAbsolutePath();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return  null;
    }

    @Override
    public boolean generatePath(MultipartFile file) {
        String path = uploadExcel(file);
        if (path == null) return false;
        generateMatByExcel(path);

        return true;
    }


}
