package ir.mosi.persepolis.controller;

import io.swagger.annotations.Api;
import ir.mosi.persepolis.controller.payload.UploadFileResponse;
import ir.mosi.persepolis.model.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Api(value = "ProductFileController")
public class ProductFileController {

    Logger logger = LoggerFactory.getLogger(ProductFileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadProductFile")
    public UploadFileResponse uploadProductFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

}
