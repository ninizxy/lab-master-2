package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Report3Correction;
import com.example.mapper.Report3CorrectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Report3CorrectionService extends ServiceImpl<Report3CorrectionMapper, Report3Correction> {

    @Resource
    private Report3CorrectionMapper report3CorrectionMapper;

}