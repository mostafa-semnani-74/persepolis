package ir.mosi.persepolis.model.service;

import ir.mosi.persepolis.config.FileStorageProperties;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);
}
