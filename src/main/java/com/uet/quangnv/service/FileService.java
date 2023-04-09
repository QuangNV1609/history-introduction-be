package com.uet.quangnv.service;

import com.uet.quangnv.dto.FileDto;
import com.uet.quangnv.entities.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File saveImage(MultipartFile file, String title, Integer type, Integer referenceType, Long referenceId);

    FileDto getFile(Long id);

}
