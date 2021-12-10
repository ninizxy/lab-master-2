package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ReportTwo;
import com.example.mapper.ReportTwoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportTwoService extends ServiceImpl<ReportTwoMapper, ReportTwo> {

    @Resource
    private ReportTwoMapper reportTwoMapper;

}