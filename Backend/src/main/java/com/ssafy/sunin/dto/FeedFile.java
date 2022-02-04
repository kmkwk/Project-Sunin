package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
public class FeedFile {
    @NotBlank
    private String id;
    @NotBlank
    private List<String> fileName;
    @NotBlank
    private String userId;
}
