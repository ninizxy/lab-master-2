package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

@Data
@TableName("t_score")
public class Score extends Model<Score> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 学生学号 
      */
    private Integer studentid;

    /**
      * 学生姓名 
      */
    private String studentname;

    /**
      * 实验编号 
      */
    private Integer experiment;

    /**
      * 最终得分 
      */
    private Integer score;

    /**
      * 批改教师 
      */
    private String teachername;

    /**
      * 批改时间 
      */
    private Date time;

    /**
      * 教师评语 
      */
    private String comment;

}