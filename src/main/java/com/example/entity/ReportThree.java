package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("t_report3")
public class ReportThree extends Model<ReportThree> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private int id;
    private String thestandard;
    private String funcpoint;
    private String otherparam;
    private String theconclusion;
    private int submited;
    private int the_score;
    private int corrected;
}
