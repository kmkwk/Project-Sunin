package com.ssafy.sunin.payload.response.feed;

import com.ssafy.sunin.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FeedCommentMap {

    private Object id;
    private Comment comment;

    public static List<FeedCommentMap> feedCommentMap(List<Object> objects,List<Comment> comments){
        List<FeedCommentMap> feedCommentMaps = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            FeedCommentMap feedCommentMap = FeedCommentMap.builder()
                            .id(objects.get(i))
                            .comment(comments.get(i))
                            .build();
            feedCommentMaps.add(feedCommentMap);
        }
        return feedCommentMaps;
    }

    @Builder
    public FeedCommentMap(Object id, Comment comment) {
        this.id = id;
        this.comment = comment;
    }
}
