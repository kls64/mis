package com.kris.mis.Controller;

import com.github.pagehelper.PageInfo;
import com.kris.mis.Entity.Classinfo;
import com.kris.mis.Entity.Student;
import com.kris.mis.Service.ClassinfoService;
import com.kris.mis.Service.StudentService;
import com.kris.mis.Utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassinfoService classinfoService;

//    @GetMapping("/backend")
//    public String getAllStudents(Model model){
//        List<Student> stuLists= studentService.findAllStudent();
//        List<Classinfo>lists=classinfoService.findAllClassinfo();
//        model.addAttribute("clsinfo",lists);
//        model.addAttribute("stus",stuLists);
//        return "main";
//    }

//    @PostMapping("/backend")
    @RequestMapping("/backend")
    public String getStudentByIDName(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                     @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize,
                                     @RequestParam(value = "clsid",defaultValue = "0") Integer clsid,
                                     @RequestParam(value = "stuName",defaultValue = "") String stuName,
                                     Model model){
        PageInfo<Student>listInfo=null;
        if(clsid==0&&stuName.isEmpty())
            listInfo=studentService.findAllStudent(pageIndex,pageSize);
        else if(clsid==0&&!stuName.isEmpty())
            listInfo=studentService.findStudentByStuName(pageIndex,pageSize,stuName);
        else
            listInfo = studentService.findStudentByClsIDStuName(pageIndex,pageSize,clsid,stuName);
        List<Classinfo> classinfos=classinfoService.findAllClassinfo();
        model.addAttribute("stus",listInfo);
        model.addAttribute("clsinfo",classinfos);

        model.addAttribute("clsid",clsid);
        model.addAttribute("stuname",stuName);
        return "main";
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        List<Classinfo>lists=classinfoService.findAllClassinfo();
        model.addAttribute("clsinfo",lists);
        return "index";
    }

    @PostMapping("/add")
    public String addStudent(Student student, @RequestParam("filepic") MultipartFile file){
        //1.保存文件到硬盘上 利用工具类
        String fileName= file.getOriginalFilename();
        String filePath= FileUtil.getUpLoadFilePath();
        fileName = System.currentTimeMillis()+fileName;
        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        //2.保存文件名称到数据里
        student.setStu_image_url(fileName);
        studentService.addStudent(student);
        return "redirect:/stu/backend";
    }
    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Integer stuid,Model model){
        Student student = studentService.findStudentByID(stuid);
        List<Classinfo>lists=classinfoService.findAllClassinfo();
        model.addAttribute("clsinfo",lists);
        model.addAttribute("stu",student);
        return "update";

    }

    @PostMapping("/update")
    public String updateStudent(Student student,@RequestParam("filepic") MultipartFile file){
        //1.保存文件到硬盘上 利用工具类
        String fileName= file.getOriginalFilename();
        String filePath= FileUtil.getUpLoadFilePath();
        fileName = System.currentTimeMillis()+fileName;
        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        //2.保存文件名称到数据里
        student.setStu_image_url(fileName);
        studentService.updateStudentByID(student);
        return "redirect:/stu/backend";
    }



}
