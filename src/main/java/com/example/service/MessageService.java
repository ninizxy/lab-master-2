package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Message;
import com.example.entity.User;
import com.example.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private UserService userService;

    public List<Message> findByForeign() {
        LambdaQueryWrapper<Message> queryWrapper = Wrappers.<Message>lambdaQuery().eq(Message::getForeignId, 0).orderByDesc(Message::getId);
        List<Message> list = list(queryWrapper);
        for (Message Message : list) {
            User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, Message.getUsername()));
            Message.setAvatar("http://localhost:9999/files/" + one.getAvatar());
            Long parentId = Message.getParentId();
            list.stream().filter(c -> c.getId().equals(parentId)).findFirst().ifPresent(Message::setParentMessage);
        }
        return list;
    }

}
