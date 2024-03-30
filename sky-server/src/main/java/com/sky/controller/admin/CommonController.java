package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("admin/common")
@Api(tags = "通用接口")
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传到阿里云oss
     */

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result upload(MultipartFile file) throws IOException {
        log.info("文件上传：{}", file);
        //获取原始文件后缀
        String[] strings = file.getOriginalFilename().split("\\.");
        String extension = strings[strings.length - 1];
        String objectName = UUID.randomUUID().toString() + "." + extension;
        String filePath = aliOssUtil.upload(file.getBytes(), objectName);
        return Result.success(filePath);
    }

    @GetMapping("delete")
    public Result delete(String file) throws Exception {
        aliOssUtil.delete(file);
        return Result.success();
    }

}
