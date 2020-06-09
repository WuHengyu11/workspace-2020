package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @RequestMapping("/coverImageUpload")
    @ResponseBody
    public Object coverImageUpload(MultipartFile pic) throws Exception {
        //将这个pic对象传入到阿里云的oss服务中
        String imageUrl = UploadUtil.uploadAli(pic);
        return imageUrl;
    }
}
