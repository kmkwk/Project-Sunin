package com.ssafy.sunin.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FeedVO {
    private String userId;
    private String content;
    private List<String> hashtags;
    private List<MultipartFile> files;
}