package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class FeedUpdate {
    private ObjectId id;
    private String userId;
    private String content;
    private List<String> hashtags;
    private List<MultipartFile> files;
}
