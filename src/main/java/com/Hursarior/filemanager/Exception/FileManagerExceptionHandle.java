package com.Hursarior.filemanager.Exception;



import com.Hursarior.filemanager.Response.ResponseMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class FileManagerExceptionHandle {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseMessage> Error404(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessage("El Archivo que trata de descargar no existe (⌐■_■)"));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> Error500(){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("El tamaño del archivo excede la capacidad permitida de 2MB"));
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<ResponseMessage> SiNoSuben_Nada(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage("Hemos tenido problemas al intentar cargar su archivo"));
    }
}
