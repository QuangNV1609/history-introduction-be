package com.uet.quangnv.api;

import com.uet.quangnv.dto.FileDto;
import com.uet.quangnv.entities.File;
import com.uet.quangnv.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/file")
@Slf4j
public class FileApi {
    @Autowired
    private FileService fileService;

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        FileDto fileDto = fileService.getFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileDto.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + fileDto.getFileName())
                .body(new ByteArrayResource(fileDto.getData()));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<File> save(@RequestParam("file") MultipartFile file,
                                     @RequestParam("title") String title,
                                     @RequestParam("type") Integer type,
                                     @RequestParam("referenceType") Integer referenceType,
                                     @RequestParam("referenceId") Long referenceId) {
        log.info("Request to save file referenceId - " + referenceId + ", referenceType - " + referenceType);
        File fileSaved = fileService.saveFile(file, title, type, referenceType, referenceId);
        return new ResponseEntity<>(fileSaved, HttpStatus.OK);
    }
}
