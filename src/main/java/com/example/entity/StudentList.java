package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("t_studentlist")
public class StudentList extends Model<StudentList> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 学生学号 
      */
    private Integer studentId;

    /**
      * 学生姓名 
      */
    private String studentName;

    /**
      * 班级班号 
      */
    private Integer classId;

}