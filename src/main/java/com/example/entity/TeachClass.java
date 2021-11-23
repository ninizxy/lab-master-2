package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("t_class")
public class TeachClass extends Model<TeachClass> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 班级班号 
      */
    private Integer classId;

    /**
      * 老师工号 
      */
    private Integer teacherId;

    /**
      * 助教学号 
      */
    private Integer assistantId;

}