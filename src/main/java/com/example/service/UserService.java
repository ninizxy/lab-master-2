package com.example.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    public User login(User user) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        User one = getOne(queryWrapper);
        if (one == null) {
            throw new CustomException("-1", "账号或密码错误");
        }
        one.setPermission(getPermissions(one.getId()));
        return one;
    }

    public User register(User user) {
        User one = getOne((Wrappers.<User>lambdaQuery().eq(User::getEmail, user.getEmail())));
        if (one != null) {
            throw new CustomException("-1", "用户已注册");
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        user.setRole(CollUtil.newArrayList(2L));  // 默认普通用户角色
        save(user);
        return getOne((Wrappers.<User>lambdaQuery().eq(User::getEmail, user.getEmail())));
    }

    /**
     * 设置权限
     * @param userId
     * @return
     */
    public List<Permission> getPermissions(Long userId) {
        User user = getById(userId);
        List<Permission> permissions = new ArrayList<>();
        List<Long> role = user.getRole();
        if (role != null) {
            for (Object roleId : role) {
                Role realRole = roleService.getById((int) roleId);
                if (CollUtil.isNotEmpty(realRole.getPermission())) {
                    for (Object permissionId : realRole.getPermission()) {
                        Permission permission = permissionService.getById((int) permissionId);
                        if (permission != null && permissions.stream().noneMatch(p -> p.getPath().equals(permission.getPath()))) {
                            permissions.add(permission);
                        }
                    }
                }
            }
            user.setPermission(permissions);
        }
        return permissions;
    }

    public User getbyUsername(String username) {
        User one = getOne((Wrappers.<User>lambdaQuery().eq(User::getUsername, username)));
        one.setPermission(getPermissions(one.getId()));
        return one;
    }

    public List<User> getByRole(int roleNum) {
        List<User> users = UserService.this.list();
        List<User> teachers = new ArrayList<>();
        if(users!=null) {
            for (User user : users) {
                List<Long> role = user.getRole();
                for (Object roleId : role) {
                    if(roleNum!=3){
                        if ((int)roleId == 1 || (int)roleId == 2) {
                            teachers.add(user);
                        }
                    }
                    else {
                        if ((int) roleId == roleNum) {
                            teachers.add(user);
                        }
                    }
                }
            }
        }
        return teachers;
    }
}
