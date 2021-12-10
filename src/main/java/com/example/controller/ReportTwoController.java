package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;

import com.example.entity.ReportTwo;
import com.example.service.ReportTwoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.common.Result.*;


@RestController
@RequestMapping("/api/reportTwo/")
public class ReportTwoController {
    @Resource
    private ReportTwoService reportTwoService;

    @PostMapping
    public Result<?> save(@RequestBody ReportTwo reportTwo) {
        return success(reportTwoService.save(reportTwo));
    }

    @PutMapping
    public Result<?> update(@RequestBody ReportTwo reportTwo) {
        return success(reportTwoService.updateById(reportTwo));
    }


    @GetMapping
    public Result<List<ReportTwo>> findAll() {
        return success(reportTwoService.list());
    }

    @GetMapping("/{id}")
    public Result<ReportTwo> findByStudentId(@PathVariable Long id) {
        return success(reportTwoService.getById(id));
    }


}