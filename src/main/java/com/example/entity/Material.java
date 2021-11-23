package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

@Data
@TableName("t_material")
public class Material extends Model<Material> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 实验编号 
      */
    private Integer experimentId;

    /**
      * 材料名称 
      */
    private String materialName;

    /**
      * 材料编号 
      */
    private String materialId;

    /**
      * 上传时间 
      */
    private Date time;

}