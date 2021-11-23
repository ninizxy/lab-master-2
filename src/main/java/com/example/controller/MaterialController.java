package com.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.common.Result;
import com.example.entity.Material;
import com.example.service.MaterialService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;


@RestController
@RequestMapping("/api/material")
public class MaterialController {
    @Resource
    private MaterialService materialService;
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
    public Result<?> save(@RequestBody Material material) {
        return Result.success(materialService.save(material));
    }

    @PutMapping
    public Result<?> update(@RequestBody Material material) {
        return Result.success(materialService.updateById(material));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        materialService.removeById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(materialService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Material> query = Wrappers.<Material>lambdaQuery().orderByDesc(Material::getExperimentId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Material::getExperimentId, name);
        }
        return Result.success(materialService.page(new Page<>(pageNum, pageSize), query));
    }

    /**
     * 单文件上传
     */
    @PostMapping("/{experiment}/{number}")
    public Result<String> upload(@PathVariable Integer number,@PathVariable String experiment, MultipartFile file) {
        synchronized (FileController.class) {

            String filePath = System.getProperty("user.dir") + "/src/main/resources/static/file/"+experiment+"/";
            String flag = System.currentTimeMillis() + "";
            String fileName = file.getOriginalFilename();

            //将文件名加入到数据库
            Material material=new Material();
            material.setMaterialName(fileName);
            material.setExperimentId(number);
            material.setMaterialId(flag);
            material.setTime(new Date());
            materialService.save(material);

            try {
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--上传成功");
                Thread.sleep(1L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败");
            }
            return Result.success(flag);
        }
    }

    /**
     * 获取文件
     */
    @GetMapping("/{experiment}/{flag}")
    public void avatarPath(@PathVariable String flag, @PathVariable String experiment,HttpServletResponse response) {

        String basePath = System.getProperty("user.dir") + "/src/main/resources/static/file/"+experiment+"/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                // 设置响应头，控制浏览器下载该文件
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
                //设置返回类型
                response.setContentType("application/octet-stream");
                // 读取要下载的文件，保存到文件输入流

                byte[] bytes = FileUtil.readBytes(basePath + avatar);
                // 创建输出流
                OutputStream os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
                System.out.println("文件下载成功");

            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/{experiment}/{materialId}")
    public void delFile(@PathVariable String experiment,@PathVariable String materialId) {
        String basePath = System.getProperty("user.dir") + "/src/main/resources/static/file/"+experiment+"/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        if(!CollectionUtils.isEmpty(fileNames)){
            String filename = fileNames.stream().filter(name -> name.contains(materialId)).findAny().orElse("");
            FileUtil.del(basePath + filename);
            System.out.println("删除文件" + filename + "成功");
        }
    }
}
