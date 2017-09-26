package com.spring.controller;

import com.spring.dto.PageResponse;
import com.spring.model.GoddessInfo;
import com.spring.service.GoddessInfoService;
import org.apache.poi.ss.usermodel.Workbook;
//import org.jeecgframework.poi.excel.ExcelExportUtil;
//import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by dalp on 2017/8/7.
 */
@Controller
public class GoddessInfoController {

    @Autowired
    private GoddessInfoService goddessInfoService;

    @RequestMapping(value = "/goddess", method = RequestMethod.GET)
    public String getGoddessInfo(Model model){
        PageResponse<Object> pr = goddessInfoService.getGoddessInfoList();
        model.addAttribute("goddessList", pr.getContent());
        return "goddess";
    }

    @GetMapping("/delete")
    public String deleteGoddess(Long id){
//        MessageResponse<Object> mr =
                goddessInfoService.deleteGoddess(id);
//        model.addAttribute("msg",mr.getMessage());
        return "redirect:/goddess";
    }

    @GetMapping("/insert")
    public String toInsertPage(Model model, GoddessInfo goddessInfo){
        model.addAttribute("goddessInfo", goddessInfo);
        return "insert";
    }

    @PostMapping("/add")
    public String insertGoddess(GoddessInfo goddessInfo, @RequestParam("fileName") MultipartFile file) {
        if(file.isEmpty()){
            return "上传文件为空";
        }
        String fileName = file.getOriginalFilename(); //获取上传的文件名
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size); //xzq.jpg-->40696

        String path = "F:\\test" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        goddessInfo.setPrimaryPhoto(String.valueOf(dest));
        goddessInfoService.insertGoddessInfo(goddessInfo);
        return "redirect:/goddess";
    }

    /**
     * 文件的下载
     * @param response
     * @param filename
     * @return
     */
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response, @RequestParam("fileName") String filename){
        File file = new File(filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bis != null){
                        bis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if ( fis != null){
                        fis.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("----------file download " + filename);
        }

        return null;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/")
    public String index(){
        return "home";
    }

//    @RequestMapping("/goddess")
//    @ResponseBody
//    public String hello(){
//        return "hello admin";
//    }

//    // 下载execl文档
//    @RequestMapping("/download")
//    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 告诉浏览器用什么软件可以打开此文件
//        response.setHeader("content-Type", "application/vnd.ms-excel");
//        // 下载文件的默认名称
//        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
//        List<GoddessInfo> list = (List<GoddessInfo>)  goddessInfoService.getGoddessInfoList().getContent();
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), GoddessInfo.class, list);
//        workbook.write(response.getOutputStream());
//    }

}
