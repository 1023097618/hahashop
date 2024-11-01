package com.mall.controller;

import com.mall.common.CheckUtil;
import com.mall.common.Result;
import com.mall.common.ResultEnum;
import com.mall.common.ResultUtil;
import com.mall.entity.ImageStorage;
import com.mall.service.ImageStorageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import static com.mall.common.ResultEnum.SUCCESS;
import static com.mall.common.ResultEnum.UNKNOWN_ERROR;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @jakarta.annotation.Resource
    private ImageStorageService imageStorageService;
    @jakarta.annotation.Resource
    private CheckUtil checkUtil;

    @Value("${file.image-dir}") String imageDir;

    private final String baseurl = "https://localhost:8081/storage/fetch/";

    @RequestMapping ("/upload")
    public Result<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {

        if(checkUtil.tookenCheck().getPrivilege() != 1){ResultUtil.error(UNKNOWN_ERROR);}
        //图片名获取（含扩展名）
        String originalFilename = file.getOriginalFilename();
        //key生成
        String key = UUID.randomUUID().toString()+"_"+originalFilename;
        //Storage构造
        ImageStorage imageStorage = new ImageStorage();
        imageStorage.setName(originalFilename);
        imageStorage.setKey(key);
        imageStorage.setSize(file.getSize());
        imageStorage.setType(originalFilename.substring(originalFilename.lastIndexOf(".")));
        imageStorage.setUrl(baseurl + key);
        //数据库存入
        Map<String, Object> data = imageStorageService.store(imageStorage);
        if(data.get("id") != null){
            Files.copy(file.getInputStream(), Paths.get(imageDir).toAbsolutePath().normalize().resolve(key));
        }
        return ResultUtil.success(SUCCESS,data);
    }

    @RequestMapping("/fetch/{key:.+}")
    public ResponseEntity<Resource> fetch(@PathVariable String key) throws MalformedURLException {
        ImageStorage imageStorage = imageStorageService.findByKey(key);
        if (imageStorage == null || key == null) {
            return ResponseEntity.notFound().build();
        }
        if (key.contains("../")) {
            return ResponseEntity.badRequest().build();
        }
        String type = imageStorage.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Path filePath = Paths.get(imageDir).toAbsolutePath().normalize().resolve(key);
        if(!Files.exists(filePath) || !Files.isReadable(filePath)){
            return ResponseEntity.notFound().build();
        }
        Resource file = new UrlResource(filePath.toUri());

        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }



}
