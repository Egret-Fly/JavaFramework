package cn.itcast.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserColler {

    /**
     * 文件上传
     * @return
     */
    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");

        //使用fileupload组件完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");

        //判断，该路径是否存在
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }

        //解析request对象,获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //进行判断，判断当前fileitem是否是上传文件项
            if(fileItem.isFormField()){
                //说明普通表单项
            }else {
                //说明上传文件项
                //获取上传文件名称
                String filename = fileItem.getName();
                //把文件名称设置成唯一值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename =uuid +"_" + filename;
                //完成文件上传
                fileItem.write(new File(path,filename));
                //删除临时文件
                fileItem.delete();
            }

        }

        return "success";
    }
}
