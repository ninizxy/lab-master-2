package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.db.AbstractDb;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.Result;
import com.example.entity.*;
import com.example.service.Report1CorrectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/api/report1Correction")
public class Report1CorrectionController {
    @Resource
    private Report1CorrectionService report1CorrectionService;


    @GetMapping
    public Result<List<Report1Correction>> findAll() {
        return Result.success(report1CorrectionService.list());
    }

    @GetMapping("/{id}")
    public Result<Report1Correction> findById(@PathVariable Long id) {
        return Result.success(report1CorrectionService.getById(id));
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String id,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Report1Correction> query =  Wrappers.<Report1Correction>lambdaQuery().like(Report1Correction::getSubmited,"1").like(Report1Correction::getId, id ).like(Report1Correction::getCorrected, "0").orderByDesc(Report1Correction::getId);
        return Result.success(report1CorrectionService.page(new Page<>(pageNum, pageSize), query));
    }


    @PutMapping
    public Result<?> update(@RequestBody Report1Correction report1Correction) {
        return Result.success(report1CorrectionService.updateById(report1Correction));
    }

}