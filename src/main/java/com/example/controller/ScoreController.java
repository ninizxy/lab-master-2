package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Score;
import com.example.service.ScoreService;
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
@RequestMapping("/api/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;
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
    public Result<?> save(@RequestBody Score score) {
        return Result.success(scoreService.save(score));
    }

    @PutMapping
    public Result<?> update(@RequestBody Score score) {
        return Result.success(scoreService.updateById(score));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        scoreService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(scoreService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(scoreService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Score> query = Wrappers.<Score>lambdaQuery().orderByDesc(Score::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Score::getStudentname, name);
        }
        return Result.success(scoreService.page(new Page<>(pageNum, pageSize), query));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Score> all = scoreService.list();
        for (Score obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("学生学号", obj.getStudentid());
            row.put("学生姓名", obj.getStudentname());
            row.put("实验编号", obj.getExperiment());
            row.put("最终得分", obj.getScore());
            row.put("批改教师", obj.getTeachername());
            row.put("批改时间", obj.getTime());
            row.put("教师评语", obj.getComment());
            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("学生成绩单", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

    @GetMapping("/upload/{fileId}")
    public Result<?> upload(@PathVariable String fileId) {
        String basePath = System.getProperty("user.dir") + "/src/main/resources/static/file/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String file = fileNames.stream().filter(name -> name.contains(fileId)).findAny().orElse("");
        List<List<Object>> lists = ExcelUtil.getReader(basePath + file).read(1);
        List<Score> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            Score obj = new Score();
            obj.setStudentid(Integer.valueOf((String) row.get(1)));
            obj.setStudentname((String) row.get(2));
            obj.setExperiment(Integer.valueOf((String) row.get(3)));
            obj.setScore(Integer.valueOf((String) row.get(4)));
            obj.setTeachername((String) row.get(5));
            obj.setTime(DateUtil.parseDateTime((String) row.get(6)));
            obj.setComment((String) row.get(7));

            saveList.add(obj);
        }
        scoreService.saveBatch(saveList);
        return Result.success();
    }

}
