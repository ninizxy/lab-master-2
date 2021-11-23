package com.example.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Message;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Resource
    private MessageService messageService;
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
    public Result<?> save(@RequestBody Message Message) {
        Message.setUsername(getUser().getUsername());
        Message.setTime(DateUtil.formatDateTime(new Date()));
        return Result.success(messageService.save(Message));
    }

    @PutMapping
    public Result<?> update(@RequestBody Message Message) {
        return Result.success(messageService.updateById(Message));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        messageService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(messageService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(messageService.list());
    }

    @GetMapping("/foreign")
    public Result<?> findByForeign() {
        return Result.success(messageService.findByForeign());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Message> query = Wrappers.<Message>lambdaQuery().like(Message::getContent, name).orderByDesc(Message::getId);
        return Result.success(messageService.page(new Page<>(pageNum, pageSize), query));
    }

}
