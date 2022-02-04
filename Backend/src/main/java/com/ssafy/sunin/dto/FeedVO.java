package com.ssafy.sunin.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FeedVO {
    @NotBlank
    private String userId;
    private String content;
    private List<String> hashtags;
    private List<MultipartFile> files;
}