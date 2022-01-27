package com.ssafy.sunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

@Data
@AllArgsConstructor
public class FeedPage {
    private Pageable pageable;
    private String userId;
}
