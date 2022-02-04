package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class FeedUpdate {
    private String id;
    private String content;
    private List<String> hashtags;
    private List<MultipartFile> files;
}
