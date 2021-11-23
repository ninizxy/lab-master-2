package com.example.service;

import com.example.entity.StudentList;
import com.example.entity.TeachClass;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.TeachClassMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeachClassService extends ServiceImpl<TeachClassMapper, TeachClass> {

    @Resource
    private TeachClassMapper teachClassMapper;

    //得到班级名单
    public List<TeachClass> oneTeacherClass(int teacherId){
        List<TeachClass>all=TeachClassService.this.list();
        List<TeachClass>myClass=new ArrayList<>();
        for(TeachClass cla:all){
            if(cla.getTeacherId()==teacherId){
                myClass.add(cla);
            }
        }
        return myClass;
    }


}
