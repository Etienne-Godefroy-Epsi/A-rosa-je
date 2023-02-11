package com.example.mspr.Service;

import com.example.mspr.Utils.FileDownloadUtil;
import com.example.mspr.Utils.FileUploadUtil;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class PhotoService {

    public String uploadingPhoto(MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        String size = String.valueOf(multipartFile.getSize());

        FileUploadUtil fileUploadUtil = new FileUploadUtil();

        return fileUploadUtil.saveFile(fileName, multipartFile);
    }

    public Resource downloadingPhoto(String fileCode) throws IOException {

        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        return downloadUtil.getFileAsResource(fileCode);
    }
}
