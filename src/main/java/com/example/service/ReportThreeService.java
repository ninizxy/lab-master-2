package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ReportThree;
import com.example.mapper.ReportThreeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportThreeService extends ServiceImpl<ReportThreeMapper, ReportThree> {

    @Resource
    private ReportThreeMapper reportThreeMapper;

}