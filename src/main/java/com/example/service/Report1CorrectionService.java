package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Report1Correction;
import com.example.mapper.Report1CorrectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Report1CorrectionService extends ServiceImpl<Report1CorrectionMapper, Report1Correction> {

    @Resource
    private Report1CorrectionMapper report1CorrectionMapper;

}