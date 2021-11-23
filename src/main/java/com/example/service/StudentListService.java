package com.example.service;

import com.example.entity.StudentList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.StudentListMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentListService extends ServiceImpl<StudentListMapper, StudentList> {

    @Resource
    private StudentListMapper studentListMapper;

    //得到班级名单
    public List<StudentList> getByClass(int classId){
        List<StudentList>all=StudentListService.this.list();
        List<StudentList>classList=new ArrayList<>();
        for(StudentList stu:all){
            if(stu.getClassId()==classId){
                classList.add(stu);
            }
        }
        return classList;
    }

    //查找学生
    public List<StudentList> searchStudent(String search){
        List<StudentList>all=StudentListService.this.list();
        List<StudentList>student=new ArrayList<>();
        for(StudentList stu:all){
            if(Objects.equals(stu.getStudentName(), search) || Objects.equals(stu.getStudentId(), Integer.valueOf(search))){
                student.add(stu);
            }
        }
        return student;
    }



}
