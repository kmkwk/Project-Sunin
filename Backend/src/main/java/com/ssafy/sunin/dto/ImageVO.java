package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ImageVO {
    private Long id;
    private MultipartFile image;
}
