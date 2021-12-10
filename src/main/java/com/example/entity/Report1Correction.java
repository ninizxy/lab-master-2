package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("t_report1")
public class Report1Correction extends Model<Report1Correction> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private int id;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int num7;
    private int num8;
    private int num9;
    private int num10;
    private int num11;
    private int num12;
    private int num13;
    private int num14;
    private int num15;
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private int score5;
    private int score6;
    private int score7;
    private int score8;
    private int score9;
    private int score10;
    private int score11;
    private int score12;
    private int score13;
    private int score14;
    private int ufp;
    private int tdi;
    private int submited;
    private int the_score;
    private int corrected;
}
