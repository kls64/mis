package com.kris.mis.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.mis.Entity.Student;
import com.kris.mis.Mapper.StudentMapper;
import com.kris.mis.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public PageInfo<Student> findAllStudent(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Student> lists= studentMapper.findAllStudent();
        PageInfo<Student> info= new PageInfo<Student>(lists);
        return info;
    }

    @Override
    public Integer addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public Student findStudentByID(Integer stuid) {
        return studentMapper.findStudentByID(stuid);
    }

    @Override
    public Integer updateStudentByID(Student student) {
        return studentMapper.updateStudentByID(student);
    }

    @Override
    public PageInfo<Student> findStudentByClsIDStuName(Integer pageIndex, Integer pageSize, Integer clsid, String stu_name) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Student>lists = studentMapper.findStudentByClsIDStuName(clsid,stu_name);
        PageInfo<Student> info= new PageInfo<Student>(lists);
        return info;
    }

    @Override
    public PageInfo<Student> findStudentByStuName(Integer pageIndex, Integer pageSize, String stu_name) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Student>lists=studentMapper.findStudentByStuName(stu_name);
        PageInfo<Student> info= new PageInfo<Student>(lists);
        return info;
    }


}
