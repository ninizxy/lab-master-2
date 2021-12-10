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
import com.example.service.Report2CorrectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/api/report2Correction")
public class Report2CorrectionController {
    @Resource
    private Report2CorrectionService report2CorrectionService;


    @GetMapping
    public Result<List<Report2Correction>> findAll() {
        return Result.success(report2CorrectionService.list());
    }

    @GetMapping("/{id}")
    public Result<Report2Correction> findById(@PathVariable Long id) {
        return Result.success(report2CorrectionService.getById(id));
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String id,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Report2Correction> query =  Wrappers.<Report2Correction>lambdaQuery().like(Report2Correction::getSubmited,"1").like(Report2Correction::getId, id ).like(Report2Correction::getCorrected, "0").orderByDesc(Report2Correction::getId);
        return Result.success(report2CorrectionService.page(new Page<>(pageNum, pageSize), query));
    }


    @PutMapping
    public Result<?> update(@RequestBody Report2Correction report2Correction) {
        return Result.success(report2CorrectionService.updateById(report2Correction));
    }

}