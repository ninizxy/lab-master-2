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
import com.example.service.Report3CorrectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/api/report3Correction")
public class Report3CorrectionController {
    @Resource
    private Report3CorrectionService report3CorrectionService;


    @GetMapping
    public Result<List<Report3Correction>> findAll() {
        return Result.success(report3CorrectionService.list());
    }

    @GetMapping("/{id}")
    public Result<Report3Correction> findById(@PathVariable Long id) {
        return Result.success(report3CorrectionService.getById(id));
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String id,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Report3Correction> query =  Wrappers.<Report3Correction>lambdaQuery().like(Report3Correction::getSubmited,"1").like(Report3Correction::getId, id ).like(Report3Correction::getCorrected, "0").orderByDesc(Report3Correction::getId);
        return Result.success(report3CorrectionService.page(new Page<>(pageNum, pageSize), query));
    }


    @PutMapping
    public Result<?> update(@RequestBody Report3Correction report3Correction) {
        return Result.success(report3CorrectionService.updateById(report3Correction));
    }

}