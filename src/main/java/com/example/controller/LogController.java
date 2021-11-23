package com.example.controller;

import com.example.common.Result;
import com.example.entity.Log;
import com.example.service.LogService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;
import cn.hutool.core.util.StrUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/log")
public class LogController {
    @Resource
    private LogService logService;
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
    public Result<?> save(@RequestBody Log log) {
        return Result.success(logService.save(log));
    }

    @PutMapping
    public Result<?> update(@RequestBody Log log) {
        return Result.success(logService.updateById(log));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        logService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(logService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(logService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Log> query = Wrappers.<Log>lambdaQuery().orderByDesc(Log::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Log::getContent, name);
        }
        return Result.success(logService.page(new Page<>(pageNum, pageSize), query));
    }

}
