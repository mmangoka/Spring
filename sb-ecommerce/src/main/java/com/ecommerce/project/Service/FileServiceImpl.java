package com.ecommerce.project.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile image) throws IOException {

        //Get the filenames of original file
        String originalFileName = image.getOriginalFilename();
        //generate a unique file name
        String randomID = UUID.randomUUID().toString();
        //create a new filename
        assert originalFileName != null;
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filename = randomID.concat(extension);

        //check if the path exists of create

        File folder =  new File(path);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = path + File.separator +  filename;
        //upload file to server
        Files.copy(image.getInputStream(), Paths.get(filePath));
        //rename the file uniquely

        return filename;
    }

}
