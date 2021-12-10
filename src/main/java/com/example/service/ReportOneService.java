package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ReportOne;
import com.example.mapper.ReportOneMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportOneService extends ServiceImpl<ReportOneMapper, ReportOne> {

    @Resource
    private ReportOneMapper reportOneMapper;

}