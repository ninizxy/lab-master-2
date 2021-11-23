package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Notice;
import com.example.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @PostMapping
    public Result<?> save(@RequestBody Notice notice) {
        notice.setTime(DateUtil.formatDateTime(new Date()));
        return Result.success(noticeService.save(notice));
    }

    @PutMapping
    public Result<?> update(@RequestBody Notice notice) {
        return Result.success(noticeService.updateById(notice));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        noticeService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Notice> findById(@PathVariable Long id) {
        return Result.success(noticeService.getById(id));
    }

    @GetMapping
    public Result<List<Notice>> findAll() {
        return Result.success(noticeService.list());
    }

    @GetMapping("/page")
    public Result<IPage<Notice>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                          @RequestParam(required = false, defaultValue = "") String content,
                                          @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        //相当于sql语句： select * from t_notice where name like %#{name}%
        LambdaQueryWrapper<Notice> queryWrapper = Wrappers.<Notice>lambdaQuery().like(Notice::getTitle, name);
        return Result.success(noticeService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
