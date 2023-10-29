package com.Hursarior.filemanager.Controller;

import com.Hursarior.filemanager.Entity.FileEntity;
import com.Hursarior.filemanager.Response.ResponseFile;
import com.Hursarior.filemanager.Response.ResponseMessage;
import com.Hursarior.filemanager.Service.Fileservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/file-manager")
public class FileController {

    @Autowired
    private Fileservice fileservice;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> UploadFile(@RequestParam ("file") MultipartFile file) throws IOException{
        fileservice.store(file);
      return ResponseEntity.status(HttpStatus.OK)
              .body(new ResponseMessage("Documento subido con exito."));
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws FileNotFoundException {
       FileEntity file = fileservice.getfile(id).get();
        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, file.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getName()+"\"")
                .body(file.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFile(){
        List<ResponseFile> files = fileservice.getAllfile();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
