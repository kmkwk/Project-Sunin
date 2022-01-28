package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class FeedUpdate {
    private String id;
    private String content;
    private List<String> hashtags;
    private List<MultipartFile> files;
}
