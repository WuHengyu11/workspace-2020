package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.util.JsonResult;
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
    public Object uploadImg(MultipartFile pic) throws Exception {
        //将这个pic对象传入到阿里云的oss服务中
        String imageUrl = UploadUtil.uploadAli(pic);
        return imageUrl;
    }

    @RequestMapping("/uploadImg_ck")
    @ResponseBody
    public Map<String,Object> uploadImg_ck(MultipartFile upload,String Module){
        HashMap<String, Object> map = new HashMap<>();
        String imagePath = null;
        if (upload != null && upload.getSize() > 0){
            try {
                //图片保存,返回路径
                imagePath = UploadUtil.uploadAli(upload);
                //表示保存成功
                map.put("uploaded",1);
                map.put("url",imagePath);
            }catch (Exception e){
                e.printStackTrace();
                map.put("uploaded",0);
                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("message",e.getMessage());
                map1.put("err",map1);
            }
        }
        return map;
    }
}
