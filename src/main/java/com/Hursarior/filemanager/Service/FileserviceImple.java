package com.Hursarior.filemanager.Service;

import com.Hursarior.filemanager.Entity.FileEntity;
import com.Hursarior.filemanager.Repository.FileRepository;
import com.Hursarior.filemanager.Response.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileserviceImple implements Fileservice {

    @Autowired
    private FileRepository fileRepository;
    @Override
    public FileEntity store(MultipartFile file) throws IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileEntity = FileEntity.builder()
                .name(filename)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();

        return fileRepository.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> getfile(UUID id) throws FileNotFoundException {

        Optional<FileEntity> file = fileRepository.findById(id);
        if (file.isPresent()){
            return file;
        }
        throw new FileNotFoundException();
    }

    @Override
    public List<ResponseFile> getAllfile() {
        List<ResponseFile> files = fileRepository.findAll().stream().map(dbfile -> {
                    String fileDownloaduri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("api/file-manager/files/")
                            .path(dbfile.getId().toString())
                            .toUriString();
                    return ResponseFile.builder()
                            .name(dbfile.getName())
                            .url(fileDownloaduri)
                            .type(dbfile.getType())
                            .size(dbfile.getData().length).build();
                }).collect(Collectors.toList());

        return files;
    }

}
