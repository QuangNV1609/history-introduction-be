package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.FileDto;
import com.uet.quangnv.entities.File;
import com.uet.quangnv.repository.FileRepository;
import com.uet.quangnv.repository.UserInfoRepository;
import com.uet.quangnv.service.Constant;
import com.uet.quangnv.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public File saveFile(MultipartFile file, String title, Integer type, Integer referenceType, Long referenceId) {
        if (!file.isEmpty()) {
            File fileSaved = new File();
            fileSaved.setTitle(title);
            fileSaved.setType(type);
            fileSaved.setReferenceType(referenceType);
            fileSaved.setReferenceId(referenceId);
            fileSaved.setFileContentType(file.getContentType());
            fileSaved.setFileName(file.getOriginalFilename());
            StringBuilder filePath = new StringBuilder(Constant.LOCATION);
            if (referenceType == Constant.FileReferenceType.USER) {
                filePath.append(Constant.FileReferenceType.USER_NAME + referenceId);
            } else if (referenceType == Constant.FileReferenceType.ARTICLE) {
                filePath.append(Constant.FileReferenceType.ARTICLE_NAME + referenceId);
            }
            if (type == Constant.FileType.IMAGE) {
                filePath.append(Constant.FileType.IMAGE_NAME);
            } else if (type == Constant.FileType.SOUND) {
                filePath.append(Constant.FileType.SOUND_NAME);
            } else if (type == Constant.FileType.VIDEO) {
                filePath.append(Constant.FileType.VIDEO_NAME);
            }
            String[] splitDot = file.getOriginalFilename().split("\\.");
            filePath.append("/" + UUID.randomUUID() + "." + splitDot[splitDot.length - 1]);
            java.io.File dest = new java.io.File(filePath.toString());
            try {
                dest.mkdirs();
                file.transferTo(dest);
                fileSaved.setFilePath(filePath.toString());
                fileSaved = fileRepository.save(fileSaved);
                if (referenceType == Constant.FileReferenceType.USER) {
                    userInfoRepository.updateImageID(fileSaved.getId(), referenceId);
                }
                return fileSaved;
            } catch (IOException e) {
                log.error("Save file failure!");
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public FileDto getFile(Long id) {
        Optional<File> optional = fileRepository.findById(id);
        if (optional.isPresent()) {
            try {
                FileDto fileDto = new FileDto();
                byte[] bytes = new byte[0];
                bytes = Files.readAllBytes(Paths.get(optional.get().getFilePath()));
                fileDto.setFileName(optional.get().getFileName());
                fileDto.setData(bytes);
                fileDto.setFileType(optional.get().getFileContentType());
                return fileDto;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
