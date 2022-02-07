package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ImageVO {
    @NotNull
    private Long id;
    private MultipartFile image;
}