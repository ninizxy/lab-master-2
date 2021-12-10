package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Report2Correction;
import com.example.mapper.Report2CorrectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Report2CorrectionService extends ServiceImpl<Report2CorrectionMapper, Report2Correction> {

    @Resource
    private Report2CorrectionMapper report2CorrectionMapper;

}