package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("t_report2")
public class Report2Correction extends Model<Report2Correction>{
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private int id;
    private int ilf;
    private int elf;
    private int ei;
    private int eo;
    private int theeq;
    private float cf;
    private int result1;
    private int result2bef;
    private int result2aft;
    private int submited;
    private int the_score;
    private int corrected;
}
