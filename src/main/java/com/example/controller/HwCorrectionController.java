package com.example.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.service.HwCorrectionService;
import com.example.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/hw_correction")
public class HwCorrectionController {
    @Resource
    private HwCorrectionService hw_correctionService;


    @GetMapping
    public Result<List<HwCorrection>> findAll() {
        return Result.success(hw_correctionService.list());
    }

    @GetMapping("/{id}")
    public Result<HwCorrection> findById(@PathVariable Long id) {
        return Result.success(hw_correctionService.getById(id));
    }
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<HwCorrection> query =  Wrappers.<HwCorrection>lambdaQuery().like(HwCorrection::getSubmited,"1").like(HwCorrection::getId, name).like(HwCorrection::getCorrected, "0").orderByDesc(HwCorrection::getId);
        return Result.success(hw_correctionService.page(new Page<>(pageNum, pageSize), query));
    }

//    @PostMapping
//    public Result<?> save(@RequestBody HwCorrection hw_correction) {
//        return Result.success(hw_correctionService.save(hw_correction));
//    }

    @PutMapping
    public Result<?> update(@RequestBody HwCorrection hw_correction) {
        return Result.success(hw_correctionService.updateById(hw_correction));
    }

}
