package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("t_report1student")
public class Report extends Model<Report> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private int report_id;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;
    private int the_score;
    private int submited;
    private int corrected;
}

