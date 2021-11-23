package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.TeachClass;
import com.example.service.TeachClassService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;
import cn.hutool.core.util.StrUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/teachclass")
public class TeachClassController {
    @Resource
    private TeachClassService teachClassService;
    @Resource
    private HttpServletRequest request;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }

    @PostMapping
    public Result<?> save(@RequestBody TeachClass teachClass) {
        return Result.success(teachClassService.save(teachClass));
    }

    @PutMapping
    public Result<?> update(@RequestBody TeachClass teachClass) {
        return Result.success(teachClassService.updateById(teachClass));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        teachClassService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(teachClassService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(teachClassService.list());
    }

    //寻找某一老师的班级
    @GetMapping("/myclass/{teacherId}")
    public Result<?> findTeacherClass(@PathVariable int teacherId) {
        return Result.success(teachClassService.oneTeacherClass(teacherId));
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<TeachClass> query = Wrappers.<TeachClass>lambdaQuery().orderByDesc(TeachClass::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(TeachClass::getId, name);
        }
        return Result.success(teachClassService.page(new Page<>(pageNum, pageSize), query));
    }

}
