package com.example.reservas.service.inter;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient("ms-minio")
public interface MinioService {
    @PostMapping("/minio/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file);

    @GetMapping("/minio/download/{etag}")
    public String downloadFile(@PathVariable String etag);
}
