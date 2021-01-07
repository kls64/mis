package com.kris.mis.Service;

import com.github.pagehelper.PageInfo;
import com.kris.mis.Entity.Student;
public interface StudentService {
    PageInfo<Student> findAllStudent(Integer pageIndex, Integer pageSize);

    Integer addStudent(Student student);

    Student findStudentByID(Integer stuid);
    Integer updateStudentByID(Student student);
    PageInfo<Student>findStudentByClsIDStuName(Integer pageIndex,Integer pageSize,Integer clsid,String stu_name);
    PageInfo<Student>findStudentByStuName(Integer pageIndex,Integer pageSize,String stu_name);
}
