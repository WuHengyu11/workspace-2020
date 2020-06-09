package cn.wolfcode.managesystem.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class FileUploadUtil {
	
	public static Map<String,String> upload(HttpServletRequest req){
		Map<String, String> map = new HashMap<>();
		try {
			DiskFileItemFactory factoy = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factoy);
			//设置单个文件大小
			upload.setFileSizeMax(1024*80);
			
			//设置整个请求中的数据大小
			//upload.setSizeMax(sizeMax);
			List<FileItem> items = upload.parseRequest(req);
			for (FileItem fileItem : items) {
				if(fileItem.isFormField()){
					//普通表单元素的数据
					String value = fileItem.getString("UTF-8");
					map.put(fileItem.getFieldName(), value);
				}else{
					if(!fileItem.getContentType().startsWith("image/")){
						throw new LogicException("您的上传文件类型有问题");
					}
					//上传控件的数据
					//使用UUID生成一个不重复的文件名
					String name = UUID.randomUUID().toString();
					//获取文件扩展名
					String extension = FilenameUtils.getExtension(fileItem.getName());
					String filename = name + "." + extension;
					//动态获取到项目中指定目录在磁盘中的路径
					String realPath = req.getServletContext().getRealPath("/upload");
					//将文件保存到磁盘中
					fileItem.write(new File(realPath,filename));
					//将图片存储的路径存储到map集合中
					map.put("img", "/upload/"+filename);
				}
			}
		} catch (FileSizeLimitExceededException e) {
			throw new LogicException("单个上传文件大小超过了80kb",e);
		} catch (LogicException e) {
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
		return map;
	}
}
