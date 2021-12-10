package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;

import com.example.entity.ReportOne;
import com.example.entity.StudentList;
import com.example.service.ReportOneService;
import com.example.service.StudentListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.common.Result.*;


@RestController
@RequestMapping("/api/reportOne/")
public class ReportOneController {
    @Resource
    private ReportOneService reportOneService;

    @PostMapping
    public Result<?> save(@RequestBody ReportOne reportOne) {
        return success(reportOneService.save(reportOne));
    }

    @PutMapping
    public Result<?> update(@RequestBody ReportOne reportOne) {
        return success(reportOneService.updateById(reportOne));
    }


    @GetMapping
    public Result<List<ReportOne>> findAll() {
        return success(reportOneService.list());
    }

    @GetMapping("/{id}")
    public Result<ReportOne> findByStudentId(@PathVariable Long id) {
        return success(reportOneService.getById(id));
    }


}