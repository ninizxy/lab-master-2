package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Notice;
import com.example.entity.Report;

import com.example.service.ReportService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/report/")
public class ReportController {
    @Resource
    private ReportService reportService;

    @PostMapping
    public Result<?> save(@RequestBody Report report) {
        return Result.success(reportService.save(report));
    }

    @PutMapping
    public Result<?> update(@RequestBody Report report) {
        return Result.success(reportService.updateById(report));
    }


    @GetMapping
    public Result<List<Report>> findAll() {
        return Result.success(reportService.list());
    }

    @GetMapping("/{id}")
    public Result<Report> findById(@PathVariable Long id) {
        return Result.success(reportService.getById(id));
    }
}
