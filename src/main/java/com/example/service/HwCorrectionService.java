package com.example.service;

import com.example.entity.HwCorrection;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.HwCorrectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HwCorrectionService extends ServiceImpl<HwCorrectionMapper, HwCorrection> {

    @Resource
    private HwCorrectionMapper hw_correctionMapper;

}