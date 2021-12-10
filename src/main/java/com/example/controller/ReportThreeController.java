package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;

import com.example.entity.ReportThree;
import com.example.service.ReportThreeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.common.Result.*;


@RestController
@RequestMapping("/api/reportThree/")
public class ReportThreeController {
    @Resource
    private ReportThreeService reportThreeService;

    @PostMapping
    public Result<?> save(@RequestBody ReportThree reportThree) {
        return success(reportThreeService.save(reportThree));
    }

    @PutMapping
    public Result<?> update(@RequestBody ReportThree reportThree) {
        return success(reportThreeService.updateById(reportThree));
    }


    @GetMapping
    public Result<List<ReportThree>> findAll() {
        return success(reportThreeService.list());
    }

    @GetMapping("/{id}")
    public Result<ReportThree> findByStudentId(@PathVariable Long id) {
        return success(reportThreeService.getById(id));
    }


}