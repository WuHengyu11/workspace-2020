package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {


    @RequestMapping("/coverImageUpload")
    @ResponseBody
    public Object uploadImg( MultipartFile pic){
        //将pic数据存到项目文件中
        //通过oss提供api将图片数据上传到阿里云的OSS服务器中(我自己的buket)
        String url = null;
        try {
            url = UploadUtil.uploadAli(pic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}
