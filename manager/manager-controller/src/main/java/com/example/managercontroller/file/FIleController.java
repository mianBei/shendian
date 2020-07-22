package com.example.managercontroller.file;

import com.example.common.superCon.SuperController;
import com.example.common.util.FileUtil;
import com.example.common.util.JSONUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FIleController extends SuperController {

    @Value("${pic.url_prefix}")
    private String urlPrefix;
    @Value("${pic.storage_path}")
    private String basePath;
    /**
     * 上传文件
     * @param request
     * @param response
     */
    @RequestMapping("/uploadPic.htm")
    public void uploadPic(HttpServletRequest request, HttpServletResponse response){
        try{
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            List<MultipartFile> files = multipartRequest.getFiles("file");
            String paths="";
            String isUp="";
            String callback="";
            if (files.size()==0){
                files = multipartRequest.getFiles("upload");
                isUp="upload";
                callback = request.getParameter("CKEditorFuncNum");
            }
            if (files.size()>0&&files!=null){
                HashMap<String,String> resultMap=new HashMap<String,String>();
                HashMap<String,Object> infoMap=new HashMap<String,Object>();
                for (MultipartFile file:files) {
                    String to = request.getParameter("to");//区分是哪个项目的文件图片
                    if(!file.isEmpty()){
                        Map<String,String> pathMap= FileUtil.getFilePath(to,"img",basePath,urlPrefix);
                        String filePath = pathMap.get("filePath");
                        String path = pathMap.get("path");
                        String filename=file.getOriginalFilename();
                        String extension = FileUtil.getExtension(filename);
                        if(extension.isEmpty()){
                            extension=".jpg";
                        }
                        String newfilename=FileUtil.defineUploadFileNameUUID();
                        String newFileName=newfilename+extension;

                        //转存文件
                        File newfile=new File(filePath+"/"+newFileName);
                        file.transferTo(newfile);
                        if(extension.toUpperCase().equals(".JPG")||
                                extension.toUpperCase().equals(".JPEG")||
                                extension.toUpperCase().equals(".TIFF")||
                                extension.toUpperCase().equals(".PNG")||
                                extension.toUpperCase().equals(".BMP")||
                                extension.toUpperCase().equals(".GIF")){
                            paths+=path+"/"+newFileName+",";
                            if ("upload".equals(isUp)){
                                paths = "/imgTest"+paths.substring(0, paths.length() - 1);

                                PrintWriter out = response.getWriter();
                                out.println("<script type=\"text/javascript\">");
                                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                                        + ",'" +paths+ "','')");
                                out.println("</script>");
                            }
                        }else{
                            newfile.delete();
                            infoMap.put("status", -1);
                            infoMap.put("info","文件格式不正确");
                            outJsonForMap(infoMap, response);
                        }
                    }
                }
                if (!"upload".equals(isUp)) {
                    paths = paths.substring(0, paths.length() - 1);
                    resultMap.put("path", paths);
                    infoMap.put("status", 1);
                    infoMap.put("data", resultMap);
                    outJsonForMap(infoMap, response);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            HashMap<String,Object> infoMap=new HashMap<String,Object>();
            infoMap.put("status", -1);
            infoMap.put("info","上传失败");
            outJsonForMap(infoMap, response);
        }
    }
}
