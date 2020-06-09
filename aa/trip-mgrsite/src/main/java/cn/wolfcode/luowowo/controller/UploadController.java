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


    @RequestMapping("/uploadImg")
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

    @RequestMapping("/uploadImg_ck")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile upload, String module){
        Map<String, Object> map = new HashMap<String, Object>();
        String imagePath= null;
        if(upload != null && upload.getSize() > 0){
            try {
                //图片保存, 返回路径
                imagePath =  UploadUtil.uploadAli(upload);
                //表示保存成功
                map.put("uploaded", 1);
                map.put("url",imagePath);

            }catch (Exception e){
                e.printStackTrace();
                map.put("uploaded", 0);
                Map<String, Object> mm = new HashMap<String, Object>();
                mm.put("message",e.getMessage() );
                map.put("error", mm);
            }
        }
        return map;
    }
}
