package ink.verge.logistics.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.fehead.lang.response.CommonReturnType;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Verge
 * @Date 2020/10/26 20:21
 * @Version 1.0
 */
@Component
public class OssUtils {
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.accessKeyId}")
    private String ALIYUN_OSS_ACCESSKEYID;
    @Value("${aliyun.oss.accessKeySecret}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;

    public OSS getOssClient(){
        return new OSSClientBuilder().build(ALIYUN_OSS_ENDPOINT,ALIYUN_OSS_ACCESSKEYID,ALIYUN_OSS_ACCESSKEYSECRET);
    }

    public CommonReturnType uploadFile(MultipartFile file){
        OSS oss = getOssClient();
        String objectName;
        try {
            Date date = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat format2 = new SimpleDateFormat("hhmmss");
            String oriName = file.getOriginalFilename();
            String suffix = oriName.substring(oriName.indexOf('.'));
            String randString = RandomStringUtils.randomAlphanumeric(5);
            objectName = "files/"+format1.format(date)+"/"+format2.format(date)+randString+suffix;
            oss.putObject(bucketName,objectName,file.getInputStream());
        } catch (Exception e){
            e.printStackTrace();
            return CommonReturnType.create("上传文件时出错");
        }finally {
            oss.shutdown();
        }

        return CommonReturnType.create("https://"+bucketName+"."+endpoint+"/"+objectName);
    }
}
