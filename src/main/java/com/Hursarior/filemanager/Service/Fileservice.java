package com.Hursarior.filemanager.Service;

import com.Hursarior.filemanager.Entity.FileEntity;
import com.Hursarior.filemanager.Response.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Fileservice {

    FileEntity store(MultipartFile file) throws IOException;

    Optional<FileEntity> getfile(UUID id) throws FileNotFoundException;


    List<ResponseFile> getAllfile();
}
