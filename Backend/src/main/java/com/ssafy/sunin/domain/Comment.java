package com.ssafy.sunin.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
@Getter
public class Comment {

    @Id
    private ObjectId id;
    private String content;
    private String writer;

    @Builder
    public Comment(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }

}
