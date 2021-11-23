package com.example.service;

import cn.hutool.core.collection.CollUtil;
import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.entity.Score;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService extends ServiceImpl<ScoreMapper, Score> {

    @Resource
    private ScoreMapper scoreMapper;

//    public List<Score> getById(Integer studentid){
//        List<Score> scores=new ArrayList<>();
//        for (Score score: scores) {
//            if (CollUtil.isNotEmpty(r.getPermission())) {
//                for (Object permissionId : r.getPermission()) {
//                    Permission permission = getById((int) permissionId);
//                    if (permissions.stream().noneMatch(p -> p.getPath().equals(permission.getPath()))) {
//                        permissions.add(permission);
//                    }
//                }
//            }
//        }
//        return scores;
//    }
//
}
