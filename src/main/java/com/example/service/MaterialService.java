package com.example.service;

import com.example.entity.Material;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.StudentList;
import com.example.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MaterialService extends ServiceImpl<MaterialMapper, Material> {

    @Resource
    private MaterialMapper materialMapper;

    //根据材料ID删除记录
    public void deleteMaterial(String materialId){
        List<Material>all=MaterialService.this.list();
        for(Material material:all) {
            if (material.getMaterialId() == materialId) {
                MaterialService.this.removeById(material.getId());
            }
        }
    }
}
