package com.example.service;

import com.example.entity.Report;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ReportMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReportService extends ServiceImpl<ReportMapper, Report> {

    @Resource
    private ReportMapper reportMapper;

}