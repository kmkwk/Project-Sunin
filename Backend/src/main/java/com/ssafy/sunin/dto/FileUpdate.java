package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
public class FileUpdate {
    @NotBlank
    private String id;
    private List<String> files;
    @NotBlank
    private String userId;
}
